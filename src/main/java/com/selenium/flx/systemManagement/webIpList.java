package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class webIpList {

    //webIp白名单
    public boolean webIpList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/other/webIp/webIpList.jsp", 0);

            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/other/webIp/entryWebIp.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "webname$text", "99000099");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "ip$text", "192.168.9.09");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            Thread.sleep(500);
            switchIframe(driver, "/other/webIp/webIpList.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "webname$text", "99000099");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[3]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();

            Thread.sleep(500);
            switchIframe(driver, "/other/webIp/editWebIp.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "ip$text", "192.168.0.99");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            Thread.sleep(500);
            switchIframe(driver, "/other/webIp/webIpList.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[4]/a/span")).click();

            if (journal) {
                Reporter.log("系统管理--webIp白名单--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--webIp白名单--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
