package com.selenium.flx.financeManagement.statisticalReport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class queryReportSpend {

    //统计报表--平台优分使用汇总
    public boolean queryReportSpend(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/report/queryReportSpend.jsp", 0);

            Thread.sleep(500);
            updateInput(driver, "id", "startDate$text", "2015-10-10");
            updateInput(driver, "id", "endDate$text", "2016-12-31");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[2]")).click();
            updateInput(driver, "id", "monthDate$text", "2017-01");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[3]")).click();
            Thread.sleep(1000);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[4]")).click();
            driver.findElement(By.id("yearDate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2017");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[14]/a/span")).click();
            Thread.sleep(1500);

            if (journal) {
                Reporter.log("财务管理--统计报表--平台优分使用汇总--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--统计报表--平台优分使用汇总--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
