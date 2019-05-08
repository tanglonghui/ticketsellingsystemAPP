package org.ironman.ticketsellingsystem.model;

import java.io.Serializable;
import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by Archer on 2019/4/28.
 */

public class PasengerInfo implements IModel {

    /**
     * success : true
     * message : null
     * data : null
     * list : [{"id":4,"userId":3,"pasengerId":30,"name":"Tony","idCard":"123123","idCardType":null,"phone":"12121221212","type":"成人"},{"id":5,"userId":3,"pasengerId":31,"name":"MkaT","idCard":"123123","idCardType":null,"phone":"12121221212","type":"成人"},{"id":6,"userId":3,"pasengerId":32,"name":"MkaT","idCard":"123123","idCardType":null,"phone":"12121221212","type":"成人"},{"id":7,"userId":3,"pasengerId":33,"name":"Roly","idCard":"123123","idCardType":null,"phone":"12121221212","type":"学生"}]
     * totalSize : null
     * page : null
     * totalPage : null
     */

    private boolean success;
    private String message;
    private Object data;
    private Object totalSize;
    private Object page;
    private Object totalPage;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
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

    public static class ListBean implements Serializable{
        /**
         * id : 4
         * userId : 3
         * pasengerId : 30
         * name : Tony
         * idCard : 123123
         * idCardType : null
         * phone : 12121221212
         * type : 成人
         */

        private Integer id;
        private Integer userId;
        private Integer pasengerId;
        private String name;
        private String idCard;
        private Object idCardType;
        private String phone;
        private String type;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getPasengerId() {
            return pasengerId;
        }

        public void setPasengerId(int pasengerId) {
            this.pasengerId = pasengerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public Object getIdCardType() {
            return idCardType;
        }

        public void setIdCardType(Object idCardType) {
            this.idCardType = idCardType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
