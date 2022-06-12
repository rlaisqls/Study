package com.study.jpastudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;

@SpringBootApplication
public class JpaStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaStudyApplication.class, args);
	}

}