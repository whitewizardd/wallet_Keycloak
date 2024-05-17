package wallet.wizard.authentication.dtos.response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter@ToString
public class LoginResponse {
    private String access_token;
    private String refresh_token;
}
