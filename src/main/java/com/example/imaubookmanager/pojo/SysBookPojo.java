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
@TableName("sys_book")
public class SysBookPojo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("isbn")
    private String isbn;

    @TableField("name")
    private String name;

    @TableField("author")
    private String author;

    @TableField("category_id")
    private Integer categoryId;

    @TableField("detail")
    private String detail;

    @TableField("cover")
    private String cover;

    @TableField("publishdate")
    private Date publishDate;

    @TableField("publisher")
    private String publisher;

    @TableField("status")
    private Integer status;

    @TableField("type")
    private Integer type;

    @TableField("url")
    private String url;

    @TableField("count")
    private Integer count;

    @TableField("file_name")
    private String fileName;
    public static class ResponseResult {
    }
}
