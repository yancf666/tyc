package com.example.tyc;

import com.example.tyc.pojo.QYSource;
import com.example.tyc.sercive.SourceService;
import com.example.tyc.utils.Other;
import jakarta.annotation.Resource;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title Data
 * @Author ycf
 * @Date: 2023-11-02 14:23
 * @Version: 1.0
 */
@SpringBootTest
public class GetDataByQCC {
    @Resource
    SourceService service;
    @Test
    void te() {

        //搜索信息，必须大于四个字
        String key = "南昌 信息";

        int j = 0;

        // 设置ChromeDriver的路径，根据你的实际路径进行设置
        System.setProperty("webdriver.chrome.driver", "D://desktop/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:8998");

        // 创建ChromeDriver实例
        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.tianyancha.com");

        try {
            for (int i = 0; i < 60; i++) {

                Thread.sleep( 1000); // 等待30秒
                System.out.println(60-i+"秒后开始执行，请登录");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        for (int i = 1; i <= 500; i++) {

            String mainurl = "https://www.tianyancha.com/search?key="+key.replace(" ","+")+"&pageNum="+i;

            // 打开网页
            driver.get(mainurl);

            System.out.println(" 搜索"+mainurl);
            // 等待一段时间（可选）
            try {
                Thread.sleep(5 * 1000); // 等待30秒
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String pageSource = driver.getPageSource();
            //用正则表达式获取网页地址
            List<String> extractedURLs = new ArrayList<>();

            Pattern pattern = Pattern.compile("https://www\\.tianyancha\\.com/company/([^.]+)\" target=\"_blank\"");
            Matcher matcher = pattern.matcher(pageSource);

            while (matcher.find()) {
                String extractedURL = "https://www.tianyancha.com/company/" + matcher.group(1)+"/past" ;
                extractedURLs.add(extractedURL);
            }
            for (String url : extractedURLs) {
                j++;
//                System.out.println("查看公司详细：  "+url);
                QYSource qySource = new QYSource();
                driver.get(url);
                try {
                    Thread.sleep(4 * 1000); // 等待页面加载完成
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String h5text = driver.getPageSource();
                //获取企业名称  --直接在标题里面取
                String qymc = Jsoup.parse(h5text).title().replace("历史信息 - 天眼查", "");
                // 学院、学校、大学、银行   直接过掉下一个
                if (qymc.contains("学院") || qymc.contains("学校") || qymc.contains("大学") || qymc.contains("银行")) {
                    System.out.println(qymc+"包含学院、学校、大学、银行 直接跳过");
                    continue;
                }



                //获取历史高消费
                String lsxzgxf = Other.getText(h5text,"历史限制消费令</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+","");;
                //获取被执行人
                String lsbzxr  = Other.getText(h5text,"历史被执行人</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+","");
                String lssxbzxr  = Other.getText(h5text,"历史失信被执行人</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+","");
                //限制高消费
                String xzxfl  = Other.getText(h5text,">限制消费令</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+","");

                lsxzgxf="".equals(lsxzgxf) ? "0" : lsxzgxf;
                lsbzxr="".equals(lsbzxr) ? "0" : lsbzxr;
                lssxbzxr="".equals(lssxbzxr) ? "0" : lssxbzxr;

                //如果高消费为空那就继续，限制高消费的话直接下一个
                if (!xzxfl.equals("")) {
                    System.out.println(url+  "此企业被限制，直接跳过，次数"+xzxfl);
                    continue;
                }
                //电话号码
                String photo = "";
                try {
                    // 查找指定 class 的元素
                    WebElement element = driver.findElement(By.className("index_detail-tel__fgpsE"));
                    // 获取元素的文本内容
                    photo = element.getText();
                    if (!photo.equals("")) {
                        photo=photo.substring(0, 1).equals("1") ? photo : "";
                    }
                } catch (Exception e) {
                    System.out.println("获取号码异常");
                }



                String zjrq = "";
                if (!lsbzxr.equals("0")) {
                    zjrq = Other.getText(h5text, "<td class=\"\">立案日期</td><td class=\"\">案号</td><td class=\"\">执行标的</td><td class=\"\">执行法院</td><td class=\"\">操作</td></tr></thead><tbody><tr><td>1</td><td>(\\d{4}-\\d{2}-\\d{2})</td><td>");
                }

                //被限制了，登录再继续    有历史执行数量，但是日期取不到
                if ((!lsbzxr.equals("0")) && ("".equals(zjrq))) {
                    Scanner scan = new Scanner(System.in);
                    System.out.print("前往浏览器登录后，在此输入任意字符回车键继续爬取：");
                    if (scan.hasNext()) {
                        System.out.println("继续");
                    }
                    scan.close();
                }

                qySource.setQymc(qymc);
                qySource.setPhoto(photo);
                qySource.setZjlarq(zjrq);
                qySource.setLsbzxr(lsbzxr);
                qySource.setUrl(url);
                qySource.setLsxzbgxf(lsxzgxf);
                qySource.setLssxbzxr(lssxbzxr);
                if (service.existence(url) == 0) {
                    service.saveDate(qySource);
                } else {
                    System.out.println(url+"已存在数据库内，直接跳过");
                }
                System.out.println(j+"  链接{"+url+"}\t    企业名称{"+qymc+"} \t    历史限制消费令{"+lsxzgxf+"}  \t 历史被执行人{"+lsbzxr+"}   历史失信被执行人{"+lssxbzxr+"}     \t 最近一次被执行日期{"+zjrq+"}   \t   电话号码{"+photo+"}");


            }

        }



    }
}
