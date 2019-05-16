package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class interfacesPowerManage {
    //接口权限管理
    public boolean interfacesPowerManage(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/interfacepower/interfacep/interface_power.jsp", 0);

            //查询接口
            Thread.sleep(500);
            updateInput(driver, "name", "customInfo.company", "ceshi0322");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            updateInput(driver, "name", "customInfo.customNo", "02560027");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            //点击配置
            Thread.sleep(500);
            driver.findElement(By.id("02560027")).click();

            //更改与还原接口权限
            for (int i = 0; i < 2; i++) {
                Thread.sleep(1000);
                List<WebElement> list = driver.findElements(By.className("mini-grid-radio-mask"));
                list.get(3).click();
                list.get(6).click();
                list.get(12).click();
                list.get(15).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@class=\"mini-messagebox-buttons\"]/a/span")).click();
            }

            Thread.sleep(1000);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--接口权限管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--接口权限管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
