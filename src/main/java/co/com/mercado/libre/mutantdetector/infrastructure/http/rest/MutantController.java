package co.com.mercado.libre.mutantdetector.infrastructure.http.rest;

import co.com.mercado.libre.mutantdetector.domain.dto.Dna;
import co.com.mercado.libre.mutantdetector.domain.usecase.MutantUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MutantController {

    @Autowired
    private MutantUseCase mutantUseCase;

    @PostMapping(value = "mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void isMutant(@RequestBody Dna dna) {
        mutantUseCase.validateDna(dna.getDna());
    }

}
