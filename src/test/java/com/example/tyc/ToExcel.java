package com.example.tyc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.tyc.pojo.QYSource;
import com.example.tyc.sercive.SourceService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class ToExcel {

    @Resource
    SourceService service;
    @Test
    void contextLoads() throws FileNotFoundException {
        // 创建一个输出流，指定Excel文件的路径
        String excelFilePath = "D://desktop/output.xlsx";
        FileOutputStream fos = new FileOutputStream(excelFilePath);
        // 创建 ExcelWriter 和 WriteSheet
        ExcelWriter excelWriter = EasyExcel.write(fos, QYSource.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("Sheet1").build();
        List<QYSource> data = service.getqyData();

        excelWriter.write(data, writeSheet);
        // 完成写入操作
        excelWriter.finish();
        // 关闭输出流
        try {
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    void test() {
        QYSource qySource = new QYSource();
        qySource.setQymc("测试");
        service.saveDate(qySource);
    }

}
