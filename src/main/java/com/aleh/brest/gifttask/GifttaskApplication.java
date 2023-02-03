package com.aleh.brest.gifttask;

import com.aleh.brest.gifttask.service.RunGiftChoice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class GifttaskApplication {

	public static void main(String[] args) throws FileNotFoundException {

		SpringApplication.run(GifttaskApplication.class, args);
		//RunGiftChoice runFitChoice = new RunGiftChoice();
		RunGiftChoice.runFit();
		System.out.println("HURA!!!!!!!!!!!!!!!!!");
	}

}
