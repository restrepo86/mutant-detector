package co.com.mercado.libre.mutantdetector.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

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
