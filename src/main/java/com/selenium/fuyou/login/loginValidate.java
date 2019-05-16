package com.selenium.fuyou.login;

import com.selenium.test.baidu.OcrTest;
import com.selenium.utils.PropertiesConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class loginValidate {
    //读取验证码
    public static String validateCoding(WebDriver driver){
        Map<String,Object> result = new HashMap<>();
        try {
            WebElement ele = driver.findElement(By.id("imgVerifyCode"));
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            BufferedImage fullImage = ImageIO.read(screenshot);
            Point point = ele.getLocation();
            int width = ele.getSize().getWidth();
            int height = ele.getSize().getHeight();
            BufferedImage eleScreenshot = fullImage.getSubimage(point.x,point.y,width,height);
            ImageIO.write(eleScreenshot,"png",screenshot);
            File screenshotLocation = new File(PropertiesConfig.getInstance().getProperty("fuYou.validateCodeLocation"));
            FileUtils.copyFile(screenshot, screenshotLocation);
            result = OcrTest.orcImage(screenshotLocation.toString());
            boolean flag = (boolean) result.get("states");
            while(!flag){
                Thread.sleep(1000*5);
                result = OcrTest.orcImage(screenshotLocation.toString());
                flag = (boolean) result.get("states");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  (String)result.get("words");
    }
}
