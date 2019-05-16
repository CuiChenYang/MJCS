package com.selenium.flx.custom;

import com.selenium.utils.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

@Slf4j
public class editCustom {
    public String customNo;

    /**
     * 客户管理 查询用户
     */
    public boolean queryCustom(WebDriver driver, String customNo) {
        try {
            Thread.sleep(1000);
            this.customNo = customNo;
            driver.switchTo().frame("mainframe");
            driver.findElement(By.id("customNo$text")).click();
            driver.findElement(By.id("customNo$text")).sendKeys(customNo);
            Thread.sleep(1000);
            driver.findElement(By.className("search-condition"));
            driver.findElement(By.id("queryForm"));
            driver.findElement(By.className("mini-button-text")).click();
            driver.switchTo().defaultContent();
            return true;
        } catch (Exception e) {
            taskScreenShot(driver);
            if (journal) {
                Reporter.log("客户管理 查询用户失败。错误：" + e.toString()+"<br/>");
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * 客户管理 企业审核
     */
    public boolean auditCustom(WebDriver driver) {
        try {
            Thread.sleep(2000);
            driver.switchTo().frame("mainframe");
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            //点击修改按钮，弹出修改界面
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            //返回主窗体，进入修改页面
            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/custom/cusprofile/editCusProfileNew.jsp')]")));
            //企业审核
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-goto")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("mini-119")).click();
            driver.switchTo().defaultContent();
            if (journal) {
                Reporter.log("企业审核成功，企业号为：" + customNo+"<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                Reporter.log("客户管理--企业:" + customNo + "审核失败。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }
}
