package wallet.wizard.fx;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import wallet.wizard.fx.response.FxResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchange {
    @Value("${exchange.app_id}")
    private String appId;
    @Value("${exchange.resource_path}")
    private String path;
    private final HttpHeaders headers;
    private final RestTemplate restTemplate;

    public FxResponse getUpdatedFx() {
        String url = "https://openexchangerates.org/api/latest.json";
        UriBuilder uriBuilder = UriComponentsBuilder.fromPath(url)
                .queryParam("app_id", appId)
                .queryParam("base", "USD");
//                .queryParam("symbols", "GBP,EUR,JPY,NGN,CNY");

//        uriBuilder.
        FxResponse response =  restTemplate.getForObject(uriBuilder.toString(), FxResponse.class);
        log.info("fx ::: {}", response);

        return response;
    }
}
