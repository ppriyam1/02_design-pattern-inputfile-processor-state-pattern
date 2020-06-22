package channelpopularity.state;

public interface StateI {

	/**
	 * @param input
	 */
	public void add(String input);

	/**
	 * @param input
	 */
	public void remove(String input);

	/**
	 * @param input
	 */
	public void metrics(String input);

	/**
	 * @param input
	 */
	public void request(String input);

}
