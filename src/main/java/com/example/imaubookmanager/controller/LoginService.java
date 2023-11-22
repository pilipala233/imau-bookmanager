package com.example.imaubookmanager.controller;

import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginService {
    @Autowired
    private LoginServiceImpl loginServcie;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUserPojo user){

        try{
            return loginServcie.login(user);
        }catch (Exception e){
            e.printStackTrace();

            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

    }

    @GetMapping("/api/logout")

    public ResponseResult logout(){
        try{
            return loginServcie.logout();
        }catch (Exception e){
            e.printStackTrace();

            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

    }
}
