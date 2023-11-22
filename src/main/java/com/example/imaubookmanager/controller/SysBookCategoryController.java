package com.example.imaubookmanager.controller;

import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysBookCategoryPojo;
import com.example.imaubookmanager.service.SysBookCategoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SysBookCategoryController {
    @Autowired
    private SysBookCategoryImpl sysBookCategoryImpl;

    @GetMapping("/findAllBookCategory")
    public ResponseResult addUser(){
        try {

            List<SysBookCategoryPojo> temp= sysBookCategoryImpl.findAllBookCategory();
            Map<Integer, String> resultMap = new HashMap<>();
            for (SysBookCategoryPojo sysBookCategoryPojo : temp) {
                  resultMap.put(sysBookCategoryPojo.getId(), sysBookCategoryPojo.getName());
            }
            return new ResponseResult(HttpStatus.OK.value(), "查詢成功",resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查詢失败");
        }

    }
}
