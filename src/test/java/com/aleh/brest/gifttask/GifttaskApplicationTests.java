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

		Long [] idGoods = { 1L, 2L, 3L//, 4L, 5L
		};

		String [] presentNames = {  "Glasses", "BoomBox", "Wallet"//, "Printer", "Firemaker"
		};

		Double [] presentVolumes = {2.530, 9.110, 4.530//, 6.00, 1.040
		};

		Double [] presentPrices = {   6.00, 45.030, 1.230//, 32.930, 6.990
		};

		Integer [] presentQuantities = {   30, 5, 1//, 32, 6
		};

		DataLoadable loadData = new LoadData();
		List <Goods> goodsList = loadData.loadDataGoods(idGoods, presentNames
														, presentVolumes, presentPrices, presentQuantities) ;


		Assertions.assertTrue(goodsList.size() == 3);
		Assertions.assertTrue(goodsList.get(0).getIdGood() == 1);
		Assertions.assertTrue(goodsList.get(1).getGoodName().equals("BoomBox"));


	}

	@Test
	void isResult2 () throws FileNotFoundException {
		Long [] idGoods = { 1L, 2L, 3L, 4L};
		String [] presentNames = {  "12xy", "12zy", "12ty", "12xy"};
		Double [] presentVolumes = {1.0, 1.0, 3.0, 2.00};
		Double [] presentPrices = { 1.00, 3.00, 2.0, 4.0};
		Integer [] presentQuantities = {1, 1, 1, 1};

		List<TaskConditions> taskConditions = new ArrayList<>();
		taskConditions.add(new TaskConditions(4.0, 2.0,1));
		taskConditions.add(new TaskConditions(5.0, 2.0,1));
		taskConditions.add(new TaskConditions(4.0, 3.0,1));
		taskConditions.add(new TaskConditions(7.0, 2.0,1));
		taskConditions.add(new TaskConditions(4.0, 4.0,1));
		taskConditions.add(new TaskConditions(3.0, 4.0,1));
		taskConditions.add(new TaskConditions(3.0, 5.0,1));
		taskConditions.add(new TaskConditions(5.0, 3.0,1));
		taskConditions.add(new TaskConditions(5.0, 5.0,1));
		taskConditions.add(new TaskConditions(5.0, 4.0,1));
		taskConditions.add(new TaskConditions(7.0, 3.0,1));
		taskConditions.add(new TaskConditions(7.0, 6.0,1));
		taskConditions.add(new TaskConditions(8.0, 4.0,1));
		taskConditions.add(new TaskConditions(6.0, 5.0,1));
		taskConditions.add(new TaskConditions(7.0, 5.0,1));
		taskConditions.add(new TaskConditions(5.0, 4.0,1));
		taskConditions.add(new TaskConditions(6.0, 4.0,1));

		DataLoadable loadData = new LoadData();
		List <Goods> goodsOfList = loadData.loadDataGoods(idGoods, presentNames
				, presentVolumes, presentPrices, presentQuantities) ;

		PrintStream outResult = new PrintStream(new FileOutputStream("testresult2.log"));
		System.setOut(outResult);

		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			DataLoadable loadData1 = new LoadData();
			List <Goods> goodsOfList1 = loadData.loadDataGoods(idGoods, presentNames
					, presentVolumes, presentPrices, presentQuantities) ;
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

		List<TaskConditions> taskConditions = new ArrayList<>();
		taskConditions.add(new TaskConditions(10.0, 7.0,2));
		taskConditions.add(new TaskConditions(11.0, 7.0,2));
		taskConditions.add(new TaskConditions(10.0, 8.0,2));
		/*taskConditions.add(new TaskConditions(12.01, 7.57,2));
		taskConditions.add(new TaskConditions(11.99, 9.59,2));
		taskConditions.add(new TaskConditions(11.99, 9.58,2));
		taskConditions.add(new TaskConditions(12.01, 9.57,2));
		taskConditions.add(new TaskConditions(12.0, 9.57,2));
		taskConditions.add(new TaskConditions(12.42, 7.57,2));
		taskConditions.add(new TaskConditions(12.41, 7.58,2));
		taskConditions.add(new TaskConditions(12.41, 9.57,2));
		taskConditions.add(new TaskConditions(12.40, 9.57,2));
		taskConditions.add(new TaskConditions(15.42, 7.57,2));
		taskConditions.add(new TaskConditions(16.0, 7.57,2));
		*/
		DataLoadable loadData = new LoadData();
		List <Goods> goodsOfList = loadData.loadDataGoods(idGoods, presentNames
				, presentVolumes, presentPrices, presentQuantities) ;

		PrintStream outResult = new PrintStream(new FileOutputStream("testresult3.log"));
		System.setOut(outResult);

		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			DataLoadable loadData1 = new LoadData();
			List <Goods> goodsOfList1 = loadData.loadDataGoods(idGoods, presentNames
					, presentVolumes, presentPrices, presentQuantities) ;
			SolutionDTO solution = new SolutionDTO(goodsOfList1, conditions);

			solution.createGifts();
			printListGoods(goodsOfList1);
			solution.printResult();
		}
	}


	@Test
	void isResult31 () throws FileNotFoundException {
		Long [] idGoods = { 1L, 2L, 3L, 4L, 5L};
		String [] presentNames = {  "12xy", "12zy", "12ty", "12xy", "12zz"};
		Double [] presentVolumes = {2.0, 1.99, 4.0, 2.00, 1.59};
		Double [] presentPrices = { 4.01, 3.00, 3.99, 4.41, 1.0};
		Integer [] presentQuantities = {1, 1, 1, 1, 1};

		List<TaskConditions> taskConditions = new ArrayList<>();
		/*taskConditions.add(new TaskConditions(10.0, 7.0,2));
		taskConditions.add(new TaskConditions(11.0, 7.0,2));
		taskConditions.add(new TaskConditions(10.0, 8.0,2));*/
		taskConditions.add(new TaskConditions(12.01, 7.57,2));
		taskConditions.add(new TaskConditions(11.99, 9.59,2));
		taskConditions.add(new TaskConditions(11.99, 9.58,2));
		taskConditions.add(new TaskConditions(12.01, 9.57,2));
		taskConditions.add(new TaskConditions(12.0, 9.57,2));
		taskConditions.add(new TaskConditions(12.42, 7.57,2));
		taskConditions.add(new TaskConditions(12.41, 7.58,2));
		taskConditions.add(new TaskConditions(12.41, 9.57,2));
		taskConditions.add(new TaskConditions(12.40, 9.57,2));
		taskConditions.add(new TaskConditions(15.42, 7.57,2));
		taskConditions.add(new TaskConditions(15.42, 9.99,2));
		taskConditions.add(new TaskConditions(16.0, 7.57,2));

		DataLoadable loadData = new LoadData();
		List <Goods> goodsOfList = loadData.loadDataGoods(idGoods, presentNames
				, presentVolumes, presentPrices, presentQuantities) ;

		PrintStream outResult = new PrintStream(new FileOutputStream("testresult31.log"));
		System.setOut(outResult);

		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			DataLoadable loadData1 = new LoadData();
			List <Goods> goodsOfList1 = loadData.loadDataGoods(idGoods, presentNames
					, presentVolumes, presentPrices, presentQuantities) ;
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
