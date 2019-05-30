package com.selenium.flx.financeManagement.accountReconciliation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class specialAccountChangeInputList {

    //账务调账业务--特殊业务调账
    public boolean specialAccountChangeInputList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/service/specialAccountChange/specialAccountChangeInputList_1.jsp", 0);

            Thread.sleep(500);
            driver.findElement(By.id("status$text")).click();
            driver.findElement(By.id("mini-13$1")).click();
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            Thread.sleep(1000);

            updateInput(driver, "id", "inAccountNo$text", "MJ000010");
            updateInput(driver, "id", "inAccountName$text", "满嘉调账账户");
            updateInput(driver, "id", "inputDateStart$text", "2017-02-21");
            updateInput(driver, "id", "inputDateEnd$text", "2018-01-01");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            Thread.sleep(1000);

            updateInput(driver, "id", "reviewDateStart$text", "2017-02-01");
            updateInput(driver, "id", "reviewDateEnd$text", "2017-10-01");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            Thread.sleep(1000);

            updateInput(driver, "id", "outAccountNo$text", "01510045");
            updateInput(driver, "id", "outAccountName$text", "201703011416");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            Thread.sleep(1000);

            updateInput(driver, "id", "reviewUser$text", "200001");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            Thread.sleep(1000);

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[@style=';']")).click();

            Thread.sleep(500);
            switchIframe(driver, "/service/specialAccountChange/specialAccountChangeInputList_1.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[9]/a/span")).click();
            Thread.sleep(500);

            if (journal) {
                Reporter.log("财务管理--账务调账业务--特殊业务调账--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--账务调账业务--特殊业务调账--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
