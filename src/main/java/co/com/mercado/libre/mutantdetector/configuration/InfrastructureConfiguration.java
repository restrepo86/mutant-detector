package co.com.mercado.libre.mutantdetector.configuration;

import co.com.mercado.libre.mutantdetector.infrastructure.data.repository.MutantDetectorHistoryRepository;
import co.com.mercado.libre.mutantdetector.infrastructure.data.services.MutantDetectorHistoryServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfrastructureConfiguration {

    @Bean
    public MutantDetectorHistoryServices buildMutantDetectorHistoryServices(MutantDetectorHistoryRepository mutantDetectorHistoryRepository) {
        return new MutantDetectorHistoryServices(mutantDetectorHistoryRepository);
    }

}
