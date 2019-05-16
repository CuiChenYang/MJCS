package com.selenium.flx.customService;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

@Slf4j
public class customDetail {

    /**
     * 客服明细查询 查询客户当前优分余额
     */
    public boolean queryDetail(WebDriver driver, String customNo) {
        try {
            driver.findElement(By.id("1662")).click();
            driver.findElement(By.id("1663")).click();
            Thread.sleep(1000);
            driver.switchTo().frame("mainframe");
            driver.findElement(By.id("customNo$text")).sendKeys(customNo);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"shopScore\"]/span")).click();
            Thread.sleep(3000);
            String str = driver.findElement(By.className("mini-messagebox-content-text")).getText();
            driver.findElement(By.xpath("//*[@class=\"mini-messagebox-buttons\"]/a")).click();
            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/p/span[2]/a[2]")).click();
            if (journal) {
                Reporter.log("客服明细查询--企业：" + customNo + "，" + str+"<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客服明细查询失败--企业：" + customNo + "。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }

}