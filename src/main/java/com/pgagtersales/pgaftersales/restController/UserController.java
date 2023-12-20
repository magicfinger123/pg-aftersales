package com.pgagtersales.pgaftersales.restController;


import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import com.pgagtersales.pgaftersales.model.response.UserRest;
import com.pgagtersales.pgaftersales.model.resquest.UserSignUpRequest;
import com.pgagtersales.pgaftersales.service.StorageService;
import com.pgagtersales.pgaftersales.service.UserService;
import com.pgagtersales.pgaftersales.service.impl.UserServiceImpl;
import com.pgagtersales.pgaftersales.shared.dto.ChangePasswordDto;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    StorageService storageService;

    @Autowired
    private
    ApiResponse apiResponse;

    @PostMapping()
    public  ResponseEntity<ApiResponse> signUpUsers(@RequestBody UserSignUpRequest userSignUpRequest)
    {
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userSignUpRequest, returnValue);
        UserServiceImpl userService1 = new UserServiceImpl();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userSignUpRequest,userDto);
        apiResponse  = userService.createUser(userDto);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PostMapping("/changePassword")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordDto dto)
    {
        Long startTime = System.currentTimeMillis();
        apiResponse = userService.changeUserPassword(dto);
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @PutMapping()
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDto dto)
    {
        long startTime = System.currentTimeMillis();
        apiResponse = userService.updateUser(dto);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @PutMapping("/delete")
    public ResponseEntity<ApiResponse> deleteUser(@RequestBody UserDto dto)
    {
        long startTime = System.currentTimeMillis();
        apiResponse = userService.deleteUser(dto);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse> getUserByUsername(@PathVariable  (value = "username") String username)
    {
        return null;
    }
    @GetMapping()
    public ResponseEntity<ApiResponse> getAllUser()
    {
        Long startTime = System.currentTimeMillis();
        apiResponse = userService.getAllUsers();
        Long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = Double.valueOf(duration)/100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/Checklogin")
    public ResponseEntity<ApiResponse> getByUserId(@RequestParam("userId") String userId){
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = userService.getUserbyUserId(userId);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getUserByUserId(@PathVariable String userId){
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse = userService.getUserbyUserId(userId);
        long duration = System.currentTimeMillis()-startTime;
        apiResponse.executionTime = (double) duration /100;
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
