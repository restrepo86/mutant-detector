package co.com.mercado.libre.mutantdetector.configuration;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;
import co.com.mercado.libre.mutantdetector.domain.validations.DnaHasInvalidNitrogenBase;
import co.com.mercado.libre.mutantdetector.domain.validations.DnaHasInvalidSize;
import co.com.mercado.libre.mutantdetector.domain.validations.DnaIsNullOrEmpty;
import co.com.mercado.libre.mutantdetector.domain.validations.Validations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfiguration {

    @Bean
    public MutantBusiness buildMutantBusiness() {
        return new MutantBusiness();
    }

    @Bean
    public Validations buildValidations(
            final DnaIsNullOrEmpty dnaIsNullOrEmpty,
            final DnaHasInvalidSize dnaHasInvalidSize,
            final DnaHasInvalidNitrogenBase dnaHasInvalidNitrogenBase) {
        return new Validations(dnaIsNullOrEmpty, dnaHasInvalidSize, dnaHasInvalidNitrogenBase);
    }

    @Bean
    public DnaIsNullOrEmpty buildDnaIsNullOrEmpty() {
        return new DnaIsNullOrEmpty();
    }

    @Bean
    public DnaHasInvalidSize buildDnaHasInvalidSize() {
        return new DnaHasInvalidSize();
    }

    @Bean
    public DnaHasInvalidNitrogenBase buildDnaHasInvalidNitrogenBase() {
        return new DnaHasInvalidNitrogenBase();
    }

}
