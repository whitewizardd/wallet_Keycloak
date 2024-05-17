package wallet.wizard.wallet.service;

import wallet.wizard.user.entity.User;
import wallet.wizard.wallet.dtos.request.CreateUserWalletRequest;
import wallet.wizard.wallet.entity.Wallet;

public interface WalletService {

    Wallet createWallet(CreateUserWalletRequest request, User user);
    Wallet getWalletWithUserId(Long userId);
}
