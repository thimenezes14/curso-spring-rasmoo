package com.rasmoo.cliente.escola.gradecurricular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
 * CONFIGURAÇÕES DE INICIALIZAÇÃO DE API REST NO SPRINGBOOT:
 * ESTENDER A CLASSE PARA SPRINGBOOTSERVLETINITIALIZER E SOBRESCREVER SEU MÉTODO CONFIGURE PASSANDO A CLASSE PRINCIPAL DO PROJETO
 * */

@SpringBootApplication
public class GradeCurricularApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GradeCurricularApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GradeCurricularApplication.class, args);
	}

}
