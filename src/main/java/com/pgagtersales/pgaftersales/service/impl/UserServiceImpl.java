package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.UserService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserDto returnValue = new UserDto();
        if (userRepository.findByUsername(userDto.getUsername())!=null) {
            throw new UserServiceException("user already exist","user already exist");
        }
        userDto.setUserId(utils.generateUserId(20));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()).toString());
        userDto.setActivated(true);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        UserEntity saveUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(saveUser, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUser(String username) {
        UserDto returnValue = new UserDto();
         UserEntity userEntity = userRepository.findByUsername(username);
         if (userEntity == null) throw new UserServiceException("invalid username: "+username,username+": does not exist");
         BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserbyUserId(String userId) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s);
        if (userEntity == null) throw new UserServiceException("invalid login credentials","user: "+s+" not founnd");
        return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
