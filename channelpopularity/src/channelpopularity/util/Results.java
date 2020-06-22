package channelpopularity.util;

import channelpopularity.state.StateName;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	public Results() {
	}

	private static final StringBuilder builder = new StringBuilder();

	public static void add(StateName currentState, String operation, String target) {
		builder.append(currentState + "__" + operation + "::" + target + " \n");
	}

	@Override
	public void printToConsole() {
		System.out.println(builder.toString());
	}

	@Override
	public void printToFile(String fileName) {
		// TODO: Open an output stream to push string builder content on the output file
	}
}
