package com.example.imaubookmanager.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.imaubookmanager.dao.SysMenuDao;
import com.example.imaubookmanager.dao.SysUserDao;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.pojo.vo.AddUserVO;
import com.example.imaubookmanager.pojo.vo.LoginUser;
import com.example.imaubookmanager.pojo.vo.UpdateUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserImpl  implements UserDetailsService {
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUserPojo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserPojo::getUserName,username);
        SysUserPojo user = sysUserDao.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //TODO 根据用户查询权限信息 添加到LoginUser中
//        //测试写法
//        List<String> list = new ArrayList<>(Arrays.asList("test"));
        List<String> permissionKeyList =  sysMenuDao.selectPermsByUserId(user.getId());
        return new LoginUser(user,permissionKeyList);
    }
    //添加一个用户
    public int addUser(AddUserVO addUserVO) {
        //获取当前时间


        SysUserPojo sysUserPojo = new SysUserPojo();
        addUserVO.setPassword(passwordEncoder.encode(addUserVO.getPassword()));

        sysUserPojo.setCreateTime(new Date());
        BeanUtils.copyProperties(addUserVO, sysUserPojo);

        int insertCount = sysUserDao.insert(sysUserPojo);
        return insertCount;



    }

    //更新用户信息
    public int updateUser(UpdateUserVO updateUserVO) {
        SysUserPojo sysUser = sysUserDao.selectById(updateUserVO.getId());

        //判断用户名是否存在
        if (sysUser == null) {
            throw new RuntimeException("用户名不存在");
        }
        //获取当前时间
        Date date = new Date();

        sysUser.setUpdateTime(new Date());
        if (updateUserVO.getPassword() != null)
        {
            updateUserVO.setPassword(passwordEncoder.encode(updateUserVO.getPassword()));
        }

        //更新用户信息


        BeanUtils.copyProperties(updateUserVO, sysUser);
        int updateCount = sysUserDao.updateById(sysUser);


        return updateCount;


    }

    //删除用户
    public int deleteUser(Long id) {
        SysUserPojo sysUser = sysUserDao.selectById(id);

        //判断用户名是否存在
        if (sysUser == null) {
            throw new RuntimeException("用户名不存在");
        }
        int deleteCount = sysUserDao.deleteById(id);

        return deleteCount;



    }

    //查询用户信息
    public SysUserPojo selectUserById(Long id) {
        SysUserPojo sysUser = sysUserDao.selectById(id);

        //判断用户名是否存在
        if (sysUser == null) {
            throw new RuntimeException("用户名不存在");
        } else {
            return sysUser;
        }

    }

    //查询分页用户信息
    public Page<SysUserPojo> selectUserByPage(int pageNum, int pageSize, String keyWord) {
        // 构造分页对象
        Page<SysUserPojo> page = new Page<>(pageNum, pageSize);
        // 构造查询条件
        QueryWrapper<SysUserPojo> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper -> wrapper
                .like("user_name", keyWord)
                .or()
                .like("phonenumber", keyWord)
                .or()
                .like("email", keyWord)
        );
        // 调用 MyBatis-Plus 提供的分页查询方法
        Page<SysUserPojo> sysUserPojoPage = sysUserDao.selectPage(page, queryWrapper);
        return sysUserPojoPage;
    }


}
