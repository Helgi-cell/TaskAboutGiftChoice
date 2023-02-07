package com.aleh.brest.gifttask.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaskConditions {
    private BigDecimal budget;
    private BigDecimal bagVolume;
    private Integer peopleNum;

    public TaskConditions(BigDecimal budget, BigDecimal bagVolume, Integer peopleNum) {
        //result.setScale(2, RoundingMode.HALF_UP);
        this.budget = budget;
        this.bagVolume = bagVolume;
        this.peopleNum = peopleNum;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getBagVolume() {
        return bagVolume;
    }

    public void setBagVolume(BigDecimal bagVolume) {
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
