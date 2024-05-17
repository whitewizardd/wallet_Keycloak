package wallet.wizard.monnify.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter@Setter
@ToString
public class CreateWalletResponse {
    private String requestSuccessful;
    private WalletResponseBody responseBody;
}
