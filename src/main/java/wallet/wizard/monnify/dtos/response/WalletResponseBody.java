package wallet.wizard.monnify.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class WalletResponseBody {
    private String walletReference;
    private String walletName;
    private String accountName;
    private String accountNumber;
}
