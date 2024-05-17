package wallet.wizard.monnify.dtos.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;



@Getter@Setter
public class CreateWallet {
    private String walletReference;
    private String walletName;
    private String customerName;
    @JsonProperty("bvnDetails")
    private BvnDetails bvnDetails;
    private String customerEmail;
    public CreateWallet(){
        this.walletName = "the_wizard_";
    }
}
