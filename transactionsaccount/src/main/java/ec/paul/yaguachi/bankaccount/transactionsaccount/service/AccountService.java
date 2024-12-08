package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.mapper.AccountMapper;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import ec.paul.yaguachi.bankaccount.transactionsaccount.repository.IAccountRepository;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.AccountVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Class AccountService
 *
 * @author Paul Yaguachi
 */
@Lazy
@Service
@Slf4j
public class AccountService implements IAccountService {

    @Lazy
    @Autowired
    private IAccountRepository accountRepository;

    @Transactional(rollbackFor = AppException.class)
    @Override
    public void saveAccount(AccountVo accountIn) throws AppException {
        try {
            Account account = AccountMapper.toAccount(accountIn);
            accountRepository.save(account);
        } catch (Exception e) {
            log.error("Error al guardar la cuenta: {}", e.getMessage());
            throw new AppException("Inconveniente al guardar la cuenta, por favor comuniquese con el administrador ");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<AccountVo> getAllAccounts() {

        Collection<Account> customers = accountRepository.findAll();

        return customers.stream()
                .map(AccountMapper::toAccountVo)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<AccountVo> getAccountById(Long id) throws AppException {
        try {
            Optional<Account> accountFound = accountRepository.findById(id);
            return accountFound.flatMap(account -> Optional.of(AccountMapper.toAccountVo(account)));
        } catch (Exception e) {
            log.error("Error al obtener la cuenta: {}", e.getMessage());
            throw new AppException("Inconveniente al obtener la cuenta, por favor comuniquese con el administrador ");
        }
    }

    @Override
    public Optional<Account> getAccountByNumber(String numberAccount) throws AppException {
        try {
            return accountRepository.getAccountByAccountNumber(numberAccount);
        } catch (Exception e) {
            log.error("Error al obtener la cuenta: {}", e.getMessage());
            throw new AppException("Inconveniente al obtener la cuenta, por favor comuniquese con el administrador ");
        }
    }

    @Transactional
    @Override
    public void deleteAccount(Long id) throws AppException {
        try {
            accountRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error al eliminar la cuenta: {}", e.getMessage());
            throw new AppException("Inconveniente al procesar la cuenta, por favor comuniquese con el administrador ");
        }
    }
}
