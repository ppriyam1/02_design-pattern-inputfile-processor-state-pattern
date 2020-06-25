package channelpopularity.exception;

/**
 * @author preetipriyam
 *
 */
public class ChannelPopularityException extends Exception {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = -5967807911580996637L;

	private ErrorCode code;

	/**
	 * @param message
	 * @param errorCode
	 */
	public ChannelPopularityException(ErrorCode code, String message) {
		super(message);

		this.code = code;
	}

	/**
	 * @return String: errorCode
	 */
	public String getErrorCode() {
		return this.code.toString();
	}

	/**
	 * @param exception
	 * @throws ChannelPopularityException
	 */
	public static void processException(ChannelPopularityException exception) throws Exception {
		throw new Exception(exception.getErrorCode() + ": " + exception.getMessage());
	}
}
