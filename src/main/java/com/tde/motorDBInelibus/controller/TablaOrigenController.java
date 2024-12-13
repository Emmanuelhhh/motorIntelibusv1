package com.tde.motorDBInelibus.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tde.motorDBInelibus.service.TablaOrigenService;

@Controller
@RequestMapping( "/motor" +  "/tablas-origen")
public class TablaOrigenController {

    @Autowired
    private TablaOrigenService tablaOrigenService;

    @GetMapping
    public String mostrarEstadoTablas(Model model) {
        Map<String, String> estadoTablas = tablaOrigenService.verificarDatos();
        model.addAttribute("estadoTablas", estadoTablas);
        return "estado-tablas";
    }
}
