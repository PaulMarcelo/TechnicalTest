package ec.paul.yaguachi.bankaccount.customerperson.controller;

import ec.paul.yaguachi.bankaccount.customerperson.exception.AppException;
import ec.paul.yaguachi.bankaccount.customerperson.service.ICustomberService;
import ec.paul.yaguachi.bankaccount.customerperson.vo.CustomerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * Class CustomerController Rest Services
 *
 * @author Paul Yaguachi
 */

@Lazy
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Lazy
    @Autowired
    private ICustomberService customerService;

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerVo customer) {
        try {
            CustomerVo savedCustomer = customerService.saveCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<CustomerVo>> getAllCustomers() {
        Collection<CustomerVo> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        try {
            Optional<CustomerVo> customer = customerService.getCustomerById(id);
            return customer.<ResponseEntity<Object>>map(value ->
                    new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() ->
                    new ResponseEntity<>("Cliente no encontrado", HttpStatus.NOT_FOUND));
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody CustomerVo customer) {
        try {
            Optional<CustomerVo> existingCustomer = customerService.getCustomerById(id);
            if (existingCustomer.isPresent()) {
                customer.setId(id);
                CustomerVo updatedCustomer = customerService.saveCustomer(customer);
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
        try {
            Optional<CustomerVo> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                customerService.deleteCustomer(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (AppException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
