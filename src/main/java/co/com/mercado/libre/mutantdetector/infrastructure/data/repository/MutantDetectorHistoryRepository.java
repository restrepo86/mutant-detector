package co.com.mercado.libre.mutantdetector.infrastructure.data.repository;

import co.com.mercado.libre.mutantdetector.infrastructure.data.entities.MutantDetectorHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface MutantDetectorHistoryRepository extends CrudRepository<MutantDetectorHistoryEntity, BigInteger> {
    Optional<MutantDetectorHistoryEntity> findByDna(String dna);
    List<MutantDetectorHistoryEntity> findByMutant(boolean mutant);
}
