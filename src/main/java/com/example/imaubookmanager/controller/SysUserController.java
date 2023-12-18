package com.example.imaubookmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.pojo.vo.*;
import com.example.imaubookmanager.service.SysMenuImpl;
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
    @Autowired
    SysMenuImpl sysMenuImpl;

    @PostMapping("/addUser")

    @PreAuthorize("hasAuthority('user:add')" )
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
    //用户注册
    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisteVO registeVO){
        try {
            int inserCount =  sysUserImpl.register(registeVO);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "注册成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

    }

    @PostMapping("/updateUser")
//    @PreAuthorize("hasAuthority('system:user:edit')")
    public ResponseResult updateUser(@RequestBody UpdateUserVO updateUserVO) {
        try {
            if (updateUserVO.getId() == null || updateUserVO.getUserName() == null || updateUserVO.getPhoneNumber() == null || updateUserVO.getEmail() == null
                    || updateUserVO.getUserName().isEmpty() || updateUserVO.getPhoneNumber().isEmpty() || updateUserVO.getEmail().isEmpty()) {
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "参数不能为空");
            }


            int inserCount =  sysUserImpl.updateUser(updateUserVO);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "更新成功",sysUserImpl.selectUserById(updateUserVO.getId()));
            }
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
        }

    }

    @GetMapping("/deleteUser")
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
    @PostMapping("/deleteUsers")
    public ResponseResult deleteUsers(@RequestBody BatchIdsVO batchIdsVO) {
        try {
            System.out.println("====");
            System.out.println(batchIdsVO.getIds());
            int deleteCount = sysUserImpl.deleteUsers(batchIdsVO.getIds());
            if (deleteCount > 0) {
                return new ResponseResult(HttpStatus.OK.value(), "批量删除成功");
            }
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "批量删除失败或用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "批量删除失败");
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
    @GetMapping ("/selectMenuByUserId")
    public ResponseResult selectMenuByUserId() {
        try {

            return new ResponseResult(HttpStatus.OK.value(), "查询成功", sysMenuImpl.selectMenuByUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }

    }
}
