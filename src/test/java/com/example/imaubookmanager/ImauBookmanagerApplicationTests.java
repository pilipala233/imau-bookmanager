package com.example.imaubookmanager;

import com.example.imaubookmanager.dao.SysUserDao;
import com.example.imaubookmanager.pojo.SysUserPojo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ImauBookmanagerApplicationTests {
    @Autowired
    SysUserDao sysUserDao;
    @Test
    void contextLoads() {
       BCryptPasswordEncoder passeWord = new BCryptPasswordEncoder();
       passeWord.matches("12345","$2a$10$PUgSzvkTwWegp3NlwfS3qOYD6SOjceLY4pUlwNXHhgyC8M/0dw.3C");


       System.out.println(passeWord.encode("12345"));
        System.out.println(passeWord.encode("12345"));

    }

}
