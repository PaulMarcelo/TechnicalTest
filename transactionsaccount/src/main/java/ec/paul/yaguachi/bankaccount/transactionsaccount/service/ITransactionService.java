package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Transaction;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.MovementRequestVo;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.TransactionVo;

import java.util.Collection;
import java.util.Optional;
/**
 * Class ITransactionService
 *
 * @author Paul Yaguachi
 */
public interface ITransactionService {
    void saveTransaction(TransactionVo transaction) throws AppException;

    void saveTransactionMov(MovementRequestVo transaction) throws AppException;

    Collection<TransactionVo> getAllTransactions();

    Optional<TransactionVo> getTransactionById(Long id) throws AppException;

    void deleteTransaction(Long id) throws AppException;

}
