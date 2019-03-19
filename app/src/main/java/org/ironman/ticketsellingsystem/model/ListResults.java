package org.ironman.ticketsellingsystem.model;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by kaisa on 2016/12/11.
 */
//列表结果集

public class ListResults<T> implements IModel {

    protected boolean error;

    protected String msg;

    private int total_page;

    private List<T> data;

    public List<T> getT() {
        return data;
    }

    public void setT(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return total_page;
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
