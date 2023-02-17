package co.com.mercado.libre.mutant.detector.api.mutant.gateways;

import co.com.mercado.libre.mutant.detector.usecase.mutant.stats.dto.StatsDTO;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.AbstractWithoutInputUseCaseGateway;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IResponseHelper;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IWithoutInputUseCase;
import org.springframework.web.reactive.function.server.ServerResponse;

public class GetMutantStatsGateway extends AbstractWithoutInputUseCaseGateway<StatsDTO, ServerResponse> {

    public GetMutantStatsGateway(IWithoutInputUseCase<StatsDTO> iUseCase, IResponseHelper<StatsDTO, ServerResponse> responseHelper) {
        super(iUseCase, responseHelper);
    }
}
