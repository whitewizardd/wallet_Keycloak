package wallet.wizard.transaction.service;

import wallet.wizard.monnify.dtos.response.BalanceResponse;
import wallet.wizard.monnify.dtos.response.TransferResponseBody;
import wallet.wizard.transaction.dtos.request.AuthorizeTransfer;
import wallet.wizard.transaction.dtos.request.InitiateTransferRequest;

public interface TransactionService {

    TransferResponseBody initiateTransfer(InitiateTransferRequest request);

    BalanceResponse checkCurrentUserBalance();
    TransferResponseBody authorizeTransfer(AuthorizeTransfer authorizeTransfer);
}
