package co.com.mercado.libre.mutant.detector.model.mutant.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder(toBuilder = true)
public class Boolean {

    private Long id;
    private String nameOfTask;
    private int dayOfTask;
    private Instant creationDate;
    private java.lang.Boolean successfulExecution;

}
