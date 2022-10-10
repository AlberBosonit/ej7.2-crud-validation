package com.example.ej7.crudvalidation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //Indicamos que vamos a usar Feign
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
