package wallet.wizard.transaction.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wallet.wizard.wallet.entity.Wallet;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    private String amount;
    @Column(unique = true)
    private String reference;
    private String dateCreated;
    private String totalFee;
    private String destinationAccountName;
    private String destinationBankName;
    private String destinationAccountNumber;
}
