package com.selenium.flx.financeManagement.statisticalReport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class queryReportDailyOrderTbl {

    //统计报表--每日订单汇总
    public boolean queryReportDailyOrderTbl(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/report/reportV2/queryReportDailyOrderTbl.jsp", 0);

            query(driver, "startDate$text", "2018-05-17", "endDate$text", "2019-05-17");

            driver.findElement(By.id("monthRadio")).click();
            query(driver, "startMonth$text", "2018-05", "endMonth$text", "2019-05");

            driver.findElement(By.id("yearRadio")).click();
            Thread.sleep(500);
            //输入框存在默认值时，若需要修改其值。使用sendKeys(Keys.chord(Keys.CONTROL,"a"),"value") value为需要更改的值
            driver.findElement(By.id("startYear$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2017");
            driver.findElement(By.id("endYear$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2018");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.id("seasonRadio")).click();
            Thread.sleep(500);
            driver.findElement(By.id("year$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2018");
            querySpinner(driver, 4, "season$text", "mini-18$", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();

            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a/span")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("财务管理--统计报表--每日订单汇总--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--统计报表--每日订单汇总--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String startUrl, String startValue, String endUrl, String endValue) throws InterruptedException {
        Thread.sleep(500);
        updateInput(driver, "id", startUrl, startValue);
        updateInput(driver, "id", endUrl, endValue);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
        Thread.sleep(1500);
    }
}
