package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.InsufficientBalanceException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.mapper.TransacctionMapper;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Transaction;
import ec.paul.yaguachi.bankaccount.transactionsaccount.repository.IAccountRepository;
import ec.paul.yaguachi.bankaccount.transactionsaccount.repository.ITransactionRepository;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.MovementRequestVo;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.TransactionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static ec.paul.yaguachi.bankaccount.transactionsaccount.common.AppConstant.DEPOSIT;
import static ec.paul.yaguachi.bankaccount.transactionsaccount.common.AppConstant.WITHDRAWAL;
/**
 * Class TransactionService
 *
 * @author Paul Yaguachi
 */
@Lazy
@Service
@Slf4j
public class TransactionService implements ITransactionService {

    @Lazy
    @Autowired
    private ITransactionRepository transactionRepository;

    @Lazy
    @Autowired
    private IAccountRepository accountRepository;



    @Override
    public void saveTransactionMov(MovementRequestVo transaction) throws AppException {
        try {
            int operation = this.getOperation(transaction);
            if (operation == 0) {
                throw new AppException("Transaccion no valida");
            }

            Account account = accountRepository.getAccountByAccountNumber(transaction.getAccountNumber())
                    .orElseThrow(() -> new AppException("Cuenta no encontrada, no se puede realizar la transacción"));

            if (account.getInitialBalance() == 0) {
                throw new InsufficientBalanceException();
            }

            Double currentBalance = transactionRepository.findTopByAccountId(account.getId())
                    .map(Transaction::getBalance)
                    .orElse(account.getInitialBalance());

            Double newBalance = currentBalance + (operation * transaction.getAmount());

            Transaction newTransaction = new Transaction();
            newTransaction.setDate(new Date());
            newTransaction.setTransactionType(transaction.getTransactionType());
            newTransaction.setAmount(transaction.getAmount());
            newTransaction.setBalance(newBalance);
            newTransaction.setAccountId(account.getId());

            transactionRepository.save(newTransaction);

//            account.setInitialBalance(newBalance);
//            accountRepository.save(account);

        } catch (AppException e) {
            log.error("Error de aplicación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado al guardar la transacción: {}", e.getMessage(), e);
            throw new AppException("Inconveniente al guardar la transacción. Por favor, comuníquese con el administrador.");
        }
    }

    private int getOperation(MovementRequestVo transaction) {
        if (transaction.getTransactionType().equalsIgnoreCase(WITHDRAWAL)) {
            return -1;
        } else if (transaction.getTransactionType().equals(DEPOSIT)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void saveTransaction(TransactionVo transactionIn) throws AppException {
        try {
            Transaction transaction = TransacctionMapper.toTransaction(transactionIn);
            transactionRepository.save(transaction);
        } catch (Exception e) {
            log.error("Error al guardar la transaccion: {}", e.getMessage());
            throw new AppException("Inconveniente al guardar la transaccion, por favor comuniquese con el administrador ");
        }
    }


    @Override
    public Collection<TransactionVo> getAllTransactions() {

        Collection<Transaction> customers = transactionRepository.findAll();

        return customers.stream()
                .map(TransacctionMapper::toTransactionVo)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionVo> getTransactionById(Long id) throws AppException {
        try {
            Optional<Transaction> transactionFound = transactionRepository.findById(id);
            return transactionFound.flatMap(transaction -> Optional.of(TransacctionMapper.toTransactionVo(transaction)));
        } catch (Exception e) {
            log.error("Error al obtener la transaccion: {}", e.getMessage());
            throw new AppException("Inconveniente al obtener la transaccion, por favor comuniquese con el administrador ");
        }
    }

    @Override
    public void deleteTransaction(Long id) throws AppException {
        try {
            transactionRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error al eliminar la transaccion: {}", e.getMessage());
            throw new AppException("Inconveniente al procesar la transaccion, por favor comuniquese con el administrador ");
        }
    }


}
