package wallet.wizard.wallet.service.concrete;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wallet.wizard.monnify.dtos.request.BvnDetails;
import wallet.wizard.monnify.dtos.request.CreateWallet;
import wallet.wizard.monnify.dtos.response.CreateWalletResponse;
import wallet.wizard.monnify.service.MonnifyService;
import wallet.wizard.user.entity.User;
import wallet.wizard.wallet.dtos.request.CreateUserWalletRequest;
import wallet.wizard.wallet.entity.Wallet;
import wallet.wizard.wallet.repository.WalletRepository;
import wallet.wizard.wallet.service.WalletService;

@Service
@AllArgsConstructor
@Slf4j
public class WizardWalletService implements WalletService {

    private final MonnifyService monnifyService;
    private final ModelMapper modelMapper;
    private final WalletRepository walletRepository;
//    private final

    @Override
//    @Transactional
    public Wallet createWallet(CreateUserWalletRequest request, User user) {
        CreateWallet createWallet = modelMapper.map(request, CreateWallet.class);
        BvnDetails bvnDetails = modelMapper.map(request, BvnDetails.class);
        createWallet.setBvnDetails(bvnDetails);
        CreateWalletResponse walletResponse = monnifyService.createWallet(createWallet);
        Wallet wallet = modelMapper.map(walletResponse.getResponseBody(), Wallet.class);
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWalletWithUserId(Long userId) {
        return walletRepository.findWalletByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Could not find user with wallet details."));
    }
}
