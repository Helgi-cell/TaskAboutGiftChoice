package com.aleh.brest.gifttask.goodsAPI;

import com.aleh.brest.gifttask.entities.TaskConditions;

import java.math.BigDecimal;

public interface TaskConditionsInterface {

    final TaskConditions conditionsI = new TaskConditions(BigDecimal.valueOf(183.23)
                                                           , BigDecimal.valueOf(64.11)
                                                           , 7);
}

