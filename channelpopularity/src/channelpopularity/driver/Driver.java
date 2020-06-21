package channelpopularity.driver;

import channelpopularity.util.Results;
import channelpopularity.util.FileProcessor;

import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.context.ChannelContext;

/**
 * @author Preeti Priyam
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

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
		String inputFile = args[0];
		SimpleStateFactory SimpleStateFactoryVar;
		//ChannelContext ChannelContextVar = new ChannelContext(SimpleStateFactoryI stateFactoryIn, List<State> stateNames);
		FileProcessor fileprocessor = new FileProcessor(inputFile);
		//private static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9.]*$";
		String word = fileprocessor.poll();

		while(word != null){
			String[] array = word.split("::");
			for(int i=0; i < array.length; i++){
				System.out.println(array[i]);
				word = fileprocessor.poll();
		}
	}
}
}
