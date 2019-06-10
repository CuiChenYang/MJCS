package com.selenium.flx.financeManagement.creditCard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class errorRepayment {

    //信用卡业务--还款异常处理
    public boolean errorRepayment(WebDriver driver) {
        try {

            Thread.sleep(1000);
            switchIframe(driver, "/other/repayment/errorRepayment.jsp", 0);

            Thread.sleep(1000);
            updateInput(driver, "id", "import_time$text", "2018-11-28");
            driver.findElement(By.id("payment_time$text")).clear();
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            loading(driver);

            querySpinner(driver, false, "orderStatus$text", "mini-5", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span", true);
            querySpinner(driver, true, "channel$text", "mini-8", "//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span", false);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
            loading(driver);
            driver.findElement(By.id("channel$text")).click();
            driver.findElement(By.id("mini-8$3")).click();

            query(driver, "", "", "6225768673856546", "李啸天");
            query(driver, "18655137085", "", "6225768673856546", "李啸天");
            query(driver, "01510136", "yinheng", "6225768673856546", "李啸天");

            driver.findElement(By.className("mini-grid-radio-mask")).click();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();

            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[10]/a/span")).click();

            Thread.sleep(500);

            if (journal) {
                Reporter.log("财务管理--信用卡业务--还款异常处理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--还款异常处理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String customNo, String company, String card_no, String cardholder) throws InterruptedException {

        updateInput(driver, "id", "customNo$text", customNo);
        updateInput(driver, "id", "company$text", company);
        updateInput(driver, "id", "card_no$text", card_no);
        updateInput(driver, "id", "cardholder$text", cardholder);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[9]/a/span")).click();
        loading(driver);
    }
}
