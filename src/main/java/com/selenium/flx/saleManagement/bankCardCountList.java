package com.selenium.flx.saleManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class bankCardCountList {

    //银行卡库存查询
    public boolean bankCardCountList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/sales/salesInventory/bankCardCountList.jsp", 0);

            Thread.sleep(500);
            driver.findElement(By.id("bankCode$text")).click();
            Thread.sleep(500);
            List<WebElement> list = driver.findElements(By.className("mini-listbox-item"));
            Thread.sleep(500);
            for (int i = 1; i < list.size(); i++) {
                list.get(i).click();
                Thread.sleep(1000);
                if (i != list.size() - 1) {
                    driver.findElement(By.id("bankCode$text")).click();
                    Thread.sleep(500);
                }
            }

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[3]/a/span")).click();

            Thread.sleep(500);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("销售管理--银行卡库存查询--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("销售管理--银行卡库存查询--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }


}
