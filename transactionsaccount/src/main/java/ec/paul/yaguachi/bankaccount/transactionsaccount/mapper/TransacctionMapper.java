package ec.paul.yaguachi.bankaccount.transactionsaccount.mapper;

import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Transaction;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.TransactionVo;

/**
 * Class TransacctionMapper
 *
 * @author Paul Yaguachi
 */
public class TransacctionMapper {

    /**
     *
     * @param transaction
     * @return
     */
    public static TransactionVo toTransactionVo(Transaction transaction) {
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setId(transaction.getId());
        transactionVo.setDate(transaction.getDate());
        transactionVo.setTransactionType(transaction.getTransactionType());
        transactionVo.setAmount(transaction.getAmount());
        transactionVo.setBalance(transaction.getBalance());
        transactionVo.setAccountId(transaction.getAccountId());
        return transactionVo;
    }

    /**
     *
     * @param transactionVo
     * @return
     */
    public static Transaction toTransaction(TransactionVo transactionVo) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionVo.getId());
        transaction.setDate(transactionVo.getDate());
        transaction.setTransactionType(transactionVo.getTransactionType());
        transaction.setAmount(transactionVo.getAmount());
        transaction.setBalance(transactionVo.getBalance());
        transaction.setAccountId(transactionVo.getAccountId());
        return transaction;
    }
}
