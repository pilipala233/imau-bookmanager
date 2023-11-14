package com.example.imaubookmanager.controller;

import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {
    @Autowired
    private LoginServiceImpl loginServcie;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUserPojo user){
        return loginServcie.login(user);
    }
}
