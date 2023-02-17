package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.impl.MutantDetectorHistoryEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReactiveRepositoryConfiguration {

    @Bean
    public MutantDetectorHistoryEntityMapper buildMutantDetectorHistoryEntityMapper() {
        return new MutantDetectorHistoryEntityMapper();
    }

}
