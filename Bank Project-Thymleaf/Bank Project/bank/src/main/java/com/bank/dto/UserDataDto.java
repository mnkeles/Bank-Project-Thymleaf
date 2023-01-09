package com.bank.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserDataDto {   // User kayıt etmek için oluşturulan DTO

    @NotBlank
    @Size(min=6,max= 20)
    private String userName;

    @NotBlank
    @Email(message = "email not valid")  // email formatına uygun olup olmadığını kontrol eder
    private String email;

    @NotBlank
    @Size(min=8,max=25)
    private String password;
}
