package channelpopularity.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ContextI;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.util.StdoutDisplayInterface;

/**
 * @author Preeti Priyam
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
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		System.out.println("Hello World! Lets get started with the assignment");
		init(args[0]);
	}

	/**
	 * @param input
	 */
	public static void init(String input) {

			try{
					final String PATH = "./" + input;
					FileProcessor fp = new FileProcessor(PATH);
					SimpleStateFactoryI factoryI = new SimpleStateFactory();
					ChannelContextI context = new ChannelContext(factoryI, StateName.getList());
					String instruction = null;
					do {
							instruction = fp.poll();// read next line

							if (instruction != null) {

								if (instruction.contains(Operation.ADD_VIDEO.value)) {
										context.add(instruction);
									} else if (instruction.contains(Operation.REMOVE_VIDEO.value)) {
										context.remove(instruction);
									} else {
										System.out.println("exception: Invalid Input exception!");
									}
								} else {

								}
				 } while (instruction != null);

					StdoutDisplayInterface stdout = new Results();
					stdout.printToConsole();

					fp.close();
		  } catch (InvalidPathException | SecurityException | IOException e) {
					e.printStackTrace();
			}
		}
	}
