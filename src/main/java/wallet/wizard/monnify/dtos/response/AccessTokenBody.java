package wallet.wizard.monnify.dtos.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class AccessTokenBody {
    private boolean requestSuccessful;
    private AccessToken responseBody;
}
