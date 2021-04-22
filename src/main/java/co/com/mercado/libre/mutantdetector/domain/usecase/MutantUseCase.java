package co.com.mercado.libre.mutantdetector.domain.usecase;

import co.com.mercado.libre.mutantdetector.domain.business.MutantBusiness;
import co.com.mercado.libre.mutantdetector.domain.exceptions.InvalidRequestException;
import co.com.mercado.libre.mutantdetector.domain.validations.Validations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
public class MutantUseCase {

    private final MutantBusiness mutantBusiness;
    private final Validations validations;

    public MutantUseCase(MutantBusiness mutantBusiness, Validations validations) {
        this.mutantBusiness = mutantBusiness;
        this.validations = validations;
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
            log.error("Internal error cause -> ", exception);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "sorry, something has gone wrong, please try again");
        }
    }

    private void isMutant(List<String> dna) {
        if (mutantBusiness.isMutant(dna)) {
            throw new ResponseStatusException(HttpStatus.OK, "dna belongs to mutant!");
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "dna belongs to human!");
        }
    }
}
