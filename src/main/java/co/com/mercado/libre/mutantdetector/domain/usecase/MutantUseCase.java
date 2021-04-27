package co.com.mercado.libre.mutantdetector.domain.usecase;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;
import co.com.mercado.libre.mutantdetector.domain.dto.MutanDetectorDTO;
import co.com.mercado.libre.mutantdetector.domain.dto.StatsDTO;
import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;
import co.com.mercado.libre.mutantdetector.domain.validations.Validations;
import co.com.mercado.libre.mutantdetector.infrastructure.data.services.MutantDetectorHistoryServices;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class MutantUseCase {

    private static final String SORRY_SOMETHING_HAS_GONE_WRONG_PLEASE_TRY_AGAIN = "sorry, something has gone wrong, please try again";

    private final MutantBusiness mutantBusiness;
    private final Validations validations;
    private final MutantDetectorHistoryServices mutantDetectorHistoryServices;

    public MutantUseCase(MutantBusiness mutantBusiness, Validations validations, MutantDetectorHistoryServices mutantDetectorHistoryServices) {
        this.mutantBusiness = mutantBusiness;
        this.validations = validations;
        this.mutantDetectorHistoryServices = mutantDetectorHistoryServices;
    }

    public void validateDna(List<String> dna) {
        try {
            validations.execute(dna);
            isMutant(dna);
        } catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        } catch (InvalidRequestException invalidRequestException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, invalidRequestException.getMessage());
        } catch (Exception exception) {
            throwInternalError(exception);
        }
    }

    private void throwInternalError(Exception exception) {
        log.error("Internal error cause -> ", exception);
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, SORRY_SOMETHING_HAS_GONE_WRONG_PLEASE_TRY_AGAIN);
    }

    private void isMutant(List<String> dna) {
        boolean mutant = mutantBusiness.isMutant(dna);
        mutantDetectorHistoryServices.save(buildMutantDetectorDTO(dna, mutant));
        if (mutant) {
            throw new ResponseStatusException(HttpStatus.OK, "dna belongs to mutant!");
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "dna belongs to human!");
        }
    }

    private MutanDetectorDTO buildMutantDetectorDTO(List<String> dna, boolean mutant) {
        return MutanDetectorDTO.builder()
                .dna(new Gson().toJson(dna))
                .mutant(mutant)
                .build();
    }

    public StatsDTO stats() {
        StatsDTO statsDTO = null;
        try {
            Long countHumanDna = mutantDetectorHistoryServices.countHumanDna();
            Long countMutantDna = mutantDetectorHistoryServices.countMutantDna();
            validateHumanDnaDetected(countHumanDna);
            statsDTO = StatsDTO.builder()
                    .countMutantDna(countMutantDna)
                    .countHumanDna(countHumanDna)
                    .ratio(BigDecimal.valueOf(countMutantDna.doubleValue() / countHumanDna.doubleValue()))
                    .build();
        } catch (ResponseStatusException responseStatusException) {
            throw responseStatusException;
        } catch (Exception exception) {
            throwInternalError(exception);
        }
        return statsDTO;
    }

    private void validateHumanDnaDetected(Long countHumanDna) {
        if (countHumanDna == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "there are not enough values to find the radius, since human DNA has not been detected yet");
        }
    }
}
