package com.tde.motorDBInelibus;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableScheduling

public class MotorBDintelibusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotorBDintelibusApplication.class, args);
	}

}
