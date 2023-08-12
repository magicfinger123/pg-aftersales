package com.pgagtersales.pgaftersales.service.impl;

import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.UserService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.ChangePasswordDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public ApiResponse createUser(UserDto userDto) {
        UserDto returnValue = new UserDto();

        userDto.setUserId(utils.generateId(20));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()).toString());
        userDto.setActivated(true);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setRole(userDto.getRole().toLowerCase());
        UserEntity saveUser = userRepository.save(userEntity);
        BeanUtils.copyProperties(saveUser, returnValue);
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
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
    public ApiResponse getUserbyUserId(String userId) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) throw new UserServiceException("invalid userid: "+userId,userId+": does not exist");
        BeanUtils.copyProperties(userEntity, returnValue);
        returnValue.setPassword("");
        returnValue.setEncryptedPassword("");
        returnValue.setUserPic("");
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        apiResponse.responseEntity = ResponseEntity.ok(returnValue);
        return apiResponse;
    }
    @Override
    public ApiResponse getAllUsers() {
        List<UserDto> returnedValue = new ArrayList<>();
        try {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<UserEntity> teamPage = userRepository.findAll(pageable);
            List<UserEntity> teamList = teamPage.getContent();
            for (UserEntity team : teamList) {
                UserDto userDto = new UserDto();
                BeanUtils.copyProperties(team, userDto);
                returnedValue.add(userDto);
            }
            ApiResponse apiResponse = responseBuilder.successfulResponse();
            apiResponse.responseEntity = ResponseEntity.ok(returnedValue);
            return apiResponse;
        } catch (Exception e) {
            throw new UserServiceException("Unable to create schedule","something went wrong: "+e.getLocalizedMessage());
        }
    }

    @Override
    public ApiResponse changeUserPassword(ChangePasswordDto dto) {
        UserEntity user = userRepository.findByUsername(dto.getUsername());
        if (user == null){
            throw new UserServiceException("username incorrect", "username incorrect");
        }
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(dto.getPassword()).toString());
        user.setPassword(dto.getPassword());
        UserEntity saveUser = userRepository.save(user);
        if (saveUser==null){
            throw new UserServiceException("unable to change password", "could not change password");
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("Password Successfully changed").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s);
        if (userEntity == null) throw new UserServiceException("invalid login credentials","user: "+s+" not founnd");
        return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
