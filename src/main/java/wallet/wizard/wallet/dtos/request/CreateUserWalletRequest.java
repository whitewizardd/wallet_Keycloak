package wallet.wizard.wallet.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class CreateUserWalletRequest {
    private String customerName;
    private String customerEmail;
    private String bvn;
    private String bvnDateOfBirth;
}
