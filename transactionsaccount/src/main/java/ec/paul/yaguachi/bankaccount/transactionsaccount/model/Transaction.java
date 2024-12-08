package ec.paul.yaguachi.bankaccount.transactionsaccount.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Class Transaction
 *
 * @author Paul Yaguachi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", insertable = false,updatable = false)
    private Account account;
}
