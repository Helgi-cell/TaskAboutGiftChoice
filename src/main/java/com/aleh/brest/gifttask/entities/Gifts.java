package com.aleh.brest.gifttask.entities;

import java.util.List;

public class Gifts {

    private List<Goods> gift;
    private Double volumeGift;
    private Double priceGift;

    public Gifts(List<Goods> giftIn) {
        this.gift = giftIn;
        this.volumeGift = encountVolumeGift(this.gift);
        this.priceGift = encountPriceGift(this.gift);
    }

    public void insertGood (Goods good){
        this.gift.add(good);
        this.volumeGift = encountVolumeGift(this.gift);
        this.priceGift = encountPriceGift(this.gift);
    }

    private Double encountVolumeGift(List<Goods> giftList){
        Double volume = 0.0;
        for (Goods good:giftList ) {
            volume += good.getPresentVolume();
        }
        return volume;
    }

    private Double encountPriceGift(List<Goods> giftList){
        Double price = 0.0;
        for (Goods good:giftList ) {
            price += good.getPresentPrice();
        }
        return price;
    }


    public List<Goods> getGift() {
        return gift;
    }

    public void setGift(List<Goods> gift) {
        this.gift = gift;
    }

    public Double getVolumeGift() {
        return volumeGift;
    }

    public void setVolumeGift(Double volumeGift) {
        this.volumeGift = volumeGift;
    }

    public Double getPriceGift() {
        return priceGift;
    }

    public void setPriceGift(Double priceGift) {
        this.priceGift = priceGift;
    }

    @Override
    public String toString() {
        return "Gifts{" +
                "gift=" + gift +
                "\n, volumeGift=" + volumeGift +
                "\n, priceGift=" + priceGift +
                "}\n";
    }

}
