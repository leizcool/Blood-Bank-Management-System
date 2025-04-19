package com.anm.spring.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.anm.spring.frontend", "com.anm.spring.restapi.service"})
public class SpringBootAleliMacapagalAssign3BloodBankFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAleliMacapagalAssign3BloodBankFrontendApplication.class, args);
	}

}
