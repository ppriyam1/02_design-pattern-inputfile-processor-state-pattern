package channelpopularity.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VideoStore {

	private static volatile VideoStore videoStore;

	private static final Map<String, Video> STORE = new ConcurrentHashMap<>();

	private VideoStore() {
		// avoid manipulation from reflection
		if (videoStore != null) {
			throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
		}
	}

	// Avoid manipulation during serialization/deserializaation operation.
	protected VideoStore readResolve() {
		return getInstance();
	}

	/**
	 * @return
	 */
	public Map<String, Video> getStore() {
		return STORE;
	}

	/**
	 * @return
	 */
	public static VideoStore getInstance() {
		// Double check locking pattern
		if (videoStore == null) {

			synchronized (VideoStore.class) {
				// if there is no instance available... create new one
				if (videoStore == null)
					videoStore = new VideoStore();
			}
		}

		return videoStore;
	}

}
