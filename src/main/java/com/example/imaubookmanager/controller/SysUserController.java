package com.example.imaubookmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.pojo.vo.AddUserVO;
import com.example.imaubookmanager.pojo.vo.SearchUserVO;
import com.example.imaubookmanager.pojo.vo.UpdateUserVO;
import com.example.imaubookmanager.service.SysUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SysUserController {
    @Autowired
    SysUserImpl sysUserImpl;

   @PostMapping("/addUser")


    public ResponseResult addUser(@RequestBody AddUserVO addUserVO){
        try {
            int inserCount =  sysUserImpl.addUser(addUserVO);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "添加成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
        }

    }

    @PostMapping("/updateUser")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public ResponseResult updateUser(@RequestBody UpdateUserVO updateUserVO) {
        try {
            int inserCount =  sysUserImpl.updateUser(updateUserVO);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "更新成功");
            }
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
        }

    }

    @PostMapping("/deleteUser")
    public ResponseResult deleteUser(@RequestParam("id") Long id) {
        try {
            int inserCount =  sysUserImpl.deleteUser(id);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "删除成功");
            }
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "删除失败");
        }

    }
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo() {
        try {
            SysUserPojo data = sysUserImpl.getUserInfo();
            return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }

    }

    @PostMapping("/selectUserById")
    public ResponseResult selectUserById(@RequestParam("id") Long id) {
        SysUserPojo data = null;
        try {
            System.out.println(id);
            data = sysUserImpl.selectUserById(id);
            System.out.println(data);
            return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }

    }


    @PostMapping("selectUserByPage")
    public ResponseResult selectUserByPage(@RequestBody SearchUserVO searchUserVO) {
        try {

            Page<SysUserPojo> data =  sysUserImpl.selectUserByPage(searchUserVO.getPageNum(),searchUserVO.getPageSize(),searchUserVO.getKeyWord());
            System.out.println(data);
            //遍历data
            for (SysUserPojo sysUserPojo : data.getRecords()) {
                System.out.println(sysUserPojo);
            }
            return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }

    }

}
