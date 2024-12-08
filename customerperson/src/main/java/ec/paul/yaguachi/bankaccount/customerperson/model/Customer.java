package ec.paul.yaguachi.bankaccount.customerperson.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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


}
