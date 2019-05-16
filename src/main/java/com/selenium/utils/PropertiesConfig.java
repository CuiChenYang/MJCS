package com.selenium.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Created by 李啸天 on 2018/11/26.
 * */
public class PropertiesConfig extends Properties{

    private static final long serialVersionUID = 1L;

    private InputStream inputStream = this.getClass().getResourceAsStream("/sms.properties");

    private static PropertiesConfig propertiesUtil;

    private PropertiesConfig()
    {
        try
        {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            this.load(bf);
            inputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static PropertiesConfig getInstance()
    {
        if (propertiesUtil == null)
        {
            return new PropertiesConfig();
        }
        else
        {
            return propertiesUtil;
        }
    }

    public static void main(String[] args) {
        String urls = PropertiesConfig.getInstance().getProperty("fuYou.employeeImportUrl");
        System.out.println(urls);
    }
}
