package com.example.imaubookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.imaubookmanager.pojo.SysBookPojo;
import com.example.imaubookmanager.pojo.SysMenuPojo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysBookDao extends BaseMapper<SysBookPojo> {

}
