package wallet.wizard.monnify.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class BalanceInquiry {
    private boolean requestSuccessful;
    private String responseMessage;
    private BalanceResponse responseBody;
}