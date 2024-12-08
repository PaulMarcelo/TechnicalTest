package ec.paul.yaguachi.bankaccount.transactionsaccount.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 * Class Customer
 *
 * @author Paul Yaguachi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "person_customer")
public class Customer extends Person implements Serializable {

    @Column(name = "status")
    private Boolean status;
    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

}
