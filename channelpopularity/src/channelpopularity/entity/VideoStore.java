package channelpopularity.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author preetipriyam
 *
 */
public class VideoStore {

	private static volatile VideoStore videoStore;

	private static final Map<String, Video> STORE = new ConcurrentHashMap<>();

	private VideoStore() {
		// avoid manipulation from reflection
		if (videoStore != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	// Avoid manipulation during serialization/deserialization operation.
	protected VideoStore readResolve() {
		return getInstance();
	}

	/**
	 * @return Map: STORE.
	 */
	public Map<String, Video> getStore() {
		return STORE;
	}

	/**
	 * @return VideoStore: instance of videoStore.
	 */
	public static VideoStore getInstance() {
		if (videoStore == null) {
			synchronized (VideoStore.class) {
				if (videoStore == null)
					videoStore = new VideoStore();
			}
		}

		return videoStore;
	}

}
