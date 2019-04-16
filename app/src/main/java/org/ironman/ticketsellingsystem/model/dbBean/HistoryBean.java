package org.ironman.ticketsellingsystem.model.dbBean;

import java.util.Date;

/**
 * @data created on 2019/4/16
 * @describe TODO 保存列车查询的历史信息
 */
public class HistoryBean {
    /**
     * 起点
     */
    private String startPlace;

    /**
     * 终点
     */
    private String endPlace;

    /**
     * 时间(MM月DD日)
     */
    private String tvDate;

    /**
     * 时间(yyyy-MM-DD)
     */
    private String date;

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getTvDate() {
        return tvDate;
    }

    public void setTvDate(String tvDate) {
        this.tvDate = tvDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
