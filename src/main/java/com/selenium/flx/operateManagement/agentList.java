package com.selenium.flx.operateManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class agentList {

    //代理商管理
    public boolean agentList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/agentList.jsp", 0);


            //查询
            queryInput(driver, "代理商", "", "", "", "", "");
            queryInput(driver, "", "dl0004", "", "", "", "");
            queryInput(driver, "", "", "上海销售", "", "", "");
            queryInput(driver, "", "", "", "500001", "", "");
            queryInput(driver, "", "", "", "", "2018-08-14", "");
            queryInput(driver, "", "", "", "", "", "2018-08-14");
            queryInput(driver, "", "", "", "", "2018-07-27", "2018-08-14");
            queryInput(driver, "代理商", "", "", "", "2018-07-27", "2018-08-14");
            queryInput(driver, "代理商", "", "上海销售", "", "2018-07-27", "2018-08-14");
            queryInput(driver, "代理商", "", "上海销售", "530001", "2018-07-27", "2018-08-14");
            queryInput(driver, "代理商", "dl0002", "上海销售", "530001", "2018-07-27", "2018-08-14");
            //重置
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[6]/a/span")).click();

            //添加
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/addAgent.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "agentName$text", "test001");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"userId\"]/span/span/span[2]/span")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/getSales.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "userName$text", "测试");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[5]/a/span")).click();
            Thread.sleep(500);
            driver.findElements(By.className("mini-grid-radio-mask")).get(0).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/addAgent.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            driver.findElement(By.id("agentRate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "1");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            //修改
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/agentList.jsp", 0);
            Thread.sleep(500);
            queryInput(driver, "test001", "", "", "", "", "");
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/editAgent.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "agentName$text", "test003");
            Thread.sleep(500);
            driver.findElement(By.id("agentRate$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "3");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            //删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/agent/agent/agentList.jsp", 0);
            Thread.sleep(500);
            queryInput(driver, "test003", "", "", "", "", "");
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(500);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("运营管理--代理商管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("运营管理--代理商管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void queryInput(WebDriver driver, String agentName, String agentCode, String userName, String userId, String startTime, String endTime) throws InterruptedException {

        updateInput(driver, "id", "agentName$text", agentName);
        updateInput(driver, "id", "agentCode$text", agentCode);
        updateInput(driver, "id", "userName$text", userName);
        updateInput(driver, "id", "userId$text", userId);
        updateInput(driver, "id", "createTimeStart$text", startTime);
        updateInput(driver, "id", "createTimeEnd$text", endTime);

        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[5]/a/span")).click();
        Thread.sleep(1000);
    }
}
