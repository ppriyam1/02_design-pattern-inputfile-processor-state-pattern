package channelpopularity.state.data;

import channelpopularity.context.ChannelContextI;
import channelpopularity.entity.Advertisement;
import channelpopularity.entity.Video;
import channelpopularity.operation.Operation;
import channelpopularity.state.AbstractState;
import channelpopularity.state.StateName;
import channelpopularity.util.Results;

public class UnpopularState extends AbstractState {

	private static final String STATE_NAME = StateName.UNPOPULAR.toString();

	private static final Integer STATE_POPULARITY_SCORE_MIN_LIMIT = 1;
	private static final Integer STATE_POPULARITY_SCORE_MAX_LIMIT = 1000;

	private static final Integer STATE_AD_REQUEST_RANGE = 10;

	private ChannelContextI context;

	/**
	 * @param context
	 */
	public UnpopularState(ChannelContextI context) {
		this.context = context;
	}

	@Override
	protected void updateScore() {
		synchronized (this) {
			Integer newScore = this.getAverageTotalScore(this.context);
			if (!newScore.equals(this.channelScore)) {
				this.channelScore = newScore;
			}
		}
	}

	@Override
	protected void updateState() {
		if (!(this.channelScore <= STATE_POPULARITY_SCORE_MIN_LIMIT)) {
			synchronized (this) {
				if (this.channelScore > STATE_POPULARITY_SCORE_MAX_LIMIT)
					this.context.updateState(StateName.MILDLY_POPULAR);
			}
		}
	}

	@Override
	public void add(String input) {

		String videoName = this.formatter(input);

		if (!this.context.getDataSource().getStore().containsKey(videoName)) {
			// Adding video to cache
			this.context.getDataSource().getStore().put(videoName, new Video(videoName));

			// Need to update score
			updateScore();

			// Updating state
			updateState();

			// Adding to result
			Results.add(STATE_NAME, this.CONSTANT_VIDEO_ADDED, videoName);
		} else {
			// TODO: Do something if Video already present
			System.out.println("exception: video name aleady present!");
		}
	}

	@Override
	public void remove(String input) {

		String videoName = this.formatter(input);

		if (this.context.getDataSource().getStore().containsKey(videoName)) {
			// Removing video to cache
			this.context.getDataSource().getStore().remove(videoName);

			// Need to update score
			updateScore();

			// Updating state
			updateState();

			// Adding to result
			Results.add(STATE_NAME, this.CONSTANT_VIDEO_REMOVED, videoName);
		} else {
			// TODO: Do something if Video is not present
			System.out.println("exception: video not found!");
		}
	}

	@Override
	public void metrics(String input) {

		final Video videoRef = this.metricsformatter(input);

		if (this.context.getDataSource().getStore().containsKey(videoRef.getName())) {

			// Updating video in the cache
			this.context.getDataSource().getStore().get(videoRef.getName()).update(videoRef.getViews(),
					videoRef.getLikes(), videoRef.getDislikes());

			// Need to update score
			updateScore();

			// Updating state
			updateState();

			// Adding to result
			Results.add(STATE_NAME, this.CONSTANT_SCORE_UPDATE, this.channelScore.toString());

		} else {
			// TODO: Do something if Video is not present
			System.out.println("exception: video not found!");
		}
	}

	@Override
	public void request(String input) {
		final Advertisement advertisementRef = this.requestformatter(input);

		if (advertisementRef.getLength() <= STATE_AD_REQUEST_RANGE) {
			// Adding to result
			Results.add(STATE_NAME, Operation.AD_REQUEST.value, this.CONSTANT_AD_REQUEST_APPROVED);
		} else {
			// Adding to result
			Results.add(STATE_NAME, Operation.AD_REQUEST.value, this.CONSTANT_AD_REQUEST_REJECTED);
		}

	}
}
