package com.selenium.utils;

import org.hibernate.validator.internal.util.privilegedactions.GetResource;

import java.net.URLDecoder;

/**
 * Created by 李啸天 on 2019/3/28.
 */
public class ResourcesUrlUtil {

    /**
     * 读取resources下文件的绝对路径
     * @param resourceUrl
     * @return
     */
    public static String pathUrl(String resourceUrl){
        String path = null;
        try {
            String encodePath = URLDecoder.decode(GetResource.class.getClassLoader().getResource(resourceUrl).getPath(),"utf-8");
            path = encodePath.substring(1,encodePath.length()).replaceAll("/","\\\\");
            System.out.println(path);
        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }

    public static void main(String[] args) {
        ResourcesUrlUtil.pathUrl("application.yml");
    }
}
