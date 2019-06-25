package com.selenium.flx.achievementsManagement.gradientManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class terminalCustomer {

    //绩效管理--梯度管理--终端客户管理
    public boolean terminalCustomer(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/pa/ValidCustomer/terminalCustomer.jsp", 0);

            //判断信息是否更改
            Thread.sleep(500);
            if (!judge(driver)) {
                Reporter.log("绩效管理--梯度管理--终端客户管理--请查看编号7一行的信息是否更改 <br/>");
                Reporter.log("测试开发时，原信息为：编号7 、开始总业绩3500000、结束总业绩null、奖金计提比例0.5、备注11 <br/>");
                return false;
            }

            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            String str1 = driver.findElement(By.id("7$cell$6")).getText();
            String str2 = driver.findElement(By.id("7$cell$7")).getText();
            driver.findElement(By.id("7$cell$7")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            driver.findElement(By.id("14$cell$6")).click();
            Thread.sleep(100);
            driver.findElement(By.id("14$cell$6")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys(str1);
            Thread.sleep(500);
            driver.findElement(By.id("14$cell$7")).click();
            Thread.sleep(100);
            driver.findElement(By.id("14$cell$7")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys(str2);
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("绩效管理--梯度管理--终端客户管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("绩效管理--梯度管理--终端客户管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public boolean judge(WebDriver driver) {
        if (!"7".equals(driver.findElement(By.id("7$cell$3")).getText().trim()))
            return false;

        if (!"3500000".equals(driver.findElement(By.id("7$cell$4")).getText().trim()))
            return false;

        String str = driver.findElement(By.id("7$cell$5")).getText().trim();
        if (!("".equals(str) || str == null))
            return false;

        if (!"0.5".equals(driver.findElement(By.id("7$cell$6")).getText().trim()))
            return false;

        if (!"11".equals(driver.findElement(By.id("7$cell$7")).getText().trim()))
            return false;

        return true;
    }

}
