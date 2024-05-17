package wallet.wizard.login;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wallet.wizard.authentication.dtos.request.LoginRequest;
import wallet.wizard.authentication.service.AuthenticationService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class LoginRequestTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void loginUser() {
        LoginRequest request = new LoginRequest();
        request.setEmail("banjo-ooo");
        request.setPassword("password111");

        var response = authenticationService.login(request);
        assertNotNull(response.getAccess_token());
        assertNotNull(response.getRefresh_token());
        assertNotNull(response);
    }
}
