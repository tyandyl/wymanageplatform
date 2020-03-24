package com.wy.manage.platform.core;

import com.wy.manage.platform.core.utils.ChinaFontTools;
import com.wy.manage.platform.core.widget.*;

/**
 * Created by tianye
 */
public class Test2 {

    public static void main(String[] args)throws Exception{
        String ss="yu宋io";
        char[] chars = ss.toCharArray();
        for(int i=0;i<chars.length;i++){
            if(chars[i]>1988){
                String s = ChinaFontTools.gbEncoding(chars[i]);
                int length = chars.length;
                int i1 = length - i - 1;
                char[] arrayCp=new char[chars.length+s.length()-1];
                //前半部分
                System.arraycopy(chars,0,arrayCp,0,i);
                //新增部分
                System.arraycopy(s.toCharArray(),0,arrayCp,i,s.length());
                //后半部分
                System.arraycopy(chars,i+1,arrayCp,s.length()+i,i1);
                System.out.println(arrayCp);
                chars=arrayCp;
                i--;
            }
        }


    }

}
