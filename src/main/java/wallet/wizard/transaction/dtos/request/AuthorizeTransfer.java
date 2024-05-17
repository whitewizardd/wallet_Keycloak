package wallet.wizard.transaction.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class AuthorizeTransfer {
    private String reference;
    private String authorizationCode;
}
