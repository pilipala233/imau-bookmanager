package com.example.imaubookmanager.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor

@AllArgsConstructor

public class selectTicketByPageRespVO {
    private  String KeyWord;
    //这里是借书单的id
    private Integer id;


    private String isbn;


    private String name;


    private String author;


    private Integer categoryId;

    private String detail;


    private Integer status;
    //这里是书的类型name
    private String type;


    private String url;


    private Integer count;


    private Date planReturnDate;


    private Date rentDate;



    private Date returnDate;


    private int isContinue;


    private Long userId;


    private int todoStatus;


    private int approverId;


    private Date createTime;


    private String userName;






}
