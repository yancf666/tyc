package com.example.tyc.utils;

import com.example.tyc.pojo.QYSource;
import com.example.tyc.sercive.SourceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @title SourceSave
 * @Author ycf
 * @Date: 2024-08-30 14:58
 * @Version: 1.0
 */
@Service
public class SourceSave {
    @Resource
    SourceService service;
    public void checkSave(QYSource source) {
        String str = source.getLsbzxr() +
                source.getSfjx() +
                source.getFlss() +
                source.getLsflss() +
                source.getKtgg() +
                source.getLaxx() +
                source.getLslaxx() +
                source.getLsxzcf() +
                source.getLshbcf();

        if (str.equals("000000000")) {
            System.out.println("所有均为空，直接跳过");
            return;
        }



        //已存在数据库不保存
        if (service.existence(source.getUrl()) == 0) {
            System.out.println("保存"+source);

            service.saveDate(source);
        } else {
            System.out.println(source.getUrl() + "已存在数据库内，直接跳过");
        }

        //保存

    }
}
