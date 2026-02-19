package mx.com.sfinx.metrorrey.cco.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mx.com.sfinx.metrorrey.cco.persistence.entity.OpeUser
import mx.com.sfinx.metrorrey.cco.persistence.repository.OpeUserRepo
import mx.com.sfinx.metrorrey.cco.security.SecurityConstants.EXPIRATION_TIME
import mx.com.sfinx.metrorrey.cco.security.SecurityConstants.EXPIRATION_TIME_YEAR
import mx.com.sfinx.metrorrey.cco.security.SecurityConstants.HEADER_STRING
import mx.com.sfinx.metrorrey.cco.security.SecurityConstants.ISSUER_INFO
import mx.com.sfinx.metrorrey.cco.security.SecurityConstants.SECRET
import mx.com.sfinx.metrorrey.cco.security.SecurityConstants.TOKEN_PREFIX
import mx.com.sfinx.metrorrey.cco.utils.ApiConstants
import org.apache.commons.io.IOUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.nio.charset.Charset
import java.sql.Timestamp
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter constructor(
    private val authenticationManger: AuthenticationManager,
    private val opeUserRepo: OpeUserRepo
) :
    UsernamePasswordAuthenticationFilter() {

    init {
        setFilterProcessesUrl("${ApiConstants.REST_API_VERSION}/login")
    }

    companion object {
        val log: Logger = LoggerFactory.getLogger(JWTAuthenticationFilter::class.java)
    }

    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        try {
            val inputStream = IOUtils.toString(request?.inputStream, Charset.defaultCharset())

            //log.info("INPUT STREAM: {}", inputStream)

            val creds = ObjectMapper().readValue(inputStream, OpeUser::class.java)

            return authenticationManger.authenticate(
                UsernamePasswordAuthenticationToken(
                    creds.username,
                    creds.password,
                    emptyList()
                )
            )
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val username = (authResult?.principal as User).username
        //val expDate = Date(System.currentTimeMillis() + EXPIRATION_TIME)
        val expDate = Date(System.currentTimeMillis() + EXPIRATION_TIME_YEAR)
        val token = Jwts.builder().setIssuedAt(Date()).setIssuer(ISSUER_INFO)
            .setSubject(username)
            .setExpiration(expDate)
            .signWith(SignatureAlgorithm.HS512, SECRET).compact()

        log.info("TOKEN: {}, expiration: {}", token, expDate.toString())

        val tokenInfo = Base64.getDecoder().decode(token.split(".")[1])
        val parsedToken = Gson().fromJson(String(tokenInfo), JWToken::class.java)

        val opeUser = opeUserRepo.findByUsername(username).get()

        val roleList: MutableList<String> = mutableListOf()
        opeUser.roles.map{ roleList.add(it.code!!)}

        val tokenHumanized = JWTParsed(parsedToken.iat, parsedToken.iss, parsedToken.sub, parsedToken.exp, token, roleList)

        response?.addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        response?.writer?.write(Gson().toJson(tokenHumanized))

        response?.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        failed: AuthenticationException?
    ) {
        log.info("ERROR AUTH: {}", failed?.message)

        val jsonError = JsonObject()
        jsonError.addProperty("message", failed?.localizedMessage)
        jsonError.addProperty("status", 403)

        response?.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        response?.writer?.write(Gson().toJson(jsonError))
        response?.status = HttpStatus.UNAUTHORIZED.value()
    }
}