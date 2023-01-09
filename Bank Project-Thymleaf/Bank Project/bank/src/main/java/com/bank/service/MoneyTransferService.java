package com.bank.service;

import com.bank.dto.MoneyTransferDto;

import java.util.List;

public interface MoneyTransferService {

    MoneyTransferDto moneyTransfer(MoneyTransferDto moneyTransferDto) throws Exception;

    MoneyTransferDto withdrawMoney(MoneyTransferDto moneyTransferDto) throws Exception;

    MoneyTransferDto addMoney(MoneyTransferDto moneyTransferDto) throws Exception;

    List<MoneyTransferDto> getAllMoneyProcess();
}
