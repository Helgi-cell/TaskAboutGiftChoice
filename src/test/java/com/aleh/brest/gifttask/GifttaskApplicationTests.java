package com.aleh.brest.gifttask;

import com.aleh.brest.gifttask.entities.GiftsInBag;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GifttaskApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void isGoods () throws FileNotFoundException {

		List<Goods> goodsList = new ArrayList<>();
		goodsList.add(new Goods( 1L, "12aa", BigDecimal.valueOf(4.53), BigDecimal.valueOf(12.23), 1));
		goodsList.add(new Goods( 2L, "12bb", BigDecimal.valueOf(9.11), BigDecimal.valueOf(45.03), 1));
		goodsList.add(new Goods( 3L, "12cc", BigDecimal.valueOf(2.53), BigDecimal.valueOf(12.23), 1));
		DataLoadable loadData = new LoadData();
		Assertions.assertTrue(goodsList.size() == 3);
		Assertions.assertTrue(goodsList.get(0).getIdGood() == 1);
		Assertions.assertTrue(goodsList.get(1).getGoodName().equals("12bb"));
	}

	@Test
	void isResult2 () throws FileNotFoundException {
		List <Goods> goodsOfList = new ArrayList<>();
		goodsOfList.add(new Goods( 1L, "12aa", BigDecimal.valueOf(1.0), BigDecimal.valueOf(1.0), 1));
		goodsOfList.add(new Goods( 2L, "12bb", BigDecimal.valueOf(1.0), BigDecimal.valueOf(3.0), 1));
		goodsOfList.add(new Goods( 3L, "12cc", BigDecimal.valueOf(3.0), BigDecimal.valueOf(2.0), 1));
		goodsOfList.add(new Goods( 4L, "12cc", BigDecimal.valueOf(2.0), BigDecimal.valueOf(4.0), 1));

		List<TaskConditions> taskConditions = new ArrayList<>();
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(4.0), BigDecimal.valueOf(2.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(5.0), BigDecimal.valueOf(2.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(4.0), BigDecimal.valueOf(3.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(7.0), BigDecimal.valueOf(2.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(4.0), BigDecimal.valueOf(4.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(3.0), BigDecimal.valueOf(4.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(3.0), BigDecimal.valueOf(5.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(5.0), BigDecimal.valueOf(3.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(5.0), BigDecimal.valueOf(5.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(5.0), BigDecimal.valueOf(4.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(7.0), BigDecimal.valueOf(3.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(7.0), BigDecimal.valueOf(6.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(8.0), BigDecimal.valueOf(4.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(6.0), BigDecimal.valueOf(5.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(7.0), BigDecimal.valueOf(5.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(5.0), BigDecimal.valueOf(4.0),1));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(6.0), BigDecimal.valueOf(4.0),1));

		DataLoadable loadData = new LoadData();
		PrintStream outResult = new PrintStream(new FileOutputStream("testresult2.log"));
		System.setOut(outResult);
		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			printListGoods(goodsOfList);
			List <Goods> goodsOfList1 = new ArrayList<>();
			for (Goods good:goodsOfList) {
				goodsOfList1.add(new Goods(good.getIdGood(), good.getGoodName()
						, good.getPresentVolume(), good.getPresentPrice(), good.getQuantity()));
			}
			SolutionDTO solution = new SolutionDTO(goodsOfList1, conditions);
			solution.createGifts();
			printListGoods(goodsOfList1);
			solution.printResult();
		}
	}


	@Test
	void isResult3 () throws FileNotFoundException {
		Long [] idGoods = { 1L, 2L, 3L, 4L};
		String [] presentNames = {  "12xy", "12zy", "12ty", "12xy"};
		Double [] presentVolumes = {1.0, 1.0, 3.0, 2.00};
		Double [] presentPrices = { 1.00, 3.00, 2.0, 4.0};
		Integer [] presentQuantities = {1, 1, 1, 1};

		List <Goods> goodsOfList = new ArrayList<>();
		goodsOfList.add(new Goods( 1L, "12aa", BigDecimal.valueOf(1.0), BigDecimal.valueOf(1.0), 1));
		goodsOfList.add(new Goods( 2L, "12bb", BigDecimal.valueOf(1.0), BigDecimal.valueOf(3.0), 1));
		goodsOfList.add(new Goods( 3L, "12cc", BigDecimal.valueOf(3.0), BigDecimal.valueOf(2.0), 1));
		goodsOfList.add(new Goods( 4L, "12cc", BigDecimal.valueOf(2.0), BigDecimal.valueOf(4.0), 1));

		List<TaskConditions> taskConditions = new ArrayList<>();
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(10.0), BigDecimal.valueOf(7.0),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(11.0), BigDecimal.valueOf(7.0),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(10.0), BigDecimal.valueOf(8.0),2));

		DataLoadable loadData = new LoadData();
		PrintStream outResult = new PrintStream(new FileOutputStream("testresult3.log"));
		System.setOut(outResult);
		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			printListGoods(goodsOfList);
			List <Goods> goodsOfList1 = new ArrayList<>();
			for (Goods good:goodsOfList) {
				goodsOfList1.add(new Goods(good.getIdGood(), good.getGoodName()
						, good.getPresentVolume(), good.getPresentPrice(), good.getQuantity()));
			}
			SolutionDTO solution = new SolutionDTO(goodsOfList1, conditions);
			solution.createGifts();
			printListGoods(goodsOfList1);
			solution.printResult();
		}
	}

	@Test
	void isResult31 () throws FileNotFoundException {
		List <Goods> goodsOfList = new ArrayList<>();
		goodsOfList.add(new Goods( 1L, "12aa", BigDecimal.valueOf(2.0), BigDecimal.valueOf(4.01), 1));
		goodsOfList.add(new Goods( 2L, "12bb", BigDecimal.valueOf(1.99), BigDecimal.valueOf(3.0), 1));
		goodsOfList.add(new Goods( 3L, "12cc", BigDecimal.valueOf(4.0), BigDecimal.valueOf(3.99), 1));
		goodsOfList.add(new Goods( 4L, "12cc", BigDecimal.valueOf(2.0), BigDecimal.valueOf(4.41), 1));
		goodsOfList.add(new Goods( 5L, "12dd", BigDecimal.valueOf(1.59), BigDecimal.valueOf(1.0), 1));

		List<TaskConditions> taskConditions = new ArrayList<>();
        taskConditions.add(new TaskConditions(BigDecimal.valueOf(10.0), BigDecimal.valueOf(7.0),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(11.0), BigDecimal.valueOf(7.0),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(10.0), BigDecimal.valueOf(8.0),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.01), BigDecimal.valueOf(7.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(11.99), BigDecimal.valueOf(9.59),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(11.99), BigDecimal.valueOf(9.58),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.01), BigDecimal.valueOf(9.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.0), BigDecimal.valueOf(9.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.42), BigDecimal.valueOf(7.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.41), BigDecimal.valueOf(7.58),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.41), BigDecimal.valueOf(9.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(12.40), BigDecimal.valueOf(9.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(15.42), BigDecimal.valueOf(7.57),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(15.42), BigDecimal.valueOf(9.99),2));
		taskConditions.add(new TaskConditions(BigDecimal.valueOf(16.0), BigDecimal.valueOf(7.57),2));

		DataLoadable loadData = new LoadData();
		PrintStream outResult = new PrintStream(new FileOutputStream("testresult31.log"));
		System.setOut(outResult);

		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			printListGoods(goodsOfList);
			List <Goods> goodsOfList1 = new ArrayList<>();
			for (Goods good:goodsOfList) {
				goodsOfList1.add(new Goods(good.getIdGood(), good.getGoodName()
						, good.getPresentVolume(), good.getPresentPrice(), good.getQuantity()));
			}
			SolutionDTO solution = new SolutionDTO(goodsOfList1, conditions);
			solution.createGifts();
			printListGoods(goodsOfList1);
			solution.printResult();
		}
	}

	public void printListGoods (List<Goods> goods){
		System.out.println("id\t\t" + "price\t\t" + "volume");
		for (Goods good:goods){
			StringBuilder typeIt = new StringBuilder();
			typeIt.append(good.getIdGood() + "\t\t")
					.append(String.format("%.2f" ,good.getPresentPrice()))
					.append("\t\t")
					.append(String.format("%.2f" ,good.getPresentVolume()));
			System.out.println( typeIt);
		}
		System.out.println("\n\n");
	}
}
