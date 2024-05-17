package wallet.wizard.authentication.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import wallet.wizard.authentication.dtos.request.LoginRequest;
import wallet.wizard.authentication.dtos.response.LoginResponse;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    @Value("${key_cloak.login.request}")
    private String serverUrl;
    @Value("${key_cloak.login.client_id}")
    private String clientId;
    @Value("${key_cloak.login.grant_type}")
    private String grantType;

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;

    public LoginResponse login(LoginRequest request){
        try{
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("client_id", clientId);
            multiValueMap.add("username", request.getEmail());
            multiValueMap.add("password", request.getPassword());
            multiValueMap.add("grant_type", grantType);
            HttpEntity<MultiValueMap<?, ?>> mapHttpEntity =
                    new HttpEntity<>(multiValueMap, httpHeaders);
            ResponseEntity<LoginResponse> responseEntity =
                    restTemplate.exchange(
                            serverUrl,
                            HttpMethod.POST,
                            mapHttpEntity,
                            LoginResponse.class
                    );
            return Objects.requireNonNull(responseEntity.getBody());
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
