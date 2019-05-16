package com.selenium.flx.systemManagement;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class authorizationManage {

    //角色授权
    public boolean roleAuthorization(WebDriver driver) {
        try {
            switchIframe(driver, "/FlxServer/coframe/auth/role_auth.jsp", 1);
            //输入并查询
            Thread.sleep(1000);
            updateInput(driver, "xpath", "//*[@id=\"table1\"]/tbody/tr/td[1]/span/span/input", "test");
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[1]/span/span/input")).clear();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(1000);
            updateInput(driver, "xpath", "//*[@id=\"table1\"]/tbody/tr/td[2]/span/span/input", "测试");
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();

            //配置
            Thread.sleep(1000);
            driver.findElement(By.id("1281")).click();

            //功能
            switchIframe(driver, "/FlxServer/coframe/framework/function/function_role_auth.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"panel1\"]/div/div[2]/div[1]/div/table/tbody/tr/td[1]/a[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-15$checkbox$5")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-15$checkbox$11")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-15$checkbox$115")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "key$text", "修改应用");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-15$checkbox$4")).click();
            Thread.sleep(500);
            driver.findElement(By.id("key$text")).clear();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            //保存
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-iconOnly.icon-save")).click();
            Thread.sleep(500);
            waitClick(driver, "//*[@id=\"mini-27\"]/span", 3);

            //机构
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/role_auth.jsp", 0);
            driver.findElement(By.id("mini-24$2")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_auth.jsp", 1);
            Thread.sleep(500);
            updateInput(driver, "id", "key$text", "北京");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"4$cell$1\"]/div/div/span/span[2]")).click();
            //添加成功
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"addBtn\"]/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("key$text")).clear();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"7$cell$1\"]/div/div/span/span[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"addBtn\"]/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"8$cell$1\"]/div/div/span/span[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"addBtn\"]/span")).click();
            //删除
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"mini-28$1\"]/td[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"deleteBtn\"]/span")).click();
            //保存
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-iconOnly.icon-save")).click();
            Thread.sleep(500);
            waitClick(driver, "//*[@id=\"mini-30\"]/span", 3);

            //工作组
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/role_auth.jsp", 0);
            driver.findElement(By.id("mini-24$3")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/group/group_auth.jsp", 1);

            //数据实体
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/role_auth.jsp", 0);
            driver.findElement(By.id("mini-24$4")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/entityauth/rule/entity_rule_auth.jsp", 1);

            //测试完成后删除测试数据
            //功能
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/role_auth.jsp", 0);
            driver.findElement(By.id("mini-24$1")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/framework/function/function_role_auth.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.id("mini-15$checkbox$1")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-15$checkbox$1")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-iconOnly.icon-save")).click();
            Thread.sleep(500);
            waitClick(driver, "//*[@id=\"mini-27\"]/span", 3);
            //机构
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/role_auth.jsp", 0);
            driver.findElement(By.id("mini-24$2")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_auth.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"allDeleteBtn\"]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-iconOnly.icon-save")).click();
            Thread.sleep(500);
            waitClick(driver, "//*[@id=\"mini-30\"]/span", 3);

            Thread.sleep(1000);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--授权管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--授权管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
