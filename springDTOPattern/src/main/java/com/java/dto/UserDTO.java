package com.java.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Integer id;

    @Size(min = 3, message = "First Name must be grater than 3 character.")
    private String firstName;
    @Size(min = 3, message = "Last Name must be grater than 3 character.")
    private String lastName;
    @Email
    private String emailAddress;
    @Size(min = 10,max = 10,message = "Phone No. must be 10 character.")
    private String phone;
}
