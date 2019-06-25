package com.selenium.flx.achievementsManagement.gradientManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.waitSearch;

public class validCustomer {

    //绩效管理--梯度管理--有效客户管理
    public boolean validCustomer(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/pa/ValidCustomer/validCustomer.jsp", 0);

            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            if (!"6".equals(waitSearch(driver, By.id("6$cell$3")).getText())) {
                Reporter.log("绩效管理--梯度管理--有效客户管理--请确认是否更新过数据 <br/>");
                Reporter.log("测试开发时原数据最后一行为： 编号5 、开始总业绩47、结束总业绩56、奖金计提比例12、备注null <br/>");
                return false;
            }
            Thread.sleep(500);
            driver.findElement(By.id("6$cell$5")).click();
            Thread.sleep(200);
            driver.findElement(By.id("6$cell$5")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys("99");
            Thread.sleep(500);
            driver.findElement(By.id("6$cell$6")).click();
            Thread.sleep(200);
            driver.findElement(By.id("6$cell$6")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys("150");
            Thread.sleep(500);
            driver.findElement(By.id("6$cell$7")).click();
            Thread.sleep(200);
            driver.findElement(By.id("6$cell$7")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-textbox-input")).sendKeys("测试用例");
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(1000);
            List<WebElement> list = driver.findElements(By.cssSelector(".mini-grid-cell-inner.mini-grid-cell-nowrap"));
            for (int i = list.size() - 1; i >= 0; i--) {
                if ("测试用例".equals(list.get(i).getText())) {
                    list.get(i).click();
                    break;
                }
            }
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("绩效管理--梯度管理--有效客户管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("绩效管理--梯度管理--有效客户管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
