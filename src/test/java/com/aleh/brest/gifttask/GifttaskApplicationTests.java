package com.aleh.brest.gifttask;

import com.aleh.brest.gifttask.entities.Goods;
import com.aleh.brest.gifttask.entities.TaskConditions;
import com.aleh.brest.gifttask.goodsAPI.DataLoadable;
import com.aleh.brest.gifttask.loadingData.LoadData;
import com.aleh.brest.gifttask.service.SolutionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.RoundingMode;
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


	}


	@Test
	void isGoods () throws FileNotFoundException {

		Long [] idGoods = { 1L, 2L, 3L//, 4L, 5L
		};

		String [] presentNames = {  "Glasses", "BoomBox", "Wallet"//, "Printer", "Firemaker"
		};

		Double [] presentVolumes = {2.530, 9.110, 4.530//, 6.00, 1.040
		};

		Double [] presentPrices = {   6.00, 45.030, 1.230//, 32.930, 6.990
		};

		Integer [] presentQuantities = {   30, 2, 1//, 32, 6
		};

		DataLoadable loadData = new LoadData();
		List <Goods> goodsList = loadData.loadDataGoods(idGoods, presentNames
														, presentVolumes, presentPrices, presentQuantities) ;


		Assertions.assertTrue(goodsList.size() == 3);
		Assertions.assertTrue(goodsList.get(0).getIdGood() == 1);
		Assertions.assertTrue(goodsList.get(1).getGoodName().equals("BoomBox"));


	}

	@Test
	void isResult () throws FileNotFoundException {

		Long [] idGoods = { 1L, 2L, 3L//, 4L, 5L, 6L
		};

		String [] presentNames = {  "Glasses", "BoomBox", "Laundry"//, "Printer", "Firemaker", "Lego"
		};

		Double [] presentVolumes = {1.530, 3.110, 74.530//, 6.00, 1.040, 5.02
		};

		Double [] presentPrices = { 6.00, 18.00, 55.230//, 32.930, 6.990, 12.01
		};

		Integer [] presentQuantities = {29, 1, 1//, 3, 6, 8
		};

		Double budget = 180.01;
		Double bagVolume = 64.11;
		Integer peopleNum = 6;

		DataLoadable loadData = new LoadData();
		List <Goods> goodsList = loadData.loadDataGoods(idGoods, presentNames
				, presentVolumes, presentPrices, presentQuantities) ;
		TaskConditions taskConditions = new TaskConditions(budget, bagVolume, peopleNum);

		SolutionDTO solution = new SolutionDTO(goodsList, taskConditions);
		solution.createGifts();
		PrintStream outGoods = new PrintStream(new FileOutputStream("testgoodslistout.log"));
		PrintStream outCondition = new PrintStream(new FileOutputStream("testtaskconditionsout.log"));
		PrintStream outResult = new PrintStream(new FileOutputStream("testresultsout.log"));

		System.setOut(outResult);
		System.out.println(taskConditions);

		System.setOut(outResult);
		System.out.println(goodsList);

		System.setOut(outResult);
		System.out.println(solution.resultGifts);
		System.out.println(solution.delta);

		//Assertions.assertTrue(solution.bagGifts.size() == 0);
	}


}
