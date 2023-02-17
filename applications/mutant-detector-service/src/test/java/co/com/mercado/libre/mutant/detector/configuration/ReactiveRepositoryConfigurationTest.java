package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.mappers.impl.MutantDetectorHistoryEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class ReactiveRepositoryConfigurationTest {

    @InjectMocks
    private ReactiveRepositoryConfiguration reactiveRepositoryConfiguration;

    @Test
    void shouldBuildMutantDetectorHistoryEntityMapper() {
        MutantDetectorHistoryEntityMapper mutantDetectorHistoryEntityMapper =
                reactiveRepositoryConfiguration.buildMutantDetectorHistoryEntityMapper();
        assertNotNull(mutantDetectorHistoryEntityMapper);
    }

}
