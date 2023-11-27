package com.example.imaubookmanager.service;

import com.example.imaubookmanager.dao.SysMenuDao;
import com.example.imaubookmanager.pojo.SysMenuPojo;
import com.example.imaubookmanager.pojo.vo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysMenuImpl {
    @Autowired
    SysMenuDao sysMenuDao;

    //根据用户id查询权限
    public List<SysMenuPojo> selectMenuByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        return sysMenuDao.selectMenuByUserId(userid);
    }

}
