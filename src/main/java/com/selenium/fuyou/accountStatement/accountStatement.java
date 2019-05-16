package com.selenium.fuyou.accountStatement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.fuyou.fuYouMethod.getNavList;
import static com.selenium.fuyou.fuYouMethod.inputSearchDate;

public class accountStatement {

    //region 对账单

    public boolean searchStatement(WebDriver driver){
        try{
            Thread.sleep(500);
            driver.findElement(By.id("cardNo")).sendKeys("17607153713");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".qyzx_search.qyzx_btn")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".qyzx_clear.qyzx_btn")).click();
            Thread.sleep(500);
            driver.findElement(By.id("outOrderNo")).sendKeys("20190305173732700183");
            driver.findElement(By.cssSelector(".qyzx_search.qyzx_btn")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".qyzx_clear.qyzx_btn")).click();


            Thread.sleep(500);
            inputSearchDate(driver,"2019-01-28","2019-01-29","CompanyStatement_acceptBeginDate","CompanyStatement_acceptEndDate");
            driver.findElement(By.cssSelector(".qyzx_search.qyzx_btn")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".qyzx_clear.qyzx_btn")).click();
            Thread.sleep(500);

            inputSearchDate(driver,"2019-02-25","2019-01-22","CompanyStatement_handleBeginDate","CompanyStatement_handleEndDate");
            driver.findElement(By.cssSelector(".qyzx_search.qyzx_btn")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".qyzx_clear.qyzx_btn")).click();
            Thread.sleep(500);

            driver.findElement(By.cssSelector(".qyzx_download.qyzx_btn")).click();

            List<WebElement> list = getNavList(driver,null,".xsdd_time_bm.index_3","li",2);
            for (int i = 0; i < list.size(); i++) {
                list = getNavList(driver,null,".xsdd_time_bm.index_3","li",2);
                list.get(i).findElement(By.tagName("a")).click();
                Thread.sleep(500);
            }
            list = getNavList(driver,null,".xsdd_time_bm.index_3","li",2);
            list.get(0).findElement(By.tagName("a")).click();
            Thread.sleep(500);

            list = getNavList(driver,null,".xsdd_time_bm.index_4","li",2);
            for (int i = 0; i < list.size(); i++) {
                list = getNavList(driver,null,".xsdd_time_bm.index_4","li",2);
                list.get(i).findElement(By.tagName("a")).click();
                Thread.sleep(500);
            }
            Thread.sleep(500);
            Reporter.log("对账单 账单查询成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("对账单 账单查询失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

}
