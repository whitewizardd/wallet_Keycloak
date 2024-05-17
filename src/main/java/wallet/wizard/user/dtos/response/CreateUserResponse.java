package wallet.wizard.user.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@ToString
public class CreateUserResponse {
    private String accountNumber;
    private String accountName;
    private String message;
}
