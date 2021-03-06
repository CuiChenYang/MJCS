package com.selenium.flx.systemManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.List;

import static com.selenium.flx.flx.journal;
import static com.selenium.flx.flxPublicMethod.switchIframe;
import static com.selenium.flx.flxPublicMethod.taskScreenShot;
import static com.selenium.flx.flxPublicMethod.updateInput;
import static com.selenium.fuyou.fuYouMethod.isAlertPresent;
import static com.selenium.fuyou.fuYouMethod.isExistBoxOrExistButton;

public class other {

    //设置安全策略
    public boolean installSafeStrategy(WebDriver driver) {
        try {
            Thread.sleep(500);
            switchIframe(driver, "/policy/access_rules_list.jsp", 0);

            //新增
            installSafeStrategy_add(driver);
            Thread.sleep(500);
            //判断是否已有测试信息
            if (isExistBoxOrExistButton(driver, "//*[@class=\"mini-messagebox-buttons\"]/a/span", 3)) {
                Thread.sleep(500);
                driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-cancel")).click();
                Thread.sleep(500);
                switchIframe(driver, "/policy/access_rules_list.jsp", 0);
                Thread.sleep(500);
                if (!installSafeStrategy_clickIP(driver, "自动化测试")) {
                    if (journal) {
                        taskScreenShot(driver);
                        Reporter.log("系统管理--其他--设置安全策略--测试失败。错误：已存在自动化测试ip：192.168.44.44或192.168.44.77。请查看并更改" + "<br/>");
                        return false;
                    }
                }
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
                Thread.sleep(500);
                driver.switchTo().alert().accept();
                Thread.sleep(1000);
                installSafeStrategy_add(driver);
            }

            //编辑
            Thread.sleep(500);
            switchIframe(driver, "/policy/access_rules_list.jsp", 0);
            Thread.sleep(500);
            installSafeStrategy_clickIP(driver, "自动化测试");
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-edit")).click();
            Thread.sleep(500);
            switchIframe(driver, "/policy/access_rules_edit.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "endIP$text", "192.168.44.46");
            Thread.sleep(500);
            driver.findElement(By.id("enabled$text")).click();
            Thread.sleep(500);
            driver.findElement(By.id("mini-10$1")).click();
            Thread.sleep(500);
            driver.findElement(By.className("mini-fit")).click();
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();

            //删除
            Thread.sleep(500);
            switchIframe(driver, "/policy/access_rules_list.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
            Thread.sleep(500);
            driver.switchTo().alert().accept();

            Thread.sleep(1000);

            if (journal) {
                Reporter.log("系统管理--其他--设置安全策略--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--其他--设置安全策略--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void installSafeStrategy_add(WebDriver driver) throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-add")).click();
        Thread.sleep(500);
        switchIframe(driver, "/policy/access_rules_edit.jsp", 0);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
        Thread.sleep(500);
        updateInput(driver, "id", "startIP$text", "524512");
        Thread.sleep(500);
        updateInput(driver, "id", "endIP$text", "sgdrgs");
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
        Thread.sleep(500);
        updateInput(driver, "id", "startIP$text", "192.168.44.44");
        Thread.sleep(500);
        updateInput(driver, "id", "endIP$text", "192.168.44.45");
        Thread.sleep(500);
        updateInput(driver, "id", "remark$text", "自动化测试");
        Thread.sleep(500);
        driver.findElement(By.className("mini-fit")).click();
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
    }

    public boolean installSafeStrategy_clickIP(WebDriver driver, String name) throws InterruptedException {
        Thread.sleep(500);
        List<WebElement> list = driver.findElements(By.cssSelector(".mini-grid-cell-inner.mini-grid-cell-nowrap "));
        Thread.sleep(500);
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i).getText().trim())) {
                list.get(i).click();
                return true;
            }
        }
        return false;
    }

    //配置业务字典
    public boolean disposeTransactionDictionary(WebDriver driver) {
        try {
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);

            // 业务字典类型
            //添加
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/div/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td/a[1]/span")).click();
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/edit_dict_type.jsp", 0);
            Thread.sleep(500);
            updatedicttype(driver, "test", "测试");
            //若已存在先删除再添加
            Thread.sleep(1000);
            if (isExistBoxOrExistButton(driver, "mini-messagebox-buttons", 1)) {
                Thread.sleep(1000);
                driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-close")).click();
                Thread.sleep(500);
                switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
                Thread.sleep(500);
                updateInput(driver, "id", "dicttypeid$text", "test");
                Thread.sleep(500);
                driver.findElement(By.xpath("//*[@id=\"query_dict_type_form\"]/table/tbody/tr[1]/td[5]/a/span")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-remove")).click();
                Thread.sleep(500);
                driver.findElement(By.xpath("//div[@class='mini-messagebox-buttons']/a[1]")).click();

                Thread.sleep(500);
                driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/div/div/div/div[2]/div[2]/div[2]/div/table/tbody/tr/td/a[1]/span")).click();
                Thread.sleep(500);
                switchIframe(driver, "/coframe/dict/edit_dict_type.jsp", 0);
                Thread.sleep(500);
                updatedicttype(driver, "test", "测试");
            }
            //添加子类型
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
            Thread.sleep(500);
            updateInput(driver, "id", "dicttypeid$text", "test");
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"query_dict_type_form\"]/table/tbody/tr[1]/td[5]/a/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"btn_addSubDictType\"]/span")).click();
            Thread.sleep(500);
            updatedicttype(driver, "test_test", "测试子类型");
            //修改
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"btn_editDictType\"]/span")).click();
            Thread.sleep(500);
            updatedicttype(driver, "", "测试1");

            //业务字典项
            //新增
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/div/div/div/div[2]/div[1]/div/a[1]/span")).click();
            Thread.sleep(1000);
            updatedict(driver, "false", "test01", "测试字典项01", "1");
            //添加子项
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
            Thread.sleep(1000);
            driver.findElements(By.className("mini-grid-radio-mask")).get(2).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"btn_addSubDict\"]/span")).click();
            Thread.sleep(1000);
            updatedict(driver, "true", "test01_test01", "测试字典项01子项", "1");
            //修改
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@id=\"btn_editDict\"]/span")).click();
            Thread.sleep(1000);
            updatedict(driver, "false", "", "测试字典项02", "2");
            //删除  业务字典项
            Thread.sleep(500);
            switchIframe(driver, "/coframe/dict/dict_manager.jsp", 0);
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-tree-node-ecicon.mini-tree-node-ecicon-firstAndlast")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"btn_removeDict\"]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a[1]")).click();
            //删除  业务字典类型
            Thread.sleep(500);
            driver.findElement(By.cssSelector(".mini-tree-node-ecicon.mini-tree-node-ecicon-first")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"btn_removeDictType\"]/span")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//*[@class='mini-messagebox-buttons']/a[1]")).click();

            Thread.sleep(1000);

            if (journal) {
                Reporter.log("系统管理--其他--配置业务字典--测试完成 <br/>");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (journal) {
                taskScreenShot(driver);
                Reporter.log("系统管理--其他--配置业务字典--测试失败。错误：" + e.toString() + "<br/>");
            }
            return false;
        }
    }

    public void updatedicttype(WebDriver driver, String dicttypeid$text, String dicttypename$text) throws InterruptedException {
        switchIframe(driver, "/coframe/dict/edit_dict_type.jsp", 0);
        Thread.sleep(500);
        if (!(dicttypeid$text == null || "".equals(dicttypeid$text)))
            updateInput(driver, "id", "dicttypeid$text", dicttypeid$text);
        Thread.sleep(500);
        updateInput(driver, "id", "dicttypename$text", dicttypename$text);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
    }

    public void updatedict(WebDriver driver, String dicttypeid$text, String dictid$text, String dictname$text, String sortno$text) throws InterruptedException {
        switchIframe(driver, "/coframe/dict/edit_dict.jsp", 0);
        if ("true".equals(dicttypeid$text)) {
            Thread.sleep(1000);
            driver.findElement(By.id("dicttypeid$text")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("mini-listbox-item")).click();
        }
        if (!(dictid$text == null || "".equals(dictid$text))) {
            Thread.sleep(500);
            updateInput(driver, "id", "dictid$text", dictid$text);
        }
        Thread.sleep(500);
        updateInput(driver, "id", "dictname$text", dictname$text);
        Thread.sleep(500);
        updateInput(driver, "id", "sortno$text", sortno$text);
        Thread.sleep(500);
        driver.findElement(By.cssSelector(".mini-button-text.mini-button-icon.icon-save")).click();
    }
}
