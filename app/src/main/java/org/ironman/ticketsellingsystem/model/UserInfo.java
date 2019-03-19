package org.ironman.ticketsellingsystem.model;


import cn.droidlover.xdroidmvp.net.IModel;

public class UserInfo implements IModel{

    /**
     * success : true
     * message : 登录成功
     * data : {"id":1,"account":"5","password":"2","name":"2","age":2,"idCard":3,"sex":"2222","phone":"2","type":"2","emal":"2","state":"1"}
     * list : null
     * totalSize : null
     * page : null
     * totalPage : null
     */

    private boolean success;
    private String message;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * id : 1
         * account : 5
         * password : 2
         * name : 2
         * age : 2
         * idCard : 3
         * sex : 2222
         * phone : 2
         * type : 2
         * emal : 2
         * state : 1
         */

        private int id;
        private String account;
        private String password;
        private String name;
        private int age;
        private int idCard;
        private String sex;
        private String phone;
        private String type;
        private String emal;
        private String state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getIdCard() {
            return idCard;
        }

        public void setIdCard(int idCard) {
            this.idCard = idCard;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public String getEmal() {
            return emal;
        }

        public void setEmal(String emal) {
            this.emal = emal;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}