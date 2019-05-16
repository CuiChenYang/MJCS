package com.selenium.fuyou.enterpriseProcurement;

import com.selenium.supplier.supplier;
import com.selenium.utils.PropertiesConfig;
import org.openqa.selenium.*;

import java.util.Set;

import static com.selenium.fuyou.fuYouMethod.clearDate;
import static com.selenium.fuyou.fuYouMethod.inputSearchDate;

public class purchaseOrder {

    /**
     * 查询订单（参数均为查询条件）
     *
     * @param driver
     * @param orderId   订单编号
     * @param goodsName 商品名称
     * @param startTime 下单开始时间（格式：2016-06-06）
     * @param endTime   下单结束时间（格式：2016-06-06）
     */
    public void getOrder(WebDriver driver, String orderId, String goodsName, String startTime, String endTime) throws InterruptedException {
        //订单编号
        driver.findElement(By.id("CompanyOrderInformation_txtOrderId")).clear();
        driver.findElement(By.id("CompanyOrderInformation_txtOrderId")).sendKeys(orderId);
        //商品名称
        driver.findElement(By.id("CompanyOrderInformation_txtProductName")).clear();
        driver.findElement(By.id("CompanyOrderInformation_txtProductName")).sendKeys(goodsName);
        //清空下单时间
        clearDate(driver, "CompanyOrderInformation_calendarStartDate", "CompanyOrderInformation_calendarEndDate");
        //输入下单时间
        inputSearchDate(driver, startTime, endTime, "CompanyOrderInformation_calendarStartDate", "CompanyOrderInformation_calendarEndDate");
        //查询
        driver.findElement(By.id("CompanyOrderInformation_imgbtnSearch")).click();

    }

    //测试各种查询订单
    public void VariousGetOrder(WebDriver driver) throws InterruptedException {
        getOrder(driver, "asegfSEfg", "", "", "");
        getOrder(driver, "", "SZEgffwq", "", "");
        getOrder(driver, "", "", "2019-04-04", "2018-04-04");
        //提示开始时间不能大于结束时间！ 点击确认
        driver.findElement(By.xpath("//*[@id=\"xubox_layer2\"]/div[1]/span/a[1]")).click();
        getOrder(driver, "2019", "", "", "");
        getOrder(driver, "", "测试", "", "");
        getOrder(driver, "", "", "2019-04-03", "2019-04-04");
    }

    /**
     * 订单流程-1：（订单已付款）
     * 申请退款 查看订单
     */
    public boolean orderProcessRefund(WebDriver driver, String orderId) {
        try {
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@orderid,'" + orderId + "')]")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/span/a[1]")).click();
            Thread.sleep(2000);
            //点击查看进入查看页面3秒后关闭查看页面
            lookOrder(driver, orderId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //点击查看进入查看页面3秒后关闭查看页面退回到原页面
    public void lookOrder(WebDriver driver, String orderId) throws InterruptedException {
        //查看 打开一个新页面
        Thread.sleep(1000);
        driver.findElement(By.id("CompanyOrderInformation_Common_OrderManage_OrderList___listOrders_ctl00_hlinkOrderDetails")).click();
        Thread.sleep(3000);
        String oldHandle = goNewTabPage(driver, PropertiesConfig.getInstance().getProperty("driver.fuYou.url") + "Company/CompanyOrderDetails?OrderId=" + orderId);
        //关闭查看页面 重新定位到原页面
        returnOldPage(driver, oldHandle, PropertiesConfig.getInstance().getProperty("driver.fuYou.url") + "Company/CompanyOrderInformation");
    }

    /**
     * 订单流程2：（订单未付款）
     * 查询订单并取消付款
     */
    public boolean orderProcessCancelPayment(WebDriver driver, String orderId) {
        try {
            //查询订单
            Thread.sleep(500);
            getOrder(driver, orderId, "", "", "");
            //取消付款，弹出对话框
            driver.findElement(By.id("CompanyOrderInformation_Common_OrderManage_OrderList___listOrders_ctl00_lkbtnCloseOrder")).click();
            //获取弹出的对话框
            Thread.sleep(1000);
            Alert alt = driver.switchTo().alert();
            //点击确定
            alt.accept();
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //新开一个tab页并跳转定位到此页面，返回跳转前页面句柄（原页面）
    public String goNewTabPage(WebDriver driver, String newUrl) {
        //保存当前页面的句柄
        String oldHandle = driver.getWindowHandle();
        //获取当前浏览器打开的页面Handle集合
        Set<String> set = driver.getWindowHandles();
        //定位到新打开的tab页
        for (String h : set) {
            if (!h.equals(driver.getWindowHandle())) {
                driver.switchTo().window(h);
            }
        }
        driver.get(newUrl);
        return oldHandle;
    }

    //关闭当前页面返回之前的页面
    public void returnOldPage(WebDriver driver, String oldHandle, String oldUrl) {
        driver.close();
        driver.switchTo().window(oldHandle);
        driver.get(oldUrl);
    }

    /**
     * 订单流程3：（订单未付款）
     * 查询订单-完成付款-供应商发货-查看物流-确认订单
     *
     * @param driver
     * @param orderId
     * @return
     */
    public boolean orderProcess(WebDriver driver, String orderId) {
        try {
            getOrder(driver, orderId, "", "", "");
            Thread.sleep(500);
            driver.findElement(By.id("CompanyOrderInformation_Common_OrderManage_OrderList___listOrders_ctl00_hlinkPay")).click();
            //点击付款弹到付款页面
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[3]/td/div/button[1]")).click();
            //跳转并定位到付款页面
            String oldHandle = goNewTabPage(driver, PropertiesConfig.getInstance().getProperty("driver.fuYou.url") + "Company/CompanyFinishOrder?orderId=" + orderId);

            //进行付款操作
            enterpriseProcurement ep = new enterpriseProcurement();
            boolean flag = ep.payment(driver);
            if (flag) {
                //关闭当前页面 重新定位到原页面（采购订单页面）
                returnOldPage(driver, oldHandle, PropertiesConfig.getInstance().getProperty("driver.fuYou.url") + "Company/CompanyOrderInformation");

                //查询一次当前订单（刷新）
                getOrder(driver, orderId, "", "", "");

                //新开tab页
                Thread.sleep(500);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.open();");
                Thread.sleep(500);
                //登录供应商发货
                supplier s = new supplier();
                if (!s.giveExpress(driver, orderId))
                    return false;
                //关闭当前页面 重新定位到原页面（采购订单页面）
                returnOldPage(driver, oldHandle, PropertiesConfig.getInstance().getProperty("driver.fuYou.url") + "Company/CompanyOrderInformation");

                //查看物流
                Thread.sleep(1000);
                driver.findElement(By.id("CompanyOrderInformation_Common_OrderManage_OrderList___listOrders_ctl00_Logistics")).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/td/div/a")).click();
                //确认订单
                Thread.sleep(1000);
                driver.findElement(By.id("CompanyOrderInformation_Common_OrderManage_OrderList___listOrders_ctl00_lkbtnConfirmOrder")).click();
                //获取弹出的对话框
                Thread.sleep(1000);
                Alert alt = driver.switchTo().alert();
                //点击确定
                Thread.sleep(1000);
                alt.accept();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}