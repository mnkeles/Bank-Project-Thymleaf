package com.bank.service.impl;

import com.bank.dto.*;
import com.bank.mapper.MoneyTransferDtoConverter;
import com.bank.model.Account;
import com.bank.model.MoneyTransfer;
import com.bank.model.Process;
import com.bank.repository.MoneyTransferRepository;
import com.bank.service.AccountService;
import com.bank.service.MoneyTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MoneyTransferServiceImpl implements MoneyTransferService {
    private final MoneyTransferRepository moneyTransferRepository;
    private final AccountService accountService;

    private final MoneyTransferDtoConverter moneyTransferDtoConverter;

    @Override
    public List<MoneyTransferDto> getAllMoneyProcess() {
        List<MoneyTransfer> moneyTransferList = moneyTransferRepository.findAll();
        List<MoneyTransferDto> moneyTransferDtoList = new ArrayList<>();
        for (MoneyTransfer transfer : moneyTransferList) {
            moneyTransferDtoList.add(moneyTransferDtoConverter.toMoneyTransferDto(transfer));

        }
        moneyTransferRepository.saveAll(moneyTransferList);
        return moneyTransferDtoList;
    }

    @Override
    public MoneyTransferDto moneyTransfer(MoneyTransferDto moneyTransferDto) throws Exception {
        Account account = accountService.getAccountById(moneyTransferDto.getFromId());
        Account account1 = accountService.getAccountById(moneyTransferDto.getToId());
        MoneyTransfer moneyTransfer = MoneyTransfer.builder().fromId(moneyTransferDto.getFromId()).toId(moneyTransferDto.getToId())
                .amount(moneyTransferDto.getAmount()).process(Process.transfer).build();

        if (account == null || account.equals("") || account1 == null || account1.equals("") || account.getCurrency() != account1.getCurrency()) {
            return null;
        }

        if (account.getBalance() <= moneyTransferDto.getAmount()) {
            return  null;
        }
        account1.setBalance(account1.getBalance() + moneyTransferDto.getAmount());
        account.setBalance(account.getBalance() - moneyTransferDto.getAmount());

        accountService.saveAccount(account);
        accountService.saveAccount(account1);
        moneyTransferRepository.save(moneyTransfer);


        return moneyTransferDtoConverter.toMoneyTransferDto(moneyTransfer);

    }

    @Override
    public MoneyTransferDto addMoney(MoneyTransferDto moneyTransferDto) throws Exception {
        Account account = accountService.getAccountById(moneyTransferDto.getFromId());
        MoneyTransfer moneyTransfer = MoneyTransfer.builder().fromId(moneyTransferDto.getFromId()).toId(moneyTransferDto.getFromId()).amount(moneyTransferDto.getAmount()).process(Process.add).build();
        if (account == null || account.equals((""))) {
            return null;
        }
        account.setBalance(account.getBalance() + moneyTransferDto.getAmount());
        accountService.saveAccount(account);
        moneyTransferRepository.save(moneyTransfer);

        return moneyTransferDtoConverter.toMoneyTransferDto(moneyTransfer);
    }


    @Override
    public MoneyTransferDto withdrawMoney(MoneyTransferDto moneyTransferDto) throws Exception {
        Account account = accountService.getAccountById(moneyTransferDto.getFromId());
        MoneyTransfer moneyTransfer = MoneyTransfer.builder().fromId(moneyTransferDto.getFromId()).toId(moneyTransferDto.getFromId()).amount(moneyTransferDto.getAmount()).process(Process.reduce).build();
        if (account == null || account.equals((""))) {
            return null;
        }

        if (account.getBalance() >= moneyTransferDto.getAmount()) {

            account.setBalance(account.getBalance() - moneyTransferDto.getAmount());
            accountService.saveAccount(account);
        }
        moneyTransferRepository.save(moneyTransfer);

        return moneyTransferDtoConverter.toMoneyTransferDto(moneyTransfer);
    }
}
