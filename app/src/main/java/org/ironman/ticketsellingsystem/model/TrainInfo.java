package org.ironman.ticketsellingsystem.model;

import java.util.List;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by Administrator on 2019/4/19.
 */

public class TrainInfo implements IModel {

    /**
     * totalSize : null
     * data : null
     * success : true
     * totalPage : null
     * page : null
     * message : null
     * list : [{"startPlace":"长沙","businessSeat":"100","trainCard":"k-747","endPlace":"北京","businessPrice":200,"trainTime":"2019-03-26T00:00:00.000+0000","firstSeat":"100","firstSeatPrice":100,"startTime":"2019-03-26T16:22:25.000+0000","secondSeat":"100","secondSeatPrice":50,"id":1,"endTime":"2019-03-27T16:22:32.000+0000"},{"startPlace":"长沙","businessSeat":"50","trainCard":"k-777","endPlace":"北京","businessPrice":200,"trainTime":"2019-03-26T00:00:00.000+0000","firstSeat":"200","firstSeatPrice":100,"startTime":"2019-03-26T08:36:25.000+0000","secondSeat":"200","secondSeatPrice":50,"id":2,"endTime":"2019-03-26T20:36:33.000+0000"}]
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
         * startPlace : 长沙
         * businessSeat : 100
         * trainCard : k-747
         * endPlace : 北京
         * businessPrice : 200
         * trainTime : 2019-03-26T00:00:00.000+0000
         * firstSeat : 100
         * firstSeatPrice : 100
         * startTime : 2019-03-26T16:22:25.000+0000
         * secondSeat : 100
         * secondSeatPrice : 50
         * id : 1
         * endTime : 2019-03-27T16:22:32.000+0000
         */
        private String startPlace;
        private String businessSeat;
        private String trainCard;
        private String endPlace;
        private int businessPrice;
        private String trainTime;
        private String firstSeat;
        private int firstSeatPrice;
        private String startTime;
        private String secondSeat;
        private int secondSeatPrice;
        private int id;
        private String endTime;

        public void setStartPlace(String startPlace) {
            this.startPlace = startPlace;
        }

        public void setBusinessSeat(String businessSeat) {
            this.businessSeat = businessSeat;
        }

        public void setTrainCard(String trainCard) {
            this.trainCard = trainCard;
        }

        public void setEndPlace(String endPlace) {
            this.endPlace = endPlace;
        }

        public void setBusinessPrice(int businessPrice) {
            this.businessPrice = businessPrice;
        }

        public void setTrainTime(String trainTime) {
            this.trainTime = trainTime;
        }

        public void setFirstSeat(String firstSeat) {
            this.firstSeat = firstSeat;
        }

        public void setFirstSeatPrice(int firstSeatPrice) {
            this.firstSeatPrice = firstSeatPrice;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public void setSecondSeat(String secondSeat) {
            this.secondSeat = secondSeat;
        }

        public void setSecondSeatPrice(int secondSeatPrice) {
            this.secondSeatPrice = secondSeatPrice;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getStartPlace() {
            return startPlace;
        }

        public String getBusinessSeat() {
            return businessSeat;
        }

        public String getTrainCard() {
            return trainCard;
        }

        public String getEndPlace() {
            return endPlace;
        }

        public int getBusinessPrice() {
            return businessPrice;
        }

        public String getTrainTime() {
            return trainTime;
        }

        public String getFirstSeat() {
            return firstSeat;
        }

        public int getFirstSeatPrice() {
            return firstSeatPrice;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getSecondSeat() {
            return secondSeat;
        }

        public int getSecondSeatPrice() {
            return secondSeatPrice;
        }

        public int getId() {
            return id;
        }

        public String getEndTime() {
            return endTime;
        }
    }
}
