package com.selenium.fuyou;

import com.selenium.base.DriverBase;
import com.selenium.fuyou.accountStatement.accountStatement;
import com.selenium.fuyou.announcementManager.announcementList;
import com.selenium.fuyou.employeeManager.departmentList;
import com.selenium.fuyou.employeeManager.employeeList;
import com.selenium.fuyou.enterpriseProcurement.enterpriseProcurement;
import com.selenium.fuyou.login.firstLogin;
import com.selenium.fuyou.login.loginValidate;
import com.selenium.fuyou.welfareManager.welfareManager;
import com.selenium.fuyou.transactionManager.electronicInvoice;
import com.selenium.fuyou.transactionManager.transactionRecord;
import com.selenium.utils.PropertiesConfig;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.fuyou.fuYouMethod.*;
import static com.selenium.fuyou.fuYouMethod.getNavListId;

@Slf4j
public class fuYou extends DriverBase {
    DesiredCapabilities caps = setDownloadsPath("E:\\SeleniumDemo1\\src\\main\\resources\\downloadFile");
    public WebDriver driver = driverName(caps);
    //key
    public String fuYouUrl = PropertiesConfig.getInstance().getProperty("driver.fuYou.url");
    public String username = PropertiesConfig.getInstance().getProperty("fuYou.username");
    public String password = PropertiesConfig.getInstance().getProperty("fuYou.password");

    //创建鼠标
    Actions mouse = new Actions(driver);
    //顶部导航栏列表
    List<WebElement> list = null;
    List<WebElement> aList = null;
    //对应顶部导航栏的index
    int navIndex = 0;

    departmentList dep = new departmentList();
    employeeList emp = new employeeList();
    enterpriseProcurement ep = new enterpriseProcurement();
    welfareManager w = new welfareManager();
    announcementList notice = new announcementList();
    accountStatement as = new accountStatement();

    //ReportNG 只能显示字符而无法显示成链接，则取消注释
    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";

    @Test
    public void fuYouTest() {

        //ReportNG 只能显示字符而无法显示成链接，则取消注释
        System.setProperty(ESCAPE_PROPERTY, "false");

        driver.get(fuYouUrl);
        driver.manage().window().maximize();
    }

    //region 登陆
    @Test(dependsOnMethods = "fuYouTest",description = "登陆")
    public boolean login(){
        try {
            loginValidate log = new loginValidate();
            //判断是否存在广告
            boolean flag = isExistBoxOrExistButton(driver,"notice",1);
            if (flag) {
                driver.findElement(By.className("notice_close")).click();
                Thread.sleep(500);
            }

            //判断是否存在提示窗体
            flag = isExistBoxOrExistButton(driver, "layui-layer-shade1", 0);
            if (flag) {
                driver.findElement(By.className("layui-layer-setwin")).click();
                Thread.sleep(500);
            }


            driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/label[1]/a")).click();
            Thread.sleep(500);
            driver.findElement(By.id("username")).sendKeys(username);
            Thread.sleep(500);
            driver.findElement(By.id("password")).sendKeys(password);
            String result = log.validateCoding(driver);
            driver.findElement(By.id("verifyCode")).sendKeys(result);
            Thread.sleep(500);
            driver.findElement(By.className("login_Btn")).click();
            Thread.sleep(1000);
            //循环输入验证码登陆
            while (isAlertPresent(driver)) {
                driver.switchTo().alert().accept();
                driver.findElement(By.id("verifyCode")).clear();
                Thread.sleep(500);
                result = log.validateCoding(driver);
                driver.findElement(By.id("verifyCode")).sendKeys(result);
                Thread.sleep(500);
                driver.findElement(By.className("login_Btn")).click();
                Thread.sleep(500);
            }

            Reporter.log("企业账号登陆成功--企业号：" + username+"<br/>");

            flag = isExistBoxOrExistButton(driver,"gb",1);
            if(flag){
                driver.findElement(By.className("gb")).click();
            }

            Thread.sleep(500);
            flag = isExistBoxOrExistButton(driver,"qyBeginIcon",1);
            if (flag) {
                Thread.sleep(500);
                driver.findElement(By.className("qyCloseIcon")).click();
            }

            //判断是否为企业账号首次登录
            //首次登录企业账号需要进行账号验证并激活！
            Thread.sleep(1000);
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(fuYouUrl + "Company/CompanyFirstLoad")) {
                firstLogin fl = new firstLogin();
                if (!fl.verificationCustom(driver, username)) {
                    driver.close();
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业账号登陆失败，企业号："+username+",错误："+e.toString()+"<br/>");
            return false;
        }
    }

    public boolean login(String username, String password) {
        try {

            //ReportNG 只能显示字符而无法显示成链接，则取消注释
            System.setProperty(ESCAPE_PROPERTY, "false");

            driver.get(fuYouUrl);
            driver.manage().window().maximize();
            loginValidate log = new loginValidate();
            boolean flag = isExistBoxOrExistButton(driver, "notice", 1);
            if (flag) {
                driver.findElement(By.className("notice_close")).click();
                Thread.sleep(500);
            }
            flag = isExistBoxOrExistButton(driver, "layui-layer-shade1", 0);
            if (flag) {
                driver.findElement(By.className("layui-layer-setwin")).click();
                Thread.sleep(500);
            }
            driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/label[1]/a")).click();
            Thread.sleep(500);
            driver.findElement(By.id("username")).sendKeys(username);
            Thread.sleep(500);
            driver.findElement(By.id("password")).sendKeys(password);
            String result = log.validateCoding(driver);
            driver.findElement(By.id("verifyCode")).sendKeys(result);
            Thread.sleep(500);
            driver.findElement(By.className("login_Btn")).click();
            Thread.sleep(1000);
            while (isAlertPresent(driver)) {
                driver.switchTo().alert().accept();
                driver.findElement(By.id("verifyCode")).clear();
                Thread.sleep(500);
                result = log.validateCoding(driver);
                driver.findElement(By.id("verifyCode")).sendKeys(result);
                Thread.sleep(500);
                driver.findElement(By.className("login_Btn")).click();
                Thread.sleep(500);
            }
            Thread.sleep(500);
            flag = isExistBoxOrExistButton(driver,"gb",1);
            if(flag){
                driver.findElement(By.className("gb")).click();
            }
            flag = isExistBoxOrExistButton(driver, "qyBeginIcon", 1);
            if (flag) {
                Thread.sleep(500);
                driver.findElement(By.className("qyCloseIcon")).click();
            }
            //判断是否为企业账号首次登录
            //首次登录企业账号需要进行账号验证并激活！
            Thread.sleep(1000);
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.equals(fuYouUrl + "Company/CompanyFirstLoad")) {
                firstLogin fl = new firstLogin();
                if (!fl.verificationCustom(driver, username)) {
                    driver.close();
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("fuyou登录失败。错误：" + e.toString()+"<br/>");
            }
            return false;
        }
    }

    //endregion

    //region 员工管理

    @Test(dependsOnMethods = "login",description = "部门列表")
    public void departmentList() throws Exception {
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("员工管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("部门列表",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    //部门添加
                    if(!dep.addDep(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    //部门删除
                    if(!dep.deleteDep(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    //部门编辑
                    if(!dep.updateDep(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
    }

    @Test(dependsOnMethods = "departmentList",description = "员工列表")
    public void employeeList() throws Exception {
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("员工管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("员工列表",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    //添加员工
                    if(!emp.addEmp(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    //批量导入员工
                    if(!emp.batchImportEmp(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    //编辑员工
                    if(!emp.updateEmp(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    //删除员工
                    if(!emp.deleteEmp(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    //搜索员工
                    if(!emp.searchEmp(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
    }

    //endregion

    //region 福利管理

    @Test(dependsOnMethods = "employeeList",description = "福利发放")
    public void welfarePayment() throws Exception {
        try{
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("福利管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("福利发放",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    if (!provideWelfare())
                        driver.findElement(By.id("asdf")).click();
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("福利发放--失败。错误：" + e.toString() + "<br/>");
            driver.findElement(By.id("asdf")).click();
        }
    }

    @Test(dependsOnMethods = "welfarePayment",description = "企业收款管理",alwaysRun = true)
    public void enterpriseReceiptManager() throws Exception {
        try{
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("福利管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("企业收款管理",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    if (!w.companyGatheringQrcode(driver, username))
                        driver.findElement(By.id("asdf")).click();
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业收款管理--失败。错误：" + e.toString() + "<br/>");
            driver.findElement(By.id("asdf")).click();
        }
    }

    @Test(dependsOnMethods = "enterpriseReceiptManager",description = "一卡通兑换",alwaysRun = true)
    public void cardExchange() throws Exception {
        try{
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("福利管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("一卡通兑换",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    if(!w.companyCardPassExchange(driver, username))
                        driver.findElement(By.id("asdf")).click();
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("一卡通兑换--失败。错误：" + e.toString() + "<br/>");
            driver.findElement(By.id("asdf")).click();
        }
    }

    /**
     * 福利发放
     */
    private boolean provideWelfare() {
        try {
            welfareManager w = new welfareManager();
            //单个福利发放 批量福利发放
            return w.singleProvideWelfare(driver, username) && w.multipleprovideWelfare(driver, username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 企业优分订单回复
     */
    public boolean replyCustomOrder(String customNo) {
        try {
            //福利管理 优分订单管理
            Thread.sleep(1000);
            Actions mouse = new Actions(driver);
            mouse.moveToElement(driver.findElement(By.xpath("//*[@id=\"fbgg_menu\"]/li[4]"))).perform();
            driver.findElement(By.xpath("//*[@id=\"fbgg_menu\"]/li[4]/ul/li[3]/a")).click();
            welfareManager w = new welfareManager();
            if (!w.replyOrder(driver, customNo)) {
                driver.close();
            }
            Thread.sleep(1000);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("回复企业订单--企业号：" + customNo + "--失败。错误：" + e.toString()+"<br/>");
            }
            return false;
        }

    }

    //endregion

    //region 公告管理

    @Test(dependsOnMethods = "cardExchange",description = "公告管理",alwaysRun = true)
    public void announcementManager() throws Exception {
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("公告管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("公告列表",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    if(!notice.announcement(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                    if(!notice.deleteAnnouncement(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
    }

    //endregion

    //region 交易管理

    @Test(dependsOnMethods = "announcementManager",description = "交易记录")
    public void electronicInvoiceManager() throws Exception {
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("交易管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("交易记录",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    transactionRecord tr = new transactionRecord();
                    if(!tr.transactionRecordSearch(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                }
            }
            Thread.sleep(500);
    }

    @Test(dependsOnMethods = "electronicInvoiceManager",description = "电子发票",enabled = false)
    public void transactionRecordManager() throws Exception {
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("交易管理",list);
            if(navIndex != -1){
                mouse.moveToElement(list.get(navIndex)).perform();
                Thread.sleep(500);
                aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("电子发票",aList);
                if(navIndex != -1){
                    aList.get(navIndex).findElement(By.tagName("a")).click();
                    electronicInvoice ei = new electronicInvoice();
                    if(!ei.electronicInvoiceSearch(driver)){
                        driver.findElement(By.id("111")).click();
                    }
                }else{
                    Reporter.log("未开通此功能"+"<br/>");
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
    }

    //endregion

    //region 对账单

    @Test(dependsOnMethods = "electronicInvoiceManager",description = "对账单")
    public void accountStatementManager() throws Exception {
            Thread.sleep(500);
            list = getNavList(driver,null,"fbgg_menu","li",0);
            navIndex = getNavListId("对账单",list);
            if(navIndex != -1){
                list.get(navIndex).click();
                if(!as.searchStatement(driver)){
                    driver.findElement(By.id("111")).click();
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
    }

    //endregion

    //region 企业采购

    @Test(dependsOnMethods = "accountStatementManager",description = "企业采购")
    public void enterpriseProcurementInterface() throws Exception {
        Thread.sleep(500);
        list = getNavList(driver,null,"fbgg_menu","li",0);
        navIndex = getNavListId("企业采购",list);
        if(navIndex != -1){
            mouse.moveToElement(list.get(navIndex)).perform();
            Thread.sleep(500);
            aList = getNavList(driver,list.get(navIndex),"", "li", 0);
            navIndex = getNavListId("企业采购",aList);
            if(navIndex != -1){
                aList.get(navIndex).findElement(By.tagName("a")).click();
                    //新增企业采购地址
                if(!ep.isHaveAddress(driver)){
                    driver.findElement(By.id("1111")).click();
                }
                    //修改企业采购地址
                if(!ep.updateAddress(driver)){
                    driver.findElement(By.id("1111")).click();
                }
                    //删除企业采购地址
                if(!ep.deleteAddress(driver)){
                    driver.findElement(By.id("1111")).click();
                }
                    ///查找商品
                if(!ep.navMenu(driver)){
                    driver.findElement(By.id("1111")).click();
                }
                if(!ep.searchProduct(driver)){
                    driver.findElement(By.id("1111")).click();
                }
                    //商品购买
                if(!ep.purchaseGoods(driver)){
                    driver.findElement(By.id("1111")).click();
                }
            }else{
                Reporter.log("未开通此功能"+"<br/>");
            }
            Thread.sleep(500);
        }else{
            Reporter.log("未开通此功能"+"<br/>");
        }
    }

    //endregion

}
