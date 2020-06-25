package channelpopularity.util;

import channelpopularity.exception.ChannelPopularityException;

public interface FileDisplayInterface {

	/**
	 * Method to print output to a file.
	 *
	 * @param fileName
	 * @throws ChannelPopularityException
	 */
	public void print(String fileName) throws ChannelPopularityException;
}
