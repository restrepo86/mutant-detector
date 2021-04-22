package co.com.mercado.libre.mutantdetector.infrastructure.data.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "mutant_detector_history")
public class MutantDetectorHistoryEntity {

    @Id
    @SequenceGenerator(name = "mutant_detector_history_id_seq", sequenceName = "mutant_detector_history_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mutant_detector_history_id_seq")
    @Column(name = "id")
    private BigInteger id;

    @Column(name = "dna")
    private String dna;

    @Column(name = "mutant")
    private Boolean mutant;

    @Column(name = "creation_date")
    private Date creationDate;

}
