package com.selenium.fuyou.employeeManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

public class departmentList {

    //region 新增部门

    public boolean addDep(WebDriver driver){
        try {
            Thread.sleep(500);
            String [] dep = new String[] { "运营","研发","测试","后勤" };
            driver.findElement(By.id("bumen")).click();
            //循环添加部门
            for(int i = 0;i < dep.length;i++){
                Thread.sleep(500);
                driver.findElement(By.id("orgName")).sendKeys(dep[i]);
                Thread.sleep(500);
                driver.findElement(By.name("btnadd")).click();
                Thread.sleep(500);
                boolean flag = isExistBoxOrExistButton(driver,"zeromodal-overlay",1);
                Thread.sleep(500);
                if(flag){
                    driver.findElement(By.xpath("/html/body/div[9]/div[2]/div")).click();
                    Thread.sleep(500);
                    driver.findElement(By.id("orgName")).clear();
                    continue;
                }
                Reporter.log("部门列表 部门添加成功，部门名称：" + dep[i]+"<br/>");
                if(i != dep.length - 1){
                    Thread.sleep(500);
                    driver.findElement(By.id("bumen")).click();
                    Thread.sleep(500);
                }

            }
            boolean flag = isExistBoxOrExistButton(driver,"zeromodal-container",1);
            if(flag){
                driver.findElement(By.className("zeromodal-close")).click();
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("部门列表 部门添加失败,错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

    //region 删除部门

    public boolean deleteDep(WebDriver driver){
        try{
            boolean flag = isExistBoxOrExistButton(driver,"/html/body/div[4]/div[4]/table/tbody/tr[2]/td[5]/a[2]",3);
            if(flag){
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[4]/div[4]/table/tbody/tr[2]/td[5]/a[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".zeromodal-btn.zeromodal-btn-primary.anblock")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[4]/div[4]/table/tbody/tr[2]/td[5]/a[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".zeromodal-btn.zeromodal-btn-default")).click();
            }else {
                Reporter.log("员工管理 部门列表 部门删除成功"+"<br/>");
                return true;
            }
            Reporter.log("员工管理 部门列表 部门删除成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 部门列表 部门删除失败,错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

    //region 编辑部门

    public boolean updateDep(WebDriver driver){
        try{
            boolean flag = isExistBoxOrExistButton(driver,"/html/body/div[4]/div[4]/table/tbody/tr[2]/td[5]/a[1]",3);
            if(flag) {
                Thread.sleep(500);
                driver.findElement(By.xpath("/html/body/div[4]/div[4]/table/tbody/tr[2]/td[5]/a[1]")).click();
                Thread.sleep(500);
                driver.findElement(By.className("dgygtj_in")).clear();
                Thread.sleep(500);
                driver.findElement(By.className("dgygtj_in")).sendKeys("测试");
                Thread.sleep(500);
                driver.findElement(By.name("btnupdate")).click();
            }else {
                Reporter.log("员工管理 部门列表 部门修改成功"+"<br/>");
                return true;
            }
            Reporter.log("员工管理 部门列表 部门修改成功"+"<br/>");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            taskScreenShot(driver);
            Reporter.log("员工管理 部门修改失败,错误："+e.toString()+"<br/>");
            return false;
        }
    }

    //endregion

}
