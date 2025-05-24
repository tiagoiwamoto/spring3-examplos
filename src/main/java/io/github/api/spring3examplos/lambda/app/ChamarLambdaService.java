package io.github.api.spring3examplos.lambda.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChamarLambdaService {

    private final LambdaClient lambdaClient;
    private final ObjectMapper objectMapper;
    @Value(value = "${app.aws.lambda.arn}")
    private String arn;

    public void chamarLambda(Map<String, Object> request) {
        try {
            log.info("Chamando Lambda");

            // Convertendo o Map para JSON
            String jsonRequest = objectMapper.writeValueAsString(request);
            var requestLambda = InvokeRequest.builder()
                    .functionName(arn)
                    .payload(SdkBytes.fromUtf8String(jsonRequest))
                    .build();
            InvokeResponse response = lambdaClient.invoke(requestLambda);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
