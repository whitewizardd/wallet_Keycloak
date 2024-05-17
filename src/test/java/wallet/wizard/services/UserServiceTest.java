package wallet.wizard.services;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wallet.wizard.register.Registration;
import wallet.wizard.user.dtos.request.CreateUserRequest;
import wallet.wizard.user.dtos.response.CreateUserResponse;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class UserServiceTest {
    @Autowired
    private Registration registration;
    @Test
    public void testCreateUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setEmail("banjo.oladele.samuel@gmail.com");
        request.setUsername("banjo-ooo");
        request.setPassword("password111");
        request.setFirstName("Banjo");
        request.setLastName("Samuel");
        request.setBvn("22411467236");
        request.setBvnDateOfBirth("1998-08-11");

        CreateUserResponse response = registration.registerUser(request);
        log.info("end result ::: {}", response);
        assertTrue(response.getAccountName().startsWith("wizaard-banjo"));
        assertNotNull(response);
    }
}
