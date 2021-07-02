package com.assignment.calculator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.assignment.exception.Co2CalculatorException;

/**
 * The convention used for test names is in the format :
 * '<method_under_test>_<conditions/scenario>_<expectation>'
 * 
 * @author amolekande
 *
 */
public class TestCo2DataContainer {
	
	@Test
	public void getInstance__createsInstanceWithDataLoadedFromCSV() throws Co2CalculatorException {
		Co2DataContainer co2DataContainer = Co2DataContainer.getInstance();
		assertNotNull(co2DataContainer);
		// there is data for 14 different types of transportation methods
		assertTrue(co2DataContainer.getCo2Data().size() == 14);
		//verifying a couple of them out of 14 to confirm, data is loaded
		assertTrue(co2DataContainer.getCo2Data().containsKey("medium-diesel-car"));
		assertTrue(co2DataContainer.getCo2Data().get("medium-diesel-car") == 171);
		assertTrue(co2DataContainer.getCo2Data().containsKey("train"));
		assertTrue(co2DataContainer.getCo2Data().get("train") == 6);
		//verifying the transportation methods which do not exist
		assertFalse(co2DataContainer.getCo2Data().containsKey("medium-sports-car"));		
	}
}
