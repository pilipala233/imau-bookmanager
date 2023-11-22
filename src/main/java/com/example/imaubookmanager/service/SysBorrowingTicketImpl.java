package com.example.imaubookmanager.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.imaubookmanager.dao.SysBorrowingTicketDao;
import com.example.imaubookmanager.pojo.SysBookPojo;
import com.example.imaubookmanager.pojo.SysBorrowingTicketPojo;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.pojo.vo.LoginUser;
import com.example.imaubookmanager.pojo.vo.selectTicketByPageRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service

public class SysBorrowingTicketImpl {
    @Autowired
    private SysBorrowingTicketDao sysBorrowingTicketDao;

    @Autowired
    private SysBookImpl sysBookImpl;

    @Autowired
    private SysUserImpl sysUserImpl;

    @Transactional(rollbackFor = Exception.class)
    //新增借閱單
    public int addBorrowingTicket(long bookId, long userId) {
        //先查询图书剩余量
        SysBookPojo book = sysBookImpl.getBookById((int)bookId);

        if(book.getCount()>0){
//            SysUserImpl sysuserImpl = new SysUserImpl();
            SysUserPojo user = sysUserImpl.selectUserById(userId);
            System.out.println(user.getUserName());
            //再新增借閱單
            SysBorrowingTicketPojo borrowingTicket = new SysBorrowingTicketPojo();
            borrowingTicket.setBookId(bookId);
            borrowingTicket.setUserId(userId);
            // 设置借阅日期为当天
            LocalDate rentDate = LocalDate.now();
            borrowingTicket.setRentDate(java.sql.Date.valueOf(rentDate));

            // 设置应归还日期为借阅日期后的三个月
            LocalDate planReturnDate = rentDate.plusMonths(3);
            borrowingTicket.setPlanReturnDate(java.sql.Date.valueOf(planReturnDate));



            borrowingTicket.setCreateTime(java.sql.Date.valueOf(rentDate));
            borrowingTicket.setUserName(user.getUserName());
            borrowingTicket.setBookName(book.getName());
            int count = sysBorrowingTicketDao.insert(borrowingTicket);
            //再修改图书剩余量
            book.setCount(book.getCount()-1);
            sysBookImpl.updateBook(book);
            return count;

        }else {
            return 0;
        }




    }

    //分页查询借阅单
    public IPage<selectTicketByPageRespVO> selectTicketByPage(int pageNum, int pageSize) {
        // 构造分页对象
       // Page<SysBorrowingTicketPojo> page = new Page<>(pageNum, pageSize);
        // 构造查询条件
//        QueryWrapper<SysBorrowingTicketPojo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.and(wrapper -> wrapper
//                .like("user_name", keyWord)
//                .or()
//                .like("book_name", keyWord)
//
//        );
//        // 调用 MyBatis-Plus 提供的分页查询方法
//        Page<SysBorrowingTicketPojo> data = sysBorrowingTicketDao.selectPage(page, queryWrapper);
//        return data;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        IPage<selectTicketByPageRespVO> pageResult = sysBorrowingTicketDao.selectTicketByPage(new Page<>(pageNum, pageSize),userid);
        return pageResult;







    }

}
