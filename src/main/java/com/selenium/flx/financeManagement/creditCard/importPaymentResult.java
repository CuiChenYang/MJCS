package com.selenium.flx.financeManagement.creditCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class importPaymentResult {

    //信用卡业务--还款结果导入
    public boolean importPaymentResult(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/other/repayment/importPaymentResult.jsp", 0);

            Thread.sleep(500);
            updateInput(driver, "id", "importTime$text", "2018-11-28");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[6]/a[1]/span")).click();
            Thread.sleep(1000);

            querySpinner(driver, false, "isUpdate$text", "mini-7", "//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[6]/a[1]/span", true);
            querySpinner(driver, false, "paymentStatus$text", "mini-14", "//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[6]/a[1]/span", true);

            query(driver, "", "04");
            query(driver, "20181128133627305390", "04");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[6]/a[2]/span")).click();

            Thread.sleep(1000);

            if (journal) {
                Reporter.log("财务管理--信用卡业务--还款结果导入--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--还款结果导入--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String orderNoFlx, String orderNoBank) throws InterruptedException {

        updateInput(driver, "id", "orderNoFlx$text", orderNoFlx);
        updateInput(driver, "id", "orderNoBank$text", orderNoBank);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[6]/a[1]/span")).click();
        Thread.sleep(1500);
    }
}
