package com.selenium.fuyou;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class fuYouMethod {

    //日期输入
    public static void inputSearchDate(WebDriver driver, String startDate, String endDate, String startDateJSPath, String endDateJSPath){
        try{
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("document.getElementById('"+startDateJSPath+"').removeAttribute('readonly')");
            driver.findElement(By.id(startDateJSPath)).sendKeys(startDate);
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("document.getElementById('"+endDateJSPath+"').removeAttribute('readonly')");
            driver.findElement(By.id(endDateJSPath)).sendKeys(endDate);
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //清空日期输入
    public static void clearDate(WebDriver driver, String startDateJSPath, String endDateJSPath){
        try{
            ((JavascriptExecutor) driver).executeScript("document.getElementById('"+startDateJSPath+"').removeAttribute('readonly')");
            ((JavascriptExecutor) driver).executeScript("document.getElementById('"+endDateJSPath+"').removeAttribute('readonly')");
            Thread.sleep(500);
            driver.findElement(By.id(startDateJSPath)).clear();
            Thread.sleep(500);
            driver.findElement(By.id(endDateJSPath)).clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //获取导航列表
    //byState：0 id,1 className,2 cssSelector,3 xPath
    public static List<WebElement> getNavList(WebDriver driver, WebElement element, String ulPath, String findTagName, int byState){
        try {
            if(element == null){
                switch (byState){
                    case 0:element = driver.findElement(By.id(ulPath));break;
                    case 1:element = driver.findElement(By.className(ulPath));break;
                    case 2:element = driver.findElement(By.cssSelector(ulPath));break;
                    case 3:element = driver.findElement(By.xpath(ulPath));break;
                }
            }
            List<WebElement> liList = element.findElements(By.tagName(findTagName));
            return liList;
        }catch (Exception e){
            return null;
        }
    }

    //判断下拉列表是否超出索引
    public static int trySelectGet(WebDriver driver,String path,int num){
        try{
            WebElement ele = driver.findElement(By.id(path));
            Select downList = new Select(ele);
            downList.selectByIndex(num);
            return num;
        }catch (Exception e){
            return 0;
        }
    }

    //返回当前时间  格式为：yyyyMMddHHmmss（例：20190401160321）
    public static String nowDate() {
        //获取当前时间并进行格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

    //验证是否有弹窗
    public static boolean isAlertPresent(WebDriver driver){
        try {
            driver.switchTo().alert();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //是否存在窗体
    public static boolean isExistBoxOrExistButton(WebDriver driver,String path,int num){
        try{
            switch (num){
                case 0 :
                    driver.findElement(By.id(path));
                    break;
                case 1 :
                    driver.findElement(By.className(path));
                    break;
                case 2 :
                    driver.findElement(By.cssSelector(path));
                    break;
                case 3 :
                    driver.findElement(By.xpath(path));
                    break;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //匹配导航栏列表
    public static int getNavListId(String findName,List<WebElement> list){
        int num = list.size();
        int result = -1;
        for (int i = 0; i < num; i++) {
            if(list.get(i).getText().equals(findName)){
                result = i;
                break;
            }
        }
        return result;
    }

}
