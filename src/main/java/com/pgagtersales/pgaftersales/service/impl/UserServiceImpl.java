package com.pgagtersales.pgaftersales.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.pgagtersales.pgaftersales.controller.AmazonS3Uploader;
import com.pgagtersales.pgaftersales.exceptions.UserServiceException;
import com.pgagtersales.pgaftersales.io.SuccessMessage;
import com.pgagtersales.pgaftersales.io.entity.ReportLogEntity;
import com.pgagtersales.pgaftersales.io.entity.UserEntity;
import com.pgagtersales.pgaftersales.io.messages.NotificationMessages;
import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.ResponseBuilder;
import com.pgagtersales.pgaftersales.repository.ReportLogRepo;
import com.pgagtersales.pgaftersales.repository.UserRepository;
import com.pgagtersales.pgaftersales.service.UserService;
import com.pgagtersales.pgaftersales.shared.Utils;
import com.pgagtersales.pgaftersales.shared.dto.ChangePasswordDto;
import com.pgagtersales.pgaftersales.shared.dto.ReportLogDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    NotificationMessages message;
    @Autowired
    AmazonS3Uploader s3Uploader;

    @Autowired
    ReportLogRepo reportLogRepo;


    @Override
    public ApiResponse createUser(UserDto userDto) {
        UserDto returnValue = new UserDto();
        userDto.setUserId(utils.generateId(20));
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()).toString());
        userDto.setActivated(true);
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setRole(userDto.getRole().toLowerCase());
        userEntity.setUserPic("https://crmspowergen.s3.eu-north-1.amazonaws.com/"+userDto.getUsername()+"."+userDto.getFileExt());
        UserEntity saveUser = userRepository.save(userEntity);
        s3Uploader.upload(userDto.getUserPic(), "crmspowergen", userDto.getUsername()+"."+userDto.getFileExt());
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
         if (!userEntity.getActivated()) throw new UserServiceException("User disabled","user:  has been disabled");
         BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }
    @Override
    public ApiResponse getUserbyUserId(String userId) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) throw new UserServiceException("invalid userid: "+userId,userId+": does not exist");
        if (!userEntity.getActivated()) throw new UserServiceException("User disabled","user:  has been disabled");
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
                if (!team.getActivated()){
                    continue;
                }
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
    public ApiResponse updateUser(UserDto dto) {
        UserDto userDto = dto;
        UserEntity user = userRepository.findByUsername(dto.getUsername());
        if (user == null){
            throw new UserServiceException("username incorrect", "username incorrect");
        }
        if (userDto.getUserPic() == null){
            userDto.setUserPic(user.getUserPic());
        }
        userDto.setEncryptedPassword(user.getEncryptedPassword());
        userDto.setPassword(user.getPassword());
        userDto.setActivated(true);
        BeanUtils.copyProperties(userDto, user);
        UserEntity saveUser = userRepository.save(user);
        if (saveUser==null){
            throw new UserServiceException("unable to update user", "could not update user");
        }
        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("User updated successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        return apiResponse;
    }

    @Override
    public ApiResponse deleteUser(UserDto dto) {
        UserEntity user = userRepository.findByUserId(dto.getUserId());

        if (user == null){
            throw new UserServiceException("user does not exist", "user does not exist");
        }
        user.setActivated(false);
        userRepository.save(user);

        ApiResponse apiResponse = responseBuilder.successfulResponse();
        SuccessMessage successMessage = SuccessMessage.builder().message("User deactivated successfully").build();
        apiResponse.responseEntity = ResponseEntity.ok(successMessage);
        try {
            ReportLogDto reportLogDto = new ReportLogDto();
            reportLogDto.setUserId(message.getUserDetails().getUserId());
            reportLogDto.setDescription(message.getUserDetails().getFirst_name()+ " disabled "+ user.getFirst_name() + " "+ user.getLast_name());
            reportLogDto.setAction("Delete user");
            reportLogDto.setStatus("User deleted");
            reportLogDto.setDate(java.sql.Date.valueOf(utils.getDate()));
            reportLogDto.setTime(utils.getTime());
            ReportLogEntity ent = new ReportLogEntity();
            BeanUtils.copyProperties(reportLogDto,ent);
            reportLogRepo.save(ent);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s);
        if (userEntity == null) throw new UserServiceException("invalid login credentials","user: "+s+" not founnd");
        if (!userEntity.getActivated()) throw new UserServiceException("User disabled","user: "+s+" has been disabled");
        return new User(userEntity.getUsername(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }


}
