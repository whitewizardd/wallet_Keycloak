package wallet.wizard.user.service;

import wallet.wizard.user.dtos.request.CreateUserRequest;
import wallet.wizard.user.entity.User;

public interface UserService {

    User createUser(CreateUserRequest request);
    wallet.wizard.user.entity.User getUserByUserName(String username);
}
