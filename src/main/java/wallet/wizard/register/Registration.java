package wallet.wizard.register;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wallet.wizard.user.dtos.request.CreateUserRequest;
import wallet.wizard.user.dtos.response.CreateUserResponse;
import wallet.wizard.user.entity.User;
import wallet.wizard.user.service.UserService;
import wallet.wizard.wallet.dtos.request.CreateUserWalletRequest;
import wallet.wizard.wallet.entity.Wallet;
import wallet.wizard.wallet.service.WalletService;

@Service
@AllArgsConstructor
@Slf4j
public class Registration {
    private final UserService userService;
    private final WalletService walletService;
    private final ModelMapper modelMapper;

    public CreateUserResponse registerUser(CreateUserRequest request) {
        User user = userService.createUser(request);
        CreateUserWalletRequest walletRequest =
                modelMapper.map(request, CreateUserWalletRequest.class);
        walletRequest.setCustomerEmail(request.getEmail());
        walletRequest.setCustomerName(request.getUsername());
        Wallet wallet = walletService.createWallet(walletRequest, user);
        CreateUserResponse response =
                modelMapper.map(wallet, CreateUserResponse.class);
        response.setMessage("successfully registered...");
        return response;
    }
}
