package com.selenium.flx.achievementsManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class queryTotalCustomer {

    //绩效管理--企业关联关系--列表信息管理
    public boolean queryTotalCustomer(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/pa/TotalCustomer/queryTotalCustomer.jsp", 0);

            Thread.sleep(500);
            driver.findElement(By.id("cname$text")).sendKeys("测试1129");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"3$cell$1\"]/div/div/span[3]/span[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();

            Thread.sleep(500);
            add(driver, "0118测试01");

            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("绩效管理--企业关联关系--列表信息管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("绩效管理--企业关联关系--列表信息管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void add(WebDriver driver, String name) throws InterruptedException {
        Thread.sleep(500);
        switchIframe(driver, "/pa/TotalCustomer/relationTotalCustomer.jsp", 0);
        Thread.sleep(500);
        driver.findElement(By.id("buname$text")).sendKeys(name);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
        Thread.sleep(500);
        driver.findElement(By.className("mini-grid-radio-mask")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
        Thread.sleep(500);
        switchIframe(driver, "/pa/TotalCustomer/queryTotalCustomer.jsp", 0);
    }
}
