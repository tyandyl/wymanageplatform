package com.wy.manage.platform.core.parser;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianye
 */
public class DfaStateNode implements Serializable {
    private static final long serialVersionUID = 1504473872845557276L;
    private Set<NfaStateNode> nfaStateNodes=new HashSet<NfaStateNode>();

}
