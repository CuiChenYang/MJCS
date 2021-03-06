package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

public class customerIPSettings {
    //客户IP设置
    public boolean customerIPSettings(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/other/customIp/customIpQueryList.jsp", 0);

            //新增
            add(driver);
            //若已存在，先删除原有
            Thread.sleep(500);
            if (delete(driver, "01510182", "192.168.44.44"))
                add(driver);
            Thread.sleep(500);
            driver.findElement(By.id("savebtn1")).click();

            //查询选择
            Thread.sleep(1000);
            switchIframe(driver, "/other/customIp/customIpQueryList.jsp", 0);
            Thread.sleep(500);
            query(driver, "01510182", "192.168.44.44");
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();

            //编辑
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/other/customIp/editCustomIp.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("ipAddress$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "192.168.44.45");
            Thread.sleep(500);
            driver.findElement(By.id("formtab1")).click();
            Thread.sleep(1000);
            //若已存在，先删除原有
            if (delete(driver, "01510182", "192.168.44.45")) {
                Thread.sleep(500);
                switchIframe(driver, "/other/customIp/customIpQueryList.jsp", 0);
                Thread.sleep(500);
                query(driver, "01510182", "192.168.44.44");
                Thread.sleep(500);
                driver.findElement(By.className("mini-grid-radio-mask")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
                Thread.sleep(500);
                switchIframe(driver, "/other/customIp/editCustomIp.jsp", 0);
                Thread.sleep(500);
                driver.findElement(By.id("ipAddress$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "192.168.44.45");
                Thread.sleep(500);
                driver.findElement(By.id("formtab1")).click();
            }
            Thread.sleep(500);
            driver.findElement(By.id("savebtn1")).click();

            //查询并删除
            Thread.sleep(1000);
            switchIframe(driver, "/other/customIp/customIpQueryList.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a[2]/span")).click();
            query(driver, "01510182", "192.168.44.45");
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"add\"]/../a[3]")).click();
            loading(driver);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(1000);

            if (journal) {
                Reporter.log("系统管理--客户IP设置--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--客户IP设置--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String customNo, String ipAddress) throws InterruptedException {
        Thread.sleep(500);
        updateInput(driver, "name", "customIp.customNo", customNo);
        updateInput(driver, "name", "customIp.ipAddress", ipAddress);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
        Thread.sleep(1000);
    }

    public void add(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
        Thread.sleep(500);
        switchIframe(driver, "/other/customIp/editCustomIp.jsp", 0);
        Thread.sleep(500);
        driver.findElement(By.className("mini-buttonedit-icon")).click();
        Thread.sleep(500);
        switchIframe(driver, "/custom/cusprofile/cusProfileManagerNew.jsp", 0);
        Thread.sleep(500);
        updateInput(driver, "id", "customNo$text", "01510182");
        Thread.sleep(500);
//        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[11]/a/span")).click();
        Thread.sleep(500);
        driver.findElement(By.className("mini-grid-radio-mask")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();
        Thread.sleep(500);
        switchIframe(driver, "/other/customIp/editCustomIp.jsp", 0);
        Thread.sleep(500);
        driver.findElement(By.id("ipAddress$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "192.168.44.44");
        Thread.sleep(500);
        driver.findElement(By.id("formtab1")).click();
        Thread.sleep(500);
    }

    public boolean delete(WebDriver driver, String queryid, String queryip) throws InterruptedException {
        if (isExistBoxOrExistButton(driver, "//div[@class='mini-messagebox-buttons']/a", 3)) {
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-cancel")).click();
            Thread.sleep(500);
            switchIframe(driver, "/other/customIp/customIpQueryList.jsp", 0);
            Thread.sleep(500);
            query(driver, queryid, queryip);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"add\"]/../a[3]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            return true;
        }
        return false;
    }
}
