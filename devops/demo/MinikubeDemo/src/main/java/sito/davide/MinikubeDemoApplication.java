package sito.davide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"sito.davide.entity"})
@EnableJpaRepositories(  basePackages = { "sito.davide.dao" })
public class MinikubeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinikubeDemoApplication.class, args);
	}

}
