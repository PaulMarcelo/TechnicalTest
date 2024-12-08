package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.CustomerVo;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.ReportResponseVo;
/**
 * Class IExternalService
 *
 * @author Paul Yaguachi
 */
public interface IExternalService {
    CustomerVo getCustomerById(Long clienteid) throws AppException;
}
