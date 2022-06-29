package co.com.mercado.libre.mutant.detector.usecase.mutant.detect;

import co.com.mercado.libre.mutant.detector.model.mutant.dto.Dna;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.gateways.IDetectMutantUseCase;
import co.com.mercado.libre.mutant.detector.usecase.mutant.gateways.persistence.IMutantDetectorHistoryServices;
import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;
import co.com.mercado.libre.mutant.detector.model.mutant.gateways.IMutantBusinessGateway;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.MutantDetectorDTO;
import com.google.gson.Gson;
import reactor.core.publisher.Mono;

import java.util.List;

public class DetectMutantUseCase implements IDetectMutantUseCase {

    private final IMutantBusinessGateway iMutantBusinessGateway;
    private final IMutantDetectorHistoryServices iMutantDetectorHistoryServices;

    public DetectMutantUseCase(IMutantBusinessGateway iMutantBusinessGateway, IMutantDetectorHistoryServices iMutantDetectorHistoryServices) {
        this.iMutantBusinessGateway = iMutantBusinessGateway;
        this.iMutantDetectorHistoryServices = iMutantDetectorHistoryServices;
    }

    @Override
    public Mono<Void> execute(Mono<DnaInputDTO> inputUseCaseObject) {
        return inputUseCaseObject
                .map(dnaInputDTO -> Dna.builder().dna(dnaInputDTO.getDna()).build())
                .flatMap(dna -> iMutantBusinessGateway.executeBusinessRules(dna)
                        .flatMap(isMutant -> iMutantDetectorHistoryServices.save(buildMutantDetectorDTO(dna.getDna(), isMutant))))
                .flatMap(mutantDetectorDTO -> mutantDetectorDTO.isMutant() ?
                        Mono.error(new UseCaseException(ApplicationCodesEnum.DNA_MUTANT)) :
                        Mono.error(new UseCaseException(ApplicationCodesEnum.DNA_HUMAN))
                );
    }

    private MutantDetectorDTO buildMutantDetectorDTO(List<String> dna, boolean mutant) {
        return MutantDetectorDTO.builder()
                .dna(new Gson().toJson(dna))
                .mutant(mutant)
                .build();
    }

}
