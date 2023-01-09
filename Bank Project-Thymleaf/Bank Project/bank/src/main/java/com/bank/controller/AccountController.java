package com.bank.controller;


import com.bank.dto.*;
import com.bank.model.Account;
import com.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/account")
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accountGetAll";
    }

    @GetMapping("/account/{id}")
    public String getAccountById(@PathVariable Integer id, Model model) {
        model.addAttribute("account", accountService.getAccountDtoById(id));
        return "accountGetById";
    }

    @GetMapping("/account/create")
    public String createAccount(Model model) {
        model.addAttribute("accountCreate", new AccountDto());
        return "accountCreate";
    }

    @PostMapping("/account/create")
    public String createAccount(@ModelAttribute AccountDto accountDto) throws Exception {
        accountService.createAccount(accountDto);
        return "redirect:/account";
    }

    @GetMapping("/account/update/{id}")
    public String updateAccount(@PathVariable Integer id, Model model) {
        model.addAttribute("accountUpdate", accountService.getAccountDtoById(id));
        return "accountUpdate";
    }

    @PostMapping("/account/update/{id}")
    public String updateAccount(@PathVariable Integer id, @ModelAttribute AccountDto accountDto) {
        accountService.updateAccount(id, accountDto);
        return "redirect:/account";
    }

    @GetMapping("/account/delete/{id}")
    public String deleteAccount(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return "redirect:/account";
    }


}
