package com.selenium.flx;

import com.selenium.base.DriverBase;
import com.selenium.flx.customService.customDetail;
import com.selenium.flx.customerManagement.cooperationCustomRelation;
import com.selenium.flx.customerManagement.cooperationManager;
import com.selenium.flx.customerManagement.cusProfileAlterManager;
import com.selenium.flx.financeManagement.rechargeQueryList;
import com.selenium.flx.operateManagement.agentList;
import com.selenium.flx.operateManagement.custom_power;
import com.selenium.flx.order.editOrder;
import com.selenium.flx.custom.editCustom;
import com.selenium.flx.custom.sepecEditCustom;
import com.selenium.flx.saleManagement.bankCardCountList;
import com.selenium.flx.saleManagement.prepaidCardCountList;
import com.selenium.flx.systemManagement.*;
import com.selenium.fuyou.fuYou;
import com.selenium.utils.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;


import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

@Slf4j
public class flx extends DriverBase {
    public WebDriver driver = driverName();
    public String windowsHandle;
    public sepecEditCustom se = new sepecEditCustom();
    public editCustom custom = new editCustom();
    public editOrder order = new editOrder();
    public customDetail cd = new customDetail();
    //    public fuYou fy = new fuYou();
    //测试地址
    public String flxUrl = PropertiesConfig.getInstance().getProperty("driver.flx.url");
    //登录用户名
    private String flxUserId = PropertiesConfig.getInstance().getProperty("driver.flx.userId");
    //登录密码
    private String flxPassword = PropertiesConfig.getInstance().getProperty("driver.flx.passWord");
    //开关用于判断是否开启特殊企业开户
    private String flxOpenSwitch = PropertiesConfig.getInstance().getProperty("driver.flx.openSwitch");
    //是否打印日志
    public static boolean journal = true;
    //ReportNG 只能显示字符而无法显示成链接，则取消注释
    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    @Test
    public void flx() {

        //ReportNG 只能显示字符而无法显示成链接，则取消注释
        System.setProperty(ESCAPE_PROPERTY, "false");

        //chrom插件路径
        driver.get(flxUrl);
        driver.manage().window().maximize();
    }

    /**
     * 登录
     */
    @Test(dependsOnMethods = "flx", description = "登录")
    public void login() {
        try {
            Thread.sleep(1000);
            windowsHandle = driver.getWindowHandle();
            driver.findElement(By.id("userId")).sendKeys("789456");
            driver.findElement(By.id("password")).sendKeys(flxPassword);
            driver.findElement(By.className("log")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-button-text")).click();
            driver.findElement(By.id("userId")).click();
            updateInput(driver, "id", "userId", flxUserId);
            driver.findElement(By.className("log")).click();
            Thread.sleep(500);
            if (journal) {
                Reporter.log("登入flx成功<br/>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("登录flx。失败。错误：" + e.toString() + "<br/>");
                driver.findElement(By.id("asdf")).click();
            }
        }
    }


//region    系统管理

    //用户管理
    @Test(dependsOnMethods = "login", description = "系统管理-用户管理", alwaysRun = true)
    public void userManage() {
        driver.findElement(By.id("1021")).click();
        driver.findElement(By.id("7")).click();
        userManage u = new userManage();
        u.selectUser(driver);
    }

    //菜单管理
    @Test(dependsOnMethods = "userManage", description = "系统管理-菜单管理", alwaysRun = true)
    public void menuManage() {
        driver.findElement(By.id("4")).click();
        menuManage m = new menuManage();
        m.menu(driver);
    }

    //授权管理
    @Test(dependsOnMethods = "menuManage", description = "系统管理-授权管理", alwaysRun = true)
    public void authorizationManage() {
        driver.findElement(By.id("5")).click();
        authorizationManage a = new authorizationManage();
        a.roleAuthorization(driver);
    }

    //角色管理
    @Test(dependsOnMethods = "authorizationManage", description = "系统管理-角色管理", alwaysRun = true)
    public void roleManage() {
        driver.findElement(By.id("6")).click();
        roleManage r = new roleManage();
        r.roleManage(driver);
    }

    //参数管理
    @Test(dependsOnMethods = "roleManage", description = "系统管理-参数管理", alwaysRun = true)
    public void parameterManage() {
        driver.findElement(By.id("1101")).click();
        parameterManage p = new parameterManage();
        p.parameterManage(driver);
    }

    //应用功能管理
    @Test(dependsOnMethods = "parameterManage", description = "系统管理-应用功能管理", alwaysRun = true)
    public void appFunctionManage() {
        driver.findElement(By.id("3")).click();
        appFunctionManage a = new appFunctionManage();
        a.appFunctionManage(driver);
    }

    //组织机构管理
    @Test(dependsOnMethods = "appFunctionManage", description = "系统管理-组织机构管理", alwaysRun = true)
    public void organizationManage() {
        driver.findElement(By.id("8")).click();
        organizationManage o = new organizationManage();
        o.organizationManage(driver);
    }

    //接口权限管理
    @Test(dependsOnMethods = "organizationManage", description = "系统管理-接口权限管理", alwaysRun = true)
    public void interfacesPowerManage() {
        driver.findElement(By.id("1301")).click();
        interfacesPowerManage i = new interfacesPowerManage();
        i.interfacesPowerManage(driver);
    }

    //客户IP设置
    @Test(dependsOnMethods = "interfacesPowerManage", description = "系统管理-客户IP设置", alwaysRun = true)
    public void customerIPSettings() {
        driver.findElement(By.id("1641")).click();
        customerIPSettings c = new customerIPSettings();
        c.customerIPSettings(driver);
    }

    //第三方账号管理
    @Test(dependsOnMethods = "customerIPSettings", description = "系统管理-第三方账号管理", alwaysRun = true)
    public void thirdPartyAccountManage() {
        driver.findElement(By.id("3021")).click();
        thirdPartyAccountManage t = new thirdPartyAccountManage();
        t.thirdPartyAccountManage(driver);
    }

    //其他(设置安全策略、配置业务字典)
    @Test(dependsOnMethods = "thirdPartyAccountManage", description = "系统管理-其他(设置安全策略、配置业务字典)", alwaysRun = true)
    public void other() {
        driver.findElement(By.id("1102")).click();
        other o = new other();
        o.installSafeStrategy(driver);
        o.disposeTransactionDictionary(driver);
    }

    //系统账户优分设置
    @Test(dependsOnMethods = "other", description = "系统管理-系统账户优分设置", alwaysRun = true)
    public void queryShopScore() {
        driver.findElement(By.id("2344")).click();
        queryShopScore q = new queryShopScore();
        q.queryShopScore(driver);
    }

    //系统账户优分设置变更记录
    @Test(dependsOnMethods = "queryShopScore", description = "系统管理-系统账户优分设置变更记录", alwaysRun = true)
    public void queryShopScoreAlter() {
        driver.findElement(By.id("2361")).click();
        queryShopScoreAlter q = new queryShopScoreAlter();
        q.queryShopScoreAlter(driver);
    }

    //日志查询(用户登录日志、用户操作日志)
    @Test(dependsOnMethods = "queryShopScoreAlter", description = "系统管理-日志查询(用户登录日志、用户操作日志)", alwaysRun = true)
    public void queryLog() {
        driver.findElement(By.id("2301")).click();
        queryLog q = new queryLog();
        q.userLoginLog(driver);
        q.userOperateLog(driver);
    }
//endregion

//region    客户管理

    //客户档案变更记录
    @Test(dependsOnMethods = "queryLog", description = "客户管理--客户档案变更记录", alwaysRun = true)
    public void cusProfileAlterManager() {
        driver.findElement(By.id("1061")).click();
        driver.findElement(By.id("1481")).click();
        cusProfileAlterManager c = new cusProfileAlterManager();
        c.cusProfileAlterManager(driver);
    }

    //合作伙伴档案管理
    @Test(dependsOnMethods = "cusProfileAlterManager", description = "客户管理--合作伙伴档案管理", alwaysRun = true)
    public void cooperationManager() {
        driver.findElement(By.id("2561")).click();
        cooperationManager c = new cooperationManager();
        c.cooperationManager(driver);
    }

    //客户与合作伙伴关系管理
    @Test(dependsOnMethods = "cooperationManager", description = "客户管理--客户与合作伙伴关系管理", alwaysRun = true)
    public void cooperationCustomRelation() {
        driver.findElement(By.id("2562")).click();
        cooperationCustomRelation c = new cooperationCustomRelation();
        c.cooperationCustomRelation(driver);
    }


    /**
     * 正常开户
     */
    @Test(dependsOnMethods = "login", description = "正常开户")
    public void normalOpenCustom() {
        if (!se.normalCustom(driver)) {
            driver.findElement(By.id("asdf")).click();
        }
    }

    /**
     * 客户管理 企业审核
     */
    @Test(dependsOnMethods = "normalOpenCustom", description = "客户管理 企业审核")
    public void auditCustom() {
        if (!custom.queryCustom(driver, se.customNo) || !custom.auditCustom(driver))
            driver.findElement(By.id("asdf")).click();
    }

    //region     特殊开户

    /**
     * 特殊开户
     */
    @Test(dependsOnMethods = "queryDetail", description = "特殊开户")
    public void specialOpenCustom_1() {
        //判断是否开启特殊开户
        if (Boolean.parseBoolean(flxOpenSwitch)) {
            Reporter.log("-----------以下为特殊开户。" + "<br/>");
            journal = false;

            login();
            if (se.custom01(driver) && specialOpenCustomProcedure())
                Reporter.log("特殊开户1----开户并充值一千万优分成功。企业号：" + se.customNo + "<br/>");
            else
                Reporter.log("特殊开户1----开户失败" + "<br/>");

            login();
            if (se.custom02(driver) && specialOpenCustomProcedure())
                Reporter.log("特殊开户2----开户并充值一千万优分成功。企业号：" + se.customNo + "<br/>");
            else
                Reporter.log("特殊开户1----开户失败" + "<br/>");
        } else {
            Reporter.log("特殊开户未开启" + "<br/>");
        }
    }

    //特殊开户流程
    public boolean specialOpenCustomProcedure() {
        //客户管理 企业审核
        if (!custom.queryCustom(driver, se.customNo))
            return false;
        if (!custom.auditCustom(driver))
            return false;
        //销售管理 订单录入
        if (!order.entryOrder(se.customNo, driver))
            return false;
        //销售管理 订单复核
        if (!order.checkOrder(se.customNo, driver))
            return false;
        //首次登录激活企业
        fuYou fy = new fuYou();
        if (!fy.login(se.customNo, "123456"))
            return false;
        //激活成功后重新登录 并回复订单
        if (!fy.login(se.customNo, "123456"))
            return false;
        if (!fy.replyCustomOrder(se.customNo))
            return false;
        else
            fy.driver.close();
        //财务管理 订单业务 订单经办
        if (!order.handleOrder(driver))
            return false;
        //财务管理 订单激活
        if (!order.activateOrder(driver))
            return false;
        //完成后注销
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/p/span[2]/a[2]")).click();
        return true;
    }
    //endregion

//endregion

//region    销售管理

    //银行卡库存查询
    @Test(dependsOnMethods = "cooperationCustomRelation", description = "销售管理--银行卡库存查询", alwaysRun = true)
    public void bankCardCountList() {
        driver.findElement(By.id("1081")).click();
        driver.findElement(By.id("1201")).click();
        bankCardCountList b = new bankCardCountList();
        b.bankCardCountList(driver);
    }

    //充值卡库存查询
    @Test(dependsOnMethods = "bankCardCountList", description = "销售管理--充值卡库存查询", alwaysRun = true)
    public void prepaidCardCountList() {
        driver.findElement(By.id("1241")).click();
        prepaidCardCountList p = new prepaidCardCountList();
        p.prepaidCardCountList(driver);
    }

    /**
     * 销售管理 订单录入
     */
    @Test(dependsOnMethods = "auditCustom", description = "销售管理 订单录入")
    public void entryOrder() {
        if (!order.entryOrder(se.customNo, driver))
            driver.findElement(By.id("asdf")).click();
    }

    /**
     * 销售管理 订单复核
     */
    @Test(dependsOnMethods = "entryOrder", description = "销售管理 订单复核")
    public void checkOrder() {
        //订单复核
        if (!order.checkOrder(se.customNo, driver))
            driver.findElement(By.id("asdf")).click();
    }
//endregion

//region    运营管理

    //客户绑定
    @Test(dependsOnMethods = "prepaidCardCountList", description = "运营管理--客户绑定", alwaysRun = true)
    public void custom_power() {
        driver.findElement(By.id("1121")).click();
        driver.findElement(By.id("1701")).click();
        custom_power q = new custom_power();
        q.custom_power(driver);
    }

    //代理商管理
    @Test(dependsOnMethods = "custom_power", description = "运营管理--代理商管理", alwaysRun = true)
    public void agentList() {
        driver.findElement(By.id("2921")).click();
        agentList a = new agentList();
        a.agentList(driver);
    }


//endregion

//region    财务管理

    //批量代充业务（查询）
//    @Test(dependsOnMethods = "login", description = "财务管理--批量代充业务（查询）", alwaysRun = true)
//    public void rechargeQueryList() {
//        driver.findElement(By.id("1261")).click();
//        driver.findElement(By.id("1591")).click();
//        driver.findElement(By.id("1596")).click();
//        rechargeQueryList r = new rechargeQueryList();
//        r.rechargeQueryList(driver);
//    }


    /**
     * 财务管理 订单业务 订单经办
     */
    @Test(dependsOnMethods = "againLoginFuYou", description = "财务管理 订单业务 订单经办")
    public void handleOrder() {
        if (!order.handleOrder(driver))
            driver.findElement(By.id("asdf")).click();
    }

    /**
     * 财务管理 订单激活
     */
    @Test(dependsOnMethods = "handleOrder", description = "财务管理 订单激活")
    public void activateOrder() {
        if (!order.activateOrder(driver))
            driver.findElement(By.id("asdf")).click();
    }
//endregion

//region    矩阵还款
//endregion

//region    客服明细

    /**
     * 客服明细查询 查询显示余额三秒后注销用户
     */
    @Test(dependsOnMethods = "activateOrder", description = "客服明细查询 查询显示余额三秒后注销用户")
    public void queryDetail() {
        if (!cd.queryDetail(driver, se.customNo))
            driver.findElement(By.id("asdf")).click();
    }
//endregion

//region    绩效管理
//endregion

//region    银联扫码
//endregion


    /**
     * 首次登录激活企业
     */
    @Test(dependsOnMethods = "checkOrder", description = "首次登录激活企业")
    public void firstLoginFuYou() {
        //首次登录激活企业
//        if (!fy.login(se.customNo, "123456"))
//        driver.findElement(By.id("asdf")).click();
    }

    /**
     * 激活成功后重新登录 并回复订单
     */
    @Test(dependsOnMethods = "firstLoginFuYou", description = "激活成功后重新登录 并回复订单")
    public void againLoginFuYou() {
        //激活成功后重新登录
//        if (!fy.login(se.customNo, "123456") || !fy.replyCustomOrder(se.customNo))
//            driver.findElement(By.id("asdf")).click();
//        else {
//            if (journal) {
//                Reporter.log("回复企业订单成功，企业号：" + se.customNo + "订单号为：" + order.orderId + "<br/>");
//            }
//            fy.driver.close();
//        }
    }


}
