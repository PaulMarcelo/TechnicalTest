package ec.paul.yaguachi.bankaccount.transactionsaccount.service;

import ec.paul.yaguachi.bankaccount.transactionsaccount.exception.AppException;
import ec.paul.yaguachi.bankaccount.transactionsaccount.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static ec.paul.yaguachi.bankaccount.transactionsaccount.common.AppConstant.URL_CUSTOMER;

/**
 * Class ExternalService
 *
 * @author Paul Yaguachi
 */
@Service
public class ExternalService implements IExternalService {

    @Lazy
    @Autowired
    @Qualifier("restTemplateCustomer")
    private  RestTemplate restTemplate;

    @Override
    public CustomerVo getCustomerById(Long clienteid) throws AppException {
        String path = URL_CUSTOMER + clienteid;
        return restTemplate.getForEntity(path, CustomerVo.class).getBody();
    }
}
