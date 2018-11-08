package com.wy.manage.platform.core.model;

import com.wy.manage.platform.core.action.Action;
import com.wy.manage.platform.core.parser.NfaStateMachine;

/**
 * Created by tianye
 * 解析css模型
 */
public interface Model {
    String getAddress();
    //解释模型
    NfaStateMachine parserExplain()throws Exception;
    //编译模型
    String parserCompile();
    void defineAction();
    void defineAction(Action action);
    void execute(String str)throws Exception;

}
