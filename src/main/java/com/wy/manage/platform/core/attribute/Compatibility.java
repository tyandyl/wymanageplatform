package com.wy.manage.platform.core.attribute;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tianye on 2018/8/8.
 */
public class Compatibility implements Serializable {

    private static final long serialVersionUID = -2067736064017930069L;

    private String proTypeId;

    private List<String> browserIds;

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }

    public List<String> getBrowserIds() {
        return browserIds;
    }

    public void setBrowserIds(List<String> browserIds) {
        this.browserIds = browserIds;
    }
}
