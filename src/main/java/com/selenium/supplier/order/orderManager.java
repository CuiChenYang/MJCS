package com.selenium.supplier.order;

import com.selenium.utils.PropertiesConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class orderManager {

    public String expressName = PropertiesConfig.getInstance().getProperty("supplier.expressName");
    public String expressOrderId = PropertiesConfig.getInstance().getProperty("supplier.expressOrderId");


    //发货操作
    public boolean deliverGoods(WebDriver driver, String orderId) {
        try {
            driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a[2]")).click();
            driver.switchTo().frame("frammain");
            Thread.sleep(1000);
            driver.findElement(By.id("ctl00_contentHolder_txtOrderId")).sendKeys(orderId);
            Thread.sleep(500);
            driver.findElement(By.id("ctl00_contentHolder_btnSearchButton")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"ctl00_contentHolder_dlstOrders_ctl01_lkbtnSendGoods\"]/a")).click();
            driver.switchTo().defaultContent();
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'sales/SendOrderGoods.aspx')]")));
            driver.findElement(By.xpath("//*[@id=\"select_ui_id_ctl00_contentHolder_expressRadioButtonList\"]/div[1]/div[2]")).click();
            //选择快递 填写单号
            List liList = driver.findElements(By.cssSelector(".optionBox.oNormal"));
            for (int i = 0; i < liList.size() - 1; i++) {
                WebElement element = (WebElement) liList.get(i);
                if (expressName.equals(element.getText())) {
                    element.click();
                    break;
                }
            }
            driver.findElement(By.id("ctl00_contentHolder_txtShipOrderNumber")).sendKeys(expressOrderId);
            Thread.sleep(1000);
            //发货
            driver.findElement(By.id("ctl00_contentHolder_btnSendGoods")).click();
            Reporter.log("登录供应商发货成功。快递名称：" + expressName + "。 快递单号：" + expressOrderId + "<br/>");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工删除失败，错误：" + e.toString() + "<br/>");
            return false;
        }
    }

}
