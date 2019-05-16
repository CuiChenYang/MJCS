package com.selenium.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by 李啸天 on 2018/10/9.
 */
@Slf4j
public class POIUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";


    /**
     * 读取文件，并修改excel文件中的优分金额
     *
     * @param file
     */
    public static void readExcel(File file) {
        try {
            FileInputStream in = new FileInputStream(file); // 文件流
            //XSSFWorkbook 适用于xlsx
            //Workbook workbook = new XSSFWorkbook(in);
            //HSSFWorkbook 适用于xls
            Workbook workbook2 = new HSSFWorkbook(in);
            //获取第一个sheet
            Sheet sheet = workbook2.getSheetAt(0);
            //表格总计多少行
            int total = sheet.getLastRowNum();
            //遍历每一行
            for (Row row : sheet) {
                //获取第1，2个单元格对象
                //如果cell为空，直接用row.createCell(0)就行
                Cell cell1 = row.getCell(2);
                cell1.setCellType(CellType.STRING);
                String cellValue = cell1.getStringCellValue();
                System.out.println(cellValue);
                if ("0".equals(cellValue)) {
                    cell1.removeCellComment();
                    cell1.setCellValue("100");
                }
            }
            //对文档的写入还在缓存区，需要把流刷到文件中
            FileOutputStream out = new FileOutputStream(file);
            workbook2.write(out);
            //该关闭的关闭
            workbook2.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String FixFileName(String filePath, String newFileName) {
        File f = new File(filePath);
        if (!f.exists()) { // 判断原文件是否存在（防止文件名冲突）
            return null;
        }
        newFileName = newFileName.trim();
        if ("".equals(newFileName) || newFileName == null) // 文件名不能为空
            return null;
        String newFilePath = null;
        if (f.isDirectory()) { // 判断是否为文件夹
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName;
        } else {
            newFilePath = filePath.substring(0, filePath.lastIndexOf("\\")) + "\\" + newFileName;
        }
        File nf = new File(newFilePath);
        try {
            f.renameTo(nf); // 修改文件名
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
        return newFilePath;
    }

    /**
     * 读取某个文件夹下面的xls结尾的文件
     * @param fileUrl
     * @return
     */
    public static String xlsUrl(String fileUrl){
        try {
            File file = ResourceUtils.getFile(fileUrl);
            File [] files = file.listFiles();
            for(File name:files){
                String names = name.toString();
                if(names.indexOf("员工导入模版")==-1||names.indexOf("企业对账信息")==-1)
                if(names.endsWith(".xls")){
                    System.out.println(names);
                    return names;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) throws Exception {
        //定义文件夹
        String path = "D:\\IDEA\\SeleniumDemo1\\src\\main\\resources\\fuyou";
        //查询文件夹下以xls结尾的文件
        String fileName = POIUtil.xlsUrl(path);
        //读取
        File file  = ResourceUtils.getFile(fileName);
        //修改优分余额
        POIUtil.readExcel(file);

        //上传操作完成后删除
        file.delete();
        //POIUtil.FixFileName(fileName,"123.xls");

    }
}
