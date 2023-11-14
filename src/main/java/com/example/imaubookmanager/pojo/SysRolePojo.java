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
@TableName("sys_role")
public class SysRolePojo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("role_key")
    private String roleKey;

    @TableField("status")
    private String status;

    @TableField("del_flag")
    private Integer delFlag;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("remark")
    private String remark;

    // 构造方法、Getter 和 Setter 省略


}
