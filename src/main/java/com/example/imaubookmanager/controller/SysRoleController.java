package com.example.imaubookmanager.controller;

import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysRolePojo;
import com.example.imaubookmanager.service.SysRoleImpl;
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
public class SysRoleController {

    @Autowired
    private SysRoleImpl sysRoleImpl;

    @GetMapping("/findAllRole")
    public ResponseResult findAllRole(){
        try {

            List<SysRolePojo> temp= sysRoleImpl.findAllRole();
            Map<Integer, String> resultMap = new HashMap<>();
            for (SysRolePojo sysRolePojo : temp) {
                resultMap.put(Math.toIntExact(sysRolePojo.getId()), sysRolePojo.getName());
            }
            return new ResponseResult(HttpStatus.OK.value(), "查詢成功",resultMap);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查詢失败");
        }

    }
}
