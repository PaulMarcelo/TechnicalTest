package ec.paul.yaguachi.bankaccount.transactionsaccount.mapper;

import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.AccountVo;

/**
 * Class AccountMapper
 *
 * @author Paul Yaguachi
 */
public class AccountMapper {

    /**
     *
     * @param account
     * @return
     */
    public static AccountVo toAccountVo(Account account) {
        AccountVo accountVo = new AccountVo();
        accountVo.setId(account.getId());
        accountVo.setAccountNumber(account.getAccountNumber());
        accountVo.setAccountType(account.getAccountType());
        accountVo.setInitialBalance(account.getInitialBalance());
        accountVo.setStatus(account.getStatus());
        accountVo.setCustomerId(account.getCustomerId());
        return accountVo;
    }

    /**
     *
     * @param accountVo
     * @return
     */
    public static Account toAccount(AccountVo accountVo) {
        Account account = new Account();
        account.setId(accountVo.getId());
        account.setAccountNumber(accountVo.getAccountNumber());
        account.setAccountType(accountVo.getAccountType());
        account.setInitialBalance(accountVo.getInitialBalance());
        account.setStatus(accountVo.getStatus());
        account.setCustomerId(accountVo.getCustomerId());
        return account;
    }
}
