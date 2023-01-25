package com.aleh.brest.gifttask.entities;

import java.math.BigDecimal;
import java.util.List;

public class GiftsInBag {

    private List<Gifts> bagWithGifts;

    private Double deltaToBudget;

    private TaskConditions taskConditions;
    public GiftsInBag(List<Gifts> bagWithGifts, TaskConditions taskConditions) {
        this.taskConditions = taskConditions;
        this.bagWithGifts = bagWithGifts;
        this.deltaToBudget = encountDeltaBudget();
    }


    private Boolean encountVolumeBag(List<Gifts> bagGifts) {
        Double volumeBag = 0.0;
        for (Gifts gift : bagGifts) {
            volumeBag += gift.getVolumeGift();
        }
        if (volumeBag <= taskConditions.getBagVolume()){
            return true;
        } else {
            return false;
        }
    }

    private Boolean encountPriceBag(List<Gifts> bagGifts) {
        Double priceBag = 0.0;
        for (Gifts gift : bagGifts) {
            priceBag += gift.getPriceGift();
        }
        if (priceBag <= taskConditions.getBudget()) {
            return true;
        } else {
            return false;
        }
    }

    private Double encountDeltaBudget() {
        if (encountPriceBag(this.bagWithGifts) && encountVolumeBag(this.bagWithGifts)){
            Double delta = 0.0;
            for (Gifts gift:this.bagWithGifts ) {
                delta += gift.getPriceGift();
            }
            return taskConditions.getBudget() - delta;
        } else {
            return taskConditions.getBudget() + 1.0;
        }
    }



    public List<Gifts> getBagWithGifts() {
        return bagWithGifts;
    }

    public void setBagWithGifts(List<Gifts> bagWithGifts) {
        this.bagWithGifts = bagWithGifts;
    }


    public Double getDeltaToBudget() {
        return deltaToBudget;
    }

    public void setDeltaToBudget(Double deltaToBudget) {
        this.deltaToBudget = deltaToBudget;
    }

    @Override
    public String toString() {
        return "GiftsInBag{" +
                "bagWithGifts=" + bagWithGifts +
                ", deltaToBudget=" + deltaToBudget +
                '}';
    }
}
