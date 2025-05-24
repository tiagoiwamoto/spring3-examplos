package io.github.api.spring3examplos.lambda.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AnonymousCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;

import java.net.URI;

@Configuration
@Slf4j
public class LambdaConfig {

    @Value(value = "${app.aws.region}")
    private String region;
    @Value(value = "${app.aws.environment}")
    private String environment;

    @Bean
    public LambdaClient lambdaClient() {
        log.info("Criando cliente Lambda");
        var lambdaBuilder = LambdaClient.builder()
                .region(Region.of(region));

        if("local".equals(environment)) {
            lambdaBuilder.endpointOverride(URI.create("http://localhost:4566"));
            lambdaBuilder.credentialsProvider(AnonymousCredentialsProvider.create());
        }else{
            lambdaBuilder.credentialsProvider(DefaultCredentialsProvider.create());
        }

        return lambdaBuilder.build();
    }

}
