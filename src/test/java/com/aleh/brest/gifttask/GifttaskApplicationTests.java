package com.aleh.brest.gifttask;

import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;
import com.aleh.brest.gifttask.goodsAPI.DataLoadable;
import com.aleh.brest.gifttask.loadingData.LoadData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

@SpringBootTest
class GifttaskApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void isTaskConditions () throws FileNotFoundException {

		Double budget = 180.01;
		Double bagVolume = 64.11;
		Integer peopleNum = 6;


		TaskConditions taskConditions = new TaskConditions(budget, bagVolume, peopleNum);
		Assertions.assertTrue(taskConditions.getBudget() == 180.01);
		Assertions.assertTrue(taskConditions.getBagVolume() == 64.11);
		Assertions.assertTrue(taskConditions.getPeopleNum() == 6);

		PrintStream out = new PrintStream(new FileOutputStream("testtaskconditionsout.log"));
		System.setOut(out);
		System.out.println(taskConditions);
	}


	@Test
	void isGoods () throws FileNotFoundException {

		Long [] idGoods = { 1L, 2L, 3L//, 4L, 5L
		};

		String [] presentNames = {  "Glasses", "BoomBox", "Wallet"//, "Printer", "Firemaker"
		};

		Double [] presentVolumes = {1.530, 9.110, 4.530//, 6.00, 1.040
		};

		Double [] presentPrices = {   6.00, 45.030, 1.230//, 32.930, 6.990
		};

		DataLoadable loadData = new LoadData();
		List <Goods> goodsList = loadData.loadDataGoods(idGoods, presentNames
														, presentVolumes, presentPrices) ;


		Assertions.assertTrue(goodsList.size() == 3);
		Assertions.assertTrue(goodsList.get(0).getIdGood() == 1);
		Assertions.assertTrue(goodsList.get(1).getGoodName().equals("BoomBox"));

		PrintStream out = new PrintStream(new FileOutputStream("testgoodslistout.log"));
		System.setOut(out);
		System.out.println(goodsList);
	}


}
