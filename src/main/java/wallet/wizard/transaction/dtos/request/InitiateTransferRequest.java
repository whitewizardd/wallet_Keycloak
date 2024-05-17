package wallet.wizard.transaction.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter
public class InitiateTransferRequest {

    private String destinationAccountNumber;
    private String destinationBankCode;
    private String sourceAccountNumber;
    private String reference;
    private String amount;
    private String narration;
    private String currency;

    public InitiateTransferRequest(){
        this.currency = "NGN";
    }
}
