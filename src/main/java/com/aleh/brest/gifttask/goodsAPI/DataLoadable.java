package com.aleh.brest.gifttask.goodsAPI;

import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DataLoadable {

    Optional<List<Goods>> loadDataGoodsFromInterface();

    Optional  <TaskConditions> loadTaskConditionsFromInterface();


    TaskConditions loadTaskConditions(BigDecimal budgetAll
            , BigDecimal bagVolumeAll, Integer peopleNumAll);


    boolean insertNewGood (List<Goods> goods,Goods good);
}
