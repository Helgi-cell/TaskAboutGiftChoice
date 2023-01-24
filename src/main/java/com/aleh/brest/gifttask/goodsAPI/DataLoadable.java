package com.aleh.brest.gifttask.goodsAPI;

import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;

import java.util.List;
import java.util.Optional;

public interface DataLoadable {

    Optional<List<Goods>> loadDataGoodsFromInterface();

    Optional  <TaskConditions> loadTaskConditionsFromInterface();

    List<Goods> loadDataGoods(Long [] id, String [] names
            , Double [] volumes, Double [] prices);

    TaskConditions loadTaskConditions(Double budgetAll
            , Double bagVolumeAll, Integer peopleNumAll);
    boolean insertNewGood (List<Goods> goods,Goods good);
}
