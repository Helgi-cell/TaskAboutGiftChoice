package com.aleh.brest.gifttask.loadingData;

import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;
import com.aleh.brest.gifttask.goodsAPI.DataLoadable;
import com.aleh.brest.gifttask.goodsAPI.GoodsInterface;
import com.aleh.brest.gifttask.goodsAPI.TaskConditionsInterface;
import com.sun.jdi.ObjectCollectedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoadData implements DataLoadable, GoodsInterface, TaskConditionsInterface {

    @Override
    public Optional<List<Goods>> loadDataGoodsFromInterface() throws ObjectCollectedException {
        List<Goods> storeGoods = new ArrayList<>();
        Optional<List<Goods>> storeGoodsOptional = Optional.of(storeGoods);
        try {
            for (int i = 0; i < presentNames.length; i++) {
                storeGoods.add(new Goods(idGoods[i],presentNames[i], presentVolumes[i], presentPrices[i]));
            }
        } catch (ObjectCollectedException e) {
            return storeGoodsOptional;
        }
        return (Optional<List<Goods>>) storeGoodsOptional;
    }

    @Override
    public Optional<TaskConditions> loadTaskConditionsFromInterface() {
        TaskConditions taskConditions = new TaskConditions(budget, bagVolume, peopleNum);
        Optional<TaskConditions> taskConditionsOptional = Optional.of(taskConditions);
        return taskConditionsOptional;
    }

    @Override
    public boolean insertNewGood(List<Goods> goods, Goods good) throws ObjectCollectedException {
        try {
            goods.add(good);
        } catch (ObjectCollectedException e) {
            return false;
        }
        return true;
    }

   @Override
    public List<Goods> loadDataGoods(Long [] id, String [] names
                                   , Double [] volumes, Double [] prices) {
        List<Goods> storeGoods = new ArrayList<>();
        try {
            for (int i = 0; i < presentNames.length; i++) {
                storeGoods.add(new Goods(id[i],names[i], volumes [i], prices[i]));
            }
        } catch (ObjectCollectedException e) {
            return storeGoods;
        }
        return storeGoods;
    }

    @Override
    public TaskConditions loadTaskConditions(Double budgetAll
                                                 , Double bagVolumeAll, Integer peopleNumAll) {
        TaskConditions taskConditions = new TaskConditions(budgetAll, bagVolumeAll, peopleNumAll);
        return taskConditions;
    }


}
