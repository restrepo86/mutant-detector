package co.com.mercado.libre.mutant.detector.model.mutant.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Dna {
    private List<String> dna;
}
