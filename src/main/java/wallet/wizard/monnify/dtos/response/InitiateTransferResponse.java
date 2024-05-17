package wallet.wizard.monnify.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class InitiateTransferResponse {
    private boolean requestSuccessful;
    private TransferResponseBody responseBody;
}