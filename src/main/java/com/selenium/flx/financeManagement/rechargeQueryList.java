package com.selenium.flx.financeManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class rechargeQueryList {

    //批量代充业务（查询）
    public boolean rechargeQueryList(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/sales/recharge/rechargeQueryList_1.jsp", 0);

            driver.findElement(By.id("orderStatus$text")).click();


            if (journal) {
                Reporter.log("财务管理--批量代充业务（查询）--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("财务管理--批量代充业务（查询）--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

}
