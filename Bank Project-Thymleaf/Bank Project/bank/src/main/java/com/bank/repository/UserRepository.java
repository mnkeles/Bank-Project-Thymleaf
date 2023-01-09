package com.bank.repository;

import com.bank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    // JPQL
    // exsist ile başlayan JPQL ler boolean döner
    boolean existsByUsername(String username);

    User findByUsername(String username);


    void deleteByUsername(String username);

}
