package com.ata.account.service;

import com.ata.account.dto.CustomerDto;
import com.ata.account.dto.CustomerDtoConverter;
import com.ata.account.exception.CustomerNotFoundException;
import com.ata.account.model.Customer;
import com.ata.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer could not find by id: " + id));
    }

}
