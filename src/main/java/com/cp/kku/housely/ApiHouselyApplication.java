package com.cp.kku.housely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.cp.kku.housely")
public class ApiHouselyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHouselyApplication.class, args);
	}

}
