package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto;

import lombok.*;

import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MutantDetectorDTO {
    private BigInteger id;
    private String dna;
    private boolean mutant;
}
