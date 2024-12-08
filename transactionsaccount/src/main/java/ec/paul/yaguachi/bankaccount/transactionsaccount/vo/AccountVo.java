package ec.paul.yaguachi.bankaccount.transactionsaccount.vo;

import lombok.Data;
/**
 * Class AccountVo
 *
 * @author Paul Yaguachi
 */
@Data
public class AccountVo {
    private Long id;
    private String accountNumber;
    private String accountType;
    private Double initialBalance;
    private Boolean status;
    private Long customerId;
}
