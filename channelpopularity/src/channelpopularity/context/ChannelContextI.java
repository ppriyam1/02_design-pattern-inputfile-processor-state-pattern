package channelpopularity.context;

import channelpopularity.entity.VideoStore;
import channelpopularity.exception.ChannelPopularityException;
import channelpopularity.state.StateName;

public interface ChannelContextI {

	/**
	 * Method to get the stored videos.
	 *
	 * @return
	 */
	public VideoStore getDataSource();

	/**
	 * updateState() method called by the States based on their logic of what the
	 * machine state should change to.
	 *
	 * @param nextStateName
	 */
	public void updateState(StateName nextStateName);

	/**
	 * Method to add videos.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void add(String input) throws ChannelPopularityException;

	/**
	 * Method to remove videos.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void remove(String input) throws ChannelPopularityException;

	/**
	 * Method to calculate the channel score.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void metrics(String input) throws ChannelPopularityException;

	/**
	 * Method to accept or reject add request.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void request(String input) throws ChannelPopularityException;

}
