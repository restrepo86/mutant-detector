package co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class DnaInputDTO {
    private List<String> dna;
}
