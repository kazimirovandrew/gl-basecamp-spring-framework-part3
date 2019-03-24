package basecamp.config;

import basecamp.domain.NumberEntity;
import basecamp.service.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "basecamp")
public class WebConfig {

    @Bean
    public NumberEntity numberEntity() {
        return new NumberEntity();
    }

    @Bean
    public GameService gameService() {
        return new GameService(numberEntity());
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
