package com.selenium.fuyou.announcementManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.fuyou.fuYouMethod.getNavList;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

public class announcementList {

    //region 发布公告

    public boolean announcement(WebDriver driver){
        try{
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[4]/div[1]/label/a")).click();
            Thread.sleep(1000);
            searchEmp(driver,new String[]{"测试","张三","17607136666",""});
            Thread.sleep(500);
            checkEmp(driver,new int[]{2,5,7},new int[]{0,0,0});
            Thread.sleep(500);
            release(driver,"放假通知","没有");
            Thread.sleep(500);
            checkEmp(driver,new int[]{1,2,3},new int[0]);
            Thread.sleep(500);
            release(driver,"节假日通知","上班");
            Thread.sleep(500);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("公告发布失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //搜索
    public void searchEmp(WebDriver driver,String [] search){
        try{

            for (int i = 0;i < search.length;i++){
                driver.findElement(By.id("txtkey")).sendKeys(search[i]);
                Thread.sleep(500);
                driver.findElement(By.className("iconSearch")).click();
                Thread.sleep(1000);
                boolean flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                if(flag){
                    driver.findElement(By.className("zeromodal-close")).click();
                }
                driver.findElement(By.id("txtkey")).clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //选择发送成员
    public void checkEmp(WebDriver driver,int [] check,int [] uncheck){
        try{
            if(check.length != 0){
                List<WebElement> checkList = getNavList(driver,null,"fbgg_choice","li",0);
                for (int i = 0; i < check.length; i++) {
                    if(check[i] < checkList.size()) {
                        Thread.sleep(500);
                        checkList.get(check[i]).click();
                    }
                }
                if(uncheck.length != 0){
                    for (int i = 0; i < uncheck.length; i++) {
                        checkList = getNavList(driver,null,"fbgg_con_bg","span",0);
                        if(uncheck[i] < checkList.size()){
                            Thread.sleep(500);
                            checkList.get(uncheck[i]).findElement(By.tagName("i")).click();
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //发布
    public void release(WebDriver driver,String title,String content){
        try{
            driver.findElement(By.id("AfficheTitle")).sendKeys(title);
            Thread.sleep(500);
            driver.findElement(By.id("AfficheContent")).sendKeys(content);
            Thread.sleep(500);
            driver.findElement(By.className("jnk_send")).click();
            Thread.sleep(500);
            boolean flag = isExistBoxOrExistButton(driver,"zeromodal-title1",1);
            if(flag){
                releaseFail(driver);
            }
            flag = isExistBoxOrExistButton(driver,".zeromodal-body.zeromodal-overflow-y",2);
            if(flag){
                releaseSuccess(driver);
                Reporter.log("公告管理 公告列表 公告发布成功，公告标题：" + title+"<br/>");
            }
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("公告管理 公告列表 公告发布失败，错误："+e.toString()+"<br/>");
        }
    }

    //发布成功后操作
    public void releaseSuccess(WebDriver driver){
        try{
            List<WebElement> checkList = getNavList(driver,null,"fbgg_con_bg","span",0);
            if(checkList.size() == 0){
                driver.findElement(By.cssSelector(".zeromodal-btn.zeromodal-btn-default")) .click();
                return;
            }
            driver.findElement(By.cssSelector(".zeromodal-btn.zeromodal-btn-primary.anblock")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //发布失败后操作
    public void releaseFail(WebDriver driver){
        try{
            Thread.sleep(500);
            driver.findElement(By.className("zeromodal-close")).click();
            Thread.sleep(500);
            driver.findElement(By.id("AfficheTitle")).clear();
            driver.findElement(By.id("AfficheContent")).clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //endregion

    //region 删除公告

    public boolean deleteAnnouncement(WebDriver driver){
        try{
            boolean flag = isExistBoxOrExistButton(driver,"/html/body/div[4]/div[3]/table/tbody/tr[2]/td[7]/a",3);
            if(flag){
                Thread.sleep(500);
                driver.findElement(By.xpath("/html/body/div[4]/div[3]/table/tbody/tr[2]/td[7]/a")).click();
                flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                if(flag){
                    Thread.sleep(500);
                    driver.findElement(By.cssSelector(".zeromodal-btn.zeromodal-btn-primary.anblock")).click();
                    Reporter.log("公告管理 公告列表  公告删除成功"+"<br/>");
                    return true;
                }else{
                    return true;
                }
            }else{
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("公告管理 公告列表 公告发布失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

}
