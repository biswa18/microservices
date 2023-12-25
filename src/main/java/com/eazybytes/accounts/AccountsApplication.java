package com.eazybytes.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST Api documentation",
				description = "Demo Accounts microservice REST Api documentation",
				version = "v1",
				contact = @Contact(
						name = "Biswadeb Mukhopadhyay",
						email = "mukherjeebiswadeb@gmail.com"
						)
				),
		   externalDocs = @ExternalDocumentation(
				   url = "https://springdoc.org/",
				   description = "OpenApi official doc")
		)
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}
