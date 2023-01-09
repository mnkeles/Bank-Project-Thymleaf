package com.bank.controller;

import com.bank.dto.MoneyTransferDto;
import com.bank.service.AccountService;
import com.bank.service.MoneyTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class MoneyTransferController {
    private final MoneyTransferService moneyTransferService;
    private final AccountService accountService;

    @GetMapping("/money/process")
    public String moneyProcess(Model model) {
        model.addAttribute("process", moneyTransferService.getAllMoneyProcess());
        return "moneyProcess";
    }

    @GetMapping("/money/transfer")
    public String moneyTransfer(Model model) {
        model.addAttribute("moneyTransfer", new MoneyTransferDto());
        return "moneyTransfer";
    }

    @PostMapping("money/transfer")
    public String moneyTransfer(@ModelAttribute MoneyTransferDto moneyTransferDto) throws Exception {
        if (moneyTransferDto.getAmount() < 0) {
            return null;
        } else {
            moneyTransferService.moneyTransfer(moneyTransferDto);
        }
        return "redirect:/money/process";
    }

    @GetMapping("/money/add")
    public String moneyAdd(Model model) {
        model.addAttribute("moneyAdd", new MoneyTransferDto());
        return "moneyAdd";
    }

    @PostMapping("money/add")
    public String moneyAdd(@ModelAttribute MoneyTransferDto moneyTransferDto) throws Exception {
        if (moneyTransferDto.getAmount() < 0) {
            return null;
        } else {
            moneyTransferService.addMoney(moneyTransferDto);
        }
        return "redirect:/money/process";
    }

    @GetMapping("/money/withdraw")
    public String moneyWithdraw(Model model) {
        model.addAttribute("moneyWithdraw", new MoneyTransferDto());
        return "moneyWithdraw";
    }

    @PostMapping("money/withdraw")
    public String moneyWithdraw(@ModelAttribute MoneyTransferDto moneyTransferDto) throws Exception {
        if (moneyTransferDto.getAmount() < 0) {
            return null;
        } else {
            moneyTransferService.withdrawMoney(moneyTransferDto);
        }
        return "redirect:/money/process";
    }

}
