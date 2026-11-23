package com.example.springdto.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Integer id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min =3, max = 20, message = "First name must be between 3 and 20 characters")
    private String fName;

    @NotBlank(message = "Last name cannot be blank")
    private String lName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String emailAddress;
    private String phone;
}
