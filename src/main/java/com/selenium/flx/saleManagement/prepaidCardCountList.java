package com.selenium.flx.saleManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class prepaidCardCountList {

    //充值卡库存查询
    public boolean prepaidCardCountList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/sales/salesInventory/prepaidCardCountList.jsp", 0);

            queryInput(driver, 12, "orgcode$text", "mini-5$");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[5]/a/span")).click();
            queryInput(driver, 6, "valueNo$text", "mini-8$");
            Thread.sleep(500);
            driver.findElement(By.id("orgcode$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-5$2")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[5]/a/span")).click();

            Thread.sleep(500);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("销售管理--充值卡库存查询--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("销售管理--充值卡库存查询--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void queryInput(WebDriver driver, int num, String inputId, String trId) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.id(inputId)).click();
        Thread.sleep(500);
        for (int i = 1; i <= num; i++) {
            driver.findElement(By.id(trId + i)).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[6]/a/span")).click();
            Thread.sleep(1000);
            if (i != num)
                driver.findElement(By.id(inputId)).click();
        }
    }
}
