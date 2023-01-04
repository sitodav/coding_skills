package sito.davide.conf;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages="sito.davide.entity")
@EnableJpaRepositories(basePackages="sito.davide.dao")
@Configuration
public class DataConf
{
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
