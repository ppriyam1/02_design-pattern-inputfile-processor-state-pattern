package channelpopularity.context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import channelpopularity.entity.VideoStore;
import channelpopularity.exception.ChannelPopularityException;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.factory.SimpleStateFactoryI;

/**
 * @author preetipriyam
 *
 */
public class ChannelContext implements ChannelContextI {

	private StateI curState;

	private final Map<StateName, StateI> availableStates;

	private VideoStore videoStore = VideoStore.getInstance();

	/**
	 * Constructor to initialize states using factory instance and the provided
	 * state names
	 *
	 * @param stateFactoryIn
	 * @param stateNames
	 */
	public ChannelContext(SimpleStateFactoryI stateFactoryIn, List<StateName> stateNames) {

		availableStates = new HashMap<>();

		stateNames.forEach((stateName) -> {
			availableStates.put(stateName, stateFactoryIn.create(this, stateName));
		});

		// Initialize current state.
		this.curState = this.availableStates.get(StateName.UNPOPULAR);
	}

	/**
	 * updateState() method called by the States based on the logic of what the
	 * machine state should change to.
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
	public void add(String input) throws ChannelPopularityException {
		this.curState.add(input);
	}

	@Override
	public void remove(String input) throws ChannelPopularityException {
		this.curState.remove(input);
	}

	@Override
	public void metrics(String input) throws ChannelPopularityException {
		this.curState.metrics(input);
	}

	@Override
	public void request(String input) throws ChannelPopularityException {
		this.curState.request(input);
	}

	@Override
	public String toString() {
		return "ChannelContext [curState=" + curState + ", availableStates=" + availableStates + "]";
	}
}
