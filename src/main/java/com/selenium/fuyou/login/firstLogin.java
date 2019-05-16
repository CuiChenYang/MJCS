package com.selenium.fuyou.login;

import com.selenium.utils.JdbcUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;

@Slf4j
public class firstLogin {

    /**
     * 首次登录进行企业验证
     */
    public boolean verificationCustom(WebDriver driver, String custom) {
        try {
            //短信确认
            driver.findElement(By.id("sendcodebt")).click();
            //获取手机验证码
            Thread.sleep(1000);
            driver.findElement(By.className("zeromodal-close")).click();
            Thread.sleep(2000);
            JdbcUtil j = new JdbcUtil();
            String cord = j.querySmsCode(j.queryCellPhone(custom));
            driver.findElement(By.id("Telcode")).sendKeys(cord);
            Thread.sleep(1000);
            driver.findElement(By.className("scdl_an")).click();
            //企业信息确认（设置新密码）
            Thread.sleep(1000);
            driver.findElement(By.id("newpassword")).sendKeys("123456");
            driver.findElement(By.id("confirmnewpw")).sendKeys("123456");
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/form/div[6]/label")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("button")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("layui-layer-btn0")).click();
            if (journal) {
                Reporter.log("首次登录激活企业成功，企业号：" + custom + "<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("首次登录激活企业失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
