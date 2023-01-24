package com.aleh.brest.gifttask.entities;

public class TaskConditions {
    private Double budget;
    private Double bagVolume;
    private Integer peopleNum;

    public TaskConditions(Double budget, Double bagVolume, Integer peopleNum) {
        this.budget = budget;
        this.bagVolume = bagVolume;
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
