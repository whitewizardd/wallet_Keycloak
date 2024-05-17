package wallet.wizard.monnify;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wallet.wizard.monnify.dtos.request.BvnDetails;
import wallet.wizard.monnify.dtos.request.CreateWallet;
import wallet.wizard.monnify.dtos.response.BalanceResponse;
import wallet.wizard.monnify.dtos.response.CreateWalletResponse;
import wallet.wizard.monnify.dtos.response.TransferResponseBody;
import wallet.wizard.monnify.service.MonnifyService;
import wallet.wizard.transaction.dtos.request.AuthorizeTransfer;
import wallet.wizard.transaction.dtos.request.InitiateTransferRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class MonnifyServiceTest {

    @Autowired
    private MonnifyService monnifyService;

    @Test
    public void createWallet() {
        CreateWallet createWallet = new CreateWallet();
        BvnDetails bvnDetails = new BvnDetails(
                "22411467236",
                "1998-08-11"
        );
        createWallet.setBvnDetails(bvnDetails);
        createWallet.setCustomerEmail("leumasre@hotmail.com");
        createWallet.setCustomerName("Dleexs");

        CreateWalletResponse response = monnifyService.createWallet(createWallet);

        assertTrue(response.getResponseBody().getWalletReference().startsWith("cwmw"));
        assertTrue(response.getResponseBody().getWalletReference().contains("cwmw"));
        assertNotNull(response.getResponseBody().getAccountName());
        assertNotNull(response.getResponseBody().getAccountNumber());
    }

    @Test
    public void getWalletBalance() {
        BalanceResponse result = monnifyService.getWalletBalance("2376829248");
        assertNotNull(result);
        assertEquals(String.valueOf(result.getLedgerBalance()), "5000000000");
    }

    @Test
    public void initiateTransfer() {
        InitiateTransferRequest request = new InitiateTransferRequest();
        request.setDestinationAccountNumber("0721151261");
        request.setDestinationBankCode("044");
        request.setAmount("2000");
        request.setNarration("narrator");
        request.setSourceAccountNumber("2376829248");
        request.setReference("trfwww"+UUID.randomUUID());

        TransferResponseBody responseBody = monnifyService.initiateTransfer(request);

        log.info("response body ::: {}", responseBody);
        log.info("receiver account name ::: {}", responseBody.getDestinationAccountName());
        assertEquals(responseBody.getAmount(), "2000");
        assertEquals(responseBody.getDestinationBankName(), "Access bank");
    }

    @Test
    public void authorizeTransfer() {
        AuthorizeTransfer authorizeTransfer = new AuthorizeTransfer();
        authorizeTransfer.setReference("trfwww94452d72-5489-45c3-9098-69544b8bf1e1");
        authorizeTransfer.setAuthorizationCode("669880");
        TransferResponseBody responseBody = monnifyService.authorizeTransfer(authorizeTransfer);

        assertNotNull(responseBody);
        assertEquals(responseBody.getStatus(), "SUCCESS");
        log.info("otp response ::: {}", responseBody);
    }
}
