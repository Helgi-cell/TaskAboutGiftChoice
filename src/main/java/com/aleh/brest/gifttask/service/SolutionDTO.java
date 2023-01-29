package com.aleh.brest.gifttask.service;

import com.aleh.brest.gifttask.entities.Gifts;
import com.aleh.brest.gifttask.entities.GiftsInBag;
import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionDTO {
    public List<Goods> goodsList;
    public TaskConditions taskCondition;
    public List<Gifts> bagGifts;
    public List<GiftsInBag> resultGifts;
    public Integer criteria = 4;
    public Double delta;

    public SolutionDTO(List<Goods> goodsList, TaskConditions taskCondition) {
        this.goodsList = goodsList;
        this.taskCondition = taskCondition;
        this.bagGifts = new ArrayList<>();
        this.resultGifts = new ArrayList<>();
        this.delta = taskCondition.getBudget();
    }


    public void createGifts() {
        initGifts();
        System.out.println("\n\n=====================\nGift list possible - >\n\n " + bagGifts + "\n\n==================\n\n\n");
        while(this.bagGifts.size() > 0) {
            getBagsWithGifts();
            this.bagGifts = encountGifts();

            System.out.println("\n\n=====================\nGift list possible - > \n\n" + bagGifts + "\n\n==================\n\n\n");
          /*  System.out.println("\n\n=====================\nGift list possible - > " + resultGifts + "\n\n==================\n\n\n");
            System.out.println("\n Bag with gifts -> \n\n" + this.bagGifts + "\n size = " + this.bagGifts.size() + "\n=================\n\n");
*/

        }
    }

    private List<Gifts> initGifts() {
        Gifts gift ;
        for (int i = 0; i < goodsList.size(); i++){
            if ((goodsList.get(i).getPresentPrice() > taskCondition.getBudget())
                    || (goodsList.get(i).getPresentVolume() > taskCondition.getBagVolume())){
                goodsList.remove(i);
                i = 0;
            }
        }

        for (int i = 0; i < goodsList.size(); i++) {
            List<Goods> goods = new ArrayList<>();
            goods.add(cloneGood(goodsList.get(i)));
            gift = new Gifts(goods);
            this.bagGifts.add(gift);
        }
        return this.bagGifts;
    }


    private List<Gifts> encountGifts() {
        List<Gifts> newGifts = new ArrayList<>();
        Gifts newGift = null;
        List<Goods> goods = null;
        for (Gifts gift : this.bagGifts) {

            for (int i = 0; i < goodsList.size(); i++) {
                List<Goods> newGoods = new ArrayList<>();
                newGoods = cloneListGoods(gift.getGift());
                newGift = new Gifts(newGoods);
                newGift.insertGood(cloneGood(goodsList.get(i)));
                if((newGift.getPriceGift() < taskCondition.getBudget()/criteria)
                        && (newGift.getVolumeGift() < taskCondition.getBagVolume()/criteria)
                        && (isQuantityGifts(newGift))) {
                    newGifts.add(newGift);
                }
            }
        }
        newGifts = sortListGifts(newGifts);
        return deleteEqualsListGifts(newGifts);
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

                if (Math.abs(giftsInBag.getDeltaToBudget() - delta) < 0.02) {
                    this.resultGifts.add(giftsInBag);
                    resultGifts = deleteSimilarGiftsInBag(resultGifts);
                } else {
                    if (giftsInBag.getDeltaToBudget() < delta
                            && giftsInBag.getDeltaToBudget() > 0) {
                        this.resultGifts = new ArrayList<>();
                        resultGifts.add(cloneGiftsInBag(giftsInBag));
                        delta = giftsInBag.getDeltaToBudget();
                    }
                }
            }
            giftsList = new ArrayList<>();
            incrementMatrix(matrix);
        } while (!isMatrixComplete(matrix));
    }

    private List<GiftsInBag> deleteSimilarGiftsInBag(List<GiftsInBag> result){
        List<GiftsInBag> newResult = new ArrayList<>();
        for (GiftsInBag inBag : result) {
            newResult.add(inBag);
        }
        if (result.size() > 1) {
            for (int i = 0; i < newResult.size() - 1; i++) {
                for (int j = newResult.size() - 1; j > 0 ; j--) {
                    if (newResult.get(i).equals(newResult.get(j))){
                        newResult.remove(j);
                        newResult = deleteSimilarGiftsInBag(newResult);
                    }
                }
            }
        }


        return newResult;
    }


    private Boolean isQuantityGifts(Gifts gift){
         gift.setGift(sortListGoods(gift.getGift()));
         List <Goods> goods = gift.getGift();
         Long id = 0L;
         Integer num = 0;
        for (Goods good:goods) {
           if (id != good.getIdGood()){
               if (num != 0){
                   if (num > good.getQuantity()){
                       return false;
                   }
               }
               id = good.getIdGood();
               num = 1;
           } else {
               num++;
               if (num > good.getQuantity()){
                   return false;
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


    public Gifts cloneGift(Gifts gift) {
        return new Gifts(gift.getGift());
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
        return new GiftsInBag(giftsInBag.getBagWithGifts(), taskCondition);
    }


    private List<Goods> sortListGoods (List <Goods> unsotedList) {
          return unsotedList.stream()
                    .sorted(Comparator.comparingLong(Goods::getIdGood))
                    .collect(Collectors.toList());
    }

    private List<Gifts> sortListGifts(List<Gifts> unsotedList) {
        for (Gifts gift:unsotedList) {
            List <Goods> goods =gift.getGift();
            gift.setGift(sortListGoods(goods));
        }
        return unsotedList;
    }

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

    private List<Gifts> deleteEqualsListGifts(List <Gifts> giftsList){
        if (giftsList.size() < 2){
            return giftsList;
        }
        for (int i = 0 ; i < giftsList.size() - 2; i++) {
            for (int j = i + 1; j < giftsList.size() - 1; j++) {
                if (giftsList.get(i).equals(giftsList.get(j))){
                    giftsList.remove(j);
                    return deleteEqualsListGifts(giftsList);
                }
            }
        }

        return giftsList;
    }

} // end class

