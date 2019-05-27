package com.selenium.flx.financeManagement.creditCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class queryRepaymentAll {

    //信用卡业务--还款结果查询
    public boolean queryRepaymentAll(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/repayment/queryRepaymentAll.jsp", 0);

            updateInput(driver, "id", "importTimeStart$text", "2018-11-01");
            updateInput(driver, "id", "importTimeEnd$text", "2018-11-30");
            driver.findElement(By.id("paymentTimeStart$text")).clear();
            driver.findElement(By.id("paymentTimeEnd$text")).clear();
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
            Thread.sleep(1500);

            query(driver, "", "", "10", "", "", "");
            query(driver, "", "", "10", "100", "", "");
            query(driver, "", "", "10", "100", "6225768673856546", "");
            query(driver, "", "", "10", "100", "6225768673856546", "18655137085");
            query(driver, "2018-11-28", "2018-11-29", "10", "100", "6225768673856546", "18655137085");

            querySpinner(driver, 4, "orderStatus$text", "mini-12$", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span");
            Thread.sleep(500);
            querySpinner(driver, 6, "paymentStatus$text", "mini-15$", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[8]/a/span")).click();
            Thread.sleep(2000);

            if (journal) {
                Reporter.log("财务管理--信用卡业务--还款结果查询--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--还款结果查询--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String paymentTimeStart, String paymentTimeEnd, String amountStart, String amountEnd, String cardNo, String customNo) throws InterruptedException {

        updateInput(driver, "id", "paymentTimeStart$text", paymentTimeStart);
        updateInput(driver, "id", "paymentTimeEnd$text", paymentTimeEnd);
        updateInput(driver, "id", "amountStart$text", amountStart);
        updateInput(driver, "id", "amountEnd$text", amountEnd);
        updateInput(driver, "id", "cardNo$text", cardNo);
        updateInput(driver, "id", "customNo$text", customNo);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
        Thread.sleep(1500);

    }
}
