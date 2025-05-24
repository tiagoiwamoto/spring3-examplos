package io.github.api.spring3examplos.lambda.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;

@Configuration
@Slf4j
public class LambdaConfig {

    @Value(value = "${app.aws.region")
    private String region;

    @Bean
    public LambdaClient lambdaClient() {
        log.info("Criando cliente Lambda");
        return LambdaClient.builder()
                .region(Region.of(region))
                .build();
    }

}
