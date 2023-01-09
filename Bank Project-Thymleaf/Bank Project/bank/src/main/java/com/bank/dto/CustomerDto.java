package com.bank.dto;

import com.bank.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private Integer customerId;
    private String name;
    private Integer dateOfBirth;
    private City city;
    private String address;

}
