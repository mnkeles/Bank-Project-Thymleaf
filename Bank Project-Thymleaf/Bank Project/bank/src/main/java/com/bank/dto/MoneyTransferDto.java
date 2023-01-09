package com.bank.dto;

import com.bank.model.Process;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoneyTransferDto {

    private Integer id;
    private Integer fromId;
    private Integer toId;
    private Double amount;
    private Process process;

}
