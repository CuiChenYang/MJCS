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
    public fuYou fy = new fuYou();
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
        driver.switchTo().defaultContent();
        driver.findElement(By.id("4")).click();
        menuManage m = new menuManage();
        m.menu(driver);
    }

    //授权管理
    @Test(dependsOnMethods = "menuManage", description = "系统管理-授权管理", alwaysRun = true)
    public void authorizationManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("5")).click();
        authorizationManage a = new authorizationManage();
        a.roleAuthorization(driver);
    }

    //角色管理
    @Test(dependsOnMethods = "authorizationManage", description = "系统管理-角色管理", alwaysRun = true)
    public void roleManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("6")).click();
        roleManage r = new roleManage();
        r.roleManage(driver);
    }

    //参数管理
    @Test(dependsOnMethods = "roleManage", description = "系统管理-参数管理", alwaysRun = true)
    public void parameterManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1101")).click();
        parameterManage p = new parameterManage();
        p.parameterManage(driver);
    }

    //应用功能管理
    @Test(dependsOnMethods = "parameterManage", description = "系统管理-应用功能管理", alwaysRun = true)
    public void appFunctionManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("3")).click();
        appFunctionManage a = new appFunctionManage();
        a.appFunctionManage(driver);
    }

    //组织机构管理
    @Test(dependsOnMethods = "appFunctionManage", description = "系统管理-组织机构管理", alwaysRun = true)
    public void organizationManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("8")).click();
        organizationManage o = new organizationManage();
        o.organizationManage(driver);
    }

    //接口权限管理
    @Test(dependsOnMethods = "organizationManage", description = "系统管理-接口权限管理", alwaysRun = true)
    public void interfacesPowerManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1301")).click();
        interfacesPowerManage i = new interfacesPowerManage();
        i.interfacesPowerManage(driver);
    }

    //客户IP设置
    @Test(dependsOnMethods = "interfacesPowerManage", description = "系统管理-客户IP设置", alwaysRun = true)
    public void customerIPSettings() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1641")).click();
        customerIPSettings c = new customerIPSettings();
        c.customerIPSettings(driver);
    }

    //第三方账号管理
    @Test(dependsOnMethods = "customerIPSettings", description = "系统管理-第三方账号管理", alwaysRun = true)
    public void thirdPartyAccountManage() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("3021")).click();
        thirdPartyAccountManage t = new thirdPartyAccountManage();
        t.thirdPartyAccountManage(driver);
    }

    //其他(设置安全策略、配置业务字典)
    @Test(dependsOnMethods = "thirdPartyAccountManage", description = "系统管理-其他(设置安全策略、配置业务字典)", alwaysRun = true)
    public void other() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1102")).click();
        other o = new other();
        o.installSafeStrategy(driver);
        driver.switchTo().defaultContent();
        o.disposeTransactionDictionary(driver);
    }

    //系统账户优分设置
    @Test(dependsOnMethods = "other", description = "系统管理-系统账户优分设置", alwaysRun = true)
    public void queryShopScore() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2344")).click();
        queryShopScore q = new queryShopScore();
        q.queryShopScore(driver);
    }

    //系统账户优分设置变更记录
    @Test(dependsOnMethods = "queryShopScore", description = "系统管理-系统账户优分设置变更记录", alwaysRun = true)
    public void queryShopScoreAlter() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2361")).click();
        queryShopScoreAlter q = new queryShopScoreAlter();
        q.queryShopScoreAlter(driver);
    }

    //日志查询(用户登录日志、用户操作日志)
    @Test(dependsOnMethods = "queryShopScoreAlter", description = "系统管理-日志查询(用户登录日志、用户操作日志)", alwaysRun = true)
    public void queryLog() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2301")).click();
        queryLog q = new queryLog();
        q.userLoginLog(driver);
        driver.switchTo().defaultContent();
        q.userOperateLog(driver);
    }
//endregion

//region    客户管理

    //客户档案变更记录
    @Test(dependsOnMethods = "queryLog", description = "客户管理--客户档案变更记录", alwaysRun = true)
    public void cusProfileAlterManager() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1061")).click();
        driver.findElement(By.id("1481")).click();
        cusProfileAlterManager c = new cusProfileAlterManager();
        c.cusProfileAlterManager(driver);
    }

    //合作伙伴档案管理
    @Test(dependsOnMethods = "cusProfileAlterManager", description = "客户管理--合作伙伴档案管理", alwaysRun = true)
    public void cooperationManager() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2561")).click();
        cooperationManager c = new cooperationManager();
        c.cooperationManager(driver);
    }

    //客户与合作伙伴关系管理
    @Test(dependsOnMethods = "cooperationManager", description = "客户管理--客户与合作伙伴关系管理", alwaysRun = true)
    public void cooperationCustomRelation() {
        driver.switchTo().defaultContent();
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
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1081")).click();
        driver.findElement(By.id("1201")).click();
        bankCardCountList b = new bankCardCountList();
        b.bankCardCountList(driver);
    }

    //充值卡库存查询
    @Test(dependsOnMethods = "bankCardCountList", description = "销售管理--充值卡库存查询", alwaysRun = true)
    public void prepaidCardCountList() {
        driver.switchTo().defaultContent();
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
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1121")).click();
        driver.findElement(By.id("1701")).click();
        custom_power q = new custom_power();
        q.custom_power(driver);
    }

    //代理商管理
    @Test(dependsOnMethods = "custom_power", description = "运营管理--代理商管理", alwaysRun = true)
    public void agentList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2921")).click();
        agentList a = new agentList();
        a.agentList(driver);
    }


//endregion

//region    财务管理

    //批量代充业务（查询）
    @Test(dependsOnMethods = "agentList", description = "财务管理--批量代充业务（查询）", alwaysRun = true)
    public void rechargeQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1261")).click();
        driver.findElement(By.id("1591")).click();
        driver.findElement(By.id("1596")).click();
        rechargeQueryList r = new rechargeQueryList();
        r.rechargeQueryList(driver);
    }

    //金牛卡结果确认
    @Test(dependsOnMethods = "rechargeQueryList", description = "财务管理--金牛卡结果确认", alwaysRun = true)
    public void scoretobankQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1599")).click();
        driver.findElement(By.id("1601")).click();
        scoretobankQueryList r = new scoretobankQueryList();
        r.scoretobankQueryList(driver);
    }

    //region 信用卡业务
    //信用卡业务--信用卡导出
    @Test(dependsOnMethods = "scoretobankQueryList", description = "财务管理--信用卡业务--信用卡导出", alwaysRun = true)
    public void scoretocredit() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1603")).click();
        driver.findElement(By.id("1604")).click();
        scoretocredit s = new scoretocredit();
        s.scoretocredit(driver);
    }

    //信用卡业务--矩阵还款
    @Test(dependsOnMethods = "scoretocredit", description = "财务管理--信用卡业务--矩阵还款", alwaysRun = true)
    public void repayment() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1611")).click();
        repayment r = new repayment();
        r.repayment(driver);
    }

    //信用卡业务--还款结果查询
    @Test(dependsOnMethods = "repayment", description = "财务管理--信用卡业务--还款结果查询", alwaysRun = true)
    public void queryRepaymentAll() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1612")).click();
        queryRepaymentAll r = new queryRepaymentAll();
        r.queryRepaymentAll(driver);
    }

    //信用卡业务--信用卡财务经办
    @Test(dependsOnMethods = "queryRepaymentAll", description = "财务管理--信用卡业务--信用卡财务经办", alwaysRun = true)
    public void creditQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1723")).click();
        creditQueryList c = new creditQueryList();
        c.creditQueryList(driver);
    }

    //信用卡业务--还款异常处理
    @Test(dependsOnMethods = "creditQueryList", description = "财务管理--信用卡业务--还款异常处理", alwaysRun = true)
    public void errorRepayment() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1981")).click();
        errorRepayment e = new errorRepayment();
        e.errorRepayment(driver);
    }

    //信用卡业务--还款结果导入
    @Test(dependsOnMethods = "errorRepayment", description = "财务管理--信用卡业务--还款结果导入", alwaysRun = true)
    public void importPaymentResult() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2681")).click();
        importPaymentResult i = new importPaymentResult();
        i.importPaymentResult(driver);
    }

    //信用卡业务--光大复核
    @Test(dependsOnMethods = "importPaymentResult", description = "财务管理--信用卡业务--光大复核", alwaysRun = true)
    public void paymentFileList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2721")).click();
        paymentFileList p = new paymentFileList();
        p.paymentFileList(driver);
    }
    //endregion

    //region 账务调账业务
    //账务调账业务--账务冲正
    @Test(dependsOnMethods = "paymentFileList", description = "财务管理--账务调账业务--账务冲正", alwaysRun = true)
    public void handleAcceptQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1608")).click();
        driver.findElement(By.id("1613")).click();
        handleAcceptQueryList h = new handleAcceptQueryList();
        h.handleAcceptQueryList(driver);
    }

    //账务调账业务--特殊业务调账
    @Test(dependsOnMethods = "handleAcceptQueryList", description = "财务管理--账务调账业务--特殊业务调账", alwaysRun = true)
    public void specialAccountChangeInputList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2141")).click();
        specialAccountChangeInputList s = new specialAccountChangeInputList();
        s.specialAccountChangeInputList(driver);
    }
    //endregion

    //region 凭证打印
    //凭证打印--凭证记录查询
    @Test(dependsOnMethods = "specialAccountChangeInputList", description = "财务管理--凭证打印--凭证记录查询", alwaysRun = true)
    public void orderQueryList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1614")).click();
        driver.findElement(By.id("1615")).click();
        orderQueryList o = new orderQueryList();
        o.orderQueryList(driver);
    }

    //凭证打印--手工凭证录入
    @Test(dependsOnMethods = "orderQueryList", description = "财务管理--凭证打印--手工凭证录入", alwaysRun = true)
    public void orderCredentialHandList() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1616")).click();
        orderCredentialHandList o = new orderCredentialHandList();
        o.orderCredentialHandList(driver);
    }
    //endregion

    //region统计报表
    //统计报表--每日订单汇总
    @Test(dependsOnMethods = "orderCredentialHandList", description = "财务管理--统计报表--每日订单汇总", alwaysRun = true)
    public void queryReportDailyOrderTbl() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1617")).click();
        driver.findElement(By.id("1618")).click();
        queryReportDailyOrderTbl q = new queryReportDailyOrderTbl();
        q.queryReportDailyOrderTbl(driver);
    }

    //统计报表--平台优分使用汇总
    @Test(dependsOnMethods = "queryReportDailyOrderTbl", description = "财务管理--统计报表--平台优分使用汇总", alwaysRun = true)
    public void queryReportSpend() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1619")).click();
        queryReportSpend q = new queryReportSpend();
        q.queryReportSpend(driver);
    }

    //统计报表--手续费汇总
    @Test(dependsOnMethods = "queryReportSpend", description = "财务管理--统计报表--手续费汇总", alwaysRun = true)
    public void queryCounterFee() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1620")).click();
        queryCounterFee q = new queryCounterFee();
        q.queryCounterFee(driver);
    }

    //统计报表--凭证统计汇总
    @Test(dependsOnMethods = "queryCounterFee", description = "财务管理--统计报表--凭证统计汇总", alwaysRun = true)
    public void queryCertificateReport() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1621")).click();
        queryCertificateReport q = new queryCertificateReport();
        q.queryCertificateReport(driver);
    }

    //统计报表--报表处理日志
    @Test(dependsOnMethods = "queryCertificateReport", description = "财务管理--统计报表--报表处理日志", alwaysRun = true)
    public void reportlogMangaer() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1622")).click();
        reportlogMangaer r = new reportlogMangaer();
        r.reportlogMangaer(driver);
    }

    //统计报表--账户总表
    @Test(dependsOnMethods = "reportlogMangaer", description = "财务管理--统计报表--账户总表", alwaysRun = true)
    public void queryAccountReport() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2081")).click();
        queryAccountReport q = new queryAccountReport();
        q.queryAccountReport(driver);
    }

    //统计报表--个人优分汇总
    @Test(dependsOnMethods = "queryAccountReport", description = "财务管理--统计报表--个人优分汇总", alwaysRun = true)
    public void queryPointsSummary() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2101")).click();
        queryPointsSummary q = new queryPointsSummary();
        q.queryPointsSummary(driver);
    }

    //统计报表--企业优分汇总
    @Test(dependsOnMethods = "queryPointsSummary", description = "财务管理--统计报表--企业优分汇总", alwaysRun = true)
    public void queryEnterSummary() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2121")).click();
        queryEnterSummary q = new queryEnterSummary();
        q.queryEnterSummary(driver);
    }
    //endregion

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

//region    客服明细

    //充值卡状态查询
    @Test(dependsOnMethods = "queryEnterSummary", description = "客服明细--充值卡状态查询", alwaysRun = true)
    public void queryPrepaidCardInfo() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("1662")).click();
        driver.findElement(By.id("2281")).click();
        queryPrepaidCardInfo q = new queryPrepaidCardInfo();
        q.queryPrepaidCardInfo(driver);
    }

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

    //region 梯度管理
    //梯度管理--有效客户管理
    @Test(dependsOnMethods = "queryPrepaidCardInfo", description = "绩效管理--梯度管理--有效客户管理", alwaysRun = true)
    public void validCustomer() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2781")).click();
        driver.findElement(By.id("2782")).click();
        driver.findElement(By.id("2783")).click();
        validCustomer v = new validCustomer();
        v.validCustomer(driver);
    }

    //梯度管理--平台客户管理
    @Test(dependsOnMethods = "validCustomer", description = "绩效管理--梯度管理--平台客户管理", alwaysRun = true)
    public void platformCustomer() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2784")).click();
        platformCustomer p = new platformCustomer();
        p.platformCustomer(driver);
    }

    //梯度管理--终端客户管理
    @Test(dependsOnMethods = "platformCustomer", description = "绩效管理--梯度管理--终端客户管理", alwaysRun = true)
    public void terminalCustomer() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2785")).click();
        terminalCustomer t = new terminalCustomer();
        t.terminalCustomer(driver);
    }
    //endregion

    //企业关联关系--列表信息管理
    @Test(dependsOnMethods = "terminalCustomer", description = "绩效管理--企业关联关系--列表信息管理", alwaysRun = true)
    public void queryTotalCustomer() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("2786")).click();
        driver.findElement(By.id("2787")).click();
        queryTotalCustomer q = new queryTotalCustomer();
        q.queryTotalCustomer(driver);
    }

    //代理商绩效
    @Test(dependsOnMethods = "queryTotalCustomer", description = "绩效管理--代理商绩效", alwaysRun = true)
    public void agentInfo() {
        driver.switchTo().defaultContent();
        driver.findElement(By.id("3101")).click();
        agentInfo a = new agentInfo();
        a.agentInfo(driver);
    }

//endregion

    /**
     * 首次登录激活企业
     */
    @Test(dependsOnMethods = "checkOrder", description = "首次登录激活企业")
    public void firstLoginFuYou() {
        //首次登录激活企业
        if (!fy.login(se.customNo, "123456"))
            driver.findElement(By.id("asdf")).click();
    }

    /**
     * 激活成功后重新登录 并回复订单
     */
    @Test(dependsOnMethods = "firstLoginFuYou", description = "激活成功后重新登录 并回复订单")
    public void againLoginFuYou() {
        //激活成功后重新登录
        if (!fy.login(se.customNo, "123456") || !fy.replyCustomOrder(se.customNo))
            driver.findElement(By.id("asdf")).click();
        else {
            if (journal) {
                Reporter.log("回复企业订单成功，企业号：" + se.customNo + "订单号为：" + order.orderId + "<br/>");
            }
            fy.driver.close();
        }
    }


}
