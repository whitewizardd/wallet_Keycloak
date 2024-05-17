package wallet.wizard.keycloak.service;

import org.keycloak.representations.idm.UserRepresentation;
import wallet.wizard.keycloak.dtos.request.CreateCloakUser;

public interface SecurityProviderService {

    UserRepresentation createUser(CreateCloakUser cloakUser);
}
