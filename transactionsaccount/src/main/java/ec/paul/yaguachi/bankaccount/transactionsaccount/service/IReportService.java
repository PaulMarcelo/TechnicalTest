package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.ReportResponseVo;

import java.util.Collection;
/**
 * Class IReportService
 *
 * @author Paul Yaguachi
 */
public interface IReportService {
    Collection<ReportResponseVo> getReportByClientIdDate(Long clienteid, String fecha) throws AppException;
}
