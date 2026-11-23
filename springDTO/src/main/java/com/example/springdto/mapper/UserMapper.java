package com.example.springdto.mapper;

import com.example.springdto.dto.UserDTO;
import com.example.springdto.modules.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id",target = "userId")
    @Mapping(source = "FName",target = "firstName")
    @Mapping(source = "LName",target = "lastName")
    @Mapping(source = "emailAddress",target = "email")
    User toEntity(UserDTO userDTO);

    @InheritInverseConfiguration(name = "toEntity")
    UserDTO toDto(User user);

    @Mapping(target = "userId", ignore = true)
    @Mapping(source = "FName",target = "firstName")
    @Mapping(source = "LName",target = "lastName")
    @Mapping(source = "emailAddress",target = "email")
    void updateEntity(UserDTO userDTO, @MappingTarget User user);

}
