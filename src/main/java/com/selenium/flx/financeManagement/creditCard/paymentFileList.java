package com.selenium.flx.financeManagement.creditCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class paymentFileList {

    //信用卡业务--光大复核
    public boolean paymentFileList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/other/paymentTxt/paymentFileList.jsp", 0);

            Thread.sleep(500);
            updateInput(driver, "id", "operatetime$text", "2018-11-28");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[9]/a/span")).click();
            Thread.sleep(1000);

            querySpinner(driver, false, "isreview$text", "mini-11", "//*[@id=\"queryForm\"]/table/tbody/tr/td[9]/a/span", true);

            query(driver, "201811281337000812", "");
            query(driver, "", "mj20181128_1003_shmj_2_2500_5144.txt");

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[10]/a/span")).click();
            Thread.sleep(500);

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[@style=';']")).click();

            if (journal) {
                Reporter.log("财务管理--信用卡业务--光大复核--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--光大复核--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String batchno, String filename) throws InterruptedException {

        updateInput(driver, "id", "batchno$text", batchno);
        updateInput(driver, "id", "filename$text", filename);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[9]/a/span")).click();
        Thread.sleep(1500);
    }
}
