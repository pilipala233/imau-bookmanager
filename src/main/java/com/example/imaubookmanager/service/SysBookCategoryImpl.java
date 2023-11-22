package com.example.imaubookmanager.service;

import com.example.imaubookmanager.dao.SysBookCatergoryDao;
import com.example.imaubookmanager.pojo.SysBookCategoryPojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysBookCategoryImpl {
    @Autowired
    private SysBookCatergoryDao sysBookCategoryDao;
    //查詢所有書籍類別
    public List<SysBookCategoryPojo> findAllBookCategory() {
        return sysBookCategoryDao.selectList(null);
    }


}
