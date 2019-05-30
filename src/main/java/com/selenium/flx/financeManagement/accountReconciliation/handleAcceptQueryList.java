package com.selenium.flx.financeManagement.accountReconciliation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class handleAcceptQueryList {

    //账务调账业务--账务冲正
    public boolean handleAcceptQueryList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/sales/order/handleAcceptQueryList.jsp", 0);

            Thread.sleep(500);
            query(driver, "", "18801613734");
            query(driver, "20180411175345197795", "18801613734");

            if (journal) {
                Reporter.log("财务管理--账务调账业务--账务冲正 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--账务调账业务--账务冲正。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String orderNoFlx, String customNo) throws InterruptedException {

        updateInput(driver, "id", "orderNoFlx$text", orderNoFlx);
        updateInput(driver, "id", "customNo$text", customNo);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
        Thread.sleep(1500);

    }
}
