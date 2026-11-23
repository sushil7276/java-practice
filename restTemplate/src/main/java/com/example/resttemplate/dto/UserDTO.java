package com.example.resttemplate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String fName;
    private String lName;
    private String emailAddress;
    private String phone;
}
