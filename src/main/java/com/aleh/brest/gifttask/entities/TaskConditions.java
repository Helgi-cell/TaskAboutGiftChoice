package com.aleh.brest.gifttask.entities;

import java.math.RoundingMode;

public class TaskConditions {
    private Double budget;
    private Double bagVolume;
    private Integer peopleNum;

    public TaskConditions(Double budget, Double bagVolume, Integer peopleNum) {
        //result.setScale(2, RoundingMode.HALF_UP);
        this.budget = budget + 0.010000 ;
        this.bagVolume = bagVolume + 0.01 ;
        this.peopleNum = peopleNum;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getBagVolume() {
        return bagVolume;
    }

    public void setBagVolume(Double bagVolume) {
        this.bagVolume = bagVolume;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Override
    public String toString() {
        return "TaskConditions{" +
                "budget=" + budget +
                ", bagVolume=" + bagVolume +
                ", peopleNum=" + peopleNum +
                '}';
    }
}
