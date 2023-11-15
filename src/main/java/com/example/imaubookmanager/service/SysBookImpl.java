package com.example.imaubookmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.dao.SysBookDao;
import com.example.imaubookmanager.pojo.SysBookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SysBookImpl {

    @Autowired
    private SysBookDao sysBookDao;
    public int addBook(SysBookPojo book) {


            int insertCount = sysBookDao.insert(book);
            return insertCount;

    }

    public int updateBook(SysBookPojo book) {
        SysBookPojo sysBook = sysBookDao.selectById(book.getId());
        if (sysBook == null) {
            throw new RuntimeException("书籍不存在");
        }
            int updateCount = sysBookDao.updateById(book);
            return updateCount;


    }

    public int deleteBook(Integer id) {
        SysBookPojo book = sysBookDao.selectById(id);
            if (book == null) {
                throw new RuntimeException("书籍不存在");
            }
            int deleteCount = sysBookDao.deleteById(id);
            return  deleteCount;

    }

    public SysBookPojo getBookById(Integer id) {

            SysBookPojo book = sysBookDao.selectById(id);
            //判断是否存在

            if (book == null) {
                throw new RuntimeException("书籍不存在");
            }
            return book;

    }

    public Page<SysBookPojo> getBooksByPage(int pageNum, int pageSize, String keyword) {

            Page<SysBookPojo> page = new Page<>(pageNum, pageSize);
            QueryWrapper<SysBookPojo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("name", keyword)
                    .or().like("author", keyword)
                    .or().like("detail", keyword)
                    .or().like("publisher", keyword);
            // 调用 MyBatis-Plus 提供的分页查询方法
            Page<SysBookPojo> sysBookPojo = sysBookDao.selectPage(page, queryWrapper);
            return sysBookPojo;


    }
}
