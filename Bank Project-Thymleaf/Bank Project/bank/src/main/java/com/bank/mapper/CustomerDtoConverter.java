package com.bank.mapper;

import com.bank.dto.CustomerDto;
import com.bank.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    public CustomerDto toCustomerDto(Customer customer){

       return  CustomerDto.builder().customerId(customer.getCustomerId()).name(customer.getName())
               .dateOfBirth(customer.getDateOfBirth()).city(customer.getCity()).address(customer.getAddress()).build();
    }
    public Customer toCustomer(CustomerDto customerDto){
        return Customer.builder().customerId(customerDto.getCustomerId()).name(customerDto.getName()).dateOfBirth(customerDto.getDateOfBirth())
                .city(customerDto.getCity()).address(customerDto.getAddress()).build();
    }
}
