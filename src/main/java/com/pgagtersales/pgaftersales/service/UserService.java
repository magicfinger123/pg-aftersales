package com.pgagtersales.pgaftersales.service;

import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(String username);
    UserDto getUserbyUserId(String userId);
}
