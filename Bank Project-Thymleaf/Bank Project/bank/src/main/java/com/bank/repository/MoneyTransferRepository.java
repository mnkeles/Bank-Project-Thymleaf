package com.bank.repository;

import com.bank.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyTransferRepository extends JpaRepository<MoneyTransfer,Integer> {
}
