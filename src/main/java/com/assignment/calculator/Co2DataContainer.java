package com.assignment.calculator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.assignment.exception.Co2CalculatorException;
import com.opencsv.CSVReader;

public class Co2DataContainer {
	
	private static Co2DataContainer dataContainer;
	
	private Map<String,Integer> co2eData = new HashMap<>();
	
	private Co2DataContainer() throws Co2CalculatorException {
		loadData();
	}
	
	public static Co2DataContainer getInstance() throws Co2CalculatorException {
		if(dataContainer == null) {
			return new Co2DataContainer();
		} 
		return dataContainer;
	}
	
	public Map<String,Integer> getCo2Data() {
		return co2eData;
	}
	
	@SuppressWarnings("resource")
	private void loadData() throws Co2CalculatorException {
		String fileName = "co2-data.csv";
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		if (inputStream == null) {
			throw new Co2CalculatorException("co2 data file not found! Please make sure "
					+ "the co2-data.csv file is present in the resources folder");
		} else {
			try {
				CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
				List<String[]> csvData = csvReader.readAll();
				co2eData.putAll(csvData.stream().skip(1)
						.collect(Collectors.toMap(data -> String.valueOf(data[0]),
								data -> Integer.parseInt(data[1]))));
			} catch (Exception e) {
				throw new Co2CalculatorException("invalid format for co2 data. Please make sure "
						+ "the co2-data.csv file is a proper csv file");
			}
		}
	}
}
