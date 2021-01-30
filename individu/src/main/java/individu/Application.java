package individu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by marais on 11/04/2018.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan({"individu","notification"})
@EnableSwagger2
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
