package com.selenium.flx.customerManagement;

import com.selenium.flx.systemManagement.thirdPartyAccountManage;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

public class cooperationManager {

    //合作伙伴档案管理
    public boolean cooperationManager(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/custom/cooperation/cooperationManager.jsp", 0);

            if (!isExistBoxOrExistButton(driver, ".mini-button-text.mini-button-icon.icon-add", 2)) {
                if (journal) {
                    Reporter.log("客户管理--合作伙伴档案管理--跳过测试。原因：暂无此功能 <br/>");
                }
                return true;
            }

            //添加
            add(driver);
            //如果已存在
            if (isExistBoxOrExistButton(driver, "//*[@class=\"mini-messagebox-buttons\"]/a/span", 3)) {
                driver.findElement(By.xpath("//*[@class=\"mini-messagebox-buttons\"]/a/span")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-cancel")).click();
                Thread.sleep(500);
                switchIframe(driver, "/custom/cooperation/cooperationManager.jsp", 0);
                Thread.sleep(500);
                query(driver, "999", "");
                Thread.sleep(1000);
                List<WebElement> list = driver.findElements(By.cssSelector(".mini-grid-cell-inner.mini-grid-cell-nowrap"));
                boolean b = false;
                //判断是否为测试信息
                for (int i = 0; i < list.size(); i++) {
                    if ("测试".equals(list.get(i).getText().trim()) ||
                            "测试999".equals(list.get(i).getText().trim())) {
                        b = true;
                        list.get(i).click();
                        break;
                    }
                }
                if (b) {
                    Thread.sleep(500);
                    driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
                    Thread.sleep(500);
                    driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
                    Thread.sleep(500);
                    driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
                    add(driver);
                } else {
                    if (journal) {
                        taskScreenShot(driver);
                        Reporter.log("客户管理--合作伙伴档案管理--测试失败。错误：已存在合作伙伴编号为999的信息。请查看是否为测试信息或新增信息 <br/>");
                    }
                    return false;
                }
            }

            //修改
            switchIframe(driver, "/custom/cooperation/cooperationManager.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "cooperationid$text", "999");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[5]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/custom/cooperation/editCooperation.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "cooperationname$text", "测试999");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //删除
            Thread.sleep(500);
            switchIframe(driver, "/custom/cooperation/cooperationManager.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            //重置
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[6]/a/span")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("客户管理--合作伙伴档案管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客户管理--合作伙伴档案管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String id, String name) throws InterruptedException {
        Thread.sleep(500);
        updateInput(driver, "id", "cooperationid$text", id);
        Thread.sleep(500);
        updateInput(driver, "id", "cooperationname$text", name);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr/td[5]/a/span")).click();
    }

    public void add(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
        Thread.sleep(500);
        switchIframe(driver, "/custom/cooperation/addCooperation.jsp", 0);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
        Thread.sleep(500);
        updateInput(driver, "id", "cooperationid$text", "999");
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
        Thread.sleep(500);
        updateInput(driver, "id", "cooperationname$text", "测试");
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
        Thread.sleep(1500);
    }
}
