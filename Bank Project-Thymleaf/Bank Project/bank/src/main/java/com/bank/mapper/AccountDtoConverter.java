package com.bank.mapper;

import com.bank.dto.AccountDto;
import com.bank.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    public AccountDto toAccoutDto(Account account){
        AccountDto accountDto=AccountDto.builder().accountId(account.getAccountId()).customerId(account.getCustomer().getCustomerId()).balance(account.getBalance())
                .city(account.getCity()).currency(account.getCurrency()).build();

        return accountDto;
    }


}
