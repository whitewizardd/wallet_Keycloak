package wallet.wizard.user.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wallet.wizard.register.Registration;
import wallet.wizard.user.dtos.request.CreateUserRequest;
import wallet.wizard.user.dtos.response.CreateUserResponse;

@RestController
@AllArgsConstructor
public class UserController {

    private final Registration registration;

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok().body(registration.registerUser(request));
    }


}
