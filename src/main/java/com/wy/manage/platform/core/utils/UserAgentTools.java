package com.wy.manage.platform.core.utils;

import java.util.StringTokenizer;

/**
 * Created by tianye13 on 2019/3/21.
 */
public class UserAgentTools {

    public static String getUserAgent(javax.servlet.http.HttpServletRequest request){
        String Agent = request.getHeader("User-Agent");
        StringTokenizer st = new StringTokenizer(Agent,";");
        st.nextToken();
        //得到用户的浏览器名
        String userBrowser = st.nextToken();

        //得到用户的操作系统名

        String useros = st.nextToken();

        System.out.println("用户浏览器是:"+useros);

        return userBrowser;
    }
}
