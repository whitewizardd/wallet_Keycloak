package wallet.wizard.keycloak.service.concrete;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.ArrayList;
import java.util.List;


//@Service
@Slf4j
@AllArgsConstructor
public class KeyCloakSetUp {

    private final Keycloak keycloak;

//    @PostConstruct
    public void init() {
        try {
//            createRealm();
//            createClient();
//            addRoles();
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createRealm() {
        try {
            if (keycloak.realms().findAll().stream().noneMatch(r -> r.getRealm().equals("wizardousss"))){
                RealmRepresentation realmRepresentation = new RealmRepresentation();
                realmRepresentation.setRealm("wizardousss");
                keycloak.realms().create(realmRepresentation);
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createClient() {
        try {
            if (keycloak.realm("wizardousss").clients().findAll().stream().noneMatch(cl -> cl.getClientId().equals("wallet"))){
                ClientRepresentation representation = new ClientRepresentation();
                representation.setClientId("wallet");
                keycloak.realm("wizardousss").clients().create(representation);
            }else {
                log.error("client 'wizardousss' already created");
            }
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addRoles() {
        try {
            List<String> roles = new ArrayList<>();
            roles.add("ADMIN");
            roles.add("USER");
            ClientResource resource = keycloak.realm("wizardousss").clients().get("wallet");
            RolesResource rolesResource = resource.roles();
            RoleRepresentation representation = new RoleRepresentation();
            representation.setName(roles.get(0));
            rolesResource.create(representation);

            RoleRepresentation roleRepresentation = new RoleRepresentation();
            roleRepresentation.setName(roles.get(1));
            rolesResource.create(roleRepresentation);
            log.info("{}", roles);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
