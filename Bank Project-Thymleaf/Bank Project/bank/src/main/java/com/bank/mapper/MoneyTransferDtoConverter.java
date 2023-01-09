package com.bank.mapper;

import com.bank.dto.MoneyTransferDto;
import com.bank.model.MoneyTransfer;
import org.springframework.stereotype.Component;

@Component
public class MoneyTransferDtoConverter {
    public MoneyTransferDto toMoneyTransferDto(MoneyTransfer moneyTransfer){

        return MoneyTransferDto.builder().id(moneyTransfer.getId()).fromId(moneyTransfer.getFromId())
                .toId(moneyTransfer.getToId()).amount(moneyTransfer.getAmount())
                .process(moneyTransfer.getProcess()).build();
    }
}
