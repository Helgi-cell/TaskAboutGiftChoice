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

        while(this.bagGifts.size() > 0) {
            getBagsWithGifts();
            this.bagGifts = encountGifts();

            System.out.println("\n\n=====================\nGift list possible - > " + resultGifts + "\n\n==================\n\n\n");
            System.out.println("\n Bag with gifts -> \n\n" + this.bagGifts + "\n size = " + this.bagGifts.size() + "\n=================\n\n");

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
                        && (newGift.getVolumeGift() < taskCondition.getBagVolume()/criteria)) {
                    newGifts.add(newGift);
                }
            }
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

            if (Math.abs(giftsInBag.getDeltaToBudget() - delta) < 0.00001) {
                this.resultGifts.add(giftsInBag);
            } else {
                if (giftsInBag.getDeltaToBudget() < delta
                        && giftsInBag.getDeltaToBudget() > 0){
                    this.resultGifts = new ArrayList<>();
                    resultGifts.add(cloneGiftsInBag(giftsInBag));
                    delta = giftsInBag.getDeltaToBudget();
                }
            }
            giftsList = new ArrayList<>();
            incrementMatrix(matrix);
        } while (!isMatrixComplete(matrix));
    }

    public Gifts cloneGift(Gifts gift) {
        return new Gifts(gift.getGift());
    }

    public Goods cloneGood(Goods good) {
        return new Goods(good.getIdGood(), good.getGoodName(), good.getPresentVolume(), good.getPresentPrice());
    }

    public List<Goods> cloneListGoods(List<Goods> goods) {
        List<Goods> clonedGoods = new ArrayList<>();
        for (Goods good : goods) {
            clonedGoods.add(new Goods(good.getIdGood(), good.getGoodName(), good.getPresentVolume(), good.getPresentPrice()));
        }
        return clonedGoods;
    }

    private GiftsInBag cloneGiftsInBag(GiftsInBag giftsInBag){
        return new GiftsInBag(giftsInBag.getBagWithGifts(), taskCondition);
    }

    private List<Gifts> sortListGifts(List<Gifts> unsotedList) {
        for (Gifts gift:unsotedList) {
            List <Goods> goods =gift.getGift();
            gift.setGift(goods.stream()
                    .sorted(Comparator.comparingLong(Goods::getIdGood))
                    .collect(Collectors.toList()));
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

} // end class

