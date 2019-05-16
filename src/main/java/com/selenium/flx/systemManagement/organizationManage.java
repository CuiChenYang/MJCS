package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;

public class organizationManage {
    //组织机构管理
    public boolean organizationManage(WebDriver driver) {
        try {

            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);

            //新增测试组织
            Actions mouse = new Actions(driver);
            List<WebElement> list = driver.findElements(By.className("mini-tree-nodetext"));
            Thread.sleep(500);
            mouse.contextClick(list.get(0)).perform();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"treeMenu\"]/div/div/div[1]/div[1]/div/div[2]")).click();
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_add.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "orgname$text", "test公司");
            Thread.sleep(500);
            updateInput(driver, "id", "orgcode$text", "99");
            Thread.sleep(500);
            driver.findElement(By.id("orgtype$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-10$0")).click();
            Thread.sleep(500);
            driver.findElement(By.id("orgdegree$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-13$1")).click();
            Thread.sleep(500);
            driver.findElement(By.id("status$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-16$0")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "sortno$text", "1");
            Thread.sleep(500);
            updateInput(driver, "id", "area$text", "湖北");
            Thread.sleep(500);
            updateInput(driver, "id", "orgaddr$text", "武汉");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //本级机构  修改
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            list = driver.findElements(By.className("mini-tree-nodeshow"));
            Thread.sleep(500);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getText().equals("test公司")) {
                    list.get(2).click();
                    break;
                }
            }
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_update.jsp", 1);
            Thread.sleep(500);
            updateInput(driver, "id", "sortno$text", "3");
            Thread.sleep(500);
            updateInput(driver, "id", "orgaddr$text", "襄阳");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-reset")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "orgcode$text", "88");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"startdate\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"mini-30\"]/tbody/tr[2]/td/div/span[2]")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "enddate$text", "2050-05-02");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();

            //下级机构 新增
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("mini-17$4")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/sub_org_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_add.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "orgname$text", "测试机构开发部");
            Thread.sleep(500);
            updateInput(driver, "id", "orgcode$text", "89");
            Thread.sleep(500);
            driver.findElement(By.id("orgtype$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-10$1")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //编辑
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/org/organization/sub_org_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_update.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "orgname$text", "测试下级机构");
            Thread.sleep(500);
            updateInput(driver, "id", "orgcode$text", "98");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/org/organization/sub_org_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();

            //下级岗位 新增
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("mini-17$5")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/position/position_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/position/position_org_add.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "posiname$text", "测试岗");
            Thread.sleep(500);
            updateInput(driver, "id", "posicode$text", "01");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //编辑
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/org/position/position_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/position/position_org_update.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "posiname$text", "开发岗");
            Thread.sleep(500);
            updateInput(driver, "id", "posicode$text", "02");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"startdate\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.switchTo().defaultContent();
            Thread.sleep(500);
            driver.findElement(By.className("mini-calendar-tadayButton")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/position/position_org_update.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/org/position/position_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();

            //员工信息 新增
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("mini-17$6")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/employee/employee_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/employee/employee_add.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "empname$text", "李四");
            Thread.sleep(500);
            updateInput(driver, "id", "empcode$text", "04");
            Thread.sleep(500);
            driver.findElement(By.id("gender$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-9$0")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"indate\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"mini-12\"]/tbody/tr[2]/td/div/span[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.id("empstatus$text")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"mini-24$0\"]/td[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-2$2")).click();
            Thread.sleep(500);
            updateInput(driver, "id", "haddress$text", "上海");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"major\"]/span/span/span[2]/span")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/employee/select_major.jsp", 0);
            Thread.sleep(1000);
            updateInput(driver, "xpath", "//*[@id=\"table1\"]/tbody/tr[1]/td[2]/span/span/input", "测试丁");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-search")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-ok")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/employee/employee_add.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //编辑
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/org/employee/employee_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/employee/employee_update.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("empstatus$text")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"mini-24$1\"]/td[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
            //删除
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            switchIframe(driver, "/FlxServer/coframe/org/employee/employee_list.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.className("mini-grid-radio-mask")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();

            //权限设置
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("mini-17$7")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/partyauth/auth.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/a[3]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/a[2]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();
            list = driver.findElements(By.className("mini-listbox-item"));
            Thread.sleep(500);
            list.get(2).click();
            list.get(3).click();
            list.get(4).click();
            list.get(6).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/a[4]/span")).click();
            Thread.sleep(500);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();
            Thread.sleep(500);
            list = driver.findElements(By.className("mini-listbox-item"));
            Thread.sleep(500);
            list.get(1).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/div[1]/table/tbody/tr/td[2]/a[1]/span")).click();
            Thread.sleep(500);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a")).click();

            //权限计算
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.id("mini-17$8")).click();
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/auth/authgraph/auth_graph.jsp", 1);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[name()='svg']/*[name()='text'][5]/*[name()='tspan']")).click();
            Thread.sleep(500);
            driver.switchTo().defaultContent();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//span[@id='3' and @class='mini-tools-close ']")).click();

            //删除测试组织
            Thread.sleep(500);
            switchIframe(driver, "/FlxServer/coframe/org/organization/org_tree.jsp", 1);
            list = driver.findElements(By.className("mini-tree-nodeshow"));
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getText().equals("test公司")) {
                    mouse.click(list.get(i)).perform();
                    mouse.contextClick(list.get(i)).perform();
                    break;
                }
            }
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"treeMenu\"]/div/div/div[1]/div[5]/div/div[2]")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//a[contains(@style,'width: 58px; margin-right: 15px;')]")).click();

            Thread.sleep(1500);
            driver.switchTo().defaultContent();

            if (journal) {
                Reporter.log("系统管理--组织机构管理--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--组织机构管理--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }
}
