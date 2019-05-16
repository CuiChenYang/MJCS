package com.selenium.supplier;

import com.selenium.base.DriverBase;
import com.selenium.supplier.order.orderManager;
import com.selenium.utils.PropertiesConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class supplier extends DriverBase {
    public WebDriver driver;

    private String supplierUrl = PropertiesConfig.getInstance().getProperty("supplier.url");
    private String supplierUsername = PropertiesConfig.getInstance().getProperty("supplier.username");
    private String supplierPassword = PropertiesConfig.getInstance().getProperty("supplier.password");

    //ReportNG 只能显示字符而无法显示成链接，则取消注释
    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    /**
     * 已开tab页登录供应商
     */
    public void tabLogin(WebDriver driver) throws InterruptedException {
        //隐式等待,10秒内不出现就报错
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //获取当前浏览器打开的页面Handle集合
        Set<String> set = driver.getWindowHandles();
        //定位到新打开的tab页
        for (String h:set) {
            if(!h.equals(driver.getWindowHandle())){
                driver.switchTo().window(h);
            }
        }
        driver.get(supplierUrl);
        //登录
        driver.findElement(By.id("txtAdminName")).sendKeys(supplierUsername);
        driver.findElement(By.id("txtAdminPassWord")).sendKeys(supplierPassword);
        Thread.sleep(500);
        driver.findElement(By.id("btnAdminLogin")).click();
    }

    /**
     * 用已开的tab页登录并给订单orderId发货
     */
    public boolean giveExpress(WebDriver driver, String orderId) {
        try {
            //ReportNG 只能显示字符而无法显示成链接，则取消注释
            System.setProperty(ESCAPE_PROPERTY, "false");
            //登录
            tabLogin(driver);
            orderManager o = new orderManager();
            //发货
            return o.deliverGoods(driver, orderId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
