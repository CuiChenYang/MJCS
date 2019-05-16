package com.selenium.flx.systemManagement;

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
import static com.selenium.utils.PhoneUtil.getNum;

public class parameterManage {
    //参数管理
    public boolean parameterManage(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver,"/FlxServer/parameter/code/bank_sys_code.jsp",0);

            //点击添加提示需要选中一条记录
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class=\"mini-messagebox-buttons\"]/a/span")).click();

            //选择类型名称并查询
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"codeType\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            List<WebElement> list = driver.findElements(By.className("mini-listbox-item"));
            list.get(1).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"codeType\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            list.get(6).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            //选中并编辑第四个测试参数
            Thread.sleep(1000);
            list = driver.findElements(By.className("mini-grid-radio-mask"));
            list.get(3).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();

            Thread.sleep(500);
            switchIframe(driver,"/FlxServer/parameter/code/bank_sys_code_update.jsp",0);
            Thread.sleep(500);
            updateInput(driver, "name", "sysCode.codeName", "测试" + getNum(1, 999) + getNum(1, 999));
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(1000);
            if (isExistBoxOrExistButton(driver, "//*[@class=\"mini-messagebox-buttons\"]/a/span", 3)) {
                driver.findElement(By.xpath("//*[@class=\"mini-messagebox-buttons\"]/a/span")).click();
            }

            Thread.sleep(1000);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--参数管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--参数管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
