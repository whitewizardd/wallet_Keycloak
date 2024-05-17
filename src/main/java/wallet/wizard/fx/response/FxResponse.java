package wallet.wizard.fx.response;


import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class FxResponse {
    private Map<String, String> rates;
}
