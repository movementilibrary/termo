package br.com.dasa.api.termo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.dasa.api.termo.entity.TermOfUser;
import br.com.dasa.api.termo.enumeration.StatusTermUse;
import br.com.dasa.api.termo.repository.TermOfUserRepository;

@SpringBootApplication
public class ApiTermoApplication {

	private static final Logger log = LoggerFactory.getLogger(ApiTermoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiTermoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TermOfUserRepository repository) {
		return (args) -> {
			log.info("Salva e atualiza os termos de uso:");
			repository.save(new TermOfUser("t34904918827", "teste descricao", "resumo termo", new Date(), "v1", StatusTermUse.ACTIVE));

			log.info("Todos os termos de Uso - findAll():");
			log.info("-------------------------------");
			for (TermOfUser termOfUser : repository.findAll()) {
				log.info(termOfUser.toString());
			}
			log.info("");

			TermOfUser termOfUser = repository.findById(1L);
			log.info("Termo de Uso por ID - findById:");
			log.info("--------------------------------");
			log.info(termOfUser.toString());
			log.info("");
			
		};
	}
}
