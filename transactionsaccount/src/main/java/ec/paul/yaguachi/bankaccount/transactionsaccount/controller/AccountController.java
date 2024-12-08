package ec.paul.yaguachi.bankaccount.transactionsaccount.controller;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import ec.paul.yaguachi.bankaccount.transactionsaccount.service.IAccountService;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.AccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * Class AccountController
 *
 * @author Paul Yaguachi
 */
@Lazy
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Lazy
    @Autowired
    private IAccountService accountService;


    @GetMapping
    public ResponseEntity<Collection<AccountVo>> getAllAccounts() {
        Collection<AccountVo> customers = accountService.getAllAccounts();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Object> createAccount(@RequestBody AccountVo account) {
        try {
            accountService.saveAccount(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAccountById(@PathVariable Long id) {
        try {
            Optional<AccountVo> account = accountService.getAccountById(id);
            return account.<ResponseEntity<Object>>map(accountVo -> new ResponseEntity<>(accountVo, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>("Cuenta no encontrado", HttpStatus.NOT_FOUND));
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccount(@PathVariable Long id, @RequestBody AccountVo accountVo) {
        try {
            Optional<AccountVo> existingAccount = accountService.getAccountById(id);
            if (existingAccount.isPresent()) {
                accountVo.setId(id);
                accountService.saveAccount(accountVo);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        try {
            Optional<AccountVo> account = accountService.getAccountById(id);
            if (account.isPresent()) {
                accountService.deleteAccount(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/getAccountByNumber/{numberAccount}")
    public ResponseEntity<Object> getAccountByNumber(@PathVariable String numberAccount) {
        try {
            Optional<Account> account = accountService.getAccountByNumber(numberAccount);
            return account.<ResponseEntity<Object>>map(value ->
                    new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>("Cuenta no encontrado", HttpStatus.NOT_FOUND));
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
