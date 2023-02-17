package co.com.mercado.libre.mutant.detector.api.configuration.router;

import co.com.mercado.libre.mutant.detector.api.configuration.router.enums.RouterEnum;
import co.com.mercado.libre.mutant.detector.api.mutant.handler.DetectMutantHandler;
import co.com.mercado.libre.mutant.detector.api.mutant.handler.GetMutantStatsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@RequiredArgsConstructor
public class Router {

    private final DetectMutantHandler detectMutantHandler;
    private final GetMutantStatsHandler getMutantStatsHandler;

    @Bean
    public RouterFunction<ServerResponse> composedRoutes() {
        return route(POST(RouterEnum.MUTANT_DETECTOR.getRoute()).and(accept(MediaType.APPLICATION_JSON)), detectMutantHandler::detectMutant)
                .andRoute(GET(RouterEnum.GET_MUTANT_STATS.getRoute()), serverRequest -> getMutantStatsHandler.getStats());
    }

}
