package co.com.mercado.libre.mutant.detector.api.transversal.helpers;

import co.com.mercado.libre.mutant.detector.api.transversal.dto.ErrorResponseDTO;
import co.com.mercado.libre.mutant.detector.usecase.transversal.enums.ApplicationCodesEnum;
import co.com.mercado.libre.mutant.detector.usecase.transversal.exceptions.UseCaseException;
import co.com.mercado.libre.mutant.detector.usecase.transversal.gateways.IResponseHelper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class ServerResponseHelper<I> implements IResponseHelper<I, ServerResponse> {

    @Override
    public Mono<ServerResponse> build(Mono<I> useCaseOutputObject) {
        return useCaseOutputObject
                .flatMap(this::buildSuccessErrorResponse)
                .onErrorResume(this::buildServerResponseError);
    }

    private Mono<ServerResponse> buildSuccessErrorResponse(I useCaseOutputDTO) {
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(useCaseOutputDTO);
    }

    private Mono<ServerResponse> buildServerResponseError(Throwable throwable) {
        if (throwable instanceof UseCaseException) {
            return Mono.justOrEmpty((UseCaseException) throwable)
                    .flatMap(this::processUseCaseException);
        } else {
            log.error("No se pudo procesar el servicio por -> ", throwable);
            return Mono.just(ErrorResponseDTO.builder()
                            .code(ApplicationCodesEnum.UNCHECKED_ERROR.getErrorCode())
                            .message(ApplicationCodesEnum.UNCHECKED_ERROR.getMessage())
                            .build())
                    .flatMap(errorResponseDTO ->
                            ServerResponse
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(errorResponseDTO));
        }
    }

    private Mono<ServerResponse> processUseCaseException(UseCaseException useCaseException) {
        val applicationCodesEnum = useCaseException.getApplicationCodesEnum();
        return ServerResponse
                .status(applicationCodesEnum.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(ErrorResponseDTO.builder()
                        .code(applicationCodesEnum.getErrorCode())
                        .message(
                                useCaseException.getMessage() == null ?
                                        applicationCodesEnum.getMessage() :
                                        useCaseException.getMessage())
                        .build());
    }

}
