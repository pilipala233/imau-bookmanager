package com.example.imaubookmanager.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_borrowing_ticket")
public class SysBorrowingTicketPojo {
    @TableId("id")
    private int id;

    @TableField("book_id")
    private Long bookId;

    @TableField("plan_return_date")
    private Date planReturnDate;

    @TableField("rent_date")
    private Date rentDate;


    @TableField("return_date")
    private Date returnDate;

    @TableField("is_continue")
    private int isContinue;

    @TableField("user_id")
    private Long userId;

    @TableField("todo_status")
    private int todoStatus;

    @TableField("approver_id")
    private int approverId;

    @TableField("create_time")
    private Date createTime;

    @TableField("user_name")
    private String userName;

    @TableField("book_name")
    private String bookName;
    @TableField("is_notice")
    private int isNotice;



}
