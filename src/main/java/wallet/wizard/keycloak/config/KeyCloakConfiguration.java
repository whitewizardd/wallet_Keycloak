package wallet.wizard.keycloak.config;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeyCloakConfiguration {

    @Value("${key_cloak.config.server_url}")
    private String serverUrl;
    @Value("${key_cloak.config.realm}")
    private String realmName;
    @Value("${key_cloak.config.username}")
    private String username;
    @Value("${key_cloak.config.password}")
    private String password;
    @Value("${key_cloak.config.client_id}")
    private String clientId;

    @Value("${key_cloak.config.target_realm}")
    public static String targetRealm;

    @Bean
    public Keycloak keyCloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realmName)
                .username(username)
                .password(password)
                .clientId(clientId)
                .build();
    }
}
