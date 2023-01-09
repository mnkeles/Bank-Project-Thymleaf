package com.bank.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

}
