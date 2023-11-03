package com.example.tyc;

import com.example.tyc.utils.MailUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

/**
 * @title test
 * @Author ycf
 * @Date: 2023-11-02 16:20
 * @Version: 1.0
 */
@SpringBootTest
public class test {

    @Resource
    MailUtils mailUtils;

    @Test
    void te() {
        Scanner scan = new Scanner(System.in);
        System.out.print("前往浏览器登录后，在此输入任意字符回车键继续爬取：");
        if (scan.hasNext()) {
            System.out.println("继续");
        }
        scan.close();
    }
}
