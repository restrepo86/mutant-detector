package co.com.mercado.libre.mutantdetector.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class MutantController {

    @PostMapping("mutant")
    public Mono<String> isMutant() {
        return Mono.just("Es mutante");
    }

}
