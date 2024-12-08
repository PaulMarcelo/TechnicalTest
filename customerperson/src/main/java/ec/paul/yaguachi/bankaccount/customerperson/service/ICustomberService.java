package ec.paul.yaguachi.bankaccount.customerperson.service;

import ec.paul.yaguachi.bankaccount.customerperson.exception.AppException;
import ec.paul.yaguachi.bankaccount.customerperson.model.Customer;
import ec.paul.yaguachi.bankaccount.customerperson.vo.CustomerVo;

import java.util.Collection;
import java.util.Optional;

/**
 * Class ICustomberService
 *
 * @author Paul Yaguachi
 */
public interface ICustomberService {
    CustomerVo saveCustomer(CustomerVo customervo) throws AppException;

    Collection<CustomerVo> getAllCustomers();

    Optional<CustomerVo> getCustomerById(Long id) throws AppException;

    void deleteCustomer(Long id) throws AppException;
}
