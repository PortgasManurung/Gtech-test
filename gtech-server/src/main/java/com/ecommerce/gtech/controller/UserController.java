package com.ecommerce.gtech.controller;

import com.ecommerce.gtech.models.dto.ChangePasswordRequest;
import com.ecommerce.gtech.models.dto.CustomResponse;
import com.ecommerce.gtech.models.dto.EmailRequest;
import com.ecommerce.gtech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/forgot-password")
    public ResponseEntity<CustomResponse> PostForgotPassword(@RequestBody EmailRequest request){
        return new ResponseEntity(new CustomResponse(userService.resetPassword(request.getEmail()),null), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<CustomResponse> PostChangePassword(@RequestBody ChangePasswordRequest request){
        return new ResponseEntity(new CustomResponse(userService.changePassword(request),null), HttpStatus.OK);
    }
}
