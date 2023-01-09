package com.bank.dto;

import com.bank.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDto {  // Response dönerken password dönmek hatalı bir durum olacağı ğiçin  böyle bir response tanımladık

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}