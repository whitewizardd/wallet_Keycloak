package wallet.wizard.keycloak;


import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wallet.wizard.keycloak.dtos.request.CreateCloakUser;
import wallet.wizard.keycloak.service.concrete.KeyCloakSecurityService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class KeyCloakServiceTest {

    @Autowired
    private KeyCloakSecurityService keyCloak;

    @Test
    public void createCloakUser(){
        CreateCloakUser cloakUser = new CreateCloakUser();
        cloakUser.setEmail("leumasre@hotmail.com");
        cloakUser.setPassword("password");
        cloakUser.setUsername("Dleex");
        cloakUser.setFirstname("Dell");
        cloakUser.setLastname("Mac");
        UserRepresentation representation = keyCloak.createUser(cloakUser);
        assertNotNull(representation);
        assertEquals(representation.getUsername(), "dleex");
    }
}
