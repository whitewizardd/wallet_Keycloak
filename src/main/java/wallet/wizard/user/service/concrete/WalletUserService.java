package wallet.wizard.user.service.concrete;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wallet.wizard.keycloak.dtos.request.CreateCloakUser;
import wallet.wizard.keycloak.service.concrete.KeyCloakSecurityService;
import wallet.wizard.user.dtos.request.CreateUserRequest;
import wallet.wizard.user.entity.User;
import wallet.wizard.user.repository.UserRepository;
import wallet.wizard.user.service.UserService;

import java.util.Optional;

// @todo: to own a controller

@Service
@AllArgsConstructor
@Slf4j
public class WalletUserService implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final KeyCloakSecurityService keyCloakService;

    @Override
    public User createUser(CreateUserRequest request) {
        Optional<wallet.wizard.user.entity.User> foundUser = userRepository.findByUsername(request.getUsername());
        if (foundUser.isPresent()) throw new RuntimeException("User already exist");
        CreateCloakUser cloakUser = generateCloakUserRequest(request);
        UserRepresentation representation = keyCloakService.createUser(cloakUser);
        User user = modelMapper.map(request, wallet.wizard.user.entity.User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setKeyCloakId(representation.getId());
        return userRepository.save(user);
    }

    private static CreateCloakUser generateCloakUserRequest(CreateUserRequest request) {
        CreateCloakUser cloakUser = new CreateCloakUser();
        cloakUser.setUsername(request.getUsername());
        cloakUser.setEmail(request.getEmail());
        cloakUser.setLastname(request.getLastName());
        cloakUser.setFirstname(request.getFirstName());
        cloakUser.setPassword(request.getPassword());
        return cloakUser;
    }

    @Override
    public wallet.wizard.user.entity.User getUserByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("Could not find user"));
    }

}
