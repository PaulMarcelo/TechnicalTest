package ec.paul.yaguachi.bankaccount.transactionsaccount.exception;
/**
 * Class InsufficientBalanceException
 *
 * @author Paul Yaguachi
 */
public class InsufficientBalanceException extends AppException {
    public InsufficientBalanceException() {
        super("Saldo no disponible.");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
