package com.aleh.brest.gifttask.goodsAPI;

import com.aleh.brest.gifttask.entities.Goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface GoodsInterface {

    final List<Goods> goodsI = fillGoods();


    static List<Goods> fillGoods(){
        List<Goods> goods = new ArrayList<>();
        goods.add(new Goods( 1L, "12aa", BigDecimal.valueOf(4.53), BigDecimal.valueOf(12.23), 10));
        goods.add(new Goods( 2L, "12bb", BigDecimal.valueOf(9.11), BigDecimal.valueOf(45.03), 15));
        goods.add(new Goods( 3L, "12cc", BigDecimal.valueOf(2.53), BigDecimal.valueOf(12.23), 4));
      /*  goods.add(new Goods( 4L, "12dd", BigDecimal.valueOf(6.00), BigDecimal.valueOf(32.93), 1));
        goods.add(new Goods( 6L, "12ff", BigDecimal.valueOf(1.04), BigDecimal.valueOf(6.99), 1));
        goods.add(new Goods( 7L, "12gg", BigDecimal.valueOf(0.87), BigDecimal.valueOf(5.46), 1));
        goods.add(new Goods( 8L, "12hh", BigDecimal.valueOf(2.57), BigDecimal.valueOf(7.34), 1));
        goods.add(new Goods( 9L, "12ii", BigDecimal.valueOf(19.45), BigDecimal.valueOf(65.98), 1));
        goods.add(new Goods(10L, "12jj", BigDecimal.valueOf(65.59), BigDecimal.valueOf(52.13), 1));
        goods.add(new Goods(11L, "12kk", BigDecimal.valueOf(14.14), BigDecimal.valueOf(7.23), 1));
        goods.add(new Goods(11L, "12ll", BigDecimal.valueOf(16.66), BigDecimal.valueOf(10.00), 1));
        goods.add(new Goods(11L, "12mm", BigDecimal.valueOf(13.53), BigDecimal.valueOf(25.25), 1));*/
    return goods;
    }
}

