package com.selenium.flx.financeManagement;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

public class rechargeQueryList {

    //批量代充业务（查询）
    public boolean rechargeQueryList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/sales/recharge/rechargeQueryList_1.jsp", 0);

            querySpinner(driver, false, "orderStatus$text", "mini-6", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span", true);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a/span")).click();

            Thread.sleep(500);
            updateInput(driver, "id", "orderNo$text", "20170405001");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
            Thread.sleep(1000);
            if (isExistBoxOrExistButton(driver, "mini-grid-radio-mask", 1)) {
                driver.findElement(By.className("mini-grid-radio-mask")).click();
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
                Thread.sleep(2000);
                driver.switchTo().defaultContent();
                Thread.sleep(500);
                driver.findElement(By.xpath("//span[@style=';']")).click();
            }

            if (journal) {
                Reporter.log("财务管理--批量代充业务（查询）--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--批量代充业务（查询）--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
