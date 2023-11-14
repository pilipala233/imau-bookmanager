package com.example.imaubookmanager.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("sys_user")
public class SysUserPojo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_name")
    private String userName;

    @TableField("nick_name")
    private String nickName;

    @TableField("password")
    private String password;

    @TableField("status")
    private String status;

    @TableField("email")
    private String email;

    @TableField("phonenumber")
    private String phoneNumber;

    @TableField("sex")
    private String sex;

    @TableField("avatar")
    private String avatar;

    @TableField("user_type")
    private String userType;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("del_flag")
    private Integer delFlag;
}
