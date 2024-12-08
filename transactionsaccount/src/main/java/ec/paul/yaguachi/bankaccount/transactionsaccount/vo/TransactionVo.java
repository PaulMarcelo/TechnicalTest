package ec.paul.yaguachi.bankaccount.transactionsaccount.vo;


import lombok.Data;

import java.util.Date;
/**
 * Class TransactionVo
 *
 * @author Paul Yaguachi
 */
@Data
public class TransactionVo {

    private Long id;
    private Date date;
    private String transactionType;
    private Double amount;
    private Double balance;
    private Long accountId;

}
