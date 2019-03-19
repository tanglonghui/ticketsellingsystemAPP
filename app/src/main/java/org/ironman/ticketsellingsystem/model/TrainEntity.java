package org.ironman.ticketsellingsystem.model;

import java.util.Date;


public class TrainEntity  {
    private Integer id;

    private String trainModel;

    private String startPlace;

    private String endPlace;

    private Date startTime;

    private Date endTime;

    private Integer firstSeats;

    private Integer secondSeats;

    private Integer businessSeats;

    private Float fristPrice;

    private Float secondPrice;

    private Float businessPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(String trainModel) {
        this.trainModel = trainModel == null ? null : trainModel.trim();
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace == null ? null : startPlace.trim();
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace == null ? null : endPlace.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getFirstSeats() {
        return firstSeats;
    }

    public void setFirstSeats(Integer firstSeats) {
        this.firstSeats = firstSeats;
    }

    public Integer getSecondSeats() {
        return secondSeats;
    }

    public void setSecondSeats(Integer secondSeats) {
        this.secondSeats = secondSeats;
    }

    public Integer getBusinessSeats() {
        return businessSeats;
    }

    public void setBusinessSeats(Integer businessSeats) {
        this.businessSeats = businessSeats;
    }

    public Float getFristPrice() {
        return fristPrice;
    }

    public void setFristPrice(Float fristPrice) {
        this.fristPrice = fristPrice;
    }

    public Float getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Float secondPrice) {
        this.secondPrice = secondPrice;
    }

    public Float getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(Float businessPrice) {
        this.businessPrice = businessPrice;
    }
}