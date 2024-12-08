package ec.paul.yaguachi.bankaccount.transactionsaccount.repository;

import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
/**
 * Class IAccountRepository
 *
 * @author Paul Yaguachi
 */
@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getAccountByAccountNumber(String accountNumber);

    @Query(value = "SELECT * FROM account WHERE client_id = :id", nativeQuery = true)
    Collection<Account> getAccountByCustomerId(@Param("id") Long accountId);
}
