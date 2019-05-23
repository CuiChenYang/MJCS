package com.selenium.flx.financeManagement.orderPrinting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.Set;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class orderQueryList {

    //凭证打印--凭证记录查询
    public boolean orderQueryList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/service/credentials/orderQueryList.jsp", 0);

            Thread.sleep(500);
            updateInput(driver, "id", "createTimeStart$text", "2017-05-01");
            updateInput(driver, "id", "createTimeEnd$text", "2018-01-01");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[5]/a/span")).click();
            Thread.sleep(1500);

            querySpinner(driver, 11, "orderState$text", "mini-6$", "//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[5]/a/span");
            querySpinner(driver, 37, "orderDistinction$text", "mini-15$", "//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[5]/a/span");

            updateInput(driver, "id", "orderNoFlx$text", "20170222153859607624");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[5]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            lookTabAndCloseTab(driver, ".mini-button-text.mini-button-icon.icon-search");

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/service/credentials/orderQueryList.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[6]/a/span")).click();
            Thread.sleep(500);

            if (journal) {
                Reporter.log("财务管理--凭证打印--凭证记录查询--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--凭证打印--凭证记录查询--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
