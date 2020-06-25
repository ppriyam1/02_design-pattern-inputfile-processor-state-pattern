package channelpopularity.state;

import channelpopularity.exception.ChannelPopularityException;

/**
 * @author preetipriyam
 *
 */
public interface StateI {

	/**
	 * Method to add the video.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void add(String input) throws ChannelPopularityException;

	/**
	 * Method to remove the video.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void remove(String input) throws ChannelPopularityException;

	/**
	 * Method to calculate the metrics.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void metrics(String input) throws ChannelPopularityException;

	/**
	 * Method to accept/reject Ad request.
	 *
	 * @param input
	 * @throws ChannelPopularityException
	 */
	public void request(String input) throws ChannelPopularityException;

}
