package com.bank.service.impl;

import com.bank.dto.CustomerDto;
import com.bank.mapper.CustomerDtoConverter;
import com.bank.exception.EntityNotFoundException;
import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;
import com.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDtoList.add(customerDtoConverter.toCustomerDto(customer));
        }
        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerDtoById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        return optionalCustomer.map(customerDtoConverter::toCustomerDto).orElseThrow(()->new EntityNotFoundException("Customer",id));
    }

    @Override
    public Customer getCustomerById(Integer id) throws Exception {

        return customerRepository.findById(id).orElseThrow(() ->new EntityNotFoundException("Customer",id));
    }


    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder().name(customerDto.getName())
                .dateOfBirth(customerDto.getDateOfBirth()).city(customerDto.getCity()).address(customerDto.getAddress())
                .build();
        customerRepository.save(customer);

        return customerDtoConverter.toCustomerDto(customer);
    }

    @Override
    public CustomerDto updateCustomer(Integer id, CustomerDto customerDto) {
                Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional == null || customerOptional.equals("")) {
            throw  new EntityNotFoundException("Customer",id);
        } else {
            customerOptional.ifPresent(customer -> {
                customer.setName(customerDto.getName());
                customer.setDateOfBirth(customerDto.getDateOfBirth());
                customer.setCity(customerDto.getCity());
                customer.setAddress(customerDto.getAddress());

                customerRepository.save(customer);
            });

        }
        return customerOptional.map(customerDtoConverter::toCustomerDto).orElseThrow(()-> new EntityNotFoundException("Customer",id));
    }

    @Override
    public void deleteCustomer(Integer id) {

        Customer customer = customerRepository.findById(id).get();
        if (customer.getAccounts() == null) {
            customerRepository.deleteById(id);
        } else {

                throw new RuntimeException("Hesabı olan müşteri silinemez");

        }


    }


}
