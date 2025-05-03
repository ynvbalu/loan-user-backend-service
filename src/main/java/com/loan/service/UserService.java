package com.loan.service;

import com.loan.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO getUserByUsername(String username);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    long countUsers();
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    boolean isUserOOO(Long id);
    boolean isUserOOOByUsername(String username);
} 