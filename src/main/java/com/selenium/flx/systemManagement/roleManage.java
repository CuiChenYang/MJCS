package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class roleManage {

    //角色管理
    public boolean roleManage(WebDriver driver) {
        try {
            Thread.sleep(1000);
            switchIframe(driver,"/FlxServer/coframe/rights/role/role_manager.jsp",0);
            //输入查询
            Thread.sleep(500);
            updateInput(driver,"name","criteria._expr[0].roleCode","admin");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.name("criteria._expr[0].roleCode")).clear();
            Thread.sleep(500);
            updateInput(driver,"name","criteria._expr[1].roleName","测试");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            //增加
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver,"/FlxServer/coframe/rights/role/role_add.jsp",0);
            //直接点击保存出现两个非空警告
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            updateInput(driver,"id","capRole.roleCode$text","test002");
            updateInput(driver,"id","capRole.roleName$text","自动化测试使用");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //查询新增的角色
            Thread.sleep(500);
            switchIframe(driver,"/FlxServer/coframe/rights/role/role_manager.jsp",0);
            Thread.sleep(500);
            updateInput(driver,"name","criteria._expr[1].roleName","自动化测试使用");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            //选择新增的角色
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();

            //编辑
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver,"/FlxServer/coframe/rights/role/role_update.jsp",0);
            Thread.sleep(500);
            updateInput(driver,"id","capRole.roleCode$text","test003");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"mini-12\"]/span")).click();

            //删除
            Thread.sleep(500);
            switchIframe(driver,"/FlxServer/coframe/rights/role/role_manager.jsp",0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"mini-28\"]/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"mini-31\"]/span")).click();

            Thread.sleep(1000);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--角色管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--角色管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
