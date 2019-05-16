package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import javax.xml.bind.Element;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class menuManage {
    //菜单管理
    public boolean menu(WebDriver driver) {
        try {
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_manage.jsp", 1);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_list.jsp", 1);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_add.jsp", 0);

            //直接保存 三个非空警告
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            Thread.sleep(1000);
            updateInput(driver, "name", "appmenu.menuname", "测试");
            updateInput(driver, "name", "appmenu.menucode", "test");
            updateInput(driver, "name", "appmenu.menulabel", "测试");
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_manage.jsp", 0);
            List<WebElement> list = driver.findElements(By.className("mini-tree-nodetext"));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getText().equals("测试")) {
                    list.get(i).click();
                    break;
                }
            }
            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_info.jsp", 1);
            updateInput(driver, "name", "appmenu.menuname", "测试1");
            updateInput(driver, "name", "appmenu.menucode", "test1");
            updateInput(driver, "name", "appmenu.menulabel", "测试1");
            updateInput(driver, "name", "appmenu.displayorder", "3");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"form1\"]/table/tbody/tr[4]/td/a/span")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_manage.jsp", 0);
            list = driver.findElements(By.className("mini-tree-nodeshow"));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getText().equals("测试1")) {
                    list.get(i).click();
                    break;
                }
            }
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"mini-16$3\"]/span")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_list.jsp", 1);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_add.jsp", 0);
            updateInput(driver, "name", "appmenu.menuname", "测试菜单");
            updateInput(driver, "name", "appmenu.menucode", "menuTest");
            updateInput(driver, "name", "appmenu.menulabel", "测试菜单");
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_manage.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_list.jsp", 1);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_edit.jsp", 0);
            updateInput(driver, "name", "appmenu.displayorder", "3");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"appmenu.isleaf\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"mini-15$0\"]/td[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"funccode\"]/span[1]/span/span[2]/span")).click();

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_function_select.jsp", 0);
            updateInput(driver, "name", "criteria/_expr[2]/funcname", "test");
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[5]/a/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[3]/a[1]/span")).click();
            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_edit.jsp", 0);
            driver.findElement(By.xpath("/html/body/div[2]/a[1]/span")).click();

            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_manage.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_list.jsp", 1);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            driver.findElement(By.xpath("//*[@id=\"mini-25\"]/span")).click();
            Thread.sleep(1000);
            switchIframe(driver, "/FlxServer/coframe/framework/menu/menu_manage.jsp", 0);
            //创建鼠标
            Actions mouse = new Actions(driver);
            //右键测试1  点击删除
            list = driver.findElements(By.className("mini-tree-nodetext"));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getText().equals("测试1")) {
                    mouse.contextClick(list.get(i)).perform();
                    break;
                }
            }
            Thread.sleep(1000);
            driver.findElement(By.id("removemenu$text")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();
            Thread.sleep(1000);

            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--菜单管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--菜单管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
