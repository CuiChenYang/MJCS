package com.selenium.flx.custom;

import com.selenium.utils.PhoneUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.*;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

/**
 * 开户
 */
@Slf4j
public class sepecEditCustom {
    public String customNo;

    /**
     * 开户账户01:
     * 企业客户开户信用卡还款固定手续费:
     * 协议信息除了企业批量还信用卡之外全部勾上;
     * 信用卡协议信息:
     * 信用卡单笔收费为3.00;不存在阶梯费率
     * 单笔还款手续费上限(元):为默认值, 单笔还款手续费下限(元): 3.00
     * 个人账户还信用卡单日上限(元)为:30000.00;   信用卡单日接收还款上限(元):10000.00
     * <p>
     * 积分兑换协议信息
     * 积分兑换百分比收费费率:3.00
     *
     * @param driver
     */
    public boolean custom01(WebDriver driver) {
        try {
            boolean b1 = this.saveCustomTop(driver);
            //进入协议信息
            driver.findElement(By.xpath("//*[@id=\"mini-2$3\"]/span")).click();

            //勾选业务权限，除去企业批量还信用卡(商城业务与蜘蛛网为默认选中)
            List<String> ls = new ArrayList<>();
            ls.add("商城业务");
            ls.add("蜘蛛网");
            ls.add("企业批量还信用卡");
            clickChecked(driver, ls);

            //更改信用卡协议信息
            //输入框存在默认值时，若需要修改其值。使用sendKeys(Keys.chord(Keys.CONTROL,"a"),"value") value为需要更改的值
            driver.findElement(By.id("contract.creditSingleFee$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "3.00");
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            driver.findElement(By.id("contract.individualCountToCreditUp$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "30000.00");

            //更改积分兑换协议信息
            driver.findElement(By.id("contract.exchangeServiceFee$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "3.00");

            boolean b2 = this.saveCustomBottom(driver);
            return b1 && b2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 开户账户02:
     * 企业客户开户信用卡还款阶梯手续费:
     * 协议信息除了企业批量还信用卡之外全部勾上;
     * 信用卡协议信息:
     * 信用卡单笔收费为0.00;信用卡阶梯费率金额(起)为0, 金额(止)为空, 费率为3
     * 单笔还款手续费上限(元):100.00, 单笔还款手续费下限(元): 3.00
     * 个人账户还信用卡单日上限(元)为:30000.00;   信用卡单日接收还款上限(元):10000.00
     * <p>
     * 积分兑换协议信息
     * 积分兑换百分比收费费率:3.00
     *
     * @param driver
     */
    public boolean custom02(WebDriver driver) {
        try {
            boolean b1 = this.saveCustomTop(driver);
            //进入协议信息
            driver.findElement(By.xpath("//*[@id=\"mini-2$3\"]/span")).click();

            //勾选业务权限，除去企业批量还信用卡(商城业务与蜘蛛网为默认选中)
            List<String> ls = new ArrayList<>();
            ls.add("商城业务");
            ls.add("蜘蛛网");
            ls.add("企业批量还信用卡");
            clickChecked(driver, ls);

            //更改信用卡协议信息
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            driver.findElement(By.id("stepRate.rate1")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "3");
            driver.findElement(By.id("contract.creditSingleUp$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "100.00");
            driver.findElement(By.id("contract.individualCountToCreditUp$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "30000.00");

            //更改积分兑换协议信息
            driver.findElement(By.id("contract.exchangeServiceFee$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "3.00");

            boolean b2 = this.saveCustomBottom(driver);
            return b1 && b2;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 正常流程开户
     */
    public boolean normalCustom(WebDriver driver) {
        try {
            boolean b1 = this.saveCustomTop(driver);
            //进入协议信息
            driver.findElement(By.xpath("//*[@id=\"mini-2$3\"]/span")).click();

            //勾选业务权限，除去企业批量还信用卡(商城业务与蜘蛛网为默认选中)
            List<String> ls = new ArrayList<>();
            ls.add("商城业务");
            ls.add("蜘蛛网");
            ls.add("企业批量还信用卡");
            clickChecked(driver, ls);

            boolean b2 = this.saveCustomBottom(driver);
            return b1 && b2;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("添加客户信息失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    /**
     * 添加客户信息
     */
    public boolean saveCustomTop(WebDriver driver) {
        try {
            //客户管理
            driver.findElement(By.id("1061")).click();
            //客户开户档案
            driver.findElement(By.id("1062")).click();
            Thread.sleep(2000);
            //点击添加按钮，弹出添加界面
            driver.switchTo().frame("mainframe");
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            //返回主窗体
            switchIframe(driver, "/custom/cusprofile/editCusProfileNew.jsp", 0);
            customNo = driver.findElement(By.name("entity.customNo")).getAttribute("value");
            driver.findElement(By.id("entity.company$text")).sendKeys("ceshi");
            //点击选择业务员
            driver.findElement(By.xpath("//*[@id=\"entity.salesmanid\"]/span/span/span[2]/span")).click();
            //返回主窗体切换业务员iframe
            switchIframe(driver, "/pub/selectcontrol/choice.jsp", 0);
            //获取第一个checkbox---业务员姓名
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();
            //返回主窗体切换业务员iframe---进入添加页面
            switchIframe(driver, "/custom/cusprofile/editCusProfileNew.jsp", 0);
            //正确的授权人姓名，身份证和电话
            updateInput(driver, "id", "entity.contactPerson$text", "test");
            driver.findElement(By.id("entity.contactPersonIdcard$text")).sendKeys("370281197811137612");
            driver.findElement(By.id("entity.cellPhone$text")).sendKeys("13305317992");
            //实名认证(需要则认证没有则跳过)
            if (isExistBoxOrExistButton(driver, "certbtn", 0)) {
                driver.findElement(By.id("certbtn")).click();
                //认证失败---点击alert继续
                waitSearch(driver, By.xpath("//*[@class='mini-messagebox-buttons']//a"));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("添加客户信息失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    /**
     * 保存信息
     */
    public boolean saveCustomBottom(WebDriver driver) {
        try {
            Thread.sleep(1000);
            driver.findElement(By.id("contract.taxiServiceFee$text")).sendKeys(Keys.chord(Keys.CONTROL, "a"), "0");
            //保存
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(2000);
            driver.findElements(By.xpath("//div[@class='mini-messagebox-buttons']/a")).get(2).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[2]")).click();
            driver.switchTo().defaultContent();
            if (journal) {
                Reporter.log("客户管理--企业:" + customNo + "--开户成功" + "<br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("客户管理--开户失败。" + e.toString() + "<br/>");
            }
            return false;
        }
    }


    /**
     * 勾选业务权限
     *
     * @param driver
     * @param ls    取消勾选的集合
     */
    public void clickChecked(WebDriver driver, List<String> ls) {
        List<WebElement> list = driver.findElements(By.xpath("//div[@class='mini-dictcheckboxgroup']/table/tbody/tr/td/div[1]/div"));
        for (int i = 0; i < list.size(); i++) {
            boolean b = true;
            for (int j = 0; j < ls.size(); j++) {
                if (list.get(i).getText().equals(ls.get(j))) {
                    b = false;
                    if (ls.size() > 1) {
                        ls.remove(j);
                    }
                }
            }
            if (b)
                list.get(i).findElement(By.cssSelector("input")).click();
        }
    }
}
