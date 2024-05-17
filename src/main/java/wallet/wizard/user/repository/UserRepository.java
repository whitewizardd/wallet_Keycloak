package wallet.wizard.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wallet.wizard.user.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {


    @Query("select u from User u where u.username=:username")
    Optional<User> findByUsername(@Param("username") String username);
}
