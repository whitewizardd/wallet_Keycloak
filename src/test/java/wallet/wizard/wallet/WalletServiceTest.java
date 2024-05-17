package wallet.wizard.wallet;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import wallet.wizard.user.entity.User;
import wallet.wizard.user.service.UserService;
import wallet.wizard.wallet.dtos.request.CreateUserWalletRequest;
import wallet.wizard.wallet.entity.Wallet;
import wallet.wizard.wallet.service.WalletService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class WalletServiceTest {

    private static final Logger log = LoggerFactory.getLogger(WalletServiceTest.class);
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Test
    @Sql(scripts = "/db/user.sql")
    public void createWalletTest() {
        User user = userService.getUserByUserName("blurbeast");
        assertThat(user.getUsername()).isEqualTo("blurbeast");
        assertThat(user.getId()).isEqualTo(1L);
        CreateUserWalletRequest request = new CreateUserWalletRequest();
        request.setBvn("22411467236");
        request.setBvnDateOfBirth("1998-08-11");
        request.setCustomerName("blurbeast");
        request.setCustomerEmail("leumasre@hotmail.com");
        Wallet wallet = walletService.createWallet(request, user);
        log.info("wallet result ::: {}", wallet);
        assertNotNull(wallet);
        assertEquals(wallet.getId(), 1L);
        assertEquals(wallet.getAccountName(), "wizaard-blurbeast");
    }


}
