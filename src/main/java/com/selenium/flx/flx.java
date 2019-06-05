package com.selenium.flx;

import com.selenium.base.DriverBase;
import com.selenium.flx.achievementsManagement.agentInfo;
import com.selenium.flx.achievementsManagement.gradientManagement.platformCustomer;
import com.selenium.flx.achievementsManagement.gradientManagement.terminalCustomer;
import com.selenium.flx.achievementsManagement.gradientManagement.validCustomer;
import com.selenium.flx.achievementsManagement.queryTotalCustomer;
import com.selenium.flx.customerDetailed.customDetail;
import com.selenium.flx.customerDetailed.queryPrepaidCardInfo;
import com.selenium.flx.customerManagement.cooperationCustomRelation;
import com.selenium.flx.customerManagement.cooperationManager;
import com.selenium.flx.customerManagement.cusProfileAlterManager;
import com.selenium.flx.financeManagement.accountReconciliation.handleAcceptQueryList;
import com.selenium.flx.financeManagement.accountReconciliation.specialAccountChangeInputList;
import com.selenium.flx.financeManagement.creditCard.*;
import com.selenium.flx.financeManagement.orderPrinting.orderCredentialHandList;
import com.selenium.flx.financeManagement.orderPrinting.orderQueryList;
import com.selenium.flx.financeManagement.rechargeQueryList;
import com.selenium.flx.financeManagement.scoretobankQueryList;
import com.selenium.flx.financeManagement.statisticalReport.*;
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
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;


import java.util.List;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

@Slf4j
public class flx extends DriverBase {
    public WebDriver driver = driverName();
    public String windowsHandle;
    public sepecEditCustom se = new sepecEditCustom();
    public editCustom custom = new editCustom();
    public editOrder order = new editOrder();
    public customDetail cd = new customDetail();
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
                driver.findElement(By.id("returnFalse")).click();
            }
        }
    }

    //region 开户充值

    /**
     * 正常开户
     */
    @Test(dependsOnMethods = "login", description = "正常开户")
    public void normalOpenCustom() {
        if (!se.normalCustom(driver)) {
            driver.findElement(By.id("returnFalse")).click();
        }
    }

    /**
     * 客户管理 企业审核
     */
    @Test(dependsOnMethods = "normalOpenCustom", description = "客户管理 企业审核")
    public void auditCustom() {
        if (!custom.queryCustom(driver, se.customNo) || !custom.auditCustom(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    /**
     * 销售管理 订单录入
     */
    @Test(dependsOnMethods = "auditCustom", description = "销售管理 订单录入")
    public void entryOrder() {
        if (!order.entryOrder(se.customNo, driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    /**
     * 销售管理 订单复核
     */
    @Test(dependsOnMethods = "entryOrder", description = "销售管理 订单复核")
    public void checkOrder() {
        //订单复核
        if (!order.checkOrder(se.customNo, driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    /**
     * 首次登录激活企业 并回复订单
     */
    @Test(dependsOnMethods = "checkOrder", description = "首次登录激活企业 并回复订单")
    public void firstLoginFuYou() {
        fuYou fy = new fuYou();
        if (!fy.login(se.customNo, "123456") || !fy.login(se.customNo, "123456") || !fy.replyCustomOrder(se.customNo))
            driver.findElement(By.id("returnFalse")).click();
        else {
            if (journal) {
                Reporter.log("回复企业订单成功，企业号：" + se.customNo + "订单号为：" + order.orderId + "<br/>");
            }
            fy.driver.close();
        }
    }

    /**
     * 财务管理 订单业务 订单经办
     */
    @Test(dependsOnMethods = "firstLoginFuYou", description = "财务管理 订单业务 订单经办")
    public void handleOrder() {
        if (!order.handleOrder(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    /**
     * 财务管理 订单激活
     */
    @Test(dependsOnMethods = "handleOrder", description = "财务管理 订单激活")
    public void activateOrder() {
        if (!order.activateOrder(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    /**
     * 客服明细查询 查询显示余额三秒后注销用户
     */
    @Test(dependsOnMethods = "activateOrder", description = "客服明细查询 查询显示余额三秒后注销用户")
    public void queryDetail() {
        if (!cd.queryDetail(driver, se.customNo))
            driver.findElement(By.id("returnFalse")).click();
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

    //region    系统管理

    //用户管理
//    @Test(dependsOnMethods = "specialOpenCustom_1", description = "系统管理--用户管理", alwaysRun = true)
    @Test(dependsOnMethods = "login", description = "系统管理--用户管理", alwaysRun = true)
    public void userManage() {
//        driver.switchTo().defaultContent();
//        if (isExistBoxOrExistButton(driver,"//*[@id=\"wrapper\"]/div[1]/div[1]/p/span[2]/a[2]",3)){
//            driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/p/span[2]/a[2]")).click();
//        }
//        login();
        listClickByText(driver, "系统管理");
        driver.findElement(By.xpath("//a[@url='/coframe/rights/user/user_list.jsp']")).click();
        userManage u = new userManage();
        if (!u.selectUser(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //菜单管理
    @Test(dependsOnMethods = "userManage", description = "系统管理--菜单管理", alwaysRun = true)
    public void menuManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/coframe/framework/menu/menu_manage.jsp']")).click();
        menuManage m = new menuManage();
        if (!m.menu(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //授权管理
    @Test(dependsOnMethods = "menuManage", description = "系统管理--授权管理", alwaysRun = true)
    public void authorizationManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/coframe/auth/role_auth.jsp']")).click();
        authorizationManage a = new authorizationManage();
        if (!a.roleAuthorization(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //角色管理
    @Test(dependsOnMethods = "authorizationManage", description = "系统管理--角色管理", alwaysRun = true)
    public void roleManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/coframe/rights/role/role_manager.jsp']")).click();
        roleManage r = new roleManage();
        if (!r.roleManage(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //参数管理
    @Test(dependsOnMethods = "roleManage", description = "系统管理--参数管理", alwaysRun = true)
    public void parameterManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/parameter/code/bank_sys_code.jsp']")).click();
        parameterManage p = new parameterManage();
        if (!p.parameterManage(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //应用功能管理
    @Test(dependsOnMethods = "parameterManage", description = "系统管理--应用功能管理", alwaysRun = true)
    public void appFunctionManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/coframe/framework/application/application_manage.jsp']")).click();
        appFunctionManage a = new appFunctionManage();
        if (!a.appFunctionManage(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //组织机构管理
    @Test(dependsOnMethods = "appFunctionManage", description = "系统管理--组织机构管理", alwaysRun = true)
    public void organizationManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/coframe/org/organization/org_tree.jsp']")).click();
        organizationManage o = new organizationManage();
        if (!o.organizationManage(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //接口权限管理
    @Test(dependsOnMethods = "organizationManage", description = "系统管理--接口权限管理", alwaysRun = true)
    public void interfacesPowerManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/interfacepower/interfacep/interface_power.jsp']")).click();
        interfacesPowerManage i = new interfacesPowerManage();
        if (!i.interfacesPowerManage(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //客户IP设置
    @Test(dependsOnMethods = "interfacesPowerManage", description = "系统管理--客户IP设置", alwaysRun = true)
    public void customerIPSettings() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/customIp/customIpQueryList.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/customIp/customIpQueryList.jsp']")).click();
            customerIPSettings c = new customerIPSettings();
            if (!c.customerIPSettings(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("系统管理--客户IP设置--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //第三方账号管理
    @Test(dependsOnMethods = "customerIPSettings", description = "系统管理--第三方账号管理", alwaysRun = true)
    public void thirdPartyAccountManage() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/thirdAccountInfo/thirdAccountInfo.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/thirdAccountInfo/thirdAccountInfo.jsp']")).click();
            thirdPartyAccountManage t = new thirdPartyAccountManage();
            if (!t.thirdPartyAccountManage(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("系统管理--第三方账号管理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //其他--设置安全策略
    @Test(dependsOnMethods = "thirdPartyAccountManage", description = "系统管理--其他--设置安全策略", alwaysRun = true)
    public void installSafeStrategy() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1102")).click();
        driver.findElement(By.xpath("//a[@url='/policy/access_rules_list.jsp']")).click();
        other o = new other();
        if (!o.installSafeStrategy(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //其他--配置业务字典
    @Test(dependsOnMethods = "installSafeStrategy", description = "系统管理--其他--配置业务字典", alwaysRun = true)
    public void disposeTransactionDictionary() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/coframe/dict/dict_manager.jsp']")).click();
        other o = new other();
        if (!o.disposeTransactionDictionary(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //webIp白名单
    @Test(dependsOnMethods = "disposeTransactionDictionary", description = "系统管理--webIp白名单", alwaysRun = true)
    public void webIpList() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/webIp/webIpList.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/webIp/webIpList.jsp']")).click();
            webIpList w = new webIpList();
            if (!w.webIpList(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("系统管理--webIp白名单--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //系统账户优分设置
    @Test(dependsOnMethods = "webIpList", description = "系统管理--系统账户优分设置", alwaysRun = true)
    public void queryShopScore() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/other/customMember/queryShopScore.jsp']")).click();
        queryShopScore q = new queryShopScore();
        if (!q.queryShopScore(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //系统账户优分设置变更记录
    @Test(dependsOnMethods = "queryShopScore", description = "系统管理--系统账户优分设置变更记录", alwaysRun = true)
    public void queryShopScoreAlter() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/other/customMember/queryShopScoreAlter.jsp']")).click();
        queryShopScoreAlter q = new queryShopScoreAlter();
        if (!q.queryShopScoreAlter(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //日志查询--用户登录日志
    @Test(dependsOnMethods = "queryShopScoreAlter", description = "系统管理--日志查询--用户登录日志", alwaysRun = true)
    public void userLoginLog() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2301")).click();
        driver.findElement(By.xpath("//a[@url='/coframe/auth/login/queryLoginLog.jsp']")).click();
        queryLog q = new queryLog();
        if (!q.userLoginLog(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //日志查询--用户操作日志
    @Test(dependsOnMethods = "userLoginLog", description = "系统管理--日志查询--用户操作日志", alwaysRun = true)
    public void userOperateLog() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/other/webHandle/queryHandle.jsp']")).click();
        queryLog q = new queryLog();
        if (!q.userOperateLog(driver))
            driver.findElement(By.id("returnFalse")).click();
    }
//endregion

    //region    客户管理

    //客户档案变更记录
    @Test(dependsOnMethods = "userOperateLog", description = "客户管理--客户档案变更记录", alwaysRun = true)
    public void cusProfileAlterManager() {
        driver.switchTo().defaultContent();
        listClickByText(driver, "客户管理");
        driver.findElement(By.xpath("//a[@url='/custom/cusprofile/cusProfileAlterManager.jsp']")).click();
        cusProfileAlterManager c = new cusProfileAlterManager();
        if (!c.cusProfileAlterManager(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //合作伙伴档案管理
    @Test(dependsOnMethods = "cusProfileAlterManager", description = "客户管理--合作伙伴档案管理", alwaysRun = true)
    public void cooperationManager() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/custom/cooperation/cooperationManager.jsp']")).click();
        cooperationManager c = new cooperationManager();
        if (!c.cooperationManager(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //客户与合作伙伴关系管理
    @Test(dependsOnMethods = "cooperationManager", description = "客户管理--客户与合作伙伴关系管理", alwaysRun = true)
    public void cooperationCustomRelation() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/custom/cooperation/cooperationCustomRelation.jsp']")).click();
        cooperationCustomRelation c = new cooperationCustomRelation();
        if (!c.cooperationCustomRelation(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

//endregion

    //region    销售管理

    //银行卡库存查询
    @Test(dependsOnMethods = "cooperationCustomRelation", description = "销售管理--银行卡库存查询", alwaysRun = true)
    public void bankCardCountList() {
        driver.switchTo().defaultContent();
        listClickByText(driver, "销售管理");
        driver.findElement(By.xpath("//a[@url='/sales/salesInventory/bankCardCountList.jsp']")).click();
        bankCardCountList b = new bankCardCountList();
        if (!b.bankCardCountList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //充值卡库存查询
    @Test(dependsOnMethods = "bankCardCountList", description = "销售管理--充值卡库存查询", alwaysRun = true)
    public void prepaidCardCountList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/sales/salesInventory/prepaidCardCountList.jsp']")).click();
        prepaidCardCountList p = new prepaidCardCountList();
        if (!p.prepaidCardCountList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

//endregion

    //region    运营管理

    //客户绑定
    @Test(dependsOnMethods = "prepaidCardCountList", description = "运营管理--客户绑定", alwaysRun = true)
    public void custom_power() {
        driver.switchTo().defaultContent();
        listClickByText(driver, "运营管理");
        driver.findElement(By.xpath("//a[@url='/other/customSet/custom_power.jsp']")).click();
        custom_power q = new custom_power();
        if (!q.custom_power(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //代理商管理
    @Test(dependsOnMethods = "custom_power", description = "运营管理--代理商管理", alwaysRun = true)
    public void agentList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/agent/agent/agentList.jsp']")).click();
        agentList a = new agentList();
        if (!a.agentList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

//endregion

    //region    财务管理

    //批量代充业务（查询）
    @Test(dependsOnMethods = "agentList", description = "财务管理--批量代充业务（查询）", alwaysRun = true)
    public void rechargeQueryList() {
        driver.switchTo().defaultContent();
        listClickByText(driver, "财务管理");
        driver.findElement(By.id("1591")).click();
        driver.findElement(By.xpath("//a[@url='/sales/recharge/rechargeQueryList_1.jsp']")).click();
        rechargeQueryList r = new rechargeQueryList();
        if (!r.rechargeQueryList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //region 信用卡业务
    //信用卡业务--信用卡导出
    @Test(dependsOnMethods = "rechargeQueryList", description = "财务管理--信用卡业务--信用卡导出", alwaysRun = true)
    public void scoretocredit() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "1603", 0)) {
            driver.findElement(By.id("1603")).click();
            driver.findElement(By.xpath("//a[@url='/sales/order/scoretocredit.jsp']")).click();
            scoretocredit s = new scoretocredit();
            if (!s.scoretocredit(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--信用卡导出--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //信用卡业务--矩阵还款
    @Test(dependsOnMethods = "scoretocredit", description = "财务管理--信用卡业务--矩阵还款", alwaysRun = true)
    public void repayment() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/repayment/repayment.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/repayment/repayment.jsp']")).click();
            repayment r = new repayment();
            if (!r.repayment(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--矩阵还款--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //信用卡业务--还款结果查询
    @Test(dependsOnMethods = "repayment", description = "财务管理--信用卡业务--还款结果查询", alwaysRun = true)
    public void queryRepaymentAll() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/repayment/queryRepaymentAll.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/repayment/queryRepaymentAll.jsp']")).click();
            queryRepaymentAll r = new queryRepaymentAll();
            if (!r.queryRepaymentAll(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--还款结果查询--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //信用卡业务--还款异常处理
    @Test(dependsOnMethods = "queryRepaymentAll", description = "财务管理--信用卡业务--还款异常处理", alwaysRun = true)
    public void errorRepayment() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/repayment/errorRepayment.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/repayment/errorRepayment.jsp']")).click();
            errorRepayment e = new errorRepayment();
            if (!e.errorRepayment(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--还款异常处理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //信用卡业务--信用卡财务经办
    @Test(dependsOnMethods = "errorRepayment", description = "财务管理--信用卡业务--信用卡财务经办", alwaysRun = true)
    public void creditQueryList() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/sales/credit/creditQueryList_2.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/sales/credit/creditQueryList_2.jsp']")).click();
            creditQueryList c = new creditQueryList();
            if (!c.creditQueryList(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--信用卡财务经办--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //信用卡业务--还款结果导入
    @Test(dependsOnMethods = "creditQueryList", description = "财务管理--信用卡业务--还款结果导入", alwaysRun = true)
    public void importPaymentResult() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/repayment/importPaymentResult.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/repayment/importPaymentResult.jsp']")).click();
            importPaymentResult i = new importPaymentResult();
            if (!i.importPaymentResult(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--还款结果导入--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //信用卡业务--光大复核
    @Test(dependsOnMethods = "importPaymentResult", description = "财务管理--信用卡业务--光大复核", alwaysRun = true)
    public void paymentFileList() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/other/paymentTxt/paymentFileList.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/other/paymentTxt/paymentFileList.jsp']")).click();
            paymentFileList p = new paymentFileList();
            if (!p.paymentFileList(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("财务管理--信用卡业务--光大复核--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }
    //endregion

    //region 账务调账业务
    //账务调账业务--账务冲正
    @Test(dependsOnMethods = "paymentFileList", description = "财务管理--账务调账业务--账务冲正", alwaysRun = true)
    public void handleAcceptQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1608")).click();
        driver.findElement(By.xpath("//a[@url='/sales/order/handleAcceptQueryList.jsp']")).click();
        handleAcceptQueryList h = new handleAcceptQueryList();
        if (!h.handleAcceptQueryList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //账务调账业务--特殊业务调账
    @Test(dependsOnMethods = "handleAcceptQueryList", description = "财务管理--账务调账业务--特殊业务调账", alwaysRun = true)
    public void specialAccountChangeInputList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/service/specialAccountChange/specialAccountChangeInputList_1.jsp']")).click();
        specialAccountChangeInputList s = new specialAccountChangeInputList();
        if (!s.specialAccountChangeInputList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }
    //endregion

    //region 凭证打印
    //凭证打印--凭证记录查询
    @Test(dependsOnMethods = "specialAccountChangeInputList", description = "财务管理--凭证打印--凭证记录查询", alwaysRun = true)
    public void orderQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1614")).click();
        driver.findElement(By.xpath("//a[@url='/service/credentials/orderQueryList.jsp']")).click();
        orderQueryList o = new orderQueryList();
        if (!o.orderQueryList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //凭证打印--手工凭证录入
    @Test(dependsOnMethods = "orderQueryList", description = "财务管理--凭证打印--手工凭证录入", alwaysRun = true)
    public void orderCredentialHandList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/service/credentials/orderCredentialHandList.jsp']")).click();
        orderCredentialHandList o = new orderCredentialHandList();
        if (!o.orderCredentialHandList(driver))
            driver.findElement(By.id("returnFalse")).click();
    }
    //endregion

    //region统计报表
    //统计报表--每日订单汇总
    @Test(dependsOnMethods = "orderCredentialHandList", description = "财务管理--统计报表--每日订单汇总", alwaysRun = true)
    public void queryReportDailyOrderTbl() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1617")).click();
        driver.findElement(By.xpath("//a[@url='/report/reportV2/queryReportDailyOrderTbl.jsp']")).click();
        queryReportDailyOrderTbl q = new queryReportDailyOrderTbl();
        if (!q.queryReportDailyOrderTbl(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--平台优分使用汇总
    @Test(dependsOnMethods = "queryReportDailyOrderTbl", description = "财务管理--统计报表--平台优分使用汇总", alwaysRun = true)
    public void queryReportSpend() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/queryReportSpend.jsp']")).click();
        queryReportSpend q = new queryReportSpend();
        if (!q.queryReportSpend(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--手续费汇总
    @Test(dependsOnMethods = "queryReportSpend", description = "财务管理--统计报表--手续费汇总", alwaysRun = true)
    public void queryCounterFee() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/queryCounterFee.jsp']")).click();
        queryCounterFee q = new queryCounterFee();
        if (!q.queryCounterFee(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--凭证统计汇总
    @Test(dependsOnMethods = "queryCounterFee", description = "财务管理--统计报表--凭证统计汇总", alwaysRun = true)
    public void queryCertificateReport() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/certificateReport/queryCertificateReport.jsp']")).click();
        queryCertificateReport q = new queryCertificateReport();
        if (!q.queryCertificateReport(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--报表处理日志
    @Test(dependsOnMethods = "queryCertificateReport", description = "财务管理--统计报表--报表处理日志", alwaysRun = true)
    public void reportlogMangaer() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/reportlog/reportlogMangaer.jsp']")).click();
        reportlogMangaer r = new reportlogMangaer();
        if (!r.reportlogMangaer(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--账户总表
    @Test(dependsOnMethods = "reportlogMangaer", description = "财务管理--统计报表--账户总表", alwaysRun = true)
    public void queryAccountReport() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/queryAccountReport.jsp']")).click();
        queryAccountReport q = new queryAccountReport();
        if (!q.queryAccountReport(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--个人优分汇总
    @Test(dependsOnMethods = "queryAccountReport", description = "财务管理--统计报表--个人优分汇总", alwaysRun = true)
    public void queryPointsSummary() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/queryPointsSummary.jsp']")).click();
        queryPointsSummary q = new queryPointsSummary();
        if (!q.queryPointsSummary(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

    //统计报表--企业优分汇总
    @Test(dependsOnMethods = "queryPointsSummary", description = "财务管理--统计报表--企业优分汇总", alwaysRun = true)
    public void queryEnterSummary() {
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[@url='/report/queryEnterSummary.jsp']")).click();
        queryEnterSummary q = new queryEnterSummary();
        if (!q.queryEnterSummary(driver))
            driver.findElement(By.id("returnFalse")).click();
    }
    //endregion

//endregion

    //region    客服明细

    //充值卡状态查询
    @Test(dependsOnMethods = "queryEnterSummary", description = "客服明细--充值卡状态查询", alwaysRun = true)
    public void queryPrepaidCardInfo() {
        driver.switchTo().defaultContent();
        listClickByText(driver, "客服明细");
        driver.findElement(By.xpath("//a[@url='/audit/audit/queryPrepaidCardInfo.jsp']")).click();
        queryPrepaidCardInfo q = new queryPrepaidCardInfo();
        if (!q.queryPrepaidCardInfo(driver))
            driver.findElement(By.id("returnFalse")).click();
    }

//endregion

    //region    绩效管理

    //region 梯度管理
    //梯度管理--有效客户管理
    @Test(dependsOnMethods = "queryPrepaidCardInfo", description = "绩效管理--梯度管理--有效客户管理", alwaysRun = true)
    public void validCustomer() {
        driver.switchTo().defaultContent();
        listClickByText(driver, "绩效管理");
        if (isExistBoxOrExistButton(driver, "2782", 0)) {
            driver.findElement(By.id("2782")).click();
            driver.findElement(By.xpath("//a[@url='/pa/ValidCustomer/validCustomer.jsp']")).click();
            validCustomer v = new validCustomer();
            if (!v.validCustomer(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("绩效管理--梯度管理--有效客户管理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //梯度管理--平台客户管理
    @Test(dependsOnMethods = "validCustomer", description = "绩效管理--梯度管理--平台客户管理", alwaysRun = true)
    public void platformCustomer() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/pa/ValidCustomer/platformCustomer.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/pa/ValidCustomer/platformCustomer.jsp']")).click();
            platformCustomer p = new platformCustomer();
            if (!p.platformCustomer(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("绩效管理--梯度管理--平台客户管理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //梯度管理--终端客户管理
    @Test(dependsOnMethods = "platformCustomer", description = "绩效管理--梯度管理--终端客户管理", alwaysRun = true)
    public void terminalCustomer() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/pa/ValidCustomer/terminalCustomer.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/pa/ValidCustomer/terminalCustomer.jsp']")).click();
            terminalCustomer t = new terminalCustomer();
            if (!t.terminalCustomer(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("绩效管理--梯度管理--终端客户管理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }
    //endregion

    //企业关联关系--列表信息管理
    @Test(dependsOnMethods = "terminalCustomer", description = "绩效管理--企业关联关系--列表信息管理", alwaysRun = true)
    public void queryTotalCustomer() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "2786", 0)) {
            driver.findElement(By.id("2786")).click();
            driver.findElement(By.xpath("//a[@url='/pa/TotalCustomer/queryTotalCustomer.jsp']")).click();
            queryTotalCustomer q = new queryTotalCustomer();
            if (!q.queryTotalCustomer(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("绩效管理--梯度管理--有效客户管理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

    //代理商绩效
    @Test(dependsOnMethods = "queryTotalCustomer", description = "绩效管理--代理商绩效", alwaysRun = true)
    public void agentInfo() {
        driver.switchTo().defaultContent();
        if (isExistBoxOrExistButton(driver, "//a[@url='/pa/agentInfo/agentInfo.jsp']", 3)) {
            driver.findElement(By.xpath("//a[@url='/pa/agentInfo/agentInfo.jsp']")).click();
            agentInfo a = new agentInfo();
            if (!a.agentInfo(driver))
                driver.findElement(By.id("returnFalse")).click();
        } else {
            if (journal) {
                Reporter.log("绩效管理--梯度管理--有效客户管理--跳过测试。原因：暂无此权限或功能 <br/>");
            }
        }
    }

//endregion

    //点击对应的菜单
    public boolean listClickByText(WebDriver driver, String text) {
        List<WebElement> list = driver.findElements(By.cssSelector("dt"));
        for (int i = 0; i < list.size(); i++) {
            if (text.equals(list.get(i).getText())) {
                list.get(i).click();
                return true;
            }
        }
        return false;
    }

}
