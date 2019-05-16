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

/**
 * 系统管理
 */
public class userManage {

    //  用户管理 查询用户
    public boolean selectUser(WebDriver driver) {
        try {
            switchIframe(driver, "/FlxServer/coframe/rights/user/user_list.jsp", 0);
            updateInput(driver, "className", "mini-textbox-input", "admin");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("mini-textbox-input")).clear();
            Thread.sleep(500);
            driver.findElement(By.className("mini-buttonedit-icon")).click();
            Thread.sleep(500);
            List<WebElement> list = driver.findElements(By.className("mini-listbox-item"));
            for (int i = 1; i < list.size(); i++) {
                Thread.sleep(500);
                list.get(i).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
                Thread.sleep(1000);
                if (i != list.size() - 1)
                    driver.findElement(By.className("mini-buttonedit-icon")).click();
            }
            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            if (journal) {
                Reporter.log("系统管理--用户管理--查询完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--用户管理--查询失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
