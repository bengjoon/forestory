package com.forestory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ForestoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForestoryApplication.class, args);
	}

}
