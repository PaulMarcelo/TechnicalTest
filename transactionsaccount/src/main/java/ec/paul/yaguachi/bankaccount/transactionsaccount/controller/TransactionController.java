package ec.paul.yaguachi.bankaccount.transactionsaccount.controller;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.InsufficientBalanceException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.service.ITransactionService;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.MovementRequestVo;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.TransactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * Class TransactionController
 *
 * @author Paul Yaguachi
 */
@Lazy
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Lazy
    @Autowired
    private ITransactionService transactionService;


    @PostMapping("/movement")
    public ResponseEntity<Object> createMovement(@RequestBody MovementRequestVo movement) {
        try {
            transactionService.saveTransactionMov(movement);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (InsufficientBalanceException inBalExcp) {
            return new ResponseEntity<>(inBalExcp.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (AppException appExcp) {
            return new ResponseEntity<>(appExcp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<TransactionVo>> getAllTransactions() {
        Collection<TransactionVo> customers = transactionService.getAllTransactions();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody TransactionVo transaction) {
        try {
            transactionService.saveTransaction(transaction);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransactionById(@PathVariable Long id) {
        try {
            Optional<TransactionVo> transaction = transactionService.getTransactionById(id);
            return transaction.<ResponseEntity<Object>>map(transactionVo ->
                    new ResponseEntity<>(transactionVo, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>("Transacion no encontrado", HttpStatus.NOT_FOUND));
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransaction(@PathVariable Long id, @RequestBody TransactionVo transactionVo) {
        try {
            Optional<TransactionVo> existingTransaction = transactionService.getTransactionById(id);
            if (existingTransaction.isPresent()) {
                transactionVo.setId(id);
                transactionService.saveTransaction(transactionVo);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        try {
            Optional<TransactionVo> transactionVo = transactionService.getTransactionById(id);
            if (transactionVo.isPresent()) {
                transactionService.deleteTransaction(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
