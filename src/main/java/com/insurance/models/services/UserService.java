package com.insurance.models.services;

import com.insurance.data.entities.UserEntity;
import com.insurance.models.dto.UserDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDTO getById(Long id);

    void create(UserDTO user, boolean isAdmin);

    void edit(UserDTO user);

    UserEntity findByUsername(String currentUserEmail);

    void remove(String email);

    void removeById(Long id);

    List<UserDTO> getAllUsers();
}
