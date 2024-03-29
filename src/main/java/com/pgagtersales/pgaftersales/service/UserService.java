package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.shared.dto.ChangePasswordDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ApiResponse createUser(UserDto userDto);
    UserDto getUser(String username);
    ApiResponse getUserbyUserId(String userId);
    ApiResponse getAllUsers();
    ApiResponse changeUserPassword(ChangePasswordDto dto);
    ApiResponse updateUser(UserDto dto);

    ApiResponse deleteUser(UserDto dto);
}
