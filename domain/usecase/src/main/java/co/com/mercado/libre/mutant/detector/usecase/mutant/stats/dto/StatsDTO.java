package co.com.mercado.libre.mutant.detector.usecase.mutant.stats.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StatsDTO {
    private Long countMutantDna;
    private Long countHumanDna;
    private BigDecimal ratio;
}
