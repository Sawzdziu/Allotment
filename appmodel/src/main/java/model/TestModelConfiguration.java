package model;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages =  "model")
@EntityScan(basePackages = "model.entity")
@EnableAutoConfiguration
public class TestModelConfiguration {
}
