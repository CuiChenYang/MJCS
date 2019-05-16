package com.selenium.base;

import com.selenium.utils.PropertiesConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

/**
 * Created by 李啸天 on 2019/3/27.
 * 基类
 */
public class DriverBase {
    public WebDriver driver;

    //key
    public String key = PropertiesConfig.getInstance().getProperty("driver.url");

    public WebDriver driverName(){
        System.setProperty("webdriver.chrome.driver",key);
        return new ChromeDriver();
    }
    /**
     * 获取driver
     * */

    public WebDriver driverName(DesiredCapabilities caps){
        System.setProperty("webdriver.chrome.driver",key);
        return new ChromeDriver(caps);
    }

    public static DesiredCapabilities setDownloadsPath(String downloadsPath){
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", downloadsPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return caps;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
