package com.selenium.flx.financeManagement.creditCard;

import org.apache.catalina.filters.WebdavFixFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;

public class repayment {

    //信用卡业务--矩阵还款
    public boolean repayment(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/other/repayment/repayment.jsp", 0);

            updateInput(driver, "id", "import_time$text", "2019-05-15");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[7]/a/span")).click();
            loading(driver);

            querySpinner(driver, false, "orderStatus$text", "mini-15", "//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[7]/a/span", true);
            querySpinner(driver, false, "paymentStatus$text", "mini-19", "//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[7]/a/span", true);

            query(driver, "6259588689735205", "", "", "", "");
            query(driver, "", "上海浦东发展银行", "姚健", "", "");
            query(driver, "", "上海浦东发展银行", "姚健", "18801613734", "");
            query(driver, "", "上海浦东发展银行", "姚健", "", "YJ");
            query(driver, "6259588689735206", "上海浦东发展银行", "姚健", "01510096", "YJ");

            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            loading(driver);
            driver.switchTo().defaultContent();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[@style=';']")).click();

            if (journal) {
                Reporter.log("财务管理--信用卡业务--矩阵还款--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--信用卡业务--矩阵还款--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void query(WebDriver driver, String card_no, String bankName, String cardholder, String customNo, String company) throws InterruptedException {
        updateInput(driver, "id", "card_no$text", card_no);
        updateInput(driver, "id", "bankName$text", bankName);
        updateInput(driver, "id", "cardholder$text", cardholder);
        updateInput(driver, "id", "customNo$text", customNo);
        updateInput(driver, "id", "company$text", company);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[2]/td[7]/a/span")).click();
        loading(driver);
    }
}
