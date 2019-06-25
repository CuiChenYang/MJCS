package com.selenium.flx.achievementsManagement.gradientManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class platformCustomer {

    //绩效管理--梯度管理--平台客户管理
    public boolean platformCustomer(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/pa/ValidCustomer/platformCustomer.jsp", 0);

            //判断信息是否更改
            Thread.sleep(500);
            if (!judge(driver)) {
                Reporter.log("绩效管理--梯度管理--平台客户管理--请查看编号4一行的信息是否更改 <br/>");
                Reporter.log("测试开发时，原信息为：编号4 、开始总业绩4000000、结束总业绩null、奖金计提比例0.04、备注null <br/>");
                return false;
            }

            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            String str = driver.findElement(By.id("4$cell$6")).getText();
            Thread.sleep(500);
            driver.findElement(By.id("4$cell$6")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            driver.findElement(By.id("8$cell$6")).click();
            Thread.sleep(100);
            driver.findElement(By.id("8$cell$6")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys(str);
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("绩效管理--梯度管理--平台客户管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("绩效管理--梯度管理--平台客户管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public boolean judge(WebDriver driver) {
        if (!"4".equals(driver.findElement(By.id("4$cell$3")).getText().trim()))
            return false;

        if (!"4000000".equals(driver.findElement(By.id("4$cell$4")).getText().trim()))
            return false;

        String str = driver.findElement(By.id("4$cell$5")).getText().trim();
        if (!("".equals(str) || str == null))
            return false;

        if (!"0.04".equals(driver.findElement(By.id("4$cell$6")).getText().trim()))
            return false;

        str = driver.findElement(By.id("4$cell$7")).getText().trim();
        if (!("".equals(str) || str == null))
            return false;

        return true;
    }
}
