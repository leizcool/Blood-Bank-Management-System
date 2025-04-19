package com.anm.spring.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.anm.spring.restapi"})
public class SpringBootAleliMacapagalAssign3AppplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAleliMacapagalAssign3AppplicationApplication.class, args);
	}

}
