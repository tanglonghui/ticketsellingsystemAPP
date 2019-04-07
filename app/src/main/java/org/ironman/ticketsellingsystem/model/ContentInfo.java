package org.ironman.ticketsellingsystem.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by Archer on 2019/4/6.
 */

public class ContentInfo  implements IModel {
    //基本的返回信息结果类
    private boolean success;
    private String message;
    private Object data;
    private Object list;
    private Object totalSize;
    private Object page;
    private Object totalPage;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

    public Object getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Object totalSize) {
        this.totalSize = totalSize;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

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
        return false;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }
}