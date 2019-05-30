package com.selenium.flx.financeManagement.statisticalReport;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class queryPointsSummary {

    //统计报表--个人优分汇总
    public boolean queryPointsSummary(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/report/queryPointsSummary.jsp", 0);

            updateInput(driver, "id", "summaryDate$text", "2017-05-02");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
            loading(driver);
            driver.findElement(By.id("mini-15$check")).click();
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
            loading(driver);
            driver.findElement(By.id("mini-15$check")).click();
            updateInput(driver, "id", "memberCode$text", "13761715636");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
            loading(driver);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[2]")).click();
            updateInput(driver, "id", "monthDate$text", "2017-05");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
            loading(driver);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[3]")).click();
            driver.findElement(By.id("seasonDate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2017");
            for (int i = 0; i < 4; i++) {
                driver.findElement(By.id("jiduDate$text")).click();
                driver.findElement(By.id("mini-12$" + i)).click();
                driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
                loading(driver);
            }
//            driver.findElement(By.id("jiduDate$text")).click();
//            driver.findElement(By.id("mini-12$" + 0)).click();
//            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
//            loading(driver);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[1]/div/input[4]")).click();
            driver.findElement(By.id("yearDate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "2017");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[15]/a/span")).click();
            loading(driver);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[16]/a/span")).click();
            loading(driver);

            if (journal) {
                Reporter.log("财务管理--统计报表--个人优分汇总--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--统计报表--个人优分汇总--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public static void loading(WebDriver driver) throws InterruptedException {
        boolean b = true;
        do {
            try {
                Thread.sleep(1000);
                driver.findElement(By.cssSelector(".mini-mask-msg.mini-mask-loading"));
            } catch (Exception e) {
                e.printStackTrace();
                b = false;
            }
        } while (b);
        Thread.sleep(1000);
    }
}
