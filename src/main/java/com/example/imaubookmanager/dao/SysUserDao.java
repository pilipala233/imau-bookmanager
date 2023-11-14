package com.example.imaubookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imaubookmanager.pojo.SysUserPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUserPojo> {
}
