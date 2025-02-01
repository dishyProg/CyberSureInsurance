package com.insurance.models.services;

import com.insurance.data.entities.UserEntity;
import com.insurance.data.repositories.UserRepository;
import com.insurance.models.dto.UserDTO;
import com.insurance.models.dto.mappers.UserMapper;
import com.insurance.models.exceptions.DuplicateEmailException;
import com.insurance.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email, " + username + " not found"));
    }

    @Override
    public UserDTO getById(Long id) {

        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow();

        return userMapper.toDTO(userEntity);
    }

    @Override
    public void create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword())) throw new PasswordsDoNotEqualException();

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserEntity newUser = userMapper.toEntity(user);

        newUser.setAdmin(isAdmin);

        try {
            userRepository.save(newUser);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public void edit(UserDTO user) {
        UserEntity fetchedUser = userRepository
                .findById(user.getUserId())
                .orElseThrow();

        fetchedUser.setFirstName(user.getFirstName());
        fetchedUser.setLastName(user.getLastName());
        fetchedUser.setEmail(user.getEmail());
        fetchedUser.setPhoneNumber(user.getPhoneNumber());
        fetchedUser.setStreetAndNumber(user.getStreetAndNumber());
        fetchedUser.setCity(user.getCity());
        fetchedUser.setZipCode(user.getZipCode());
        fetchedUser.setCountry(user.getCountry());

        userRepository.save(fetchedUser);
    }

    @Override
    public UserEntity findByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow();
    }

    @Override
    public void remove(String email) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent())
            userRepository.delete(optionalUser.get());
        else throw new RuntimeException("User not found");
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        List<UserEntity> filteredUsers = users.stream()
                .filter(user -> !user.isAdmin())
                .toList();

        return filteredUsers.stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public void removeById(Long id) {
        UserEntity fetchedEntity = userRepository
                .findById(id)
                .orElseThrow();

        userRepository.delete(fetchedEntity);
    }
}
