package ec.paul.yaguachi.bankaccount.customerperson.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Class GlobalExceptionHandler
 *
 * @author Paul Yaguachi
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * handleTypeMismatch
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Formato invalido de parametro.";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
