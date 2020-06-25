package channelpopularity.driver;

import channelpopularity.exception.ChannelPopularityException;
import channelpopularity.exception.ErrorCode;

/**
 * @author preetipriyam
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		} else {

			if ((args[0] == null || args[0].isEmpty()) || (args[1] == null || args[1].isEmpty()))
				throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_ARGUMENT, "Invalid arguments");

			try {
				Helper.process(args[0], args[1]);
			} catch (ChannelPopularityException e) {
				ChannelPopularityException.processException(e);
			}

		}

	}
}
