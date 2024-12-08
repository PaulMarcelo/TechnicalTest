package ec.paul.yaguachi.bankaccount.transactionsaccount.vo;

import lombok.Data;
/**
 * Class MovementRequestVo
 *
 * @author Paul Yaguachi
 */
@Data
public class MovementRequestVo {
    private String accountNumber;
    private Double amount;
    private String transactionType;

}
