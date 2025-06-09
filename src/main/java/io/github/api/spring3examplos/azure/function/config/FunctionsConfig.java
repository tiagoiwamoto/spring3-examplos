package io.github.api.spring3examplos.azure.function.config;

import com.azure.core.http.HttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FunctionsConfig {

    @Value(value = "${app.azure.environment}")
    private String environment;

    @Bean
    public HttpClient azureFunctionsClient() {
        log.info("Criando cliente Azure Functions");
        var httpClient = HttpClient.createDefault();

        log.info("Cliente Azure Functions criado com sucesso.");
        return httpClient;
    }

    @Bean
    public String azureFunctionsEndpoint() {
        if ("local".equals(environment)) {
            return "http://localhost:7071/api";
        } else {
            // Substitua pelo endpoint real da sua Azure Function
            return "https://<seu-app-name>.azurewebsites.net/api";
        }
    }

}
