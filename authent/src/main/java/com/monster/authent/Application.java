package com.monster.authent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by marais on 11/04/2018.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"com.monster.*"})
@EnableJpaRepositories({"com.monster.history.repository", "com.monster.individu.repository", "com.monster.business.repository", "com.monster.address.repository"})
@EntityScan({"com.monster.*"})
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
