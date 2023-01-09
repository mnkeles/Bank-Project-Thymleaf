package com.bank.controller;

import com.bank.dto.CustomerDto;
import com.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/customer")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customerGetAll";
    }

    @GetMapping("/customer/{id}")
    public String getCustomerById(@PathVariable Integer id, Model model) {

        model.addAttribute("customer", customerService.getCustomerDtoById(id));
        return "customerGetById";
    }


    @GetMapping("/customer/create")
    public String createCustomer(Model model) {
        model.addAttribute("customerCreate", new CustomerDto());
        return "customerCreate";
    }

    @PostMapping("/customer/create")
    public String createCustomer(@ModelAttribute CustomerDto customerDto) {
        customerService.createCustomer(customerDto);
        return "redirect:/customer";
    }

    @GetMapping("/customer/update/{id}")
    public String updateCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customerUpdate", customerService.getCustomerDtoById(id));
        return "customerUpdate";
    }


    @PostMapping("/customer/update/{id}")
    public String updateCustomer(@PathVariable Integer id, @ModelAttribute CustomerDto customerDto, Model model) {
        customerService.updateCustomer(id, customerDto);
        return "redirect:/customer";
    }

    @GetMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return "redirect:/customer";
    }

}
