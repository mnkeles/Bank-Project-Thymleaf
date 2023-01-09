package com.bank.service;

import com.bank.dto.*;
import com.bank.model.Account;
import com.bank.model.MoneyTransfer;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    AccountDto getAccountDtoById(Integer id);

    Account getAccountById(Integer id) throws Exception;

    AccountDto createAccount(AccountDto accountDto) throws Exception;

    AccountDto updateAccount(Integer id, AccountDto accountDto);

    void deleteAccount(Integer id);

    void saveAccount(Account account);


}
