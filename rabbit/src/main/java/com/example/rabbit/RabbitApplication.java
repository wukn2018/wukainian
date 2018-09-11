package com.example.rabbit;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.rabbit")
@EnableRabbit
public class RabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitApplication.class, args);
	}
}
