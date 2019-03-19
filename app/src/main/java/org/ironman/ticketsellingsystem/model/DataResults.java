package org.ironman.ticketsellingsystem.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by kaisa on 2016/12/11.
 */

//数据data
public class DataResults<T> implements IModel {

    protected boolean error;

    protected String msg;
    private T data;

    public T getT() {
        return data;
    }

    public void setT(T data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    @Override
    public boolean isNull() {
        return data == null;
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
