package com.selenium.flx.customerManagement;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class cooperationCustomRelation {

    //客户与合作伙伴关系管理
    public boolean cooperationCustomRelation(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/cooperationCustomRelation.jsp", 0);

            //查询
            query(driver, "01510120", "", "", "", "");
            query(driver, "", "测试", "", "", "");
            query(driver, "", "", "0002", "", "");
            query(driver, "", "", "", "福优管家", "");
            query(driver, "", "", "", "", "MJCom");
            query(driver, "01510120", "上海满嘉***测试企业", "", "", "");
            query(driver, "01510120", "上海满嘉***测试企业", "0002", "福优管家", "");
            query(driver, "01510120", "上海满嘉***测试企业", "0002", "福优管家", "MJCom");

            //添加
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/addCooperationCustomRelation.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"cooperationid\"]/span/span/span[2]/span")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/getCooperation.jsp", 0);
            Thread.sleep(500);
            driver.findElements(By.className("mini-grid-radio-mask")).get(0).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/addCooperationCustomRelation.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "corpaccounts$text", "test001");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"customno\"]/span/span/span[2]/span")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/pub/customPub/getCustom.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "customNo$text", "01510182");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/addCooperationCustomRelation.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //修改
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/cooperationCustomRelation.jsp", 0);
            Thread.sleep(500);
            query(driver, "01510182", "", "", "", "");
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/editCooperationCustomRelation.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "corpaccounts$text", "test003");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/custom/cooperation/cooperationCustomRelation.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            //重置
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[8]/a/span")).click();

            Thread.sleep(500);
            driver.switchTo().defaultContent();


            if (journal) {
                Reporter.log("客户管理--客户与合作伙伴关系管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客户管理--客户与合作伙伴关系管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String customno, String company, String cooperationid, String cooperationname, String corpaccounts) throws InterruptedException {

        updateInput(driver, "id", "customno$text", customno);
        updateInput(driver, "id", "company$text", company);
        updateInput(driver, "id", "cooperationid$text", cooperationid);
        updateInput(driver, "id", "cooperationname$text", cooperationname);
        updateInput(driver, "id", "corpaccounts$text", corpaccounts);

        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[7]/a/span")).click();
        Thread.sleep(1000);
    }

}
