package com.pgagtersales.pgaftersales.restController;


import com.pgagtersales.pgaftersales.model.response.UserRest;
import com.pgagtersales.pgaftersales.model.resquest.UserSignUpRequest;
import com.pgagtersales.pgaftersales.service.UserService;
import com.pgagtersales.pgaftersales.service.impl.UserServiceImpl;
import com.pgagtersales.pgaftersales.shared.dto.GeneratorDto;
import com.pgagtersales.pgaftersales.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping()
    public UserRest signUpUsers(@RequestBody UserSignUpRequest userSignUpRequest)
    {
        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userSignUpRequest, returnValue);
        UserServiceImpl userService1 = new UserServiceImpl();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userSignUpRequest,userDto);
        UserDto userDto1 = userService.createUser(userDto);
        BeanUtils.copyProperties(userDto1, returnValue);
        return returnValue;
    }

}
