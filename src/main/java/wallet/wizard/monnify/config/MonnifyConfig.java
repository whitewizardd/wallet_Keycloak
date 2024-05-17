package wallet.wizard.monnify.config;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Base64;

@Configuration
@Slf4j
public class MonnifyConfig {

    @Value("${monnify.config.api_key}")
    private String apiKey;
    @Value("${monnify.config.secret_key}")
    private String secretKey;

    @Bean
    public HttpHeaders httpHeaders() {
        log.info("apiKey {}", apiKey+"____"+secretKey);
        String base64Hash = Base64.getEncoder().encodeToString((apiKey + ":" + secretKey).getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "Basic "+ base64Hash);
        return httpHeaders;
    }
}
