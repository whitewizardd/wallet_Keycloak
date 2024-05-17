package wallet.wizard.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wallet.wizard.transaction.entity.Transactions;

import java.util.Optional;


public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    @Query("select t from Transactions t where t.reference =: reference")
    Optional<Transactions> findTransactionsByReference(@Param("reference") String reference);
}
