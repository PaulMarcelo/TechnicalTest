package ec.paul.yaguachi.bankaccount.transactionsaccount.controller;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.service.IReportService;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.ReportResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Class ReportController
 *
 * @author Paul Yaguachi
 */
@Lazy
@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Lazy
    @Autowired
    private IReportService reportService;


    @GetMapping
    public ResponseEntity<Object> getReportByClientIdDate(@RequestParam Long clienteId, @RequestParam String fecha) {
        try {
            Collection<ReportResponseVo> report = reportService.getReportByClientIdDate(clienteId, fecha);
            return new ResponseEntity<>(report, HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
