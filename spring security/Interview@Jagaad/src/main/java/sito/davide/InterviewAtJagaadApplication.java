package sito.davide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/*On startup , the utility class
 * sito.davide.utils.SecurityTablesStartupDump provides dump for security user / role tables
 */
@SpringBootApplication
public class InterviewAtJagaadApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewAtJagaadApplication.class, args);
	}

}
