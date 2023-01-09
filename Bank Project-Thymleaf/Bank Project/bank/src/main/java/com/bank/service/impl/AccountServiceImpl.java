package com.bank.service.impl;

import com.bank.dto.*;
import com.bank.exception.EntityNotFoundException;
import com.bank.mapper.AccountDtoConverter;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.repository.AccountRepository;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountDtoConverter accountDtoConverter;

    private final CustomerService customerService;


    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> accountDtoList = new ArrayList<>();
        for (Account account : accountList) {
            accountDtoList.add(accountDtoConverter.toAccoutDto(account));
        }
        return accountDtoList;
    }

    @Override
    public AccountDto getAccountDtoById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);

        return optionalAccount.map(accountDtoConverter::toAccoutDto).orElseThrow(() -> new EntityNotFoundException("Account",id));
    }

    @Override
    public Account getAccountById(Integer id) throws Exception {

        return accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Account",id));
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) throws Exception {
        Customer customer = customerService.getCustomerById(accountDto.getCustomerId());
        //CustomerDto customerDto = customerService.getCustomerDtoById(createAccountRequest.getCustomerId());
        if (customer.getCustomerId() == null) {
            return null;
        }
        Account account = new Account(accountDto.getBalance(), accountDto.getCity(), accountDto.getCurrency(), customer);
        accountRepository.save(account);

        return accountDtoConverter.toAccoutDto(account);
    }

    @Override
    public AccountDto updateAccount(Integer id, AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount == null || optionalAccount.equals("")) {
            throw  new EntityNotFoundException("Account",id);
        } else {
            optionalAccount.ifPresent(account -> {
                account.getCustomer().setCustomerId(accountDto.getCustomerId());
                account.setCity(accountDto.getCity());
                account.setCurrency(accountDto.getCurrency());
                accountRepository.save(account);
            });
        }
        return optionalAccount.map(accountDtoConverter::toAccoutDto).orElseThrow(()->new EntityNotFoundException("Account",id));
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);

    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }


}
