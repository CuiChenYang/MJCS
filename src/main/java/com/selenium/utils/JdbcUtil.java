package com.selenium.utils;

import com.selenium.base.DriverBase;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;

/**
 * Created by 李啸天 on 2019/3/27.
 */
public class JdbcUtil {

    //jdbcUrl
    public String jdbcUrl = PropertiesConfig.getInstance().getProperty("jdbc.url");

    //userName
    public String userName = PropertiesConfig.getInstance().getProperty("jdbc.userName");

    //password
    public String password = PropertiesConfig.getInstance().getProperty("jdbc.password");

    //driverName
    public String driverName = PropertiesConfig.getInstance().getProperty("jdbc.driverName");


    /**
     * 获取短信验证码
     * @param phoneNo
     */
    public String querySmsCode(String phoneNo){
        String code = null;
        try {
            //加载驱动
            Class.forName(driverName);
            //获取数据库链接
            Connection conn = DriverManager.getConnection(jdbcUrl,userName,password);
            //操作数据库
            String sql="select top 1 Code from aspnet_CodeInfo where UserName=? order by CodeId desc";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,phoneNo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("Code"));
                code = rs.getString("Code");
            }
            return code;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 手机号获取
     * @param customNo
     * @return
     */
    public String queryCellPhone(String customNo){
        String phoneNo = null;
        try {
            //加载驱动
            Class.forName(driverName);
            //获取数据库链接
            Connection conn = DriverManager.getConnection(jdbcUrl,userName,password);
            //操作数据库
            String sql="select CellPhone from aspnet_Members where UserName=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,customNo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("CellPhone"));
                phoneNo = rs.getString("CellPhone");
            }
            return phoneNo;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        JdbcUtil jdbcUtil = new JdbcUtil();
        String phoneNo = jdbcUtil.queryCellPhone("01510276");
        jdbcUtil.querySmsCode(phoneNo);
    }

}
