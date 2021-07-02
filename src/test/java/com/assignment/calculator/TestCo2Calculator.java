package com.assignment.calculator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.apache.commons.cli.HelpFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assignment.exception.Co2CalculatorException;
import com.assignment.utils.Printer;

/**
 * The convention used for test names is in the format :
 * '<method_under_test>_<conditions/scenario>_<expectation>'
 * 
 * @author amolekande
 *
 */
public class TestCo2Calculator {

	private Co2Calculator co2Calculator;

	private Printer printer;

	@BeforeEach
	private void setup() throws Co2CalculatorException {
		printer = mock(Printer.class);
		co2Calculator = new Co2Calculator(printer);
	}

	@Test
	public void run_withInvalidArguments_printsHelp() {
		co2Calculator.run("--dummy-method", "--dummy-distance");
		verify(printer).printHelp();
	}

	@Test
	public void run_withNullArgumentValues_printsReasonAndHelp() {
		co2Calculator.run("--transportation-method=null", "--distance=null");
		verify(printer).print(anyString());
		verify(printer).printHelp();
	}

	@Test
	public void run_withDistanceAsNonNumber_printsReasonAndHelp() {
		co2Calculator.run("--transportation-method=train", "--distance=abc");
		verify(printer).print(anyString());
		verify(printer).printHelp();
	}

	@Test
	public void run_withValidValuesAndEmptyUnit_returnsResult() {
		co2Calculator.run("--transportation-method=medium-diesel-car", 
				"--distance=15");
		//medium-diesel-car has co2 = 171
		// so, co2 value = 171 * 15 = 2565.0
		verify(printer).printResult(2565.0d);
	}

	@Test
	public void run_withValidValuesAndUnitAsM_returnsResult() {
		co2Calculator.run("--transportation-method=train", 
				"--distance=14500", "--unit-of-distance=m");
		//train has co2 = 6
		// so, co2 value = 6 * 14500/1000 = 87.0
		verify(printer).printResult(87.0d);
	}

	@Test
	public void run_withValidValuesAndArgumentsInAnyOrder_returnsResult() {
		co2Calculator.run("--unit-of-distance=m", "--transportation-method=train", 
				"--distance=14500");
		//train has co2 = 6
		// so, co2 value = 6 * 14500/1000 = 87.0
		verify(printer).printResult(87.0d);
	}
}
