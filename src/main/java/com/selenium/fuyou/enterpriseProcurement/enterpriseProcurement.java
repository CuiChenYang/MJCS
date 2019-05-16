package com.selenium.fuyou.enterpriseProcurement;

import com.selenium.utils.AESDncodeUtil;
import com.selenium.utils.PhoneUtil;
import com.selenium.utils.PropertiesConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.fuyou.fuYouMethod.*;

public class enterpriseProcurement {

    //region 地址

    //region 用户是否有地址以及添加地址

    public boolean isHaveAddress(WebDriver driver){
        try{
            String s = driver.findElement(By.className("chooseAddressHeader")).getText();
            if(s.equals("请选择收货地址")){
                Thread.sleep(500);
                driver.findElement(By.className("imgCloseLogin")).click();
                Thread.sleep(500);
                boolean flag = isAlertPresent(driver);
                Thread.sleep(500);
                if(flag){
                    driver.switchTo().alert().accept();
                    Thread.sleep(500);
                    driver.findElement(By.id("CompanyFinishOrder_btnAddAddress")).click();
                    isClearData(driver);
                    addAddress(driver,"张三",31,0,0,"","","","",false);
                    isClearData(driver);
                    addAddress(driver,"张三",1,17,0,"","","","",false);
                    isClearData(driver);
                    addAddress(driver,"张三",1,6,0,"1133号","","","",false);
                    isClearData(driver);
                    addAddress(driver,"张三",1,5,0,"东大名路888弄","","", PhoneUtil.getTelephone(),false);
                    isClearData(driver);
                    addAddress(driver,"李三",1,6,0,"国康路47号","","", PhoneUtil.getTelephone(),true);
                    Reporter.log("企业采购 新增地址 新增收获地址成功，企业账号：" + PropertiesConfig.getInstance().getProperty("fuYou.username")+"<br/>");
                    driver.findElement(By.className("chooseAddressHeader")).click();
                    Thread.sleep(500);
                    driver.findElement(By.id("CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl01_btnSetDefault")).click();
                    return true;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业采购 新增地址 新增收货地址失败失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //添加收货地址
    private void addAddress(WebDriver driver,String name,int provinceNum,int cityNum,int areaNum,String address,String zipCode,String telPhone,String cellPhone,boolean isClick){
        try{
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_txtShipTo")).sendKeys(name);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"province_top\"]/span[2]")).click();

            List<WebElement> list = getNavList(driver,null,"province_select","li",0);
            String s = list.get(provinceNum).findElement(By.tagName("a")).getText();
            Thread.sleep(500);
            list.get(provinceNum).findElement(By.tagName("a")).click();
            if(s.equals("[清空]")){
                driver.findElement(By.id("CompanyFinishOrder_btnAddAddress")).click();
                return;
            }
            Thread.sleep(500);
            list = getNavList(driver,null,"city_select","li",0);
            s = list.get(provinceNum).findElement(By.tagName("a")).getText();
            list.get(cityNum).findElement(By.tagName("a")).click();
            if(s.equals("[清空]")){
                driver.findElement(By.id("CompanyFinishOrder_btnAddAddress")).click();
                return;
            }
            Thread.sleep(500);
            list = getNavList(driver,null,"area_select","li",0);
            if(list.size() == 0){
                driver.findElement(By.id("CompanyFinishOrder_btnAddAddress")).click();
                return;
            }
            list.get(areaNum).findElement(By.tagName("a")).click();
            Thread.sleep(500);

            driver.findElement(By.id("CompanyFinishOrder_txtAddress")).sendKeys(address);
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_txtZipcode")).sendKeys(zipCode);
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_txtTelPhone")).sendKeys(telPhone);
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_txtCellPhone")).sendKeys(cellPhone);
            Thread.sleep(500);
            if(isClick){
                driver.findElement(By.id("chkIsDefault")).click();
            }
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_btnAddAddress")).click();
            Thread.sleep(500);
            isClearData(driver);
        }catch (Exception e){
            e.printStackTrace();
            Reporter.log("企业采购 新增地址 新增收货地址失败失败，错误："+e.toString()+"<br/>");
        }
    }

    //判断是否要清空数据
    private void isClearData(WebDriver driver){
        boolean flag = isAlertPresent(driver);
        if(flag){
            driver.switchTo().alert().accept();
        }
        String s = driver.findElement(By.className("chooseAddressHeader")).getText();
        if(s.equals("请选择收货地址")){
            driver.findElement(By.className("qingkong")).click();
        }
    }

    //endregion

    //region 编辑地址

    public boolean updateAddress(WebDriver driver){
        try{
            driver.findElement(By.className("chooseAddressHeader")).click();
            Thread.sleep(500);
            boolean flag = isExistBoxOrExistButton(driver,"CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnEdit",0);
            if(!flag){
                driver.findElement(By.id("imgCloseLogin")).click();
                Reporter.log("企业采购 修改地址 地址修成功" +"<br/>");
                return true;
            }
            addressData(driver,"CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnEdit",null,null,null,null,null,null,null,PhoneUtil.getTelephone(),true);
            //判断是否有默认地址
            flag = isExistBoxOrExistButton(driver,"CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnSetDefault",0);
            if(flag){
                driver.findElement(By.id("CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnSetDefault")).click();
            }
            Reporter.log("企业采购 修改地址 地址修成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业采购 修改地址 地址修改失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //地址数据输入
    private void addressData(WebDriver driver,String updatePath,String name,Integer provinceNum,Integer cityNum,Integer areaNum,String address,String zipCode,String telPhone,String cellPhone,boolean isClick){
        try{
            driver.findElement(By.id(updatePath)).click();
            Thread.sleep(500);
            if(name != null && name.length() != 0){
                driver.findElement(By.id("CompanyFinishOrder_txtShipTo")).sendKeys(name);
            }
            Thread.sleep(500);
            if(provinceNum != null){
                List<WebElement> list = getNavList(driver,null,"province_select","li",0);
                list.get(provinceNum).findElement(By.tagName("a")).click();
            }
            Thread.sleep(500);
            if(cityNum != null){
                List<WebElement> list = getNavList(driver,null,"city_select","li",0);
                list.get(cityNum).findElement(By.tagName("a")).click();
            }
            Thread.sleep(500);
            if(areaNum != null){
                List<WebElement> list = getNavList(driver,null,"area_select","li",0);
                list.get(areaNum).findElement(By.tagName("a")).click();
            }
            Thread.sleep(500);
            if(address != null && address.length() != 0){
                driver.findElement(By.id("CompanyFinishOrder_txtAddress")).clear();
                driver.findElement(By.id("CompanyFinishOrder_txtAddress")).sendKeys(address);
            }
            Thread.sleep(500);
            if(zipCode != null && zipCode.length() != 0){
                driver.findElement(By.id("CompanyFinishOrder_txtZipcode")).clear();
                driver.findElement(By.id("CompanyFinishOrder_txtZipcode")).sendKeys(zipCode);
            }
            Thread.sleep(500);
            if(telPhone != null && telPhone.length() != 0){
                driver.findElement(By.id("CompanyFinishOrder_txtTelPhone")).clear();
                driver.findElement(By.id("CompanyFinishOrder_txtTelPhone")).sendKeys(telPhone);
            }
            Thread.sleep(500);
            if(cellPhone != null && cellPhone.length() != 0){
                driver.findElement(By.id("CompanyFinishOrder_txtCellPhone")).clear();
                driver.findElement(By.id("CompanyFinishOrder_txtCellPhone")).sendKeys(cellPhone);
            }
            Thread.sleep(500);
            if(isClick){
                driver.findElement(By.id("chkIsDefault")).click();
                Thread.sleep(500);
            }
            driver.findElement(By.id("CompanyFinishOrder_btnAddAddress")).click();
            Thread.sleep(500);

            boolean flag = isAlertPresent(driver);
            if(flag){
                driver.switchTo().alert().accept();
            }
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //endregion

    //region 删除地址

    public boolean deleteAddress(WebDriver driver){
        try{
            boolean flag = isExistBoxOrExistButton(driver,"CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnDelete",0);
            if(!flag){
                return true;
            }
            Thread.sleep(500);
            driver.findElement(By.className("chooseAddressHeader")).click();
            Thread.sleep(500);
            List<WebElement> list = getNavList(driver,null,"tab_box1","tr",1);
            if(list.size() <= 2){
                driver.findElement(By.className("imgCloseLogin")).click();
                return true;
            }
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnDelete")).click();
            flag = isAlertPresent(driver);
            if(flag){
                Thread.sleep(500);
                driver.switchTo().alert().accept();
            }
            Thread.sleep(500);
            flag = isExistBoxOrExistButton(driver,"CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnSetDefault",0);
            if(flag){
                driver.findElement(By.id("CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnSetDefault")).click();
            }
            //判断是否没有地址
            flag = isAlertPresent(driver);
            if(flag){
                driver.switchTo().alert().accept();
                addAddress(driver,"李四",1,6,0,"国康路47号","","", PhoneUtil.getTelephone(),true);
                //判断地址数大于5时是否有默认地址
                flag = isExistBoxOrExistButton(driver,"CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnSetDefault",0);
                if(flag){
                    Thread.sleep(500);
                    driver.findElement(By.id("CompanyFinishOrder_list_CompanyCommon_Consignee_ConsigneeList___repeaterRegionsSelectm_ctl00_btnSetDefault")).click();
                    return true;
                }
                driver.findElement(By.className("imgCloseLogin")).click();
            }
            Reporter.log("企业采购 删除地址 地址删除成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业采购 删除地址 地址删除失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }


    //endregion

    //endregion

    //region 导航栏

    public boolean navMenu(WebDriver driver){
        try{
            List<WebElement> list = getNavList(driver,null,"h_chooselist","a",1);
            int num = list.size();
            for (int i = 0; i < num; i++) {
                list = getNavList(driver,null,"h_chooselist","a",1);
                list.get(i).click();
            }
            Thread.sleep(500);


            List<WebElement> divList = getNavList(driver,null,"category_list","div",1);
            List<WebElement> aList = divList.get(0).findElements(By.tagName("a"));
            int aNum = aList.size();
            for (int i = 0; i < aNum; i++) {
                divList = getNavList(driver,null,"category_list","div",1);
                aList = divList.get(0).findElements(By.tagName("a"));
                aList.get(i).click();
            }

            list = getNavList(driver,null,"category_list","h2",1);
            list.get(0).findElement(By.tagName("a")).click();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //endregion

    //region 商品搜索

    public boolean searchProduct(WebDriver driver){
        try{
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___txtKeywords")).sendKeys("套装");
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___btnSearch")).click();
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___txtStartPrice")).sendKeys("100");
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___btnSearch")).click();
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___txtEndPrice")).sendKeys("120");
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___btnSearch")).click();
            Thread.sleep(500);

            List<WebElement> list = getNavList(driver,null,"CompanyFinishOrder_search_Common_CutdownSearch___ckbListproductSearchType","input",0);
            list.get(0).click();
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___btnSearch")).click();
            Thread.sleep(500);
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___txtKeywords")).clear();
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___txtStartPrice")).clear();
            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___txtEndPrice")).clear();
            list = getNavList(driver,null,"CompanyFinishOrder_search_Common_CutdownSearch___ckbListproductSearchType","input",0);
            list.get(0).click();

            driver.findElement(By.id("CompanyFinishOrder_search_Common_CutdownSearch___btnSearch")).click();
            Thread.sleep(500);

            list = getNavList(driver,null,"category_sequence","a",1);
            for (int i = 0; i < list.size(); i++) {
                list = getNavList(driver,null,"category_sequence","a",1);
                Thread.sleep(500);
                list.get(i).click();
            }
            Reporter.log("企业采购 商品搜索成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业采购 商品搜索失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

    //region 商品购买

    public boolean purchaseGoods(WebDriver driver) {
        try {
            purchaseOrder po = new purchaseOrder();
            Thread.sleep(500);
            List<WebElement> productList = getNavList(driver, null,"category_pro_list", "li", 1);
            if(productList.size() <= 0){
                return true;
            }
            productList.get(0).click();
            Thread.sleep(500);
            List<String> url = getUrl(driver);
            driver.switchTo().window(url.get(1));

            //查看图片
            Actions mouse = new Actions(driver);
            Thread.sleep(500);
            mouse.moveToElement(driver.findElement(By.className("mousetrap"))).perform();
            Thread.sleep(500);
            mouse.moveToElement(driver.findElement(By.className("qy_bdbg"))).perform();

            //数量选择
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".shopcart-minus.shopcart1")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".shopcart-add.shopcart1")).click();
            Thread.sleep(500);
            driver.findElement(By.id("buyAmount")).clear();
            driver.findElement(By.id("buyAmount")).sendKeys("3");

            //商品详情评价
            List<WebElement> navList = getNavList(driver,null,"product_nav_wrap","a",1);
            for (int i = 0; i < navList.size(); i++) {
                Thread.sleep(1000);
                navList.get(i).click();
            }
            Thread.sleep(500);

            //添加购物车后继续购物
            addShoppingCart(driver,0);
            List<WebElement> smallProductList = getNavList(driver,null,"products_16","li",0);
            smallProductList.get(1).click();
            Thread.sleep(500);

            //关闭第一次打开的商品页面
            url = getUrl(driver);
            driver.switchTo().window(url.get(1));
            driver.close();
            Thread.sleep(500);

            //切换商品页面
            url = getUrl(driver);
            driver.switchTo().window(url.get(1));
            //添加购物车并跳转到购物车页面
            addShoppingCart(driver,1);
            //购物车
            boolean flag = shoppingCart(driver);
            String s = "";
            if(flag){
                //购买
                Buy(driver,0);
                Thread.sleep(500);
                s = driver.findElement(By.xpath("//*[@id=\"CompanyFinishOrder_divOrderPayInfo\"]/span[1]")).getText();
                s = s.substring(4);
                Thread.sleep(500);
                //付款
                boolean payFlag = payment(driver);
                if(payFlag){
                    jumpPurchaseOrder(driver,mouse);
                    Thread.sleep(500);
                    po.getOrder(driver,s,"","","");
                    Thread.sleep(500);
                    po.orderProcessRefund(driver,s);
                    Thread.sleep(500);
                }
                Reporter.log("企业采购 商品退款成功，企业号：" + PropertiesConfig.getInstance().getProperty("fuYou.username") + "，订单号：" + s+"<br/>");
                driver.close();
            }else{
                driver.close();
            }
            //创建不付款订单
            url = getUrl(driver);
            driver.switchTo().window(url.get(0));
            doNotPayment(driver);
            s = driver.findElement(By.xpath("//*[@id=\"CompanyFinishOrder_divOrderPayInfo\"]/span[1]")).getText();
            s = s.substring(4);
            Thread.sleep(500);
            jumpPurchaseOrder(driver,mouse);
            po.orderProcessCancelPayment(driver,s);
            Reporter.log("企业采购 商品购买取消成功，企业号：" + PropertiesConfig.getInstance().getProperty("fuYou.username") + "，订单号：" + s+"<br/>");
            driver.close();


            url = getUrl(driver);
            driver.switchTo().window(url.get(0));
            doNotPayment(driver);
            s = driver.findElement(By.xpath("//*[@id=\"CompanyFinishOrder_divOrderPayInfo\"]/span[1]")).getText();
            s = s.substring(4);
            Thread.sleep(500);
            jumpPurchaseOrder(driver,mouse);
            Thread.sleep(500);
            po.orderProcess(driver,s);
            Reporter.log("企业采购 商品购买成功，企业号：" + PropertiesConfig.getInstance().getProperty("fuYou.username") + "，订单号：" + s+"<br/>");
            driver.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("企业采购 商品购买失败，错误："+e.toString()+"<br/>");
            return false;
        }

    }

    //添加购物车
    private void addShoppingCart(WebDriver driver,int num){
        try{
            Thread.sleep(500);
            driver.findElement(By.id("addcartButton")).click();
            Thread.sleep(500);
            boolean flag = isExistBoxOrExistButton(driver,"divshow",0);
            if(flag && num == 0){
                driver.findElement(By.className("btn-continue")).click();
            }else if(flag && num != 0){
                driver.findElement(By.className("btn-viewcart")).click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //购物车结算
    private boolean shoppingCart(WebDriver driver){
        try{
            //取消全选
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___pnlShopProductCart\"]/div[1]/div[1]/div/ins")).click();

            //购物车选择操作
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___rptSupplier_ctl00_dataListShoppingCart_ctl00_divCheck\"]/div/ins")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___rptSupplier_ctl00_dataListShoppingCart_ctl00_divAmount\"]/span[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___rptSupplier_ctl00_dataListShoppingCart_ctl00_divAmount\"]/span[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___rptSupplier_ctl00_dataListShoppingCart_ctl00_txtBuyNum\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___rptSupplier_ctl00_dataListShoppingCart_ctl00_txtBuyNum\"]")).sendKeys("1");
            Thread.sleep(500);

            //删除商品
            List<WebElement> aList = getNavList(driver,null,"cart_commodit_settle","a",1);
            aList.get(0).click();
            Thread.sleep(500);
            boolean flag = isAlertPresent(driver);
            if(flag){
                driver.switchTo().alert().accept();
                Thread.sleep(500);
            }

            flag = isExistBoxOrExistButton(driver,"message",1);
            if(flag){
                return false;
            }

            driver.findElement(By.xpath("//*[@id=\"CompanyShoppingCart_CompanyCommon_ShoppingCart_ProductList___pnlShopProductCart\"]/div[1]/div[1]/div/ins")).click();
            Thread.sleep(500);
            driver.findElement(By.id("CompanyShoppingCart_btnCheckout")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    //购买页面操作
    private void Buy(WebDriver driver,int num){
        try{
            Thread.sleep(500);
            List<WebElement> addressList = getNavList(driver,null,"address_tab","div",1);
            if(addressList.size() > 1){
                addressList.get(1).click();
            }
            driver.findElement(By.className("combo-arrow")).click();
            boolean flag = isExistBoxOrExistButton(driver,"/html/body/div[5]/div",3);
            if(flag){
                List<WebElement> timeList = getNavList(driver,null,"/html/body/div[5]/div","div",3);
                if(num < timeList.size()){
                    timeList.get(num).click();
                }else{
                    timeList.get(0).click();
                }
            }
            driver.findElement(By.id("CompanySubmmitOrder_txtMessage")).sendKeys("测试");
            driver.findElement(By.id("CompanySubmmitOrder_btnCreateOrder")).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //付款页面
    public boolean payment(WebDriver driver){
        try{
            Thread.sleep(500);
            driver.findElement(By.id("aOrderPay")).click();
            Thread.sleep(500);
            boolean flag = isExistBoxOrExistButton(driver,"loginForBuy",0);
            if(flag){
                driver.findElement(By.id("btnyzm")).click();
                Thread.sleep(1000);
                flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                if(flag){
                    Thread.sleep(1000);
                    String s = driver.findElement(By.className("zeromodal-title1")).getText();
                    Thread.sleep(1000);
                    driver.findElement(By.className("zeromodal-close")).click();
                    while(!s.equals("发送成功!")){
                        Thread.sleep(5000);
                        driver.findElement(By.id("btnyzm")).click();
                        Thread.sleep(500);
                        s = driver.findElement(By.className("zeromodal-title1")).getText();
                        Thread.sleep(500);
                        driver.findElement(By.className("zeromodal-close")).click();
                    }
                    Thread.sleep(500);
                    String verificationCode = AESDncodeUtil.decryptAES(driver.manage().getCookieNamed("VerifyCookie").getValue(),"FMpT+/DPUvN7qCJdTktmNQ==","Vs3Wurs07nh9Lt+0QVT2Vg==");
                    driver.findElement(By.id("textfieldpassword")).sendKeys(verificationCode);
                    Thread.sleep(500);
                    driver.findElement(By.id("btnLoginAndBuy")).click();
                    Thread.sleep(500);
                    flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                    if(flag){
                        driver.findElement(By.className("zeromodal-close")).click();
                        driver.findElement(By.className("dialog_title_r")).click();
                        Thread.sleep(500);
                        return false;
                    }
                    flag = isExistBoxOrExistButton(driver,"windowsdialog",1);
                    if(flag){
                        Thread.sleep(1000 * 4);
                        return false;
                    }
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //获取窗体中打开的网站
    private List<String> getUrl(WebDriver driver) {
        Set<String> winHandles = driver.getWindowHandles();
        List<String> it = new ArrayList<>(winHandles);
        return it;
    }

    //创建不付款订单
    private void doNotPayment(WebDriver driver){
        try{
            Thread.sleep(500);
            List<WebElement> productList = getNavList(driver, null,"category_pro_list", "li", 1);
            Thread.sleep(500);
            productList.get(2).click();
            Thread.sleep(500);
            List<String> url = getUrl(driver);
            driver.switchTo().window(url.get(1));
            Thread.sleep(500);
            driver.findElement(By.id("buyButton")).click();
            Thread.sleep(500);
            Buy(driver,1);
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //采购订单跳转操作
    public void jumpPurchaseOrder(WebDriver driver,Actions mouse){
        try{
            List<WebElement> list = getNavList(driver,null,"fbgg_menu","li",0);
            int navIndex = getNavListId("企业采购",list);
            List<WebElement> aList = getNavList(driver,list.get(navIndex),"", "li", 0);
            mouse.moveToElement(list.get(navIndex)).perform();
            Thread.sleep(500);
            aList.get(1).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //endregion
}
