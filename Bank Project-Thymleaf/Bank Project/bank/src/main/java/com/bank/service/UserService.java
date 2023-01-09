package com.bank.service;

import com.bank.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    List<User> getAll();

    String signin(String username, String password);

    String signup(User user, boolean isAdmin);

    void delete(String username);

    User search(String username);

   // User whoami(HttpServletRequest req);

    String refresh(String username);
}
