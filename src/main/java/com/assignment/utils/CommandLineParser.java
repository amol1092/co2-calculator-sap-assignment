package com.assignment.utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.assignment.exception.CommandLineParserException;

public class CommandLineParser {

	public final static String TRANSPORTATION_METHOD = "transportation-method";

	public final static String DISTANCE = "distance";

	public final static String UNIT = "unit-of-distance";

	public static CommandLine parseArguments(String ...args) throws CommandLineParserException {
		Options options = getOptions();
		CommandLine line = null;

		DefaultParser parser = new DefaultParser();
		try {
			line = parser.parse(options, args);
		} catch (ParseException | RuntimeException ex) {
			String message = String.format("Failed to parse command line arguments. "
					+ "Reason : %s", ex.getMessage());
			throw new CommandLineParserException(message, ex);
		}
		return line;
	}

	public static Options getOptions() {
		Options options = new Options();
		options.addOption("tm", TRANSPORTATION_METHOD, true, "medium of transport")
		.addOption("d", DISTANCE, true, "distance travelled")
		.addOption("u", UNIT, true, "unit of distance (km or m)");
		return options;
	}

}
