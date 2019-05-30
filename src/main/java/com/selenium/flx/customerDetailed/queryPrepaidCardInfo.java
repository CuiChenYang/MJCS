package com.selenium.flx.customerDetailed;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class queryPrepaidCardInfo {

    //客服明细--充值卡状态查询
    public boolean queryPrepaidCardInfo(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/audit/audit/queryPrepaidCardInfo.jsp", 0);

            updateInput(driver, "id", "cardNo$text", "1201181210000048");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            Thread.sleep(1500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[4]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(1500);

            if (journal) {
                Reporter.log("客服明细--充值卡状态查询--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客服明细--充值卡状态查询--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
