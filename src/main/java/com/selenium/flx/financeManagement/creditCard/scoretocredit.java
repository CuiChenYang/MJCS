package com.selenium.flx.financeManagement.creditCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class scoretocredit {

    //财务管理--信用卡业务--信用卡导出
    public boolean scoretocredit(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/sales/order/scoretocredit.jsp", 0);

            //查询未完成则不进行后面的操作
            Thread.sleep(2000);
            query(driver, "4392250037952164", "", "");
            Thread.sleep(2000);
            query(driver, "", "2018-03-01", "2018-03-02");
            Thread.sleep(2000);
            query(driver, "4392250035736320", "2018-03-01", "2018-03-02");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[4]/a/span")).click();
            Thread.sleep(2000);

            if (journal) {
                Reporter.log("财务管理--信用卡业务--信用卡导出--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--信用卡导出--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String bankCardNo, String begintime, String endtime) throws InterruptedException {

        updateInput(driver, "id", "bankCardNo$text", bankCardNo);
        updateInput(driver, "id", "begintime$text", begintime);
        updateInput(driver, "id", "endtime$text", endtime);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[3]/a/span")).click();

    }
}
