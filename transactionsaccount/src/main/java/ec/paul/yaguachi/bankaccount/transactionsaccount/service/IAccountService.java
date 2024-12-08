package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.AccountVo;

import java.util.Collection;
import java.util.Optional;
/**
 * Class IAccountService
 *
 * @author Paul Yaguachi
 */
public interface IAccountService {
    void saveAccount(AccountVo account) throws AppException;

    Collection<AccountVo> getAllAccounts();

    Optional<AccountVo> getAccountById(Long id) throws AppException;

    Optional<Account> getAccountByNumber(String numberAccount) throws AppException;

    void deleteAccount(Long id) throws AppException;

}
