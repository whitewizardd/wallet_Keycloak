package wallet.wizard.user.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
public class CreateUserRequest {

    @NotNull
    @NotEmpty
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private String username;
    private String bvn;
    private String bvnDateOfBirth;
}
