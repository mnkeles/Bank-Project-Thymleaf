package com.bank.service;

import com.bank.dto.CustomerDto;
import com.bank.model.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerDtoById(Integer id);

    Customer getCustomerById(Integer id) throws Exception;
    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto updateCustomer(Integer id, CustomerDto customerDto);

    void deleteCustomer(Integer id);
}
