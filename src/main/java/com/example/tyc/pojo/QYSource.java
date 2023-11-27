package com.example.tyc.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @title ExcelData
 * @Author ycf
 * @Date: 2023-10-31 16:13
 * @Version: 1.0
 */
@Data
public class QYSource {
    @ExcelProperty(value = "序号", index = 0)
    private int id;
    @ExcelProperty(value = "地址", index = 1)
    private String url;
    @ExcelProperty(value = "企业名称", index = 2)
    private String qymc;
    @ExcelProperty(value = "历史限制消费令", index = 3)
    private String lsxzgxf;
    @ExcelProperty(value = "历史失信被执行人", index = 4)
    private String lssxbzxr;
    @ExcelProperty(value = "历史被执行人", index = 5)
    private String lsbzxr;
    @ExcelProperty(value = "最近一次立案日期", index = 6)
    private String zjlarq;
    @ExcelProperty(value = "电话号码", index = 7)
    private String photo;


    @ExcelProperty(value = "注册资金", index = 8)
    private String  zczj;
    @ExcelProperty(value = "司法解析", index = 9)
    private String sfjx;
    @ExcelProperty(value = "法律诉讼", index = 10)
    private String flss;
    @ExcelProperty(value = "历史法律诉讼", index = 11)
    private String lsflss;
    @ExcelProperty(value = "开庭公告", index = 12)
    private String ktgg;
    @ExcelProperty(value = "历史开庭公告", index = 13)
    private String lsktgg;
    @ExcelProperty(value = "立案信息", index = 14)
    private String laxx;
    @ExcelProperty(value = "历史立案信息", index = 15)
    private String lslaxx;
    @ExcelProperty(value = "历史行政处罚", index = 16)
    private String lsxzcf;
    @ExcelProperty(value = "历史环保处罚", index = 17)
    private String lshbcf;
    @ExcelProperty(value = "限制消费令", index = 18)
    private String xzxfl;

}
