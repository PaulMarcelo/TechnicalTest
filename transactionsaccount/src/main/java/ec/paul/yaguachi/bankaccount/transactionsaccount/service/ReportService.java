package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Account;
import ec.paul.yaguachi.bankaccount.transactionsaccount.model.Transaction;
import ec.paul.yaguachi.bankaccount.transactionsaccount.repository.IAccountRepository;
import ec.paul.yaguachi.bankaccount.transactionsaccount.repository.ITransactionRepository;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.CustomerVo;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.ReportResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static ec.paul.yaguachi.bankaccount.transactionsaccount.common.AppConstant.DEPOSIT;
import static ec.paul.yaguachi.bankaccount.transactionsaccount.common.AppConstant.WITHDRAWAL;
/**
 * Class ReportService
 *
 * @author Paul Yaguachi
 */
@Lazy
@Service
@Slf4j
public class ReportService implements IReportService {


    @Lazy
    @Autowired
    private IExternalService externalService;

    @Lazy
    @Autowired
    private IAccountRepository accountRepository;

    @Lazy
    @Autowired
    private ITransactionRepository transactionRepository;

    @Override
    public Collection<ReportResponseVo> getReportByClientIdDate(Long clienteid, String fecha) throws AppException {
        try {

            Collection<ReportResponseVo> colResp = new ArrayList<>();
            Date[] dates = this.getDatesFromString(fecha);

            if (dates.length != 2) {
                throw new AppException("Invalido formato de rango de fechas");
            }

            CustomerVo customerVo = externalService.getCustomerById(clienteid);

            Collection<Account> colAccount = accountRepository.getAccountByCustomerId(customerVo.getId());

            for (Account item : colAccount) {
                Collection<Transaction> colTransaccion = transactionRepository.getTransactionByAccountId(item.getId(),
                        dates[0], dates[1]);
                for (Transaction itemTra : colTransaccion) {
                    ReportResponseVo resp = new ReportResponseVo();
                    resp.setDate(this.formatDateToString(itemTra.getDate()));
                    resp.setCustomer(customerVo.getName());
                    resp.setNumberAccount(item.getAccountNumber());
                    resp.setType(item.getAccountType());
                    resp.setInitialBalance(item.getInitialBalance());
                    resp.setStatus(item.getStatus());
                    Double movementAmount = 0.0;
                    if (itemTra.getTransactionType().equalsIgnoreCase(WITHDRAWAL)) {
                        movementAmount = -1 * itemTra.getAmount();
                    } else if (itemTra.getTransactionType().equals(DEPOSIT)) {
                        movementAmount = itemTra.getAmount();
                    }
                    resp.setMovementAmount(movementAmount);
                    resp.setAvailableBalance(itemTra.getBalance());
                    colResp.add(resp);
                }

            }

            return colResp;

        } catch (AppException e) {
            log.error("Error de aplicación: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado : {}", e.getMessage(), e);
            throw new AppException("Inconveniente al obtener el reporte. Por favor, comuníquese con el administrador.");
        }
    }

    /**
     * @param dateString
     * @return
     * @throws ParseException
     */
    private Date[] getDatesFromString(String dateString) throws ParseException {
        String[] dates = dateString.split(":");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date startDate = dateFormat.parse(dates[0]);
        Date endDate = dateFormat.parse(dates[1]);

        return new Date[]{startDate, endDate};
    }

    private String formatDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }


}
