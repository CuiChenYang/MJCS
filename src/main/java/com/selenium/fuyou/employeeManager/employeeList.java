package com.selenium.fuyou.employeeManager;

import com.selenium.fuyou.baseDB.employee;
import com.selenium.utils.PhoneUtil;
import com.selenium.utils.PropertiesConfig;
import com.selenium.utils.ResourcesUrlUtil;
import com.selenium.utils.UserIDUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.fuyou.fuYouMethod.*;


public class employeeList {

    //region 新增员工

    public boolean addEmp(WebDriver driver) {
        try {
            Thread.sleep(500);
            //分页测试
            paging(driver);

            ArrayList<employee> staff = new ArrayList<>();
            staff.add(new employee("张三", 1, UserIDUtil.getUserId(), PhoneUtil.getTelephone(), "2017-01-04"));
            staff.add(new employee("李四", 1, UserIDUtil.getUserId(), PhoneUtil.getTelephone(), "2017-03-07"));
            staff.add(new employee("王五", 2, UserIDUtil.getUserId(), PhoneUtil.getTelephone(), "2017-11-12"));
            staff.add(new employee("赵柳", 2, UserIDUtil.getUserId(), PhoneUtil.getTelephone(), "2018-10-05"));
            staff.add(new employee("贤七", 3, UserIDUtil.getUserId(), PhoneUtil.getTelephone(), "2018-07-17"));
            staff.add(new employee("杨八", 3, UserIDUtil.getUserId(), PhoneUtil.getTelephone(), "2018-08-13"));
            //循环插入员工
            driver.findElement(By.id("bgcreate")).click();
            for (employee e : staff) {
                //输入员工姓名
                driver.findElement(By.id("RealName")).sendKeys(e.getRealName());
                Thread.sleep(500);
                //员工部门
                WebElement ele = driver.findElement(By.id("CompanyEmployeesList_ddlOrganization"));
                Select downList = new Select(ele);
                int num = trySelectGet(driver,"CompanyEmployeesList_ddlOrganization",e.getDepartmentID());
                downList.selectByIndex(num);

                Thread.sleep(500);
                //员工工号
                driver.findElement(By.id("ExternalUserId")).sendKeys(e.getUserID());
                Thread.sleep(500);
                //员工电话
                driver.findElement(By.id("UserName")).sendKeys(e.getPhoneNum());
                Thread.sleep(500);

                //入职日期
                ((JavascriptExecutor) driver).executeScript("document.getElementById('CompanyEmployeesList_addJoinDate').removeAttribute('readonly')");
                driver.findElement(By.id("CompanyEmployeesList_addJoinDate")).sendKeys(e.getDate());
                driver.findElement(By.id("CompanyEmployeesList_addJoinDate")).click();
                //保存
                driver.findElement(By.xpath("//*[@id=\"createcontent\"]/div/div[9]/label/input")).click();
                Thread.sleep(2000);

                //新增失败后操作
                String s = driver.findElement(By.className("zeromodal-title1")).getText();
                Thread.sleep(500);
                if(s.equals("员工工号已存在") || s.equals("已经存在相同的账号") || s.equals("手机号有误")){
                    addEmpErrorOperation(driver,downList);
                    continue;
                }
                driver.findElement(By.className("zeromodal-close")).click();
                Thread.sleep(500);
                driver.findElement(By.id("bgcreate")).click();
                Thread.sleep(500);
                Reporter.log("员工管理 员工列表 员工添加成功，员工登陆账号：" + e.getPhoneNum()+"<br/>");
            }
            boolean flag = isExistBoxOrExistButton(driver,".xubox_layer.xubox_layer_0",2);
            if(flag){
                driver.findElement(By.xpath("//*[@id=\"xubox_layer1\"]/div[1]/a")).click();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工添加失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //员工添加错误操作
    public void addEmpErrorOperation(WebDriver driver,Select downList){
        driver.findElement(By.className("zeromodal-close")).click();
        driver.findElement(By.id("RealName")).clear();
        downList.selectByIndex(0);
        driver.findElement(By.id("ExternalUserId")).clear();
        driver.findElement(By.id("UserName")).clear();
        driver.findElement(By.id("CompanyEmployeesList_addJoinDate")).clear();
    }

    //region 分页

    public void paging(WebDriver driver){
        try{
            //判断是否有下一页按钮
            boolean pageNextFlag = isExistBoxOrExistButton(driver,"page-next",1);
            if (pageNextFlag){
                driver.findElement(By.className("page-next")).click();
            }
            Thread.sleep(500);
            //判断是否有上一页按钮
            boolean pagePrevFlag = isExistBoxOrExistButton(driver,"page-prev",1);
            if(pagePrevFlag){
                driver.findElement(By.className("page-prev")).click();
            }
            Thread.sleep(500);
            if(pageNextFlag || pagePrevFlag){
                pageJump(driver);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //分页跳转
    public void pageJump(WebDriver driver){
        try{
            driver.findElement(By.id("txtGoto")).clear();
            driver.findElement(By.id("txtGoto")).sendKeys("12");
            Thread.sleep(500);
            driver.findElement(By.className("button")).click();
            Thread.sleep(500);
            driver.findElement(By.id("txtGoto")).clear();
            Thread.sleep(500);
            driver.findElement(By.id("txtGoto")).sendKeys("1");
            Thread.sleep(500);
            driver.findElement(By.className("button")).click();
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //endregion

    //endregion

    //region 修改员工信息

    public boolean updateEmp(WebDriver driver){
        try {
            Thread.sleep(500);
            boolean flag = isExistBoxOrExistButton(driver,"//*[@id=\"qyzx_plist\"]/table/tbody/tr[2]/td[6]/a[1]",3);
            if(flag){
                //点击编辑
                driver.findElement(By.xpath("//*[@id=\"qyzx_plist\"]/table/tbody/tr[2]/td[6]/a[1]")).click();
                Thread.sleep(500);
                //清空数据
                clearEmpData(driver);
                //修改
                String s = updateEmpData(driver,new employee("小刚",2,UserIDUtil.getUserId(),"","2019-01-28"));
                if(s.equals("员工工号已存在") || s.equals(null)){
                    clearEmpData(driver);
                }else{
                    driver.findElement(By.xpath("//*[@id=\"qyzx_plist\"]/table/tbody/tr[2]/td[6]/a[1]")).click();
                    clearEmpData(driver);
                }
                updateEmpData(driver,new employee("小明",2,UserIDUtil.getUserId(),"","2019-03-23"));
                Thread.sleep(500);
                //关闭多余编辑窗体
                flag = isExistBoxOrExistButton(driver,".xubox_main.xubox_main_0",2);
                if(flag){
                    driver.findElement(By.cssSelector(".xubox_close.xulayer_png32.xubox_close0_0")).click();
                }
                Thread.sleep(500);

                //部门分类跳转
                List<WebElement> eleList = getNavList(driver,null,".qyzx_bm.bm_menu","li",2);
                eleList.get(3).findElement(By.tagName("a")).click();
                Thread.sleep(1000);
            }
            else{
                Reporter.log("员工管理 员工列表 员工信息修改成功"+"<br/>");
                return true;
            }
            Reporter.log("员工管理 员工列表 员工信息修改成功"+"<br/>");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工信息修改失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //清空修改员工数据操作
    public void clearEmpData(WebDriver driver){
        try {
            //清空员工名字
            driver.findElement(By.id("updateRealName")).clear();
            //初始化部门
            WebElement element = driver.findElement(By.id("CompanyEmployeesList_ddlUpdateOrganization"));
            Select downList = new Select(element);
            downList.selectByIndex(0);
            //清空员工工号
            driver.findElement(By.id("updateExternalUserId")).clear();
            //清空入职日期
            ((JavascriptExecutor) driver).executeScript("document.getElementById('CompanyEmployeesList_updateJoinDate').removeAttribute('readonly')");
            driver.findElement(By.id("CompanyEmployeesList_updateJoinDate")).clear();
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //修改员工数据
    public String updateEmpData(WebDriver driver,employee emp){
        try {
            //员工名字
            driver.findElement(By.id("updateRealName")).sendKeys(emp.getRealName());
            //选择部门
            WebElement element = driver.findElement(By.id("CompanyEmployeesList_ddlUpdateOrganization"));
            Select downList = new Select(element);
            int num = trySelectGet(driver,"CompanyEmployeesList_ddlUpdateOrganization",emp.getDepartmentID());
            downList.selectByIndex(emp.getDepartmentID());
            //员工工号
            driver.findElement(By.id("updateExternalUserId")).sendKeys(emp.getUserID());
            //入职日期
            driver.findElement(By.id("CompanyEmployeesList_updateJoinDate")).sendKeys(emp.getDate());
            //取消日历显示
            driver.findElement(By.id("CompanyEmployeesList_updateJoinDate")).click();
            Thread.sleep(500);
            //保存
            driver.findElement(By.id("btnupdateUser")).click();
            Thread.sleep(500);
            //获取显示成功或失败文本
            String result = driver.findElement(By.className("zeromodal-title1")).getText();
            Thread.sleep(500);
            //关闭成功或失败窗体
            driver.findElement(By.className("zeromodal-close")).click();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    //endregion

    //region 删除员工

    public boolean deleteEmp(WebDriver driver){
        try{
            boolean flag = isExistBoxOrExistButton(driver,"//*[@id=\"qyzx_plist\"]/table/tbody/tr[2]/td[6]/a[2]",3);
            if(flag){
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"qyzx_plist\"]/table/tbody/tr[2]/td[6]/a[2]")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".zeromodal-btn.zeromodal-btn-primary.anblock")).click();
                Thread.sleep(500);

                //部门分类全部
                List<WebElement> eleList = getNavList(driver,null,".qyzx_bm.bm_menu","li",2);
                eleList.get(0).findElement(By.tagName("a")).click();
                Thread.sleep(1000);
            }else{
                return true;
            }
            Reporter.log("员工管理 员工列表 员工删除成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工删除失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

    //region 搜索员工

    public boolean searchEmp(WebDriver driver){
        try{
            Thread.sleep(500);
            String s = PhoneUtil.getTelephone();
            driver.findElement(By.className("qyzx_key_input")).sendKeys(s);
            driver.findElement(By.className("yqzx_key_search")).click();
            Thread.sleep(1000);
            Reporter.log("员工管理 员工列表 员工查询成功，查询手机号："+s+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工查询失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

    //region 批量导入员工

    public boolean batchImportEmp(WebDriver driver){
        try{
            Thread.sleep(500);
            driver.findElement(By.className("yqzx_pl")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[4]/div[3]/div[7]/a")).click();
            Thread.sleep(1000);
            batchImportEmpData(driver,null);//空模板错误示例
            Thread.sleep(500);
            batchImportEmpData(driver, PropertiesConfig.getInstance().getProperty("fuYou.employeeImportUrl3"));//格式错误示例
            Thread.sleep(500);
            batchImportEmpData(driver,PropertiesConfig.getInstance().getProperty("fuYou.employeeImportUrl2"));//模板数据未通过示例
            Thread.sleep(500);
            batchImportEmpData(driver,PropertiesConfig.getInstance().getProperty("fuYou.employeeImportUrl1"));//模板数据为空示例
            Thread.sleep(500);
            batchImportEmpData(driver,PropertiesConfig.getInstance().getProperty("fuYou.employeeImportUrl"));//成功示例，需模板内数据可以通过
            Thread.sleep(500);

            List<WebElement> eleList = getNavList(driver,null,".qyzx_bm.bm_menu","li",2);
            if(eleList == null){
                Actions mouse = new Actions(driver);
                List<WebElement> list = getNavList(driver,null,"fbgg_menu","li",0);
                int navIndex = getNavListId("员工管理",list);
                mouse.moveToElement(list.get(navIndex)).perform();
                List<WebElement> aList = getNavList(driver,list.get(navIndex),"", "li", 0);
                navIndex = getNavListId("员工列表",aList);
                aList.get(navIndex).findElement(By.tagName("a")).click();
            }
            Thread.sleep(500);

            //分类跳转操作
            eleList = getNavList(driver,null,".qyzx_bm.bm_menu","li",2);
            eleList.get(2).findElement(By.tagName("a")).click();
            Thread.sleep(1000);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工批量导入失败，错误："+e.toString()+"<br/>");
            return false;
        }
    }

    public void batchImportEmpData(WebDriver driver,String fileName){
        try{
            if(fileName == null){
                driver.findElement(By.id("btnImport")).click();
                Thread.sleep(500);
                boolean flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                if(flag){
                    Thread.sleep(500);
                    driver.findElement(By.className("zeromodal-close")).click();
                }
            }else{
                driver.findElement(By.id("select_btn_1")).sendKeys(ResourcesUrlUtil.pathUrl(fileName));
                Thread.sleep(500);
                boolean flag = isAlertPresent(driver);
                Thread.sleep(1000);
                if(flag){
                    driver.switchTo().alert().accept();
                    return;
                }
                Thread.sleep(500);
                flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                Thread.sleep(500);
                if(flag){
                    driver.findElement(By.className("zeromodal-close")).click();
                    return;
                }
                Thread.sleep(500);
                driver.findElement(By.id("btnImport")).click();
                Thread.sleep(500);
                flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
                if(flag){
                    driver.findElement(By.className("zeromodal-close")).click();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 员工列表 员工批量导入失败，错误："+e.toString()+"<br/>");
        }
    }

    //endregion

}
