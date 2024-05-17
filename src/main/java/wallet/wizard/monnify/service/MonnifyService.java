package wallet.wizard.monnify.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wallet.wizard.monnify.dtos.request.CreateWallet;
import wallet.wizard.monnify.dtos.response.*;
import wallet.wizard.transaction.dtos.request.AuthorizeTransfer;
import wallet.wizard.transaction.dtos.request.InitiateTransferRequest;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import static wallet.wizard.monnify.config.MonnifyApis.*;


@Service
@AllArgsConstructor
@Slf4j
public class MonnifyService {

    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ModelMapper modelMapper;

    public CreateWalletResponse createWallet(CreateWallet createWallet) {
        createWallet.setWalletReference("cwmw"+UUID.randomUUID());
        try {
            HttpEntity<CreateWallet> httpEntity = createHttpEntity(createWallet, HttpMethod.POST, httpHeaders);

            ResponseEntity<CreateWalletResponse> responseEntity =
                    createPostResponseEntity(CREATE_WALLET, httpEntity, CreateWalletResponse.class);

            CreateWalletResponse response = Objects.requireNonNull(responseEntity.getBody());
            log.info("response monnify ::: {}", response);
            return response;
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private <T> HttpEntity<T> createHttpEntity(T request, HttpMethod httpMethod, HttpHeaders headers){
        return new RequestEntity<>(request, headers, httpMethod, URI.create(""));
    }
    private <T> ResponseEntity<T> createPostResponseEntity(String path, HttpEntity<?> httpEntity, Class<T> responseBody) {
        return restTemplate.postForEntity(path, httpEntity, responseBody);
    }

    private <T> ResponseEntity<T> getEntityResponseWithoutBody(String path,
                                                               HttpMethod method,
                                                               HttpEntity<?> headerEntity,
                                                               Class<T> response) {
        return restTemplate.exchange(path, method, headerEntity, response);
    }

    public TransferResponseBody initiateTransfer(InitiateTransferRequest request) {
        try{
            AccessToken accessToken = requestAccessToken();

            httpHeaders.set("Authorization", "Bearer "+accessToken.getAccessToken());
            HttpEntity<InitiateTransferRequest> requestHttpEntity = createHttpEntity(
                    request, HttpMethod.POST, httpHeaders);

            ResponseEntity<InitiateTransferResponse> responseEntity =
                    createPostResponseEntity(TRANSFER_URL, requestHttpEntity, InitiateTransferResponse.class);

            InitiateTransferResponse transferResponse = Objects.requireNonNull(responseEntity.getBody());
            return transferResponse.getResponseBody();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private AccessToken requestAccessToken() {
        ResponseEntity<AccessTokenBody> tokenBody = getEntityResponseWithoutBody(
                LOGIN, HttpMethod.POST,  new HttpEntity<>(httpHeaders), AccessTokenBody.class);
        return Objects.requireNonNull(tokenBody.getBody()).getResponseBody();
    }

    public BalanceResponse getWalletBalance(String accountNumber) {
        String uri = CHECK_BALANCE+accountNumber;
        ResponseEntity<BalanceInquiry> responseEntity = getEntityResponseWithoutBody(
                uri, HttpMethod.GET, new HttpEntity<>(httpHeaders), BalanceInquiry.class);

        if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null){
            return responseEntity.getBody().getResponseBody();
        }else {
            throw new RuntimeException("Could not get account details");
        }
    }

    public TransferResponseBody authorizeTransfer(AuthorizeTransfer authorizeTransfer) {
        try {
            AccessToken accessToken = requestAccessToken();

            httpHeaders.set("Authorization", "Bearer " +accessToken.getAccessToken());
            HttpEntity<AuthorizeTransfer> httpEntity =
                    createHttpEntity(authorizeTransfer, HttpMethod.POST, httpHeaders);

            ResponseEntity<InitiateTransferResponse> responseEntity =
                    createPostResponseEntity(AUTHORIZE_TRF, httpEntity, InitiateTransferResponse.class);
            return Objects.requireNonNull(responseEntity.getBody()).getResponseBody();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
