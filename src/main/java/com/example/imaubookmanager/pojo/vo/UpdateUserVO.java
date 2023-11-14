package com.example.imaubookmanager.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserVO {

    private Long id;


    private String userName;


    private String nickName;


    private String password;



    private String email;


    private String phoneNumber;


    private String sex;


}
