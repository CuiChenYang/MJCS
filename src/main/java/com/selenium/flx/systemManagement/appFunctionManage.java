package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class appFunctionManage {
    //应用功能管理
    public boolean appFunctionManage(WebDriver driver) {
        try {

             Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_manage.jsp", 1);

            //新增
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_list.jsp", 1);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_add.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "appapplication.appname$text", "测试应用");
            updateInput(driver, "id", "appapplication.appcode$text", "test");
            updateInput(driver, "id", "appapplication.appdesc$text", "用于自动化测试");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //搜索并选择
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_manage.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_list.jsp", 1);
            Thread.sleep(500);
            updateInput(driver, "className", "mini-textbox-input", "测试应用");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();

            //编辑
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_edit.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"appapplication.opendate\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-calendar-tadayButton")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"appapplication.protocoltype\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-12$1")).click();
            //是否开通 mini-18$1 （$1否/$0是）
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"appapplication.isopen\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-18$1")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //功能组列表测试
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_manage.jsp", 0);
            List<WebElement> list = driver.findElements(By.className("mini-tree-nodeshow"));
            Thread.sleep(500);
            list.get(2).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-16$3")).click();
            //增加
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/functiongroup/funcgroup_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/functiongroup/funcgroup_add.jsp", 0);
            Thread.sleep(500);
            updateInput(driver,"id","appfuncgroup.funcgroupname$text","测试名称1");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //编辑
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_manage.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/framework/functiongroup/funcgroup_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/functiongroup/funcgroup_edit.jsp", 0);
            Thread.sleep(500);
            updateInput(driver,"id","appfuncgroup.funcgroupname$text","测试名称2");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_manage.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/framework/functiongroup/funcgroup_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();

            //删除测试应用
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/application/application_manage.jsp", 0);
            Actions mouse = new Actions(driver);
            list = driver.findElements(By.className("mini-tree-nodeshow"));
            mouse.contextClick(list.get(2)).perform();
            Thread.sleep(1000);
            driver.findElement(By.id("removeapplication$text")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();
            Thread.sleep(1000);

            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--应用功能管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--应用功能管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
