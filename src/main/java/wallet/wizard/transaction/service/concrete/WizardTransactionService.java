package wallet.wizard.transaction.service.concrete;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wallet.wizard.monnify.dtos.response.BalanceResponse;
import wallet.wizard.monnify.dtos.response.TransferResponseBody;
import wallet.wizard.monnify.service.MonnifyService;
import wallet.wizard.transaction.dtos.request.AuthorizeTransfer;
import wallet.wizard.transaction.dtos.request.InitiateTransferRequest;
import wallet.wizard.transaction.entity.Transactions;
import wallet.wizard.transaction.repository.TransactionsRepository;
import wallet.wizard.transaction.service.TransactionService;
import wallet.wizard.user.entity.User;
import wallet.wizard.wallet.entity.Wallet;
import wallet.wizard.wallet.service.WalletService;

import java.util.Optional;
import java.util.UUID;


//@todo: own a controller

@Service
@AllArgsConstructor
public class WizardTransactionService implements TransactionService {

    private final WalletService walletService;
    private final MonnifyService monnifyService;
    private final ModelMapper modelMapper;
    private final TransactionsRepository repository;

    @Override
    public TransferResponseBody initiateTransfer(InitiateTransferRequest request) {
        //TODO: get user from the security context
        Wallet wallet = walletService.getWalletWithUserId(Long.valueOf(1));
        request.setSourceAccountNumber(wallet.getAccountNumber());
        request.setReference("ww-trf"+ UUID.randomUUID());
        TransferResponseBody responseBody = monnifyService.initiateTransfer(request);
        Transactions transactions = modelMapper.map(responseBody, Transactions.class);
        transactions.setWallet(wallet);
        repository.save(transactions);
        return responseBody;
    }

    @Override
    public BalanceResponse checkCurrentUserBalance() {
        //todo: get user from the security context
        Wallet wallet = walletService.getWalletWithUserId(Long.valueOf(1));
        return monnifyService.getWalletBalance(wallet.getAccountNumber());
    }

    @Override
    public TransferResponseBody authorizeTransfer(AuthorizeTransfer authorizeTransfer) {
        //todo: get current user
        User user = new User();
        Optional<Transactions> transaction =repository.findTransactionsByReference(authorizeTransfer.getReference());
        if (transaction.isEmpty()) throw new RuntimeException("provided the wrong ref number");
        if (transaction.get().getWallet().getUser().equals(user)){
            Transactions transactions = transaction.get();
            TransferResponseBody responseBody = monnifyService.authorizeTransfer(authorizeTransfer);
            transactions.setStatus(responseBody.getStatus());
            transactions.setDateCreated(responseBody.getDateCreated());
            repository.save(transactions);
            return responseBody;
        }
        else throw new RuntimeException("Couldn't invalid token provided");
    }
}
