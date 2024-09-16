package com.ata.account.dto;

import com.ata.account.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public AccountCustomerDto convertToAccountCustomerDto(Customer from) {
        if (from == null) {
            return new AccountCustomerDto("", "", "");
        }
        return new AccountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }
}
