package co.com.mercado.libre.mutantdetector.infrastructure.http.rest.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({ValidationAutoConfiguration.class})
@ComponentScan(basePackages = "co.com.mercado.libre.mutantdetector.infrastructure.http.rest")
public class RestAutoConfiguration {

}
