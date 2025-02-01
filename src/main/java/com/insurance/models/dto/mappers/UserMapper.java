package com.insurance.models.dto.mappers;

import com.insurance.data.entities.UserEntity;
import com.insurance.models.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(UserDTO source);

    UserDTO toDTO(UserEntity source);

    void updateUserDTO(UserDTO source, @MappingTarget UserDTO target);
}
