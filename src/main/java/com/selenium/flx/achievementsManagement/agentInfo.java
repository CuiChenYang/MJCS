package com.selenium.flx.achievementsManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class agentInfo {

    //代理商绩效
    public boolean agentInfo(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/pa/agentInfo/agentInfo.jsp", 0);

            Thread.sleep(500);
            query(driver, "代理商", "", "", "");
            query(driver, "代理商", "", "2018-07-27", "2018-07-27");
            query(driver, "代理商", "dl0004", "2018-07-27", "2018-07-27");

            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a/span")).click();

            if (journal) {
                Reporter.log("绩效管理--代理商绩效--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("绩效管理--代理商绩效--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String agentName, String agentCode, String createTimeStart, String createTimeEnd) throws InterruptedException {

        updateInput(driver, "id", "agentName$text", agentName);
        updateInput(driver, "id", "agentCode$text", agentCode);
        updateInput(driver, "id", "createTimeStart$text", createTimeStart);
        updateInput(driver, "id", "createTimeEnd$text", createTimeEnd);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
        Thread.sleep(1500);

    }
}
