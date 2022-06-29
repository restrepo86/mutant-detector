package co.com.mercado.libre.mutant.detector.driven.adapters.repository.mutant.entities;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;
import java.util.Date;

@Getter
@Table(value = "mutant_detector_history")
public class MutantDetectorHistoryEntity {

    @Builder(toBuilder = true)
    public MutantDetectorHistoryEntity(String dna, Boolean mutant, Date creationDate) {
        this.dna = dna;
        this.mutant = mutant;
        this.creationDate = creationDate;
    }

    @Id
    @Column(value = "id")
    private BigInteger id;

    @Column(value = "dna")
    private String dna;

    @Column(value = "mutant")
    private Boolean mutant;

    @Column(value = "creation_date")
    private Date creationDate;

}
