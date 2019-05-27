package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class queryLog {

    //用户登录日志
    public boolean userLoginLog(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/login/queryLoginLog.jsp", 0);

            Thread.sleep(500);
            queryUserLoginLog(driver, "2019-04-18", "", "", "");
            Thread.sleep(1000);
            queryUserLoginLog(driver, "", "2019-04-18", "", "");
            Thread.sleep(1000);
            queryUserLoginLog(driver, "", "", "sysadmin", "");
            Thread.sleep(1000);
            queryUserLoginLog(driver, "", "", "", "100002");
            Thread.sleep(1000);
            queryUserLoginLog(driver, "2019-04-18", "2019-04-18", "", "");
            Thread.sleep(1000);
            queryUserLoginLog(driver, "2019-04-18", "2019-04-18", "sysadmin", "");
            Thread.sleep(1000);
            queryUserLoginLog(driver, "2019-04-18", "2019-04-18", "", "100002");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[8]/a/span")).click();

            Thread.sleep(1000);

            if (journal) {
                Reporter.log("系统管理--日志查询--用户登录日志--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--日志查询--用户登录日志--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    //用户操作日志
    public boolean userOperateLog(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/other/webHandle/queryHandle.jsp", 0);

            queryOver(driver,"mini-grid-cell");
            queryUserOperateLog(driver, "2019-04-18", "2019-04-18", "", "", "");
            queryOver(driver,"mini-grid-cell");
            queryUserOperateLog(driver, "2019-04-18", "2019-04-18", "sysadmin", "", "");
            queryOver(driver,"mini-grid-cell");
            queryUserOperateLog(driver, "2019-04-18", "2019-04-18", "sysadmin", "", "custom/cusprofile/com.primeton.components.nui.DictLoader.getDictData.biz.ext");
            queryOver(driver,"mini-grid-cell");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[8]/a/span")).click();
            queryOver(driver,"mini-grid-cell");

            Thread.sleep(1000);

            if (journal) {
                Reporter.log("系统管理--日志查询--用户操作日志--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--日志查询--用户操作日志--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }


    public void queryUserLoginLog(WebDriver driver, String startTime, String endTime, String userName, String userCode) throws InterruptedException {
        updateInput(driver, "id", "startOperatorTime$text", startTime);
        updateInput(driver, "id", "endOperatorTime$text", endTime);
        updateInput(driver, "id", "operatorUserName$text", userName);
        updateInput(driver, "id", "operatorUserCode$text", userCode);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[7]/a/span")).click();
    }

    public void queryUserOperateLog(WebDriver driver, String startTime, String endTime, String userName, String userId, String rul) throws InterruptedException {
        updateInput(driver, "id", "startRequestTime$text", startTime);
        updateInput(driver, "id", "endRequestTime$text", endTime);
        updateInput(driver, "id", "requestUserName$text", userName);
        updateInput(driver, "id", "requestUserId$text", userId);
        updateInput(driver, "id", "requestUri$text", rul);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
    }
}
