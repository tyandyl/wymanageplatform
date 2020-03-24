package com.wy.manage.platform.core.parser.test;

import java.nio.charset.Charset;

/**
 * Created by tianye13 on 2019/3/8.
 */
public class Encoding {
    public static String getEncoding(String str)
    {
        String encode;

        encode = "UTF-16";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "ASCII";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "ISO-8859-1";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "GBK";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "GB2312";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}

        encode = "UTF-8";
        try
        {
            if(str.equals(new String(str.getBytes(), encode)))
            {
                return encode;
            }
        }
        catch(Exception ex) {}



        /*
	 *......待完善
	 */

        return "未识别编码格式";
    }

}
