package com.wy.manage.platform.core.widget;

import java.io.Serializable;

/**
 * Created by tianye on 2018/8/13.
 */
public class Meta implements Serializable {

    private static final long serialVersionUID = 3244134854766089971L;

    private String http_equiv;
    private String content;

    public String getHttp_equiv() {
        return http_equiv;
    }

    public void setHttp_equiv(String http_equiv) {
        this.http_equiv = http_equiv;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
