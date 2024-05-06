package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String s1 = bCryptPasswordEncoder.encode("1h29hfas");
        System.out.println(s1);
    }

    @Test
    void testpassword() {
        //工作因子,里认信是10,最小信是4,最大值是31,信越大运其速度越慢
        PasswordEncoder encoder = new BCryptPasswordEncoder(4);
        //明文:"password"
        //密文:result,即使明文密码相同,每次生成的密文也不一致
        String result = encoder.encode("password");
        System.out.println(result);
        //密码校验
        Assert. isTrue(encoder.matches( "password",result),"密码不一致");
    }
}
