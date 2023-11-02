package com.example.tyc;

import com.example.tyc.utils.MailUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        mailUtils.sendErrMail("失败");
    }
}
