package wallet.wizard.monnify.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BvnDetails {
    private String bvn;
    private String bvnDateOfBirth;
}
