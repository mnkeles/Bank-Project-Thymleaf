package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="money_transfer")
public class MoneyTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="from_id")
    private Integer fromId;

    @Column(name="to_id")
    private Integer toId;

    @Column(name="amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="process")
    private Process process;

}
