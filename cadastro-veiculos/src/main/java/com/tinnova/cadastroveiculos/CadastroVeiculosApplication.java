package com.tinnova.cadastroveiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.tinnova.cadastroveiculos.model")
@EnableJpaRepositories("com.tinnova.cadastroveiculos.repository")
@ComponentScan("com.tinnova.cadastroveiculos")
@SpringBootApplication
public class CadastroVeiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroVeiculosApplication.class, args);
	}

}
