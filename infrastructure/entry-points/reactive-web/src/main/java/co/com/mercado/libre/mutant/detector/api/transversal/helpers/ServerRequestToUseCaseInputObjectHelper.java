package co.com.mercado.libre.mutant.detector.api.transversal.helpers;

import co.com.mercado.libre.mutant.detector.api.transversal.exceptions.EntryPointException;
import co.com.mercado.libre.mutant.detector.api.transversal.exceptions.RequestException;
import co.com.mercado.libre.mutant.detector.api.transversal.exceptions.RequestNoBodyException;
import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IUseCaseInputObjectHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;

@Slf4j
public class ServerRequestToUseCaseInputObjectHelper<O> implements IUseCaseInputObjectHelper<ServerRequest, O> {

    private final Class<O> outputObject;

    public ServerRequestToUseCaseInputObjectHelper(Class<O> outputObject) {
        this.outputObject = outputObject;
    }

    @Override
    public Mono<O> build(ServerRequest inputObject) {
        return Mono.just(inputObject)
                .flatMap(serverRequest -> serverRequest.bodyToMono(outputObject))
                .switchIfEmpty(Mono.error(new RequestNoBodyException(ApplicationCodesEnum.DEBES_ENVIAR_UN_CUERPO_DE_SOLICITUD_PARA_EL_CONSUMO_DE_ESTE_SERVICIO)))
                .onErrorMap(error -> {
                    EntryPointException entryPointException;
                    if (error instanceof RequestNoBodyException) {
                        entryPointException = (RequestNoBodyException) error;
                    } else {
                        log.error(ApplicationCodesEnum.NO_SE_PUDO_CONSUMIR_SERVICIO_POR.getMessage().concat(error.getLocalizedMessage() == null ? "" : error.getLocalizedMessage()));
                        entryPointException = new RequestException(ApplicationCodesEnum.INVALID_REQUEST_OBJECT);
                    }
                    return entryPointException;
                });

    }

}
