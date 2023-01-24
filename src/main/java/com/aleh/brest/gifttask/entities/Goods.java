package com.aleh.brest.gifttask.entities;

import java.util.Objects;

public class Goods {
    private long idGood;
    private String goodName;
    private Double presentVolume;
    private Double presentPrice;

    public Goods() {
    }

    public Goods(long idGood, String goodName, Double presentVolume, Double presentPrice) {
        this.idGood = idGood;
        this.goodName = goodName;
        this.presentVolume = presentVolume;
        this.presentPrice = presentPrice;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getPresentVolume() {
        return presentVolume;
    }

    public void setPresentVolume(Double presentVolume) {
        this.presentVolume = presentVolume;
    }

    public Double getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(Double presentPrice) {
        this.presentPrice = presentPrice;
    }

    public long getIdGood() {
        return idGood;
    }

    public void setIdGood(long idGood) {
        this.idGood = idGood;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "idGood=" + idGood +
                ", goodName='" + goodName + '\'' +
                ", presentVolume=" + presentVolume +
                ", presentPrice=" + presentPrice +
                '}'+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return idGood == goods.idGood && Objects.equals(goodName, goods.goodName)
                && Objects.equals(presentVolume, goods.presentVolume)
                && Objects.equals(presentPrice, goods.presentPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGood, goodName, presentVolume, presentPrice);
    }

}
