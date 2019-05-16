package com.selenium.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AESDncodeUtil {

    public static String decryptAES(String text,String key,String iv){
        try{
            byte [] encrypted = DatatypeConverter.parseBase64Binary(text);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(DatatypeConverter.parseBase64Binary(key), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(DatatypeConverter.parseBase64Binary(iv));

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.trim();
        }catch (Exception e){
            return null;
        }
    }

}
