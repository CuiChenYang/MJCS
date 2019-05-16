package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class customerIPSettings {
    //客户IP设置
    public boolean customerIPSettings(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customIp/customIpQueryList.jsp", 0);

            //新增
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customIp/editCustomIp.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.className("mini-buttonedit-icon")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cusprofile/cusProfileManagerNew.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "customNo$text", "01510182");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customIp/editCustomIp.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "ipAddress$text", "192.168.1.1");
            Thread.sleep(500);
            driver.findElement(By.id("formtab1")).click();
            driver.findElement(By.id("savebtn1")).click();

            //查询选择
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customIp/customIpQueryList.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "name", "customIp.ipAddress", "192.168.1.1");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            updateInput(driver, "name", "customIp.customNo", "01510182");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();

            //编辑
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customIp/editCustomIp.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "ipAddress$text", "192.168.1.21");
            Thread.sleep(500);
            driver.findElement(By.id("formtab1")).click();
            driver.findElement(By.id("savebtn1")).click();

            //查询并删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customIp/customIpQueryList.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a[2]/span")).click();
            Thread.sleep(500);
            updateInput(driver, "name", "customIp.ipAddress", "192.168.1.21");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"add\"]/../a[3]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(1000);
            driver.switchTo().defaultContent();

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

}
