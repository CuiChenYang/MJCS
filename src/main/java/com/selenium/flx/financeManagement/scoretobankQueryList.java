package com.selenium.flx.financeManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class scoretobankQueryList {

    //财务管理--金牛卡结果确认
    public boolean scoretobankQueryList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/sales/scoretobank/scoretobankQueryList.jsp", 0);
            query(driver, 1, "", "", 0, "", "");
            query(driver, 2, "", "", 0, "", "");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[7]/a/span")).click();
            query(driver, 0, "娄朵花", "", 0, "", "");
            query(driver, 0, "", "6229765450800497704", 0, "", "");
            query(driver, 0, "", "", 2, "", "");
            query(driver, 0, "", "", 3, "", "");
            query(driver, 0, "", "", 1, "2017-03-11", "");
            query(driver, 0, "", "", 0, "", "2017-02-16");
            query(driver, 1, "", "", 0, "2017-02-16", "2017-02-16");
            query(driver, 1, "李少渤", "6229765450800497704", 0, "2017-02-16", "2017-02-16");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[7]/a/span")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("财务管理--金牛卡结果确认--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--金牛卡结果确认--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    /**
     * 金牛卡结果回导查询
     *
     * @param driver
     * @param exportBatchNumber 批次号（0为不查，1为第一个）
     * @param cardHolder        姓名
     * @param bankCardNo        银行卡卡号
     * @param status            金牛卡状态（0为不查，1为第一个）
     * @param outputBeginTime   导出时间（前）
     * @param outputEndTime     导出时间（后）
     * @throws InterruptedException
     */
    public void query(WebDriver driver, int exportBatchNumber, String cardHolder, String bankCardNo, int status, String outputBeginTime, String outputEndTime) throws InterruptedException {

        if (exportBatchNumber != 0) {
            driver.findElement(By.xpath("//*[@id=\"exportBatchNumber\"]/span[1]/span/span[2]/span")).click();
            Thread.sleep(500);
            switchIframe(driver, "/sales/scoretobank/exportBatchNumberList.jsp", 0);
            driver.findElements(By.className("mini-grid-radio-mask")).get(exportBatchNumber - 1).click();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();
            Thread.sleep(500);
            switchIframe(driver, "/sales/scoretobank/scoretobankQueryList.jsp", 0);
        }
        updateInput(driver, "id", "cardHolder$text", cardHolder);
        updateInput(driver, "id", "bankCardNo$text", bankCardNo);
        if (status != 0) {
            driver.findElement(By.id("status$text")).click();
            driver.findElements(By.className("mini-listbox-item")).get(status - 1).click();
        }
        updateInput(driver, "xpath", "//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[4]/span[1]/span/input", outputBeginTime);
        updateInput(driver, "xpath", "//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[4]/span[2]/span/input", outputEndTime);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[6]/a/span")).click();
        Thread.sleep(1500);
    }
}
