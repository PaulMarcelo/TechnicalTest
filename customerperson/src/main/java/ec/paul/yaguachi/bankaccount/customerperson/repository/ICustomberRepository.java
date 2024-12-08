package ec.paul.yaguachi.bankaccount.customerperson.repository;

import ec.paul.yaguachi.bankaccount.customerperson.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Class ICustomberRepository
 *
 * @author Paul Yaguachi
 */
@Repository
public interface ICustomberRepository
        extends JpaRepository<Customer, Long>
{
    @Query(value = "SELECT * FROM person_customer WHERE id = :id", nativeQuery = true)
    Optional<Customer> findCustomerById(@Param("id") Long accountId);

}
