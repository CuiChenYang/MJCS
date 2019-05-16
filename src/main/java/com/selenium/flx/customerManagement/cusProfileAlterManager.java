package com.selenium.flx.customerManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.Set;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class cusProfileAlterManager {

    //客户档案变更记录
    public boolean cusProfileAlterManager(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cusprofile/cusProfileAlterManager.jsp", 0);

            //查询
            queryFile(driver, "1549", "", "", "", "");
            queryFile(driver, "", "上海演示", "", "", "");
            queryFile(driver, "", "", "2017-01-07", "", "");
            queryFile(driver, "", "", "", "2017-01-09", "");
            queryFile(driver, "", "", "", "", "01510006");
            queryFile(driver, "", "", "2017-01-12", "2017-01-13", "");
            queryFile(driver, "", "", "2017-01-12", "2017-01-13", "01510017");
            queryFile(driver, "", "福优网开发", "2017-01-12", "2017-01-13", "01510017");
            queryFile(driver, "2821", "福优网开发", "2017-01-12", "2017-01-13", "01510017");

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);

            //保存当前页面的句柄
            String oldHandle = driver.getWindowHandle();
            //点击打印跳出页面
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-print")).click();
            Thread.sleep(2000);
            //获取当前浏览器打开的页面Handle集合
            Set<String> set = driver.getWindowHandles();
            //定位到新打开的tab页
            for (String h : set) {
                if (!h.equals(driver.getWindowHandle())) {
                    driver.switchTo().window(h);
                }
            }
            //关闭打印页面，回到之前页面
            driver.close();
            driver.switchTo().window(oldHandle);

            if (journal) {
                Reporter.log("客户管理--客户档案变更记录--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客户管理--客户档案变更记录--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    //查询记录
    public void queryFile(WebDriver driver, String alterNo, String company, String createDateStart, String createDateEnd, String customNo) throws InterruptedException {

        updateInput(driver, "id", "alterNo$text", alterNo);
        updateInput(driver, "id", "company$text", company);
        updateInput(driver, "id", "createDateStart$text", createDateStart);
        updateInput(driver, "id", "createDateEnd$text", createDateEnd);
        updateInput(driver, "id", "customNo$text", customNo);

        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
        Thread.sleep(1000);
    }

}
