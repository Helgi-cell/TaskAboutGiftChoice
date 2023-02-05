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
	void isResult () throws FileNotFoundException {



		Long [] idGoods = { 1L, 2L, 3L, 4L//, 5L, 6L
		};

		String [] presentNames = {  "12xy", "12zy", "12ty", "12xy"//, "Firemaker", "Lego"
		};

		Double [] presentVolumes = {1.0, 1.0, 3.0, 2.00//, 1.040, 5.02
		};

		Double [] presentPrices = { 1.00, 3.00, 2.0, 4.0//, 6.990, 12.01
		};

		Integer [] presentQuantities = {1, 1, 1, 1//, 5, 8
		};

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



		PrintStream outResult = new PrintStream(new FileOutputStream("testresultsout.log"));
		System.setOut(outResult);



		printListGoods(goodsOfList);
		for (TaskConditions conditions: taskConditions) {
			SolutionDTO solution = new SolutionDTO(goodsOfList, conditions);

			solution.createGifts();

			solution.printResult();

		}

/*

		System.out.println("TASK CONDITIONS =>\n" + taskConditions + "\n\n");

		System.out.println("ATTENTION!!!!\n" +
				" MAX PRICE OF THE ONE GIFT =>\n" + (solution.taskCondition.getBudget()/solution.criteria) + "\n");
		System.out.println("LIST have been ANALIZED =>\n" + goodsList + "\n\n");

		if (solution.resultGifts.size() == 0){
			System.out.println("RESULT = >\n NO RESULTS" + "\n\n");
		} else {
			for (GiftsInBag giftInBag : solution.resultGifts) {
				System.out.println("RESULT = >\n" + giftInBag + "\n\n");
			}
		}

		System.out.println("CHANGE =>" + solution.delta + "\n\n");
*/

		//Assertions.assertTrue(solution.bagGifts.size() == 0);
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
