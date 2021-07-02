package com.assignment.utils;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class Printer {
	
	public void printHelp() {
        Options options = CommandLineParser.getOptions();
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Co2Calculator", options, true);
    }
	
	public void print(String message) {
		System.out.println(message);
	}
	
	public void printResult(double result) {
		String co2Value = null;
		if(result < 1000) {
			co2Value =  String.valueOf(result).concat("g");
		} else {
			result /= 1000;
			result = Math.round(result * 10) / 10.0;
			co2Value = String.valueOf(result).concat("kg");
		}
		String outputMessage = String.format("Your trip caused %s of CO2-equivalent.",
				co2Value);
		print(outputMessage);
	}
}
