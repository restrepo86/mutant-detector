package co.com.mercado.libre.mutant.detector.usecase.mutant.stats;

import co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence.IMutantDetectorHistoryServices;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.dto.StatsDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.gateways.IMutantStatsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
public class GetStatsUseCase implements IMutantStatsUseCase {

    private final IMutantDetectorHistoryServices mutantDetectorHistoryServices;

    @Override
    public Mono<StatsDTO> execute() {
        return mutantDetectorHistoryServices.countHumanDna()
                .filter(countHumanDna ->  countHumanDna != 0)
                .flatMap(countHumanDna -> mutantDetectorHistoryServices.countMutantDna()
                            .map(countMutantDna -> StatsDTO.builder()
                                    .countMutantDna(countMutantDna)
                                    .countHumanDna(countHumanDna)
                                    .ratio(BigDecimal.valueOf(countMutantDna.doubleValue() / countHumanDna.doubleValue()))
                                    .build()));
    }

}
