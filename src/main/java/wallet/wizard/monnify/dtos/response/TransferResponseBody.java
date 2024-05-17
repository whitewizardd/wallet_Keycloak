package wallet.wizard.monnify.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class TransferResponseBody {
    private String amount;
    private String reference;
    private String status;
    private String dateCreated;
    private String totalFee;
    private String destinationAccountName;
    private String destinationBankName;
    private String destinationAccountNumber;
}