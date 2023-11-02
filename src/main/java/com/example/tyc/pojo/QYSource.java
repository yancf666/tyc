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
    @ExcelProperty(value = "历史限制高消费次数", index = 3)
    private String lsxzbgxf;
    @ExcelProperty(value = "历史失信被执行人次数", index = 4)
    private String lssxbzxr;
    @ExcelProperty(value = "历史被执行人次数", index = 5)
    private String lsbzxr;
    @ExcelProperty(value = "最近一次立案日期", index = 6)
    private String zjlarq;
    @ExcelProperty(value = "电话号码", index = 7)
    private String photo;

}
