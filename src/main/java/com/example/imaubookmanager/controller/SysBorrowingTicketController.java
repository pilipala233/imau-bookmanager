package com.example.imaubookmanager.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysBorrowingTicketPojo;
import com.example.imaubookmanager.pojo.vo.AddBorrowingVO;
import com.example.imaubookmanager.pojo.vo.SearchTicketVO;
import com.example.imaubookmanager.pojo.vo.SearchUserVO;
import com.example.imaubookmanager.pojo.vo.UpdateTicketStatusVO;
import com.example.imaubookmanager.service.SysBorrowingTicketImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SysBorrowingTicketController {
    @Autowired
    private SysBorrowingTicketImpl sysBorrowingTicketImpl;

    @PostMapping("/addBorrowingTicket")
    public ResponseResult addBorrowingTicket(@RequestBody  AddBorrowingVO addBorrowingVO) {

        try {
            System.out.println(addBorrowingVO.getBookId());
            System.out.println(addBorrowingVO.getUserId());
            int count = sysBorrowingTicketImpl.addBorrowingTicket(addBorrowingVO.getBookId(),addBorrowingVO.getUserId());
            if(count == 1){
                return new ResponseResult(HttpStatus.OK.value(), "添加成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "添加失败");
        }
    }
    //查询当前用户名下的图书记录
    @PostMapping("/selectTicketByPage")
    public ResponseResult selectTicketByPage(@RequestBody SearchTicketVO searchVO) {
        try {

            return new ResponseResult(HttpStatus.OK.value(), "查询成功", sysBorrowingTicketImpl.selectTicketByPage(searchVO.getPageNum(), searchVO.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }
    }
    //查询当前用户下的审批记录
    @PostMapping("/selectApprovalTicketByPage")
    public ResponseResult selectApprovalTicketByPage(@RequestBody SearchTicketVO searchVO) {
        try {

            return new ResponseResult(HttpStatus.OK.value(), "查询成功", sysBorrowingTicketImpl.selectApprovalTicketByPage(searchVO.getPageNum(), searchVO.getPageSize()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }
    }


    //归还
    @PostMapping("/returnBook")
    public ResponseResult returnBook(@RequestParam("id") int id) {
        try {
            int inserCount =  sysBorrowingTicketImpl.returnBook(id);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "归还成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "归还失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "归还失败");
        }

    }

    //续借
    @PostMapping("/continueBook")
    public ResponseResult continueBook(@RequestParam("id") int id) {
        try {
            int inserCount =  sysBorrowingTicketImpl.continueBook(id);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "续借成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "续借失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "续借失败");
        }

    }



    //驳回与同意
    @PostMapping("/updateTicketStatus")
    public ResponseResult updateTicketStatus(@RequestBody UpdateTicketStatusVO updateTicketStatusVO) {
        try {
            int inserCount =  sysBorrowingTicketImpl.updateTicketStatus(updateTicketStatusVO.getId(),updateTicketStatusVO.getStatus());
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "续借成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "续借失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "续借失败");
        }

    }

    @GetMapping("/updateBorrowingTicketIsNotice")
    public ResponseResult updateBorrowingTicketIsNotice(@RequestParam("id") int id) {
        try {
            int inserCount =  sysBorrowingTicketImpl.updateBorrowingTicketIsNotice(id);
            if(inserCount == 1){
                return new ResponseResult(HttpStatus.OK.value(), "更新成功");
            }else{
                return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "更新失败");
        }

    }

    @PostMapping("selectBorrowingTicketByReturnDate")
    public ResponseResult selectBorrowingTicketByReturnDate(@RequestBody SearchUserVO searchUserVO) {
        try {

            Page<SysBorrowingTicketPojo> data =  sysBorrowingTicketImpl.selectBorrowingTicketByReturnDate(searchUserVO.getPageNum(),searchUserVO.getPageSize());
            System.out.println(data);
            //遍历data
            for (SysBorrowingTicketPojo sysBorrowingTicketPojo : data.getRecords()) {
                System.out.println(sysBorrowingTicketPojo);
            }
            return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }

    }

    @PostMapping("selectBorrowingTicketByIsNotice")
    public ResponseResult selectBorrowingTicketByIsNotice(@RequestBody SearchUserVO searchUserVO) {
        try {

            Page<SysBorrowingTicketPojo> data =  sysBorrowingTicketImpl.selectBorrowingTicketByIsNotice(searchUserVO.getPageNum(),searchUserVO.getPageSize());
            System.out.println(data);
            //遍历data
            for (SysBorrowingTicketPojo sysBorrowingTicketPojo : data.getRecords()) {
                System.out.println(sysBorrowingTicketPojo);
            }
            return new ResponseResult(HttpStatus.OK.value(), "查询成功", data);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "查询失败");
        }

    }
}
