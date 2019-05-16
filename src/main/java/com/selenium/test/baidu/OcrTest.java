package com.selenium.test.baidu;

import com.selenium.utils.PropertiesConfig;
import com.selenium.utils.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李啸天 on 2019/3/22.
 */
public class OcrTest {

    //设置APPID/AK/SK
    public static final String APP_ID = PropertiesConfig.getInstance().getProperty("baidu.appId");
    public static final String API_KEY =  PropertiesConfig.getInstance().getProperty("baidu.appKey");
    public static final String SECRET_KEY =  PropertiesConfig.getInstance().getProperty("baidu.secretKey");

    public static Map<String,Object> orcImage(String imageUrl) {
        AipOcr client = new AipOcr(APP_ID,API_KEY,SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        JSONObject jsonObject = client.basicGeneral(imageUrl,options);
        Map<String,Object> map =new HashMap<>();
        System.out.println("请求响应结果:"+jsonObject.toString());
        if(jsonObject.getBigInteger("log_id")!=null&&!jsonObject.has("error_msg")){
            JSONArray array = jsonObject.getJSONArray("words_result");
            if(array.length()>0){
                JSONObject json = array.getJSONObject(0);
                String words = json.getString("words");
                System.out.println(words);
                System.out.println(jsonObject.toString(2));
                map.put("states",true);
                map.put("words",words);
            }else{
                map.put("states",false);
            }
        }else{
            map.put("states",false);
        }

        return map;
    }

    public static void main(String[] args) {
            Map<String,Object> ss = OcrTest.orcImage("E:\\2019\\test.png");
            System.out.println("sss"+ss);
    }
}
