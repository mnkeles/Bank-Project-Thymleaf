package com.bank.repository;

import com.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    //Delete From account Where account_id='id'
    @Transactional
    @Modifying
    @Query("Delete From Account where accountId=:id")
    void deleteById(Integer id);

    //Account entity tablo adı
    // accountId   class daki account_id kolonuna karşılık gelen değişken adı
    }
