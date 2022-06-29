package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.model.mutant.gateways.IMutantBusinessGateway;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.DetectMutantUseCase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence.IMutantDetectorHistoryServices;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class UseCaseConfiguration {

    @Bean
    @Primary
    public LocalValidatorFactoryBean buildValidator() {
        val localValidatorFactoryBean = new LocalValidatorFactoryBean();
        return localValidatorFactoryBean;
    }

    @Bean
    public DetectMutantUseCase buildDetectMutantUseCase(
            IMutantBusinessGateway iMutantBusinessGateway,
            IMutantDetectorHistoryServices iMutantDetectorHistoryServices) {
        return new DetectMutantUseCase(iMutantBusinessGateway, iMutantDetectorHistoryServices);
    }
}
