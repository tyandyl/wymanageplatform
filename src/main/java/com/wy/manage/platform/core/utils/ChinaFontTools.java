package com.wy.manage.platform.core.utils;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tianye
 */
public class ChinaFontTools {
    public static String gbEncoding(char m) {
        String unicodeBytes = "";
        String hexB = Integer.toHexString(m).toLowerCase();
        if (hexB.length() <= 2) {
            hexB = "00" + hexB;
        }
        unicodeBytes = unicodeBytes + "\\u" + hexB;
        return unicodeBytes;
    }

    public static String decodeUnicode( String str) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find()) {

            String group = matcher.group(2);

            ch = (char) Integer.parseInt(group, 16);

            String group1 = matcher.group(1);
            str = str.replace(group1, ch + "");
        }
        return str;

    }



}
