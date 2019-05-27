package com.selenium.flx.financeManagement.orderPrinting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class orderCredentialHandList {

    //凭证打印--手工凭证录入
    public boolean orderCredentialHandList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/service/credentials/orderCredentialHandList.jsp", 0);

            Thread.sleep(500);
            query(driver, "", "2017-01-17", "");
            query(driver, "", "2017-01-17", "2017-01-18");
            query(driver, "PZ05201701180000001", "2017-01-17", "2017-01-18");

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            lookTabAndCloseTab(driver, ".mini-button-text.mini-button-icon.icon-search");

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/service/credentials/orderCredentialHandList.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[4]/a/span")).click();
            Thread.sleep(500);

            if (journal) {
                Reporter.log("财务管理--凭证打印--手工凭证录入--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--凭证打印--手工凭证录入--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String certificateNo, String adjustTimeStart, String adjustTimeEnd) throws InterruptedException {

        updateInput(driver, "id", "certificateNo$text", certificateNo);
        updateInput(driver, "id", "adjustTimeStart$text", adjustTimeStart);
        updateInput(driver, "id", "adjustTimeEnd$text", adjustTimeEnd);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[3]/a/span")).click();
        Thread.sleep(1500);

    }
}
