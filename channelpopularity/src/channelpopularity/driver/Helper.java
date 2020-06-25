package channelpopularity.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ChannelContextI;
import channelpopularity.exception.ChannelPopularityException;
import channelpopularity.exception.ErrorCode;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileDisplayInterface;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.util.StdoutDisplayInterface;

/**
 * @author preetipriyam
 *
 */
public class Helper {

	/**
	 * Method to initialize program.
	 *
	 * @param input
	 * @param output
	 * @throws ChannelPopularityException
	 */
	public static void process(String input, String output) throws ChannelPopularityException {

		FileProcessor fp = null;

		try {
			final String PATH = "./" + input;

			fp = new FileProcessor(PATH);

			SimpleStateFactoryI factoryI = new SimpleStateFactory();

			ChannelContextI context = new ChannelContext(factoryI, StateName.getList());

			String instruction = null;

			instruction = fp.poll(); // read next line

			// check file empty

			if (instruction == null || instruction.isEmpty())

				throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_EMPTY, "Input file is empty");

			while (instruction != null) {

				if (instruction.isEmpty()) {

					throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_EMPTY,

							"Line in the input file does not follow the specified formats");

				}

				if (!instruction.contains("::"))

					throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_FORMAT,

							"Line in the input file does not follow the specified formats");

				if (instruction.contains(Operation.ADD_VIDEO.value)) {

					context.add(instruction);

				} else if (instruction.contains(Operation.REMOVE_VIDEO.value)) {

					context.remove(instruction);

				} else if (instruction.contains(Operation.METRICS.value)) {

					context.metrics(instruction);

				} else if (instruction.contains(Operation.AD_REQUEST.value)) {

					context.request(instruction);

				} else {

					throw new ChannelPopularityException(ErrorCode.INVALID_IO, "Invalid input");

				}

				instruction = fp.poll();

			}

			// this is called casting, see we have same overloaded print method but see the

			// same result object behaves differently for this interface

			StdoutDisplayInterface stdout = new Results();

			stdout.print();

			// this is called casting, see we have same overloaded print method but see the

			// same result object behaves differently for this interface

			FileDisplayInterface fileout = new Results();

			fileout.print(output);
		} catch (InvalidPathException e) {
			throw new ChannelPopularityException(ErrorCode.INVALID_PATH, e.getMessage());
		} catch (SecurityException e) {
			throw new ChannelPopularityException(ErrorCode.SECURITY, e.getMessage());
		} catch (IOException e) {
			throw new ChannelPopularityException(ErrorCode.INVALID_IO, e.getMessage());
		} finally {
			try {
				if (fp != null)
					fp.close();
			} catch (IOException e) {
				throw new ChannelPopularityException(ErrorCode.RESOURCE_NOT_CLOSED, e.getMessage());
			}
		}
	}
}
