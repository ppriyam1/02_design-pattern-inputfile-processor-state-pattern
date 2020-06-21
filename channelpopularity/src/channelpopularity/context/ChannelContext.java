//we have to use Simple Factory pattern which allows us to create object based on the parameter.
// 1. The constructor of the context accepts an instance of SimpleStateFactory.
/* 2. The setState(...) method of the context accepts an enum representing the state to change to.
And this is provided to the SimpleStateFactory instance to fetch an instance of the respective state. */

// Assume imports.
package channelpopularity.context;
import channelpopularity.state.StateI;
import channelpopularity.state.State;
import channelpopularity.state.factory.SimpleStateFactoryI;
import java.util.*;
//import wordPlay.util.Results;

//import wordPlay.metrics.MetricsCalculator;

		public class ChannelContext implements ChannelContextI {
		    private StateI curState;
		    private Map<State, StateI> availableStates;
				videoStruct vs = new videoStruct();
				//Arraylist<vs> a = new Arraylist<vs>;



		    public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<State> stateNames) {
			// initialize states using factory instance and the provided state names.
			// initialize current state.
      //curState = stateNames;

		    }

		    // Called by the States based on their logic of what the machine state should change to.
		    public void setCurrentState(State nextState) {
			if (availableStates.containsKey(nextState)) { // for safety.
				curState = availableStates.get(nextState);
			}
		    }
		}
//Arraylist<struct> q = new ..
//
