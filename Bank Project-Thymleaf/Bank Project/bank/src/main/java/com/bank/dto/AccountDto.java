package com.bank.dto;

import com.bank.model.City;
import com.bank.model.Currency;
import com.bank.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Integer accountId;

    private Integer customerId;

    private Double balance;

    private City city;

    private Currency currency;
}
