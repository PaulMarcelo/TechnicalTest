package ec.paul.yaguachi.bankaccount.customerperson.service;


import ec.paul.yaguachi.bankaccount.customerperson.exception.AppException;
import ec.paul.yaguachi.bankaccount.customerperson.mapper.CustumerMapper;
import ec.paul.yaguachi.bankaccount.customerperson.model.Customer;
import ec.paul.yaguachi.bankaccount.customerperson.repository.ICustomberRepository;
import ec.paul.yaguachi.bankaccount.customerperson.vo.CustomerVo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class CustomberService
 *
 * @author Paul Yaguachi
 */
@Lazy
@Service
@Slf4j
public class CustomberService implements ICustomberService {


    @Lazy
    @Autowired
    private ICustomberRepository customerRepository;


    /**
     * {@inheritDoc}
     */
    @Transactional(rollbackFor = AppException.class)
    @Override
    public CustomerVo saveCustomer(CustomerVo customervo) throws AppException {
        try {
            Customer customer = CustumerMapper.toCustomer(customervo);
            Customer customerResp = customerRepository.save(customer);
            return CustumerMapper.toCustomerVo(customerResp);
        } catch (Exception e) {
            log.error("Error al guardar el cliente: {}", e.getMessage());
            throw new AppException("Inconveniente al guardar el Cliente, por favor comuniquese con el administrador ");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Collection<CustomerVo> getAllCustomers() {
        Collection<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(CustumerMapper::toCustomerVo)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<CustomerVo> getCustomerById(Long id) throws AppException {
        try {
            Optional<Customer> customer = customerRepository.findCustomerById(id);
            if (customer.isPresent()) {
                CustomerVo customerVo = CustumerMapper.toCustomerVo(customer.get());
                return Optional.of(customerVo);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            log.error("Error al obtener el cliente: {}", e.getMessage());
            throw new AppException("Inconveniente al obtener el Cliente, por favor comuniquese con el administrador ");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void deleteCustomer(Long id) throws AppException {
        try {
            customerRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error al eliminar el cliente: {}", e.getMessage());
            throw new AppException("Inconveniente al procesar el Cliente, por favor comuniquese con el administrador ");
        }
    }

}
