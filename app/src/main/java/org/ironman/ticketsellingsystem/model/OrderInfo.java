package org.ironman.ticketsellingsystem.model;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by Administrator on 2019/4/19.
 */

public class OrderInfo implements IModel {


    /**
     * totalSize : null
     * data : null
     * success : true
     * totalPage : null
     * page : null
     * message : null
     * list : [{"trainId":1,"startPlace":"上海","trainCard":"k-747","userId":1,"endPlace":"北京","seat":"0","orderTime":"2019-05-09 00:00:30","price":"200","pasengerId":30,"name":"tony","startTime":"16:22","id":1,"state":"0","endTime":"16:22"},{"trainId":1,"startPlace":"上海","trainCard":"k-747","userId":1,"endPlace":"北京","seat":"0","orderTime":"2019-05-09 00:02:32","price":"200","pasengerId":30,"name":"tony","startTime":"16:22","id":2,"state":"0","endTime":"16:22"},{"trainId":1,"startPlace":"上海","trainCard":"k-747","userId":1,"endPlace":"北京","seat":"0","orderTime":"2019-05-09 00:03:25","price":"200","pasengerId":30,"name":"tony","startTime":"16:22","id":3,"state":"0","endTime":"16:22"}]
     */
    private String totalSize;
    private String data;
    private boolean success;
    private String totalPage;
    private String page;
    private String message;
    private List<ListEntity> list;

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public String getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public String getPage() {
        return page;
    }

    public String getMessage() {
        return message;
    }

    public List<ListEntity> getList() {
        return list;
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

    public class ListEntity {
        /**
         * trainId : 1
         * startPlace : 上海
         * trainCard : k-747
         * userId : 1
         * endPlace : 北京
         * seat : 0
         * orderTime : 2019-05-09 00:00:30
         * price : 200
         * pasengerId : 30
         * name : tony
         * startTime : 16:22
         * id : 1
         * state : 0
         * endTime : 16:22
         */
        private int trainId;
        private String startPlace;
        private String trainCard;
        private int userId;
        private String endPlace;
        private String seat;
        private String orderTime;
        private String price;
        private int pasengerId;
        private String name;
        private String startTime;
        private int id;
        private String state;
        private String endTime;

        public void setTrainId(int trainId) {
            this.trainId = trainId;
        }

        public void setStartPlace(String startPlace) {
            this.startPlace = startPlace;
        }

        public void setTrainCard(String trainCard) {
            this.trainCard = trainCard;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setEndPlace(String endPlace) {
            this.endPlace = endPlace;
        }

        public void setSeat(String seat) {
            this.seat = seat;
        }

        public void setOrderTime(String orderTime) {
            this.orderTime = orderTime;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setPasengerId(int pasengerId) {
            this.pasengerId = pasengerId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getTrainId() {
            return trainId;
        }

        public String getStartPlace() {
            return startPlace;
        }

        public String getTrainCard() {
            return trainCard;
        }

        public int getUserId() {
            return userId;
        }

        public String getEndPlace() {
            return endPlace;
        }

        public String getSeat() {
            return seat;
        }

        public String getOrderTime() {
            return orderTime;
        }

        public String getPrice() {
            return price;
        }

        public int getPasengerId() {
            return pasengerId;
        }

        public String getName() {
            return name;
        }

        public String getStartTime() {
            return startTime;
        }

        public int getId() {
            return id;
        }

        public String getState() {
            return state;
        }

        public String getEndTime() {
            return endTime;
        }
    }
}
