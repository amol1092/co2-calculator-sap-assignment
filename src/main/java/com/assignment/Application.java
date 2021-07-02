package com.assignment;

import com.assignment.calculator.Co2Calculator;
import com.assignment.utils.Printer;

public class Application {

	public static void main(String[] args) throws Exception {
		Co2Calculator calculator = new Co2Calculator(new Printer());
		calculator.run(args);
	}
	
}
