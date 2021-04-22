package co.com.mercado.libre.mutantdetector.configuration;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;
import co.com.mercado.libre.mutantdetector.domain.usecase.MutantUseCase;
import co.com.mercado.libre.mutantdetector.domain.validations.Validations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public MutantUseCase buildMutantUseCase(
            final MutantBusiness mutantBusiness,
            final Validations validations) {
        return new MutantUseCase(mutantBusiness, validations);
    }

}
