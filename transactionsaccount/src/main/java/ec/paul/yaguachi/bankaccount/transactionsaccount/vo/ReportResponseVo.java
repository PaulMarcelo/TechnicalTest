package ec.paul.yaguachi.bankaccount.transactionsaccount.vo;

import lombok.Data;
/**
 * Class ReportResponseVo
 *
 * @author Paul Yaguachi
 */
@Data
public class ReportResponseVo {
    private String date;
    private String customer;
    private String numberAccount;
    private String type;
    private Double initialBalance;
    private Boolean status;
    private Double movementAmount;
    private Double availableBalance;
}
