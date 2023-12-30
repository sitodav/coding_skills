package sito.davide;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import sito.davide.dao.TbCourseRepository;
import sito.davide.dao.TbUserRepository;
import sito.davide.entity.TbCourse;
import sito.davide.entity.TbUser;

@SpringBootApplication
@EntityScan(basePackages = {"sito.davide.entity"})
@EnableJpaRepositories(basePackages = {"sito.davide.dao"})
@OpenAPIDefinition(info = @Info(title = "Courses API", version = "2.0", description = "Courses Information"))
public class MainApplication
{
	
	@Autowired
	private TbUserRepository userRepo;
	@Autowired
	private TbCourseRepository courseRepo;
	
	
	private static Logger log = LoggerFactory.getLogger(MainApplication.class);
	public static void main(String[] args)
	{
		SpringApplication.run(MainApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner registerRunner()
	{
		return (args) -> {
			log.info("STARTED");
			TbUser user1 = new TbUser(null, "Davide","Sito","sitodskij"+(Math.random()*100),"sitodskij"+(Math.random()*100));
			userRepo.save(user1);
			log.info("SAVED USER "+user1);
			 
			TbCourse course1 =  new TbCourse(null, "FOO BAR COURSE"+(Math.random()*100));
			courseRepo.save(course1);
			log.info("SAVED COURSE "+course1);
			
		};
	}
}
