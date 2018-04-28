package com.example.studyspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.studyspring")
public class StudyspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyspringApplication.class, args);
	}
}
