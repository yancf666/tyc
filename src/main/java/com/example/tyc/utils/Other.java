package com.example.tyc.utils;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title Other
 * @Author ycf
 * @Date: 2023-10-31 10:11
 * @Version: 1.0
 */

public class Other {
    public static String getText(String longText,String matchText) {
        String value = "";
        Pattern patterngxf = Pattern.compile(matchText);
        Matcher matchergxf = patterngxf.matcher(longText);


        if (matchergxf.find()) {
            value = matchergxf.group(1);
//            System.out.println(value);
        }
        return value;
    }


    public static void Continue() {
        System.out.print("前往浏览器登录后，在此输入任意字符回车键继续爬取：");
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext()) {
            System.out.println("继续");
        }
        scan.close();
    }
}
