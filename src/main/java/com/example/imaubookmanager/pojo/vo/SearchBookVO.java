package com.example.imaubookmanager.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookVO {
    private int pageNum;
    private int pageSize;
    private String keyWord;
}
