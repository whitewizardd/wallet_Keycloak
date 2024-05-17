package wallet.wizard.fx;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wallet.wizard.fx.response.FxResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class FxResultTest {

    @Autowired
    private CurrencyExchange exchange;


    @Test
    public void getExchange() {
        FxResponse response = exchange.getUpdatedFx();

        assertNotNull(response);
    }
}
