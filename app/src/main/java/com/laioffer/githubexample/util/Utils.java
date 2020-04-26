package com.laioffer.githubexample.util;

import android.content.Context;
import android.widget.Toast;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Utils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
    public static Toast constructToast(Context context, String msg) {
        return Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }
    /**
     * Md5 encryption, encode string
     * @param input the string to be encoded
     * @return encoded string
     */
    public static String md5Encryption(final String input){
        String result = "";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] resultByte = messageDigest.digest();
            result = new String(Hex.encodeHex(resultByte));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    public static int whiteToBlack(int pos) {
        if (pos == 0) {
            return 0xffffffff;
        } else if (pos == 1) {
            return 0xffcccccc;
        } else if (pos == 2) {
            return 0xff777777;
        } else if (pos == 3) {
            return 0xff333333;
        } else {
            return 0xff000000;
        }

    }
}
