package co.com.mercado.libre.mutant.detector.api.mutant.gateways;

import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.Validations;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.AbstractUseCaseGateway;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IUseCase;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IUseCaseInputObjectHelper;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class DetectMutantGateway extends AbstractUseCaseGateway<ServerRequest, DnaInputDTO, Void, ServerResponse> {
    private final Validations validations;

    public DetectMutantGateway(
            Class<DnaInputDTO> validationClass,
            Validator validator, IUseCase<DnaInputDTO, Void> iUseCase,
            IResponseHelper<Void, ServerResponse> responseHelper,
            IUseCaseInputObjectHelper<ServerRequest, DnaInputDTO> useCaseInputObjectHelper,
            Validations validations) {
        super(validationClass, validator, iUseCase, responseHelper, useCaseInputObjectHelper);
        this.validations = validations;
    }

    @Override
    protected Mono<DnaInputDTO> validateInputObject(Mono<DnaInputDTO> useCaseInputObject) {
        return useCaseInputObject
                .map(dna -> {
                    validations.execute(dna.getDna());
                    return dna;
                });
    }
}
