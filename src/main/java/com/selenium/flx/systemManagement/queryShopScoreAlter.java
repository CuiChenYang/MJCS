package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class queryShopScoreAlter {

    //系统账户优分设置变更记录
    public boolean queryShopScoreAlter(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customMember/queryShopScoreAlter.jsp", 0);

            Thread.sleep(500);
            select(driver, "", "", "");
            Thread.sleep(1000);
            select(driver, "sysadmin", "", "");
            Thread.sleep(1000);
            select(driver, "", "MJ000011", "");
            Thread.sleep(1000);
            select(driver, "", "", "2017-12-06");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[8]/a/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[7]/a/span")).click();

            Thread.sleep(1000);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--系统账户优分设置变更记录--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--系统账户优分设置变更记录--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void select(WebDriver driver, String user, String code, String time) throws InterruptedException {
        updateInput(driver, "id", "modify_user$text", user);
        updateInput(driver, "id", "member_code$text", code);
        updateInput(driver, "id", "modify_time$text", time);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[7]/a/span")).click();
    }
}
