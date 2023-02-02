package com.aleh.brest.gifttask.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Gifts {

    private List<Goods> gift;
    private Double volumeGift;
    private Double priceGift;

    public Gifts(List<Goods> giftIn) {
        this.gift = sortListGoods(giftIn);
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

        String giftString = "";
        int sztuc = 0;
        Long id = 0L;
        for (Goods good:this.gift) {
            if (id == 0L){
                giftString = giftString + good.getGoodName() + " = ";
                sztuc++;
                id = good.getIdGood();
            } else {
                if (id == good.getIdGood()) {
                    sztuc++;
                } else {
                    giftString = giftString + sztuc + "sz.  ";
                    id = good.getIdGood();
                    sztuc = 1;
                    giftString = giftString + good.getGoodName() + " = ";
                }
            }
            }
        giftString = giftString + sztuc + "sz.";

        return giftString;
    }


    @Override
    public boolean equals(Object gift) {
        Gifts giftNew;
        if (this.hashCode() == gift.hashCode()){
            return true;
        }
          if (gift instanceof Gifts){
                giftNew = (Gifts) gift;
                this.setGift(sortListGoods(this.getGift()));
                giftNew.setGift(sortListGoods(giftNew.getGift()));
                    if (giftNew.getGift().size() == this.getGift().size()){
                           List <Goods> thisGoods = this.getGift();
                           List <Goods> giftGoods = giftNew.getGift();
                           for (int i = 0 ; i < thisGoods.size(); i++){
                               if (!thisGoods.get(i).equals(giftGoods.get(i))){
                                   return false;
                               }
                           }

                    } else {
                        return false;
                    }

          } else {
                    return false;
                 }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gift, volumeGift, priceGift);
    }

    private List<Goods> sortListGoods (List <Goods> unsotedList) {
        return unsotedList.stream()
                .sorted(Comparator.comparingLong(Goods::getIdGood))
                .collect(Collectors.toList());
    }
}
