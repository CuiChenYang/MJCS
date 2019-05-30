package com.selenium.flx.financeManagement.statisticalReport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class queryCertificateReport {

    //统计报表--凭证统计汇总
    public boolean queryCertificateReport(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/report/certificateReport/queryCertificateReport.jsp", 0);

            updateInput(driver, "id", "startDate$text", "2017-01-15");
            updateInput(driver, "id", "endDate$text", "2017-01-17");
            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[3]/a/span")).click();
            Thread.sleep(1500);

            driver.findElement(By.xpath("//*[@id=\"queryForm\"]/table/tbody/tr[1]/td[4]/a/span")).click();
            Thread.sleep(1500);

            if (journal) {
                Reporter.log("财务管理--统计报表--凭证统计汇总--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--统计报表--凭证统计汇总--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
