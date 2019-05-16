package com.selenium.flx.order;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.selenium.fuyou.welfareManager.welfareManager;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;
import static com.selenium.flx.flxPublicMethod.waitClick;

@Slf4j
public class editOrder {
    public String orderId;

    /**
     * 销售管理 订单录入
     */
    public boolean entryOrder(String customNo, WebDriver driver) {
        try {
            driver.findElement(By.id("1081")).click();
            driver.findElement(By.id("1103")).click();
            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/orderQueryList.jsp')]")));
            //点击订单录入按钮
            driver.findElement(By.xpath("/html/body/div/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td/a[1]/span")).click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/entry_order.jsp')]")));
            //选择客户编号
            driver.findElement(By.xpath("//*[@id=\"customCode\"]/span/span/span[2]/span")).click();
            //输入客户编号查询
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/pub/customPub/getCustom.jsp')]")));
            updateInput(driver, "xpath", "//*[@id=\"customNo$text\"]", customNo);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[7]/a/span")).click();
            Thread.sleep(1000);
            //选择
            driver.findElement(By.id("42$cell$1")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/entry_order.jsp')]")));
            //进入购买优分，输入优分
            orderId = driver.findElement(By.name("orderInfo.orderNo")).getAttribute("value");
            driver.findElement(By.xpath("//*[@id=\"form1\"]/div/div/div[5]")).click();
            //输入框存在默认值时，若需要修改其值。使用sendKeys(Keys.chord(Keys.CONTROL,"a"),"value") value为需要更改的值
            driver.findElement(By.id("integral$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "10000000");
            //订单录入
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"savebtn0\"]/span")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("mini-147")).click();
            if (journal) {
                Reporter.log("销售管理 订单录入成功，企业号为：" + customNo + " 。订单号为：" + orderId+"<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("销售管理 订单录入失败。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }

    /**
     * 销售管理 订单复核
     */
    public boolean checkOrder(String customNo, WebDriver driver) {
        try {
            //订单录入时复核
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/orderQueryList.jsp')]")));
            driver.findElement(By.id("orderNo$text")).sendKeys(orderId);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[9]/a")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("btn_editDictType")).click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/entry_order.jsp')]")));
            Thread.sleep(1000);
            driver.findElement(By.id("savebtn1")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("savebtn2")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("mini-151")).click();
            driver.switchTo().defaultContent();
            if (journal) {
                Reporter.log("销售管理 订单复核成功，企业号为：" + customNo + " 。订单号为：" + orderId+"<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("销售管理--订单复核失败，企业号为：" + customNo + " 。订单号为：" + orderId + "。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }

    /**
     * 财务管理 订单经办
     */
    public boolean handleOrder(WebDriver driver) {
        try {
            driver.findElement(By.id("1261")).click();
            //订单业务 订单经办
            driver.findElement(By.id("1587")).click();
            driver.findElement(By.id("1594")).click();
            Thread.sleep(1000);
            //查询订单 处理订单
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/orderQueryList_1.jsp')]")));
            driver.findElement(By.id("orderNo$text")).sendKeys(orderId);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[5]/a/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            //订单经办
            Thread.sleep(1000);
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/entry_order_1.jsp')]")));
            driver.findElement(By.id("savebtn1")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("savebtn3")).click();
            Thread.sleep(2000);
            driver.findElement(By.id("mini-156")).click();
            driver.switchTo().defaultContent();
            if (journal) {
                Reporter.log("财务管理 订单业务 订单经办成功，订单号为：" + orderId+"<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--订单经办--订单号:" + orderId + "--失败。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }


    /**
     * 财务管理 订单激活
     */
    public boolean activateOrder(WebDriver driver) {
        try {
            //订单激活
            driver.findElement(By.id("1595")).click();
            Thread.sleep(1000);
            //查询订单
            driver.switchTo().frame("mainframe");
            driver.findElement(By.id("orderNo$text")).sendKeys(orderId);
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[3]/td[5]/a/span")).click();
            //订单激活
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'/FlxServer/sales/order/entry_order_2.jsp')]")));
            driver.findElement(By.xpath("//*[@id=\"savebtn2\"]/span")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("savebtn1")).click();
            Thread.sleep(1000);
            waitClick(driver, "mini-144", 0);
            if (journal) {
                Reporter.log("财务管理 订单激活成功，订单号为：" + orderId+"<br/>");
            }
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--订单激活--订单号:" + orderId + "--失败。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }
}
