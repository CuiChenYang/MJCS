package com.selenium.flx.financeManagement.statisticalReport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class reportlogMangaer {

    //统计报表--报表处理日志
    public boolean reportlogMangaer(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/report/reportlog/reportlogMangaer.jsp", 0);

            Thread.sleep(1000);
            queryInput(driver, 5, "//*[@id=\"form1\"]/table/tbody/tr/td[4]/span/span/input", "mini-9$", "//*[@id=\"form1\"]/table/tbody/tr/td[7]/a/span");
            Thread.sleep(1000);
            queryInput(driver, 3, "//*[@id=\"form1\"]/table/tbody/tr/td[6]/span/span/input", "mini-12$", "//*[@id=\"form1\"]/table/tbody/tr/td[7]/a/span");

            updateInput(driver, "id", "startDate$text", "2017-01-16");
            updateInput(driver, "id", "endDate$text", "2017-01-19");

            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(1500);

            if (journal) {
                Reporter.log("财务管理--统计报表--报表处理日志--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--统计报表--报表处理日志--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }


    public void queryInput(WebDriver driver, int num, String spinnerXpath, String listId, String queryXpath) throws InterruptedException {
        for (int i = 1; i < num; i++) {
            waitSearch(driver, By.xpath(spinnerXpath)).click();
            waitSearch(driver, By.id(listId + i)).click();
            waitSearch(driver, By.xpath(queryXpath)).click();
            loading(driver);
            Thread.sleep(500);
        }
        driver.findElement(By.xpath(spinnerXpath)).click();
        driver.findElement(By.id(listId + 0)).click();
    }
}
