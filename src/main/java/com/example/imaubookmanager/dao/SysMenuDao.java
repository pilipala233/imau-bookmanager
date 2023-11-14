package com.example.imaubookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imaubookmanager.pojo.SysMenuPojo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseMapper<SysMenuPojo> {
    List<String> selectPermsByUserId(Long id);
}
