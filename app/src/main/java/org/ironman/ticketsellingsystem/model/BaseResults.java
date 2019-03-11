package org.ironman.ticketsellingsystem.model;

import cn.droidlover.xdroidmvp.net.IModel;


//基础模型

public class BaseResults implements IModel {

    protected boolean error;

    protected String msg;

    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return false;
    }

    @Override
    public boolean isBizError() {
        return error;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
