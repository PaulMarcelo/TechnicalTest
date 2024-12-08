package ec.paul.yaguachi.bankaccount.customerperson.controller;

import ec.paul.yaguachi.bankaccount.customerperson.exception.AppException;
import ec.paul.yaguachi.bankaccount.customerperson.model.Customer;
import ec.paul.yaguachi.bankaccount.customerperson.service.ICustomberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private ICustomberService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);
        customer.setName("Paul Yaguachi");
    }

    @Test
    void testGetCustomerById_Success() throws AppException {
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(customer));
        ResponseEntity<Object> response = customerController.getCustomerById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Customer retrievedCustomer = (Customer) response.getBody();
        assertEquals("Paul Yaguachi", retrievedCustomer.getName());
        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    void testGetCustomerById_NotFound() throws AppException {
        when(customerService.getCustomerById(999L)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = customerController.getCustomerById(999L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Cliente no encontrado", response.getBody());
        verify(customerService, times(1)).getCustomerById(999L);
    }
}