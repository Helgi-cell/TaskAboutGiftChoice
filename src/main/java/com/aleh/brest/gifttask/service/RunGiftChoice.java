package com.aleh.brest.gifttask.service;

import com.aleh.brest.gifttask.entities.Gifts;
import com.aleh.brest.gifttask.entities.GiftsInBag;
import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.goodsAPI.DataLoadable;
import com.aleh.brest.gifttask.loadingData.LoadData;

import java.util.List;

public class RunGiftChoice {
    public RunGiftChoice() {
    }

    public static void runFit() {

        DataLoadable loadData = new LoadData();
        List<Goods> list = loadData.loadDataGoodsFromInterface().get();
        for (Goods good : list) {
            System.out.println(good.toString());
        }
        System.out.println(loadData.loadTaskConditionsFromInterface().get().toString());



        SolutionDTO solution = new SolutionDTO(loadData.loadDataGoodsFromInterface().get(), loadData.loadTaskConditionsFromInterface().get());
        solution.createGifts();
        List<GiftsInBag> allGifts = solution.resultGifts;
        System.out.println("\n==============================\nResult BAG GIFT BEST let's see bottom : ");
        System.out.println("\n\nThe number of the bags is  : " + allGifts.size());
        System.out.println(allGifts);

        for (GiftsInBag giftInBag: allGifts){
            List<Gifts> gifts = giftInBag.getBagWithGifts();
            Double volume = 0.0;
            Double price = 0.0;

            for (Gifts gift: gifts){
                volume += gift.getVolumeGift();
                price += gift.getPriceGift();
            }
            System.out.println("price gift = " + price +
                    "\nvolume gift = " + volume);
        }
        System.out.println("Change = " + solution.delta + "\n=================================\n\n");
    }
}
