package ec.paul.yaguachi.bankaccount.transactionsaccount.vo;

import lombok.Data;
/**
 * Class CustomerVo
 *
 * @author Paul Yaguachi
 */
@Data
public class CustomerVo {
    private Long id;
    private String name;
    private String gender;
    private int age;
    private String identification;
    private String address;
    private String phone;
    private Boolean status;
    private String password;
}
