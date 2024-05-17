package wallet.wizard.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wallet.wizard.wallet.entity.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {


    @Query("select w from Wallet w where w.user.id =: userId")
    Optional<Wallet> findWalletByUserId(@Param("userId") Long userId);
}
