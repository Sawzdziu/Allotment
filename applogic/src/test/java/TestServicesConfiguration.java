package services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"model", "services", "dto"})
@EntityScan(basePackages = "model.entity")
@EnableJpaRepositories("model.repository")
@EnableAutoConfiguration
public class TestServicesConfiguration {
}
