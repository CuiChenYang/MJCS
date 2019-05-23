package com.selenium.flx.financeManagement.statisticalReport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class queryCounterFee {

    //统计报表--手续费汇总
    public boolean queryCounterFee(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/report/queryCounterFee.jsp", 0);

            updateInput(driver, "id", "startDate$text", "2017-01-17");
            updateInput(driver, "id", "endDate$text", "2017-01-17");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[2]")).click();
            updateInput(driver, "id", "monthDate$text", "2017-01");
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[3]")).click();
            driver.findElement(By.id("seasonDate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2017");
            querySpinner(driver, 4, "jiduDate$text", "mini-14$", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[4]")).click();
            driver.findElement(By.id("yearDate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2017");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[13]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[14]/a/span")).click();
            Thread.sleep(1500);

            if (journal) {
                Reporter.log("财务管理--统计报表--手续费汇总--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--统计报表--手续费汇总--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }


}
