package com.aleh.brest.gifttask.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GiftsInBag {

    private List<Gifts> bagWithGifts;

    private BigDecimal deltaToBudget;

    private TaskConditions taskConditions;
    public GiftsInBag(List<Gifts> bagWithGifts, TaskConditions taskConditions) {
        this.taskConditions = taskConditions;
        this.bagWithGifts = bagWithGifts;
        this.deltaToBudget = encountDeltaBudget();
    }


    public BigDecimal howVolumeBag(List<Gifts> bagGifts){
        BigDecimal volumeBag = BigDecimal.valueOf(0);
        for (Gifts gift : bagGifts) {
            volumeBag = volumeBag.add(gift.getVolumeGift());
        }
        return volumeBag;
    }

    public BigDecimal howPriceBag(List<Gifts> bagGifts){
        BigDecimal priceBag = BigDecimal.valueOf(0);
        for (Gifts gift : bagGifts) {
            priceBag = priceBag.add(gift.getPriceGift());
        }
        return priceBag;
    }

    private Boolean isEncountVolumeBag(List<Gifts> bagGifts) {
        BigDecimal volumeBag = BigDecimal.valueOf(0);
        for (Gifts gift : bagGifts) {
            volumeBag = volumeBag.add(gift.getVolumeGift());
        }
        if (taskConditions.getBagVolume().compareTo(volumeBag) >= 0){
            return true;
        } else {
            return false;
        }
    }

    private Boolean isEncountPriceBag(List<Gifts> bagGifts) {
       BigDecimal priceBag = BigDecimal.valueOf(0);
        for (Gifts gift : bagGifts) {
            priceBag = priceBag.add(gift.getPriceGift());
        }
        if (taskConditions.getBudget().compareTo(priceBag) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private BigDecimal encountDeltaBudget() {
        BigDecimal delta = BigDecimal.valueOf(0);
        if (isEncountPriceBag(this.bagWithGifts) && isEncountVolumeBag(this.bagWithGifts)){

            for (Gifts gift:this.bagWithGifts ) {
                delta = delta.add(gift.getPriceGift());
            }
            if (delta.compareTo(taskConditions.getBudget()) == 1){
                return taskConditions.getBudget().add(BigDecimal.valueOf(100));
            } else{
                return taskConditions.getBudget().subtract(delta);
            }
        } else {

            return taskConditions.getBudget().add(BigDecimal.valueOf(100));
        }

    }



    public List<Gifts> getBagWithGifts() {
        return bagWithGifts;
    }

    public void setBagWithGifts(List<Gifts> bagWithGifts) {
        this.bagWithGifts = bagWithGifts;
    }


    public BigDecimal getDeltaToBudget() {
        return deltaToBudget;
    }

    public void setDeltaToBudget(BigDecimal deltaToBudget) {
        this.deltaToBudget = deltaToBudget;
    }

    @Override
    public String toString() {
        String bagString = "GiftsInBag =>\n";
        for (Gifts gift:this.bagWithGifts) {
            bagString += gift.toString() + "\n";
        }
        bagString = bagString + "\n Price of Bag = " + howPriceBag(bagWithGifts)
                                + "\n Volume of Bag = " + howVolumeBag(bagWithGifts)
                                + "\nCHANGE = " + deltaToBudget + "\n======================\n";
        return bagString;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof GiftsInBag)) return false;


        if (this.getBagWithGifts().size() > 0 && ((GiftsInBag) o).getBagWithGifts().size() > 0){
            GiftsInBag externalGiftInBag = cloneGiftsInBag((GiftsInBag) o);
            GiftsInBag internalGiftInBag = cloneGiftsInBag(this);

            for (Gifts internalGift:internalGiftInBag.getBagWithGifts()) {
                for (Gifts externalGift:externalGiftInBag.getBagWithGifts()){
                      if (internalGift.equals(externalGift)){
                          internalGiftInBag.getBagWithGifts().remove(internalGift);
                          externalGiftInBag.getBagWithGifts().remove(externalGift);
                          if (externalGiftInBag.getBagWithGifts().size() == 0  && internalGiftInBag.getBagWithGifts().size() == 0){
                              return true;
                          } else {
                              return internalGiftInBag.equals(externalGiftInBag);
                          }
                      }
                }
            }
        }

       return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bagWithGifts, deltaToBudget, taskConditions);
    }

    private GiftsInBag cloneGiftsInBag(GiftsInBag giftsInBag){
        List<Gifts> newListGifts = new ArrayList<>();
        for (Gifts gift:giftsInBag.getBagWithGifts()) {
            newListGifts.add(gift);
        }
        return new GiftsInBag(newListGifts, this.taskConditions);
    }
}
