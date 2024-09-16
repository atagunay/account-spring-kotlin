package com.ata.account.service;

import com.ata.account.dto.AccountDto;
import com.ata.account.dto.AccountDtoConverter;
import com.ata.account.dto.CreateAccountRequest;
import com.ata.account.model.Account;
import com.ata.account.model.Customer;
import com.ata.account.model.Transaction;
import com.ata.account.model.TransactionType;
import com.ata.account.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, CustomerService customerService,
                          AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

        Account account = new Account("",
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now(),
                customer,
                new HashSet<>());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(null,
                    TransactionType.INITIAL,
                    createAccountRequest.getInitialCredit(),
                    LocalDateTime.now(),
                    account);

            account.getTransactions().add(transaction);
        }

        return accountDtoConverter.convert(accountRepository.save(account));
    }
}