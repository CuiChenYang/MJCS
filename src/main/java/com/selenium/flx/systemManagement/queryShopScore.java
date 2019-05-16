package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class queryShopScore {
    //系统账户优分设置
    public boolean queryShopScore(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customMember/queryShopScore.jsp", 0);

            Thread.sleep(500);
            driver.findElements(By.className("mini-grid-radio-mask")).get(0).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customMember/editShopScore.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-cancel")).click();

            Thread.sleep(1000);
            driver.switchTo().defaultContent();


            if (journal) {
                Reporter.log("系统管理--系统账户优分设置--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--系统账户优分设置--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
