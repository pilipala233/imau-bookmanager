package com.example.imaubookmanager.service;

import com.example.imaubookmanager.dao.SysRoleDao;
import com.example.imaubookmanager.pojo.SysRolePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleImpl {
    @Autowired
    private SysRoleDao sysRoleDao;
    public List<SysRolePojo> findAllRole() {
        return sysRoleDao.selectList(null);
    }
}
