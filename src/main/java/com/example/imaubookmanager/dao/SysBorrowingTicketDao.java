package com.example.imaubookmanager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.imaubookmanager.pojo.SysBorrowingTicketPojo;
import com.example.imaubookmanager.pojo.vo.selectTicketByPageRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SysBorrowingTicketDao extends BaseMapper<SysBorrowingTicketPojo> {


    IPage<selectTicketByPageRespVO> selectTicketByPage(IPage<selectTicketByPageRespVO> iPage, @Param("userid") Long userid);
}