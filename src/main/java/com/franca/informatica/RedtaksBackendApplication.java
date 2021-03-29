package com.franca.informatica;

import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.franca.informatica.domain.specialty.Specialty;

@SpringBootApplication
public class RedtaksBackendApplication implements RepositoryRestConfigurer{
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(RedtaksBackendApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(RedtaksBackendApplication.class, args);
		logger.info(" Agendamento in action!");
	}
	//exposicao aos ids
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Specialty.class);
		config.setReturnBodyOnCreate(true);
		config.setReturnBodyOnUpdate(true);
		/*cors
			.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("GET", "POST", "PUT", "DELETE");*/
		
		logger.info("Repository CORS setup... OK!");
		//RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
	}
	
	//validacao custom
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Override
	public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
		Validator val = validator();
		validatingListener.addValidator("beforeCreate", val);
		validatingListener.addValidator("beforeSave", val);
		logger.info("Configure validator..OK");
	}

}
