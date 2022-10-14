package com.clean.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ArchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchitectureApplication.class, args);
	}

}
