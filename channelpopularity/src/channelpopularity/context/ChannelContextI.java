package channelpopularity.context;

import channelpopularity.entity.VideoStore;
import channelpopularity.state.StateName;

public interface ChannelContextI {

	/**
	 * @return
	 */
	public VideoStore getDataSource();

	/**
	 * @param stateName
	 */
	public void updateState(StateName nextStateName);

	/**
	 * @param input
	 */
	public void add(String input);

}
