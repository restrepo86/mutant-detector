package co.com.mercado.libre.mutantdetector.domain.dto;

import lombok.*;

import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MutanDetectorDTO {
    private BigInteger id;
    private String dna;
    private boolean mutant;
}
