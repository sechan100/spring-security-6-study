package com.secu.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AccountDto {
    private Integer Id;
    private String username;
    private String password;
    private String email;
    private String age;
    private String role;
}
