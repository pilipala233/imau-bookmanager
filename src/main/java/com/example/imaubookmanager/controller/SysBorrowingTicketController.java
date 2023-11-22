package com.example.imaubookmanager.controller;

import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.vo.AddBorrowingVO;
import com.example.imaubookmanager.pojo.vo.SearchTicketVO;
import com.example.imaubookmanager.service.SysBorrowingTicketImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    //归还

    //续借

    //驳回与同意

    //借书申请（高级用户审批）

    //超时通知

}
