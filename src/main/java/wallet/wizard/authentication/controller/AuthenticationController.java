package wallet.wizard.authentication.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wallet.wizard.authentication.service.AuthenticationService;
import wallet.wizard.authentication.dtos.request.LoginRequest;
import wallet.wizard.authentication.dtos.response.LoginResponse;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest request){
        return ResponseEntity.ok(service.login(request));
    }
}
