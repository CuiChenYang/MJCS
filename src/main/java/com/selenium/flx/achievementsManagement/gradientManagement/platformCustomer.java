package com.selenium.flx.achievementsManagement.gradientManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class platformCustomer {

    //绩效管理--梯度管理--平台客户管理
    public boolean platformCustomer(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/pa/ValidCustomer/platformCustomer.jsp", 0);

            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            //0.04
            String str = driver.findElement(By.id("4$cell$6")).getText();
            Thread.sleep(500);
            driver.findElement(By.id("4$cell$6")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            driver.findElement(By.id("8$cell$6")).click();
            Thread.sleep(100);
            driver.findElement(By.id("8$cell$6")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys(str);
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("绩效管理--梯度管理--平台客户管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("绩效管理--梯度管理--平台客户管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
