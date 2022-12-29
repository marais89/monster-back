package com.monster.authent;

import com.monster.history.repository.HistoryRepository;
import com.monster.individu.repository.AuthoritiesRepository;
import com.monster.individu.repository.IndividuRepository;
import com.monster.individu.repository.UsersRepository;
import com.monster.individu.repository.ValidationKeysRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by marais on 11/04/2018.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"com.monster.*"})
@EntityScan({"com.monster.*"})
@EnableSwagger2
@EnableMongoRepositories(basePackageClasses = {UsersRepository.class, IndividuRepository.class, AuthoritiesRepository.class, ValidationKeysRepository.class, HistoryRepository.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
