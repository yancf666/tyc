package com.example.tyc;

import com.example.tyc.pojo.QYSource;
import com.example.tyc.sercive.SourceService;
import com.example.tyc.utils.MailUtils;
import com.example.tyc.utils.Other;
import jakarta.annotation.Resource;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

/**
 * @title Data
 * @Author ycf
 * @Date: 2023-11-02 14:23
 * @Version: 1.0
 */
@SpringBootTest
public class GetDataByQCC4 {

    @Resource
    SourceService service;
    @Resource
    MailUtils mailUtils;

    int j = 0;
    @Test
    void getData()  throws InterruptedException{

        QYSource qySource = new QYSource();

        // 设置ChromeDriver的路径，根据你的实际路径进行设置
        System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");


        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.tianyancha.com/company/2351644028");



        String h5text = driver.getPageSource();
        //获取企业名称  --直接在标题里面取
        String qymc = Jsoup.parse(h5text).title().replace("历史信息 - 天眼查", "");


        System.out.println(h5text);
        //跳验证
        if ("天眼查-商业查询平台_企业信息查询_公司查询_工商查询_企业信用信息系统".equals(qymc)) {
            System.out.println("滑块");
        }
        //浏览器崩溃
        if ("www.tianyancha.com".equals(qymc)) {
            send("浏览器崩溃");

//                    continue;
        }

        // 学院、学校、大学、银行   直接过掉下一个
        if (qymc.contains("学院") || qymc.contains("学校") || qymc.contains("大学") || qymc.contains("银行")) {
            System.out.println(qymc + " 包含学院、学校、大学、银行 直接跳过");
//            continue;
        }



        //获取历史高消费
        String lsxzgxf = Other.getText(h5text, ">历史限制消费令</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //获取被执行人
        String lsbzxr = Other.getText(h5text, ">历史被执行人</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //历史失信被执行人
        String lssxbzxr = Other.getText(h5text, ">历史失信被执行人</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");

        //注册资金
        String zczj = Other.getText(h5text, ">注册资本<i class=\"tic tic-circle-question-o index_icon-name__Khq2a\"></i></td><td width=\"\"><div title=\"5300000万人民币\">(\\S+\\+?)</div></td></tr>");
        //司法解析
        String sfjx = Other.getText(h5text, ">司法解析</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //法律诉讼
        String flss = Other.getText(h5text, ">法律诉讼</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //历史法律诉讼
        String lsflss = Other.getText(h5text, ">历史法律诉讼</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //开庭公告
        String ktgg = Other.getText(h5text, ">开庭公告</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //历史开庭公告
        String lsktgg = Other.getText(h5text, ">历史开庭公告</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //立案信息
        String laxx = Other.getText(h5text, ">立案信息</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //历史立案信息
        String lslaxx = Other.getText(h5text, ">历史立案信息</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //历史行政处罚
        String lsxzcf = Other.getText(h5text, ">历史行政处罚</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //历史环保处罚
        String lshbcf = Other.getText(h5text, ">历史环保处罚</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
        //限制消费令
        String xzxfl = Other.getText(h5text, ">限制消费令</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");




        lsxzgxf = "".equals(lsxzgxf) ? "0" : lsxzgxf;
        lsbzxr = "".equals(lsbzxr) ? "0" : lsbzxr;
        lssxbzxr = "".equals(lssxbzxr) ? "0" : lssxbzxr;

        zczj = "".equals(zczj)?"0":zczj;
        sfjx = "".equals(sfjx)?"0":sfjx;
        flss = "".equals(flss)?"0":flss;
        lsflss = "".equals(lsflss)?"0":lsflss;
        ktgg = "".equals(ktgg)?"0":ktgg;
        lsktgg = "".equals(lsktgg)?"0":lsktgg;
        laxx = "".equals(laxx)?"0":laxx;
        lslaxx = "".equals(lslaxx)?"0":lslaxx;
        lsxzcf = "".equals(lsxzcf)?"0":lsxzcf;
        lshbcf = "".equals(lshbcf)?"0":lshbcf;
        xzxfl = "".equals(xzxfl)?"0":xzxfl;





        //电话号码
        String photo = "";
        try {
            // 查找指定 class 的元素
            WebElement element = driver.findElement(By.className("index_detail-tel__fgpsE"));
            // 获取元素的文本内容
            photo = element.getText();
            if (!photo.equals("")) {
                photo = photo.charAt(0) == '1' ? photo : "";
            }
        } catch (Exception e) {
            //没获取到电话号码，不用处理
        }

        String zjrq = "";
        //最近立案日期，不是和页面一起加载的，要晚于页面，得循环加延时获取，节约时间
        if (!lsbzxr.equals("0")) {
            for (int k = 0; k < 50; k++) {
                sleep(200);
                zjrq  =  Other.getText(driver.getPageSource(), "<td class=\"\">立案日期</td><td class=\"\">案号</td><td class=\"\">执行标的</td><td class=\"\">执行法院</td><td class=\"\">操作</td></tr></thead><tbody><tr><td>1</td><td>(\\d{4}-\\d{2}-\\d{2})</td><td>");
                if (!zjrq.equals("")) {
                    break;
                }
            }
        }

        qySource.setQymc(qymc);
        qySource.setPhoto(photo);
        qySource.setZjlarq(zjrq);
        qySource.setLsbzxr(lsbzxr);
        qySource.setUrl("");
        qySource.setLsxzgxf(lsxzgxf);
        qySource.setLssxbzxr(lssxbzxr);
        qySource.setZczj(zczj);
        qySource.setSfjx(sfjx);
        qySource.setFlss(flss);
        qySource.setLsflss(lsflss);
        qySource.setKtgg(ktgg);
        qySource.setLsktgg(lsktgg);
        qySource.setLaxx(laxx);
        qySource.setLslaxx(lslaxx);
        qySource.setLsxzcf(lsxzcf);
        qySource.setLshbcf(lshbcf);
        qySource.setXzxfl(xzxfl);





        System.out.println("qySource = " + qySource);

    }


    void clear(WebDriver driver) {
        try {

            driver.manage().deleteAllCookies();
            driver.get("chrome://settings/clearBrowserData");
//        driver.findElementByXPath().sendKeys(Keys.ENTER);
            WebElement element = driver.findElement(By.xpath("//settings-ui"));
            element.sendKeys(Keys.TAB);
            element.sendKeys(Keys.ENTER);


            driver.get("chrome://settings/clearBrowserData");
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            WebElement clearData =  (WebElement) jse.executeScript("return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"settings-basic-page\").shadowRoot.querySelector(\"#basicPage > settings-section:nth-child(8) > settings-privacy-page\").shadowRoot.querySelector(\"settings-clear-browsing-data-dialog\").shadowRoot.querySelector(\"#clearBrowsingDataConfirm\")");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", clearData);
        } catch (Exception e) {
            System.out.println("清楚缓存失败");
        }



    }


    /**
     * 跳验证发邮件
     */
    public void send(String msg) throws InterruptedException {
        //修改文件内容并且暂停
        modifyFileContent("stop");
        System.out.println(msg);
        //邮件提醒
        mailUtils.sendErrMail(msg,"619748460@qq.com");
        mailUtils.sendErrMail(msg,"cfyan@ipi-tech.com");

        boolean isStop = true;
        while (isStop) {
            sleep(1000);
            if (getFileContent().contains("start")) {
                isStop=false;
            }
        }
    }

    /**
     * 检查是否要保存
     * @param source
     */
    void checkSave(QYSource source) {
        //历史被执行人为空 不保存
        if (source.getLsbzxr().equals("0")) {
            System.out.println(source.getQymc()+" "+source.getUrl()+" 历史被执行人为空 不保存");
            return;
        }


        //已存在数据库不保存
        if (service.existence(source.getUrl()) == 0) {
            System.out.println("  链接{" + source.getUrl() + "}\t    企业名称{" + source.getQymc() + "} \t    历史限制消费令{" + source.getLsxzgxf() + "}       历史失信被执行人{" + source.getLssxbzxr() + "}        \t 历史被执行人{" + source.getLsbzxr() + "}    \t 最近一次被执行日期{" + source.getZjlarq() + "}   \t   电话号码{" + source.getPhoto() + "}");

            service.saveDate(source);
        } else {
            System.out.println(source.getUrl() + "已存在数据库内，直接跳过");
        }

        //保存

    }


    public String getFileContent() {
        String filePath = "D:\\Desktop\\task.txt";
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }


    public static void modifyFileContent( String newText) {
        String filePath = "D:\\Desktop\\task.txt";
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringBuilder oldContent = new StringBuilder();

            // 读取文件原内容
            while ((line = bufferedReader.readLine()) != null) {
                oldContent.append(line).append("\n");
            }

            bufferedReader.close();
            // 写入文件
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(newText);
            bufferedWriter.close();

//            System.out.println("文件内容已修改！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void test() throws InterruptedException {
        boolean isStop = true;
        while (isStop) {
            sleep(1000);
            System.out.println(getFileContent().contains("start"));
            if (getFileContent().equals("start")) {
                isStop=false;
            }
        }
    }


    @Test
    void tete() {
        String a = "万泰建设集团有限公司（曾用名：重庆万泰建设（集团）有限公司），成立于2006年，重庆丰茂生活服务成员，位于重庆市，是一家以从事房屋建筑业为主的企业。企业注册资本39000万人民币，超过了98%的重庆市同行，实缴资本7000万人民币。通过天眼查大数据分析，万泰建设集团有限公司共对外投资了11家企业，参与招投标项目196次；知识产权方面有商标信息7条；此外企业还拥有行政许可476个。风险方面共发现企业有天眼风险信息1586条；还发现企业有法律诉讼102条，涉案总金额2657.73864万元；涉诉关系729条，开庭公告729条，立案信息51条。";
        System.out.println(Other.getText(a, "企业注册资本(\\S+\\+?)人民币，"));
    }
}
