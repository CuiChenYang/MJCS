package com.selenium.flx.operateManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class custom_power {

    //客户绑定
    public boolean custom_power(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/customSet/custom_power.jsp", 0);

            Thread.sleep(500);
            updateInput(driver, "name", "entity.company", "上海演示");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            Thread.sleep(500);
            updateInput(driver, "name", "entity.customNo", "01510013");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.id("01510013")).click();

            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[5]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            Thread.sleep(500);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("运营管理--客户绑定--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("运营管理--客户绑定--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
