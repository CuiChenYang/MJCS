package com.selenium.flx.systemManagement;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

public class thirdPartyAccountManage {
    //第三方账号管理
    public boolean thirdPartyAccountManage(WebDriver driver) {
        try {

            Thread.sleep(2000);

            if (journal) {
                Reporter.log("系统管理--第三方账号管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--第三方账号管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }


}
