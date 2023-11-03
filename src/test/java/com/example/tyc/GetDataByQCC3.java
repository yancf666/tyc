package com.example.tyc;

import com.example.tyc.pojo.QYSource;
import com.example.tyc.sercive.SourceService;
import com.example.tyc.utils.MailUtils;
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

import java.io.*;
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
public class GetDataByQCC3 {

    @Resource
    SourceService service;
    @Resource
    MailUtils mailUtils;

    @Test
    void getData()  throws InterruptedException{


        // 设置ChromeDriver的路径，根据你的实际路径进行设置
        System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        WebDriver driver = new ChromeDriver(chromeOptions);




        //搜索信息，必须大于四个字
        execution(driver,"深圳 科技");
        execution(driver,"深圳 技术");
        execution(driver,"深圳 实业");
        execution(driver,"深圳 人力");
        execution(driver,"深圳 贸易");
        execution(driver,"深圳 网络");
        execution(driver,"深圳 传媒");
        execution(driver,"深圳 精密");
        execution(driver,"深圳 电子");
        execution(driver,"深圳 文化");
        execution(driver,"广州 信息");
        execution(driver,"广州 科技");
        execution(driver,"广州 技术");
        execution(driver,"广州 实业");
        execution(driver,"广州 人力");
        execution(driver,"广州 贸易");
        execution(driver,"广州 网络");
        execution(driver,"广州 传媒");
        execution(driver,"广州 精密");
        execution(driver,"广州 电子");
        execution(driver,"广州 文化");
        execution(driver,"广州 信息");
        execution(driver,"广东 科技");
        execution(driver,"广东 技术");
        execution(driver,"广东 实业");
        execution(driver,"广东 人力");
        execution(driver,"广东 贸易");
        execution(driver,"广东 网络");
        execution(driver,"广东 传媒");
        execution(driver,"广东 精密");
        execution(driver,"广东 电子");
        execution(driver,"广东 文化");
        execution(driver,"广东 信息");
        execution(driver,"江西 科技");
        execution(driver,"江西 技术");
        execution(driver,"江西 实业");
        execution(driver,"江西 人力");
        execution(driver,"江西 贸易");
        execution(driver,"江西 网络");
        execution(driver,"江西 传媒");
        execution(driver,"江西 精密");
        execution(driver,"江西 电子");
        execution(driver,"江西 文化");
        execution(driver,"江西 信息");
        execution(driver,"南昌 科技");
        execution(driver,"南昌 技术");
        execution(driver,"南昌 实业");
        execution(driver,"南昌 人力");
        execution(driver,"南昌 贸易");
        execution(driver,"南昌 网络");
        execution(driver,"南昌 传媒");
        execution(driver,"南昌 精密");
        execution(driver,"南昌 电子");
        execution(driver,"南昌 文化");
        execution(driver,"南昌 信息");
        execution(driver,"上海 科技");
        execution(driver,"上海 技术");
        execution(driver,"上海 实业");
        execution(driver,"上海 人力");
        execution(driver,"上海 贸易");
        execution(driver,"上海 网络");
        execution(driver,"上海 传媒");
        execution(driver,"上海 精密");
        execution(driver,"上海 电子");
        execution(driver,"上海 文化");
        execution(driver,"上海 信息");
        execution(driver,"北京 科技");
        execution(driver,"北京 技术");
        execution(driver,"北京 实业");
        execution(driver,"北京 人力");
        execution(driver,"北京 贸易");
        execution(driver,"北京 网络");
        execution(driver,"北京 传媒");
        execution(driver,"北京 精密");
        execution(driver,"北京 电子");
        execution(driver,"北京 文化");
        execution(driver,"北京 信息");
        execution(driver,"重庆 科技");
        execution(driver,"重庆 技术");
        execution(driver,"重庆 实业");
        execution(driver,"重庆 人力");
        execution(driver,"重庆 贸易");
        execution(driver,"重庆 网络");
        execution(driver,"重庆 传媒");
        execution(driver,"重庆 精密");
        execution(driver,"重庆 电子");
        execution(driver,"重庆 文化");
        execution(driver,"重庆 信息");
        execution(driver,"成都 科技");
        execution(driver,"成都 技术");
        execution(driver,"成都 实业");
        execution(driver,"成都 人力");
        execution(driver,"成都 贸易");
        execution(driver,"成都 网络");
        execution(driver,"成都 传媒");
        execution(driver,"成都 精密");
        execution(driver,"成都 电子");
        execution(driver,"成都 文化");
        execution(driver,"成都 信息");
    }

    @Test
    void execution(WebDriver driver,String key) throws InterruptedException {

        int j = 0;

        driver.get("https://www.tianyancha.com");

        try {
            for (int i = 0; i < 30; i++) {

                sleep(1000); // 等待30秒 登录
                System.out.println(30 - i + "秒后开始执行，请登录");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //天眼查就250页
        for (int i = 1; i <= 250; i++) {


            String mainurl = "https://www.tianyancha.com/search?key=" + key.replace(" ", "+") + "&pageNum=" + i;

            // 打开网页
            driver.get(mainurl);

            System.out.println(" 搜索" + mainurl);
            // 等待一段时间（可选）

            //7秒没取到页面的数据那在等5秒
            String pageSource = driver.getPageSource();

            //用正则表达式获取网页地址
            List<String> extractedURLs = new ArrayList<>();

            Pattern pattern = Pattern.compile("https://www\\.tianyancha\\.com/company/([^.]+)\" target=\"_blank\"");
            Matcher matcher = pattern.matcher(pageSource);

            while (matcher.find()) {
                String extractedURL = "https://www.tianyancha.com/company/" + matcher.group(1) + "/past";
                extractedURLs.add(extractedURL);
            }
            //账号问题时，直接暂停，修改文件内容后才开始
            if (extractedURLs.size() == 0) {
                //修改文件内容并且暂停
                modifyFileContent("stop");
                System.out.println("获取企业数量为0，请检查浏览器是否为无数据/登录失效");
                //邮件提醒
                mailUtils.sendErrMail("获取企业数量为0，请检查浏览器是否为无数据/登录失效");

                boolean isStop = true;
                while (isStop) {
                    sleep(1000);
                    if (getFileContent().equals("start")) {
                        isStop=false;
                    }
                }
            }
            for (String url : extractedURLs) {
                sleep(1000);
                j++;
                System.out.print(j+": ");
                QYSource qySource = new QYSource();
                driver.get(url);


                //4秒没取到页面的数据那在等4秒
                String h5text = driver.getPageSource();


                //获取企业名称  --直接在标题里面取
                String qymc = Jsoup.parse(h5text).title().replace("历史信息 - 天眼查", "");

                // 学院、学校、大学、银行   直接过掉下一个
                if (qymc.contains("学院") || qymc.contains("学校") || qymc.contains("大学") || qymc.contains("银行")) {
                    System.out.println(qymc + "包含学院、学校、大学、银行 直接跳过");
                    continue;
                }
                //限制高消费
                String xzxfl = Other.getText(h5text, ">限制消费令</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
                //如果高消费为空那就继续，限制高消费的话直接下一个
                if (!xzxfl.equals("")) {
                    System.out.println(url + "此企业被限制高消费，直接跳过，限制高消费次数:" + xzxfl);
                    continue;
                }


                //获取历史高消费
                String lsxzgxf = Other.getText(h5text, "历史限制消费令</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
                //获取被执行人
                String lsbzxr = Other.getText(h5text, "历史被执行人</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");
                //历史失信被执行人
                String lssxbzxr = Other.getText(h5text, "历史失信被执行人</span><span class=\"index_second-num__LkfmY index_num-warn__owjCq\">(\\d+\\+?)</span>").replace("+", "");


                lsxzgxf = "".equals(lsxzgxf) ? "0" : lsxzgxf;
                lsbzxr = "".equals(lsbzxr) ? "0" : lsbzxr;
                lssxbzxr = "".equals(lssxbzxr) ? "0" : lssxbzxr;



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
                qySource.setUrl(url);
                qySource.setLsxzgxf(lsxzgxf);
                qySource.setLssxbzxr(lssxbzxr);
                checkSave(qySource);
            }
        }

        sleep(1000);


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

}
