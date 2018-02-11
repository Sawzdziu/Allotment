package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"com", "model", "com.example"})
@EnableJpaRepositories("model.repository")
@EntityScan("model.entity")
public class AllotmentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllotmentsApplication.class, args);
	}
}
