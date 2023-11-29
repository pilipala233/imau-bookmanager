package com.example.imaubookmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.dao.SysBorrowingTicketDao;
import com.example.imaubookmanager.pojo.SysBookPojo;
import com.example.imaubookmanager.pojo.SysBorrowingTicketPojo;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.pojo.vo.AddBorrowingVO;
import com.example.imaubookmanager.pojo.vo.LoginUser;
import com.example.imaubookmanager.pojo.vo.SelectTicketByPageRespVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

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

            if (Integer.parseInt(user.getUserType()) == 1){
                borrowingTicket.setTodoStatus(1);
            }

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


    @Transactional(rollbackFor = Exception.class)
    public int addBatchBorrowingTickets(List<AddBorrowingVO> addBorrowingVOList) {
        int successCount = 0;

        for (AddBorrowingVO addBorrowingVO : addBorrowingVOList) {
            long bookId = addBorrowingVO.getBookId();
            long userId = addBorrowingVO.getUserId();

            SysBookPojo book = sysBookImpl.getBookById((int) bookId);

            if (book.getCount() > 0) {
                SysUserPojo user = sysUserImpl.selectUserById(userId);

                SysBorrowingTicketPojo borrowingTicket = new SysBorrowingTicketPojo();
                borrowingTicket.setBookId(bookId);
                borrowingTicket.setUserId(userId);

                LocalDate rentDate = LocalDate.now();
                borrowingTicket.setRentDate(java.sql.Date.valueOf(rentDate));

                LocalDate planReturnDate = rentDate.plusMonths(3);
                borrowingTicket.setPlanReturnDate(java.sql.Date.valueOf(planReturnDate));

                if (Integer.parseInt(user.getUserType()) == 1) {
                    borrowingTicket.setTodoStatus(1);
                }

                borrowingTicket.setCreateTime(java.sql.Date.valueOf(rentDate));
                borrowingTicket.setUserName(user.getUserName());
                borrowingTicket.setBookName(book.getName());

                int count = sysBorrowingTicketDao.insert(borrowingTicket);

                book.setCount(book.getCount() - 1);
                sysBookImpl.updateBook(book);

                if (count > 0) {
                    successCount++;
                }
            }
        }

        return successCount;
    }


    //分页查询借阅单
    public IPage<SelectTicketByPageRespVO> selectTicketByPage(int pageNum, int pageSize,String keyWord) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        IPage<SelectTicketByPageRespVO> pageResult = sysBorrowingTicketDao.selectTicketByPage(new Page<>(pageNum, pageSize),userid,keyWord);
        return pageResult;


    }



    public IPage<SelectTicketByPageRespVO> selectApprovalTicketByPage(int pageNum, int pageSize) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        loginUser.getUser().getUserType();
        if(Integer.parseInt(loginUser.getUser().getUserType())==2){


            IPage<SelectTicketByPageRespVO> pageResult = sysBorrowingTicketDao.selectApprovalTicketByPage(new Page<>(pageNum, pageSize),userid);
            return pageResult;
        }else{
            return new Page<>();
        }



    }
    //归还
    @Transactional(rollbackFor = Exception.class)
    public int returnBook(int id) {
//        //先查询借阅单
        SysBorrowingTicketPojo borrowingTicket = sysBorrowingTicketDao.selectById(id);
//        //再修改借阅单
//        LocalDate returnDate = LocalDate.now();
//        borrowingTicket.setReturnDate(java.sql.Date.valueOf(returnDate));
//        borrowingTicket.setIsContinue(2);
//        int count = sysBorrowingTicketDao.updateById(borrowingTicket);
        sysBorrowingTicketDao.deleteById(id);
        //再修改图书剩余量
        SysBookPojo book = sysBookImpl.getBookById(Math.toIntExact(borrowingTicket.getBookId()));
        book.setCount(book.getCount()+1);
        int count =sysBookImpl.updateBook(book);
        return count;
    }

    //批量归还
    @Transactional(rollbackFor = Exception.class)
    public int returnBooks(Long[] ids) {
        int count = 0;

        for (Long id : ids) {
                int tempcount = returnBook(Math.toIntExact(id));
                count += tempcount;

        }

        return count;
    }




    //续借

    public int continueBook(int id) {    // 先查询借阅单
        SysBorrowingTicketPojo borrowingTicket = sysBorrowingTicketDao.selectById(id);

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 设置新的计划还书日期和是否续借
        LocalDate newPlanReturnDate = currentDate.plusMonths(3);
        borrowingTicket.setPlanReturnDate(java.sql.Date.valueOf(newPlanReturnDate));
        borrowingTicket.setIsContinue(1);

        // 更新借阅单
        int count = sysBorrowingTicketDao.updateById(borrowingTicket);
        return count;
    }


    //借书申请驳回与同意
    public int updateTicketStatus(int id, int status) {
        //先查询借阅单
        SysBorrowingTicketPojo borrowingTicket = sysBorrowingTicketDao.selectById(id);
        //再修改借阅单
        borrowingTicket.setTodoStatus(status);
        int count = sysBorrowingTicketDao.updateById(borrowingTicket);
        return count;
    }


    //超时通知
    //查询还书时间超时的记录
    public Page<SysBorrowingTicketPojo> selectBorrowingTicketByReturnDate(int pageNum, int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        Page<SysBorrowingTicketPojo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysBorrowingTicketPojo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("plan_return_date",LocalDate.now()).eq("user_id",userid);
        // 调用 MyBatis-Plus 提供的分页查询方法
        Page<SysBorrowingTicketPojo> data = sysBorrowingTicketDao.selectPage(page, queryWrapper);
        return data;
    }

    //借书成功通知
    //查询借书表中is_notice 字段为0的记录返回给前端
    public Page<SysBorrowingTicketPojo> selectBorrowingTicketByIsNotice(int pageNum, int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        Page<SysBorrowingTicketPojo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<SysBorrowingTicketPojo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_notice",0).eq("todo_status",1).eq("user_id",userid);;
        // 调用 MyBatis-Plus 提供的分页查询方法
        Page<SysBorrowingTicketPojo> data = sysBorrowingTicketDao.selectPage(page, queryWrapper);
        return data;
    }

    //更新借书表中is_notice 字段为1的记录,代表代办中用户以知晓图书通知
    public int updateBorrowingTicketIsNotice(int id) {
        //先查询借阅单
        SysBorrowingTicketPojo borrowingTicket = sysBorrowingTicketDao.selectById(id);
        //再修改借阅单
        borrowingTicket.setIsNotice(1);
        int count = sysBorrowingTicketDao.updateById(borrowingTicket);
        return count;
    }

    //根据当前用户查询借书单

}
