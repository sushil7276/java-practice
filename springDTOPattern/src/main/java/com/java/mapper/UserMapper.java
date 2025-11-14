package com.java.mapper;

import com.java.dto.UserDTO;
import com.java.modules.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "email", target = "emailAddress")
    @Mapping(target = "phone", qualifiedByName = "maskPhone")
    UserDTO entityToDto(User user);

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "emailAddress", target = "email")
    User dtoToEntity(UserDTO userDTO);

    @Named("maskPhone")
    static String getPhoneNumber(String phone) {
        return "******" + phone.substring(6);
    }
}
