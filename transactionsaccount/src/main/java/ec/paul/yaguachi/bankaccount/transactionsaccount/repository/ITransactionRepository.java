package ec.paul.yaguachi.bankaccount.transactionsaccount.repository;

import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
/**
 * Class ITransactionRepository
 *
 * @author Paul Yaguachi
 */
@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions WHERE account_id = :id ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Optional<Transaction> findTopByAccountId(@Param("id") Long accountId);

    @Query(value = "SELECT * FROM transactions WHERE account_id = :id AND date BETWEEN :startDate AND :endDate ORDER BY date", nativeQuery = true)
    Collection<Transaction> getTransactionByAccountId(@Param("id") Long accountId,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);
}
