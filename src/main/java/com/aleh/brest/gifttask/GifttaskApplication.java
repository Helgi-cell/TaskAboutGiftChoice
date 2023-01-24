package com.aleh.brest.gifttask;

import com.aleh.brest.gifttask.service.RunGiftChoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GifttaskApplication {

	public static void main(String[] args) {

		SpringApplication.run(GifttaskApplication.class, args);
		//RunGiftChoice runFitChoice = new RunGiftChoice();
		RunGiftChoice.runFit();
		System.out.println("HURA!!!!!!!!!!!!!!!!!");
	}

}
