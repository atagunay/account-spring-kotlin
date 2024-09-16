package com.ata.account.service;

import com.ata.account.dto.CustomerDto;
import com.ata.account.dto.CustomerDtoConverter;
import com.ata.account.exception.CustomerNotFoundException;
import com.ata.account.model.Customer;
import com.ata.account.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CustomerServiceTest {
    private CustomerService service;
    private CustomerRepository customerRepository;
    private CustomerDtoConverter customerDtoConverter;

    private Customer generateCustomer() {
        return new Customer("id", "name", "surname", Set.of());
    }

    @BeforeEach
    void setUp() {
        customerRepository = mock(CustomerRepository.class);
        customerDtoConverter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, customerDtoConverter);
    }

    @Test
    public void testFindCustomerById_whenCustomerIdExist_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));

        Customer result = service.findCustomerById("id");
        assertEquals(customer, result);
    }

    @Test
    public void testFindCustomerById_whenCustomerDoesNotExist_shouldThrowCustomerNotFoundException() {
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.findCustomerById("id"));
    }

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = generateCustomer();
        CustomerDto customerDto = new CustomerDto("id", "name", "surname", Set.of());

        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(customerDtoConverter.convert(customer)).thenReturn(customerDto);

        CustomerDto result = service.getCustomerById("id");
        assertEquals(result, customerDto);
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        Mockito.when(customerRepository.findById("id")).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> service.getCustomerById("id"));
        Mockito.verifyNoInteractions(customerDtoConverter);
    }
}