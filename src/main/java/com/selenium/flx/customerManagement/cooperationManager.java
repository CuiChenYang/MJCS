package com.selenium.flx.customerManagement;

import com.selenium.flx.systemManagement.thirdPartyAccountManage;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

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


}
