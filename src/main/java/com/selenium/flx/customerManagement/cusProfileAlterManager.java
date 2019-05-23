package com.selenium.flx.customerManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.Set;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class cusProfileAlterManager {

    //客户档案变更记录
    public boolean cusProfileAlterManager(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cusprofile/cusProfileAlterManager.jsp", 0);

            //查询
            queryFile(driver, "", "", "2017-01-12", "2017-01-13", "");
            queryFile(driver, "", "", "2017-01-12", "2017-01-13", "01510017");
            queryFile(driver, "", "福优网开发", "2017-01-12", "2017-01-13", "01510017");
            queryFile(driver, "2821", "福优网开发", "2017-01-12", "2017-01-13", "01510017");

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);

            lookTabAndCloseTab(driver,".mini-button-text.mini-button-icon.icon-print");

            if (journal) {
                Reporter.log("客户管理--客户档案变更记录--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客户管理--客户档案变更记录--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    //查询记录
    public void queryFile(WebDriver driver, String alterNo, String company, String createDateStart, String createDateEnd, String customNo) throws InterruptedException {

        updateInput(driver, "id", "alterNo$text", alterNo);
        updateInput(driver, "id", "company$text", company);
        updateInput(driver, "id", "createDateStart$text", createDateStart);
        updateInput(driver, "id", "createDateEnd$text", createDateEnd);
        updateInput(driver, "id", "customNo$text", customNo);

        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
        Thread.sleep(1500);
    }

}
