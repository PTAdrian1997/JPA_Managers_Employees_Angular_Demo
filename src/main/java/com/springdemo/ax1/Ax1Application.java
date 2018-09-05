package com.springdemo.ax1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Ax1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ax1Application.class, args);
	}
}
