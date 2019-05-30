package com.selenium.flx.financeManagement.creditCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class creditQueryList {

    //信用卡业务--信用卡财务经办
    public boolean creditQueryList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/sales/credit/creditQueryList_2.jsp", 0);

            querySpinner(driver, false, "orderStatus$text", "mini-6", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span", true);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
            Thread.sleep(1000);

            updateInput(driver, "id", "orderNo$text", "20171124002");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@style=';']")).click();

            switchIframe(driver,"/sales/credit/creditQueryList_2.jsp",1);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a/span")).click();
            Thread.sleep(1000);

            if (journal) {
                Reporter.log("财务管理--信用卡业务--信用卡财务经办--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--信用卡财务经办--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
