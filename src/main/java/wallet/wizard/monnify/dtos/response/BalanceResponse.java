package wallet.wizard.monnify.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class BalanceResponse {
    private long availableBalance;
    private long ledgerBalance;
}
