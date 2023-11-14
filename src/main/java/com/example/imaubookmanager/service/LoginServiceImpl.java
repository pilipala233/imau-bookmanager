package com.example.imaubookmanager.service;

import com.example.imaubookmanager.pojo.ResponseResult;
import com.example.imaubookmanager.pojo.SysUserPojo;
import com.example.imaubookmanager.pojo.vo.LoginUser;
import com.example.imaubookmanager.untils.JwtUtil;
import com.example.imaubookmanager.untils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl  {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
   public ResponseResult login(SysUserPojo user){
       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
       //TODO 报错怎么处理
       Authentication authenticate;
       try {
           authenticate = authenticationManager.authenticate(authenticationToken);
       }catch (Exception e){

           throw e;
       }

       if(Objects.isNull(authenticate)){
           throw new RuntimeException("用户名或密码错误");
       }
       //使用userid生成token
       LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
       String userId = loginUser.getUser().getId().toString();
       String jwt = JwtUtil.createJWT(userId);
       //authenticate存入redis
       redisCache.setCacheObject("login:"+userId,loginUser);
       //把token响应给前端
       HashMap<String,String> map = new HashMap<>();
       map.put("token",jwt);
       return new ResponseResult(200,"登陆成功",map);
    }


    public ResponseResult logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
        };
}
