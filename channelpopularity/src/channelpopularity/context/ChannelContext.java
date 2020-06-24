package channelpopularity.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.entity.VideoStore;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

public class ChannelContext implements ChannelContextI {

	private StateI curState;

	private final Map<StateName, StateI> availableStates;

	private VideoStore videoStore = VideoStore.getInstance();

	/**
	 * @param stateFactoryIn
	 * @param stateNames
	 */
	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {
		// Initialise states using factory instance and the provided state names.
		availableStates = new HashMap<>();

		stateNames.forEach((stateName) -> {
			availableStates.put(stateName, stateFactoryIn.create(this, stateName));
		});

		// Initialise current state.
		this.curState = this.availableStates.get(StateName.UNPOPULAR);
	}

	/**
	 * @param nextState
	 * Called by the States based on their logic of what the machine state should change to.      
	 */
	public void updateState(StateName nextState) {
		if (availableStates.containsKey(nextState)) { // for safety.
			curState = availableStates.get(nextState);
		}
	}

	@Override
	public VideoStore getDataSource() {
		return videoStore;
	}

	@Override
	public void add(String input) {
		this.curState.add(input);
	}

	@Override
	public void remove(String input) {
		this.curState.remove(input);
	}

	@Override
	public void metrics(String input) {
		this.curState.metrics(input);
	}

	@Override
	public void request(String input) {
		this.curState.request(input);
	}

	@Override
	public String toString() {
		return "ChannelContext [curState=" + curState + ", availableStates=" + availableStates + "]";
	}
}
