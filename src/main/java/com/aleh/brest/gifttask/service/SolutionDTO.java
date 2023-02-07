package com.aleh.brest.gifttask.service;

import com.aleh.brest.gifttask.entities.Gifts;
import com.aleh.brest.gifttask.entities.GiftsInBag;
import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionDTO {
    public List<Goods> goodsList;
    public TaskConditions taskCondition;
    public List<Gifts> bagGifts;
    public List<GiftsInBag> resultGifts;
    public Integer criteria = 1;
    public BigDecimal delta;

    public SolutionDTO(List<Goods> goodsList, TaskConditions taskCondition) {
        this.goodsList = goodsList;
        this.taskCondition = taskCondition;
        this.bagGifts = new ArrayList<>();
        this.resultGifts = new ArrayList<>();
        this.delta = taskCondition.getBudget();
    }


    public void createGifts() {
        initGifts();
        while(this.bagGifts.size() != 0) {
            getBagsWithGifts();
            this.bagGifts = encountGifts(this.bagGifts);
        }
    }

    private List<Gifts> initGifts() {
        Gifts gift ;
        for (int i = 0; i < this.goodsList.size(); i++){
            if ((this.goodsList.get(i).getPresentPrice().compareTo(this.taskCondition.getBudget()) == 1)
                    || (this.goodsList.get(i).getPresentVolume().compareTo(this.taskCondition.getBagVolume())) == 1){
                goodsList.remove(i);
                i = 0;
            }
        }

        for (int i = 0; i < this.goodsList.size(); i++) {
            List<Goods> goods = new ArrayList<>();
            goods.add(cloneGood(this.goodsList.get(i)));
            gift = new Gifts(goods);
            this.bagGifts.add(gift);
        }
        return this.bagGifts;
    }


    private List<Gifts> encountGifts(List<Gifts> gifts) {
        List<Gifts> newGifts = new ArrayList<>();
        Gifts newGift ;
        List<Goods> newGoods;
        for (Gifts gift : gifts) {
            for (int i = 0; i < goodsList.size(); i++) {
                newGoods = cloneListGoods(gift.getGift());
                newGoods.add(cloneGood(goodsList.get(i)));
                newGift = new Gifts(newGoods);
                if((taskCondition.getBudget().compareTo(newGift.getPriceGift()) >= 0)
                        && (taskCondition.getBagVolume().compareTo(newGift.getVolumeGift()) >= 0)
                        && (isQuantityGifts(newGift))) {
                    newGifts.add(newGift);
                }
            }
        }
        if (newGifts.size() > 1){
        newGifts = deleteEqualsListGifts(newGifts);
        }
        return newGifts;
    }


    private void getBagsWithGifts(){
        Boolean[][] matrix = initMatrix(taskCondition.getPeopleNum(), this.bagGifts);
        GiftsInBag giftsInBag ;
        List<Gifts> giftsList = new ArrayList<>();
        do {
            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix[i].length; j++){
                    if (matrix[i][j]) {

                        giftsList.add(cloneGift(this.bagGifts.get(j)));
                    }
                }
            }
            giftsInBag = new GiftsInBag(giftsList, taskCondition);


            if (isQuantityGiftsInBag(giftsInBag)) {

                if (giftsInBag.getDeltaToBudget().compareTo(this.delta) == 0) {
                    this.resultGifts.add(giftsInBag);
                    this.resultGifts = deleteSimilarGiftsInBag(resultGifts);
                } else {
                    if (this.delta.compareTo(giftsInBag.getDeltaToBudget()) == 1) {
                        this.resultGifts = new ArrayList<>();
                        this.resultGifts.add(cloneGiftsInBag(giftsInBag));
                        delta = giftsInBag.getDeltaToBudget();
                    }
                }
            }
            giftsList = new ArrayList<>();
            incrementMatrix(matrix);
        } while (!isMatrixComplete(matrix));
    }

    private List<GiftsInBag> deleteSimilarGiftsInBag(List<GiftsInBag> result){
        if (result.size() < 2) {
            return result;
        } else {
            for (int i = 0; i <= result.size() - 2; i++) {
                for (int j = result.size() - 1; j > i; j--) {
                    if (result.get(i).equals(result.get(j))) {
                        result.remove(j);
                        if (result.size() > 1) {
                            return deleteSimilarGiftsInBag(result);
                        } else {
                            return result;
                        }
                        }
                    }
                }
            }

        return result;
    }


    private List<Gifts> deleteEqualsListGifts(List <Gifts> giftsList){
        if (giftsList.size() < 2){
            return giftsList;
        } else {
            for (int i = 0; i <= giftsList.size() - 2; i++) {
                for (int j = giftsList.size() - 1; j > i; j--) {
                    if (giftsList.get(i).equals(giftsList.get(j))) {
                        giftsList.remove(j);
                        if (giftsList.size() > 1) {
                            return deleteEqualsListGifts(giftsList);
                        } else return giftsList;
                    }
                }
            }
        }
        return giftsList;
    }

    private Boolean isQuantityGifts(Gifts gift){
       if (gift.getGift().size() == 0){
           return false;
       } else {
           Long id = 0L;
           int i = 0;
           for (Goods good : gift.getGift()) {
               if (id == 0L) {
                   id = good.getIdGood();
                   i++;
               } else {
                   if (id == good.getIdGood()) {
                       i++;
                       if (i > good.getQuantity()) {
                           return false;
                       }
                   } else {
                       if (id != good.getIdGood()) {
                           id = good.getIdGood();
                           i = 1;
                       }
                   }
               }
           }
       }
         return true;
    }

    private Boolean isQuantityGiftsInBag(GiftsInBag giftsInBag){
        int num = 0;
        for (Goods goodStore:goodsList) {
            for (Gifts gift:giftsInBag.getBagWithGifts()) {
                for (Goods good:gift.getGift()) {
                    if (good.getIdGood() == goodStore.getIdGood()){
                        num++;
                        if (num > goodStore.getQuantity()){
                            return false;
                        }
                    }
                }
            }
            num = 0;
        }
        return true;
    }


    private List<Goods> sortListGoods (List <Goods> unsotedList) {
          return unsotedList.stream()
                    .sorted(Comparator.comparingLong(Goods::getIdGood))
                    .collect(Collectors.toList());
    }
/*

    private List<Gifts> sortListGifts(List<Gifts> unsotedList) {
        for (Gifts gift:unsotedList) {
            List <Goods> goods =gift.getGift();
            gift.setGift(sortListGoods(goods));
        }
        return unsotedList;
    }
*/
/*

    private Boolean isListGoodsEqual(List<Goods> first, List<Goods> second) {
        Boolean isTrue = true;
        if (first.size() == second.size() && first.size() != 0) {
            for (int i = 0; i < first.size(); i++) {
                if (isTrue) {
                    isTrue = first.get(i).equals(second.get(i));
                }
            }
        } else {
            return false;
        }
        return isTrue;
    }
*/

    private Boolean[][] initMatrix(Integer numPersons, List<Gifts> listGifts) {
        Boolean[][] matrix = new Boolean[numPersons][listGifts.size()];
        for (int i = 0; i < numPersons; i++) {
            for (int j = 0; j < listGifts.size(); j++) {
                if (j == 0) {
                    matrix[i][j] = true;
                } else {
                    matrix[i][j] = false;
                }
            }
        }
        return matrix;
    }

    private Boolean isMatrixComplete(Boolean[][] matrix) {
        if (matrix[0].length == 1){return true;}
        int isComplete = 0;
        for (int i = 0; i < matrix.length; i++) {
            // if (matrix[i][matrix[i].length - 1] == true) {
            if (matrix[i][0] == true) {
                isComplete++;
            }
        }
        if (isComplete == matrix.length) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean[][] incrementMatrix(Boolean[][] matrix) {
        if (matrix[0].length == 1){return matrix;}
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == true) {
                    if (j < matrix[i].length - 1) {
                        matrix[i][j] = false;
                        matrix[i][j + 1] = true;
                        return matrix;
                    } else {
                        if (j == matrix[i].length - 1) {
                            matrix[i][j] = false;
                            matrix[i][0] = true;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    public Gifts cloneGift(Gifts gift) {
        List<Goods> goods = cloneListGoods(gift.getGift());
        return new Gifts(goods);
    }

    public List<Gifts> cloneListGift(List<Gifts> gifts) {
        List<Gifts> newGifts = new ArrayList<>();
        for(Gifts gift:gifts){
            newGifts.add(cloneGift(gift));
        }
        return newGifts;
    }

    public Goods cloneGood(Goods good) {
        return new Goods(good.getIdGood(), good.getGoodName(), good.getPresentVolume(), good.getPresentPrice(), good.getQuantity());
    }

    public List<Goods> cloneListGoods(List<Goods> goods) {
        List<Goods> clonedGoods = new ArrayList<>();
        for (Goods good : goods) {
            clonedGoods.add(new Goods(good.getIdGood(), good.getGoodName(), good.getPresentVolume(), good.getPresentPrice(), good.getQuantity()));
        }
        return clonedGoods;
    }

    private GiftsInBag cloneGiftsInBag(GiftsInBag giftsInBag){
        List<Gifts> gifts = cloneListGift(giftsInBag.getBagWithGifts());
        return new GiftsInBag(gifts, taskCondition);
    }

    public void printResult(){
        System.out.println("For budget = " + String.format("%.2f",taskCondition.getBudget())
                          + "  volume = " + String.format("%.2f",taskCondition.getBagVolume())
                          + " number of people = " + taskCondition.getPeopleNum()
                          + "  RESULT ITEMS = " + resultGifts.size());
        String bar = "";
        for (int i = 0; i < taskCondition.getPeopleNum(); i++){
            bar = bar + "id\t\t\t" + "price\t\t\t" + "volume\t\t\t";
        }
        System.out.println(bar + " items price\t" + " items volume\t\t\t");
        StringBuilder stringResult = new StringBuilder();

        for (GiftsInBag gifts:this.resultGifts){
            for (Gifts gift:gifts.getBagWithGifts()){
                for (Goods good:gift.getGift()){
                    stringResult = stringResult.append(good.getIdGood()).append(',');
                }
                stringResult.deleteCharAt(stringResult.length()-1)
                            .append("\t\t\t")
                            .append(gift.getPriceGift()).append("\t\t\t")
                            .append(gift.getVolumeGift()).append("\t\t\t");

            }
            stringResult.append(gifts.howPriceBag(gifts.getBagWithGifts())).append("\t\t\t")
                        .append(gifts.howVolumeBag(gifts.getBagWithGifts())).append("\t\t\t")
                    .append("\nbudget = ").append(String.format("%.2f",taskCondition.getBudget())).append(";  ")
                    .append("bagVolume = ").append(String.format("%.2f",taskCondition.getBagVolume()));
            System.out.println(stringResult );
            stringResult = new StringBuilder();

        }
        System.out.println("\n\n");
    }




} // end class

