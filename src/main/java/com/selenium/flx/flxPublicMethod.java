package com.selenium.flx;

import com.selenium.utils.JdbcUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.selenium.flx.flx.journal;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;
import static com.selenium.fuyou.fuYouMethod.trySelectGet;

public class flxPublicMethod {

    /**
     * 清除框内数据并重新赋值
     *
     * @param driver
     * @param byState id , xpath , className , cssSelector
     * @param url
     * @param value
     */
    public static void updateInput(WebDriver driver, String byState, String url, String value) {
        switch (byState) {
            case "id":
                driver.findElement(By.id(url)).clear();
                driver.findElement(By.id(url)).sendKeys(value);
                break;
            case "xpath":
                driver.findElement(By.xpath(url)).clear();
                driver.findElement(By.xpath(url)).sendKeys(value);
                break;
            case "className":
                driver.findElement(By.className(url)).clear();
                driver.findElement(By.className(url)).sendKeys(value);
                break;
            case "cssSelector":
                driver.findElement(By.cssSelector(url)).clear();
                driver.findElement(By.cssSelector(url)).sendKeys(value);
                break;
            case "name":
                driver.findElement(By.name(url)).clear();
                driver.findElement(By.name(url)).sendKeys(value);
                break;
        }

    }

    //出现此元素就点击若不出现则一直等（每一秒判断一次）
    public static void waitClick(WebDriver driver, String url, int num) throws InterruptedException {
        while (!isExistBoxOrExistButton(driver, url, num)) {
            Thread.sleep(1000);
        }
        switch (num) {
            case 0:
                driver.findElement(By.id(url)).click();
                break;
            case 1:
                driver.findElement(By.className(url)).click();
                break;
            case 2:
                driver.findElement(By.cssSelector(url)).click();
                break;
            case 3:
                driver.findElement(By.xpath(url)).click();
                break;
        }
    }


    //失败原因图片截取并加入日志
    public static void taskScreenShot(WebDriver driver) {
        long date = System.currentTimeMillis();
        String path = String.valueOf(date);
        String cusPath = System.getProperty("user.dir");
        path = path + ".png";
        String screenPath = cusPath + "/" + path;
        System.out.println(screenPath);
        //实现截图
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(screenPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Reporter.log("<a href=\"" + screenPath + "\">失败原因图片</a>" + "<br/>");
//        Reporter.log("< a href= " + screenPath + " target=_blank>失败原因图片</ a>", true);
//        Reporter.log("<img src=" + screenPath +">", true);
//        Reporter.log("失败图片地址为"+screenPath);
//        Reporter.log("<img src=\"../../" + screenPath + "\"/>");
//        Reporter.log("<a src=\"../../" + screenPath + "\"/>");


        Reporter.log("<a href=\"" + screenPath + "\">" + "<img src='" + screenPath + "' hight='100' width='100'/>" + "</a>");

    }


    /**
     * 切换iframe(切换为子级(i=0)或同级)
     *
     * @param driver
     * @param iframeSrc iframe[contains(@src,'iframeSrc')] iframe的src
     * @param i         切换为子级(0)或同级
     */
    public static void switchIframe(WebDriver driver, String iframeSrc, int i) {
        if (i == 0)
            driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'" + iframeSrc + "')]")));
    }

    /**
     * 查询下拉列表
     *
     * @param driver
     * @param zero       是否从第一个开始（0为）
     * @param spinnerId  下拉框的id
     * @param listId     列表所在div的id
     * @param queryXpath 查询按钮的xpath
     * @param first      是否重新选择列表的第一个值
     * @throws InterruptedException
     */
    public static void querySpinner(WebDriver driver, boolean zero, String spinnerId, String listId, String queryXpath, boolean first) throws InterruptedException {

        //点击下拉框
        driver.findElement(By.id(spinnerId)).click();
        Thread.sleep(1000);
        //获取列表
        List<WebElement> list = driver.findElements(By.xpath("//div[@id='" + listId + "']/div[1]/div[2]/div/table/tbody/tr"));
        //循环点击列表的每个值
        for (int i = zero ? 0 : 1; i < list.size(); i++) {
            list.get(i).click();
            driver.findElement(By.xpath(queryXpath)).click();
            loading(driver);
            Thread.sleep(1500);
            if (i != list.size() - 1)
                driver.findElement(By.id(spinnerId)).click();
        }
        if (first) {
            driver.findElement(By.id(spinnerId)).click();
            Thread.sleep(300);
            list.get(0).click();
        }
    }

    /**
     * 点击某个按钮跳出新的tab页面，停顿两秒后关闭新页面回到旧页面
     *
     * @param driver
     * @param cssSelector 按钮的cssSelector
     * @throws InterruptedException
     */
    public static void lookTabAndCloseTab(WebDriver driver, String cssSelector) throws InterruptedException {
        //保存当前页面的句柄
        String oldHandle = driver.getWindowHandle();
        //点击按钮跳出页面
        driver.findElement(By.cssSelector(cssSelector)).click();
        Thread.sleep(2000);
        //获取当前浏览器打开的页面Handle集合
        Set<String> set = driver.getWindowHandles();
        //定位到新打开的tab页
        for (String h : set) {
            if (!h.equals(driver.getWindowHandle())) {
                driver.switchTo().window(h);
            }
        }
        //关闭打印页面，回到之前页面
        driver.close();
        driver.switchTo().window(oldHandle);
    }

    /**
     * 使用鼠标左键单击或右键单击
     * 或左键单击后再右键单击
     * 集合的某元素（若元素存在）
     * 返回值为是否存在此元素
     *
     * @param driver
     * @param listClassName 集合的className
     * @param text          需要单击的元素名称
     * @param click         0为左键单击，1为右键单击，2为左键单击后再右键单击
     * @throws InterruptedException
     */
    public static boolean mouseClick(WebDriver driver, String listClassName, String text, int click) throws InterruptedException {
        Actions mouse = new Actions(driver);
        Thread.sleep(500);
        List<WebElement> list = driver.findElements(By.className(listClassName));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equals(text)) {
                if (click == 0) {
                    mouse.click(list.get(i)).perform();
                    Thread.sleep(500);
                }
                if (click == 1) {
                    mouse.contextClick(list.get(i)).perform();
                    Thread.sleep(500);

                }
                if (click == 2) {
                    Thread.sleep(500);
                    mouse.click(list.get(i)).perform();
                    Thread.sleep(500);
                    mouse.contextClick(list.get(i)).perform();
                }
                return true;
            }
        }
        return false;
    }

    //页面加载完毕前，休眠程序
    public static void loading(WebDriver driver) throws InterruptedException {
        boolean b = true;
        do {
            try {
                Thread.sleep(1000);
                driver.findElement(By.cssSelector(".mini-mask-msg.mini-mask-loading"));
            } catch (Exception e) {
                e.printStackTrace();
                b = false;
            }
        } while (b);
        Thread.sleep(1000);
    }
}
