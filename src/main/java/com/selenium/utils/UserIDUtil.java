package com.selenium.utils;

import java.util.Random;

public class UserIDUtil {

    public static String getUserId() {
        String result = "";
        Random random = new Random();
        int num = random.nextInt(20);
        while (num == 0) {
            num = random.nextInt(20);
        }
        for (int i = 0; i < num; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) { // 产生字母
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                result += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) { // 产生数字
                result += String.valueOf(random.nextInt(10));
            }
        }

        return result;
    }

}
