package channelpopularity.driver;

import java.io.IOException;
import java.nio.file.InvalidPathException;

import channelpopularity.context.ChannelContext;
import channelpopularity.context.ChannelContextI;
import channelpopularity.operation.Operation;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.state.factory.SimpleStateFactoryI;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.util.StdoutDisplayInterface;
import channelpopularity.util.FileDisplayInterface;

/**
 * @param input
 */
public class Helper{

public static void init(String input, String output) {

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
              } else if (instruction.contains(Operation.METRICS.value)) {
                  context.metrics(instruction);
              } else if (instruction.contains(Operation.AD_REQUEST.value)) {
                  context.request(instruction);
              } else {
                  System.out.println("exception: Invalid Input exception!");
              }
          } else {

          }

       } while (instruction != null);

       // this is called casting, see we have same overloaded print method but see the
 			// same result object behaves differently for this interface
 			StdoutDisplayInterface stdout = new Results();
 			stdout.print();

 			// this is called casting, see we have same overloaded print method but see the
 			// same result object behaves differently for this interface
 			FileDisplayInterface fileout = new Results();
 			fileout.print(output);

        fp.close();
    } catch (InvalidPathException | SecurityException | IOException e) {
        // TODO: Custom Exception
        e.printStackTrace();
    }
  }
}
