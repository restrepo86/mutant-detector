package co.com.mercado.libre.mutant.detector;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.tools.agent.ReactorDebugAgent;

import java.lang.management.ManagementFactory;

@Slf4j
@EnableAutoConfiguration
@EnableWebFlux
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ReactorDebugAgent.init();
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner printMemoryConfigJVM() {
        return args -> printConfigurationsJVM();
    }

    private void printConfigurationsJVM() {
        int mb = 1024 * 1024;
        val memoryBean = ManagementFactory.getMemoryMXBean();
        long xmx = memoryBean.getHeapMemoryUsage().getMax() / mb;
        long xms = memoryBean.getHeapMemoryUsage().getInit() / mb;
        log.info(String.format("Initial Memory RAM - JVM (xms) : %smb", xms));
        log.info(String.format("Max Memory RAM - JVM (xmx) : %smb", xmx));
    }

}
