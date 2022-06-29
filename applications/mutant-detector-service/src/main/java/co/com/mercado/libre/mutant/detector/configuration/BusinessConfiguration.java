package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.business.mutant.MutantBusiness;
import co.com.mercado.libre.mutant.detector.business.mutant.rules.IsMutant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessConfiguration {

    @Bean
    public MutantBusiness buildMutantBusinessGateway(IsMutant isMutant) {
        return new MutantBusiness(isMutant);
    }

}
