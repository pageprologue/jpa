package study.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import study.jpa.config.JPARegistrar;
import study.jpa.repository.service.SimpleMyRepositoryService;

@SpringBootApplication
@EnableJpaRepositories(repositoryImplementationPostfix = "Service", repositoryBaseClass = SimpleMyRepositoryService.class)
@Import(JPARegistrar.class)
// @EnableAsync
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

}
