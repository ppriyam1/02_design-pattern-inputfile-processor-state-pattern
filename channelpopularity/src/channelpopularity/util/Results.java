package channelpopularity.util;

import channelpopularity.state.StateName;
import channelpopularity.driver.Helper;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	public Results() {
	}

	private static final StringBuilder builder = new StringBuilder();

	public static void add(String state, String operation, String target) {
		builder.append(state + "__" + operation + "::" + target + " \n");
	}

	@Override
	public void print() {
		System.out.print(builder.toString());
	}

	@Override
	public void print(String fileName) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.append(builder.toString());
			writer.close();
		}
		catch(Exception e){
			System.err.println(e);
			System.exit(0);
		}
	}
}
