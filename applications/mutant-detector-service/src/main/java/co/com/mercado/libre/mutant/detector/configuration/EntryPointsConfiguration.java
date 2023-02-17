package co.com.mercado.libre.mutant.detector.configuration;

import co.com.mercado.libre.mutant.detector.api.mutant.gateways.DetectMutantGateway;
import co.com.mercado.libre.mutant.detector.api.mutant.gateways.GetMutantStatsGateway;
import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerRequestToUseCaseInputObjectHelper;
import co.com.mercado.libre.mutant.detector.api.transversal.helpers.ServerResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.dto.DnaInputDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.detect.validations.Validations;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.dto.StatsDTO;
import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.gateways.IMutantStatsUseCase;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IUseCase;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IWithoutInputUseCase;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class EntryPointsConfiguration {

    @Bean
    public WebFluxProperties buildWebFluxProperties(){
        return new WebFluxProperties();
    }

    @Bean
    public ServerResponseHelper buildServerResponseHelper() {
        return new ServerResponseHelper();
    }

    @Bean
    public DetectMutantGateway buildDetectMutantGateway(
            Validator validator,
            IUseCase<DnaInputDTO, Void> iUseCase,
            IResponseHelper<Void, ServerResponse> responseHelper,
            ServerRequestToUseCaseInputObjectHelper<DnaInputDTO> useCaseInputObjectHelper,
            Validations validations) {
        return new DetectMutantGateway(DnaInputDTO.class, validator, iUseCase, responseHelper, useCaseInputObjectHelper, validations);
    }

    @Bean
    public ServerRequestToUseCaseInputObjectHelper buildServerRequestToUseCaseInputObjectHelper() {
        return new ServerRequestToUseCaseInputObjectHelper(DnaInputDTO.class);
    }

    @Bean
    public GetMutantStatsGateway buildGetMutantStatsGateway(IMutantStatsUseCase iUseCase,
                                                            IResponseHelper<StatsDTO, ServerResponse> responseHelper) {
        return new GetMutantStatsGateway(iUseCase, responseHelper);
    }
}
