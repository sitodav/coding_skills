package sito.davide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



/*On startup , the utility class
 * sito.davide.utils.SecurityTablesStartupDump provides dump for security user / role tables
 */
@SpringBootApplication
@EnableScheduling //for the state switch CREATED -> IN PREPARATION (sito.davide.utils.OrderStateMachineComponent)
public class InterviewAtJagaadApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewAtJagaadApplication.class, args);
	}

}
