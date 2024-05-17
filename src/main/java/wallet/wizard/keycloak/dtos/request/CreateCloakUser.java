package wallet.wizard.keycloak.dtos.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@ToString
public class CreateCloakUser {

    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
}
