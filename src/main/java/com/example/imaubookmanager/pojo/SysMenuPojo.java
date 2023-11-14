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
@TableName("sys_menu")
public class SysMenuPojo {
    @TableId(value = "menu_id")
    private Long menuId;

    @TableField("menu_name")
    private  String menuName;


    @TableField("parent_id")
    private  String parentId;

    @TableField("order_num")
    private  String orderNum;

    @TableField("path")
    private String path;

    @TableField("component")
    private String component;

    @TableField("query")
    private String query;

    @TableField("is_frame")
    private Integer isFrame;

    @TableField("is_cache")
    private Integer isCache;

    @TableField("menu_type")
    private String menuType;

    @TableField("visible")
    private String visible;

    @TableField("status")
    private String status;

    @TableField("perms")
    private String perms;

    @TableField("icon")
    private String icon;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("remark")
    private String remark;


}
