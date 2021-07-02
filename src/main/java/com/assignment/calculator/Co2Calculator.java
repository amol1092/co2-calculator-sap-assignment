package com.assignment.calculator;

import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.lang3.StringUtils;

import com.assignment.exception.Co2CalculatorException;
import com.assignment.exception.CommandLineParserException;
import com.assignment.utils.CommandLineParser;
import com.assignment.utils.Printer;

public class Co2Calculator {

	private Co2DataContainer dataContainer;
	
	private Printer printer;
	
	public Co2Calculator(Printer printer) throws Co2CalculatorException {
		this.dataContainer = Co2DataContainer.getInstance();
		this.printer = printer;
	}
	
	public void run(String ...args) {
		try {
			CommandLine arguments = CommandLineParser.parseArguments(args);
			if(!arguments.hasOption(CommandLineParser.TRANSPORTATION_METHOD) ||
					!arguments.hasOption(CommandLineParser.DISTANCE)) {
				printer.printHelp();
				return;
			}
			validateInputValues(arguments);
			String transportaionMethod = arguments.getOptionValue(CommandLineParser.TRANSPORTATION_METHOD);
			double distance = Double.valueOf(arguments.getOptionValue(CommandLineParser.DISTANCE));
			String unit = (arguments.hasOption(CommandLineParser.UNIT)) ? arguments.getOptionValue(CommandLineParser.UNIT) : "km";
			
			calculateAndPrintResult(transportaionMethod, distance, unit);
		} catch(CommandLineParserException | RuntimeException cpe) {
			String message = "Invalid arguments - ".concat(cpe.getMessage());
			printer.print(message);
			printer.printHelp();
			return;
		}
	}
	
	private void validateInputValues(CommandLine arguments) {
		if(StringUtils.isBlank(arguments.getOptionValue(CommandLineParser.TRANSPORTATION_METHOD)) 
				|| StringUtils.isBlank(arguments.getOptionValue(CommandLineParser.DISTANCE))) {
			printer.print("Missing input values");
			return;
		}
	}
	
	private void calculateAndPrintResult(String transportationMethod, double distance,
			String unit) {
		Map<String, Integer> co2Data = dataContainer.getCo2Data();
		if(!co2Data.containsKey(transportationMethod)) {
			printer.print("Sorry, the co2e data is not available for this transportation method!");
			return;
		}
		Integer co2eValue = co2Data.get(transportationMethod);
		double result = co2eValue * distance;
		if(unit.equalsIgnoreCase("m")) {
			result /= 1000;
		}
		printer.printResult(result);
		return;
	}
	
}
