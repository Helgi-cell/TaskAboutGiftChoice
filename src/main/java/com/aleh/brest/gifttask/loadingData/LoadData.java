package com.aleh.brest.gifttask.loadingData;

import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;
import com.aleh.brest.gifttask.goodsAPI.DataLoadable;
import com.aleh.brest.gifttask.goodsAPI.GoodsInterface;
import com.aleh.brest.gifttask.goodsAPI.TaskConditionsInterface;
import com.sun.jdi.ObjectCollectedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class LoadData implements DataLoadable, GoodsInterface, TaskConditionsInterface {
    @Override
    public Optional<List<Goods>> loadDataGoodsFromInterface() throws ObjectCollectedException {
        List<Goods> storeGoods = GoodsInterface.goodsI;
        Optional<List<Goods>> storeGoodsOptional = Optional.of(storeGoods);
        return (Optional<List<Goods>>) storeGoodsOptional;
    }

    @Override
    public Optional<TaskConditions> loadTaskConditionsFromInterface() {
        TaskConditions taskConditions = TaskConditionsInterface.conditionsI;
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
    public TaskConditions loadTaskConditions(BigDecimal budgetAll
                                                 , BigDecimal bagVolumeAll, Integer peopleNumAll) {
        TaskConditions taskConditions = new TaskConditions(budgetAll, bagVolumeAll, peopleNumAll);
        return taskConditions;
    }
}
