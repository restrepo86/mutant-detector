package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class DnaInputDTO {
    private List<String> dna;
}
