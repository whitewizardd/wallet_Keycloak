package wallet.wizard.wallet.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import wallet.wizard.user.entity.User;

@Entity
@Table
@Setter@Getter
@ToString
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String accountName;
    private String walletReference;
    private String walletName;
    @OneToOne
    @JoinColumn(unique = true, name = "user_id")
    private User user;
}
