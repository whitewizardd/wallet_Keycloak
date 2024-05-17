package wallet.wizard.transaction.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wallet.wizard.monnify.dtos.response.BalanceResponse;
import wallet.wizard.monnify.dtos.response.TransferResponseBody;
import wallet.wizard.transaction.dtos.request.AuthorizeTransfer;
import wallet.wizard.transaction.dtos.request.InitiateTransferRequest;
import wallet.wizard.transaction.service.TransactionService;

@RestController("api/v1/transact")
@AllArgsConstructor
public class TransactionsController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransferResponseBody> initiateTransfer(@RequestBody InitiateTransferRequest request){
        return ResponseEntity.ok(transactionService.initiateTransfer(request));
    }

    @GetMapping
    public ResponseEntity<BalanceResponse> getWalletBalance(){
        return ResponseEntity.ok().body(transactionService.checkCurrentUserBalance());
    }

    @PostMapping("/otp")
    public ResponseEntity<TransferResponseBody> authorizeTransfer(@RequestBody AuthorizeTransfer authorizeTransfer) {
        return new ResponseEntity<>(transactionService.authorizeTransfer(authorizeTransfer), HttpStatus.OK);
    }
}
