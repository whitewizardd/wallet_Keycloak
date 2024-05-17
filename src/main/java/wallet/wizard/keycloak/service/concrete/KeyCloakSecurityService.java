package wallet.wizard.keycloak.service.concrete;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wallet.wizard.keycloak.dtos.request.CreateCloakUser;
import wallet.wizard.keycloak.service.SecurityProviderService;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeyCloakSecurityService implements SecurityProviderService {

    private final Keycloak keycloak;

    @Value("${key_cloak.config.target_realm}")
    private String targetRealm;

    @Override
    public UserRepresentation createUser(CreateCloakUser cloakUser) {
        UsersResource userResource =
                keycloak.realm(targetRealm).users();
        UserRepresentation representation = userRepresentation(cloakUser);
        CredentialRepresentation credentialRepresentation = setPassword(cloakUser.getPassword());
        representation.setCredentials(Collections.singletonList(credentialRepresentation));
        try(Response response = userResource.create(representation)) {
            if (response.getStatus() != 201){
                throw new RuntimeException("Username already exist");
            }
            return representation;
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private UserRepresentation userRepresentation(CreateCloakUser user){
        UserRepresentation representation = new UserRepresentation();
        representation.setEnabled(true);
        representation.setEmail(user.getEmail());
        representation.setFirstName(user.getFirstname());
        representation.setLastName(user.getLastname());
        representation.setUsername(user.getUsername().toLowerCase());
        return representation;
    }

    private CredentialRepresentation setPassword(String password){
        CredentialRepresentation representation = new CredentialRepresentation();
        representation.setType(CredentialRepresentation.PASSWORD);
        representation.setTemporary(false);
        representation.setValue(password);
        return representation;
    }

    private RoleRepresentation assignRole(String role){
        RoleRepresentation representation = new RoleRepresentation();
        representation.setName(role);
        return representation;
    }
}
