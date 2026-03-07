package com.pet.accountsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    @Email(message = "mail cannot be empty")
    private String mail;
    @NotBlank(message = "password cannot be empty")
    private String password;

}
