package ec.paul.yaguachi.bankaccount.customerperson.mapper;

import ec.paul.yaguachi.bankaccount.customerperson.model.Customer;
import ec.paul.yaguachi.bankaccount.customerperson.vo.CustomerVo;

/**
 * CustumerMapper
 */

public class CustumerMapper {

    /**
     *
     * @param customer
     * @return
     */
    public static CustomerVo toCustomerVo(Customer customer) {
        CustomerVo customerVo = new CustomerVo();
        customerVo.setId(customer.getId());
        customerVo.setName(customer.getName());
        customerVo.setGender(customer.getGender());
        customerVo.setAge(customer.getAge());
        customerVo.setIdentification(customer.getIdentification());
        customerVo.setAddress(customer.getAddress());
        customerVo.setPhone(customer.getPhone());
        customerVo.setStatus(customer.getStatus());
        customerVo.setPassword(customer.getPassword());

        return customerVo;
    }

    /**
     *
     * @param customerVo
     * @return
     */
    public static Customer toCustomer(CustomerVo customerVo) {
        Customer customer = new Customer();
        customer.setId(customerVo.getId());
        customer.setName(customerVo.getName());
        customer.setGender(customerVo.getGender());
        customer.setAge(customerVo.getAge());
        customer.setIdentification(customerVo.getIdentification());
        customer.setAddress(customerVo.getAddress());
        customer.setPhone(customerVo.getPhone());
        customer.setStatus(customerVo.getStatus());
        customer.setPassword(customerVo.getPassword());
        return customer;
    }
}
