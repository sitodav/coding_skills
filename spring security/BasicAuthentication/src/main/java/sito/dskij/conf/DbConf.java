package sito.dskij.conf;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="sito.dskij.dao")
@EntityScan(basePackages="sito.dskij.entity")
public class DbConf {

}
