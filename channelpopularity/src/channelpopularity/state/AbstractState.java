package channelpopularity.state;

import java.util.ArrayList;
import java.util.List;

import channelpopularity.context.ChannelContextI;
import channelpopularity.entity.Advertisement;
import channelpopularity.entity.Video;
import channelpopularity.exception.ChannelPopularityException;
import channelpopularity.exception.ErrorCode;

/**
 * @author preetipriyam
 *
 */
public abstract class AbstractState implements StateI {

	protected final String CONSTANT_VIDEO_ADDED = "VIDEO_ADDED";
	protected final String CONSTANT_VIDEO_REMOVED = "VIDEO_REMOVED";

	protected final String CONSTANT_SCORE_UPDATE = "POPULARITY_SCORE_UPDATE";

	protected final String CONSTANT_AD_REQUEST = "AD_REQUEST";
	protected final String CONSTANT_AD_REQUEST_APPROVED = "APPROVED";
	protected final String CONSTANT_AD_REQUEST_REJECTED = "REJECTED";

	protected Integer channelScore = 1;
	protected final int minAdvertisementLength = 2;

	/**
	 * Method to calculate the popularity score of the video.
	 *
	 * @param views
	 * @param likes
	 * @param dislikes
	 * @return type is integer.
	 */
	private static final Integer calculatePopularity(Integer views, Integer likes, Integer dislikes) {

		Integer currScore = views + 2 * (likes - dislikes);

		final Integer score = currScore > 0 ? currScore : 0;

		return score;
	}

	/**
	 * Method to find the average score of the videos.
	 *
	 * @param videoScore
	 * @param numberOfVideos
	 * @return
	 */
	private static final Integer findAverageScore(final Integer videoScore, final Integer numberOfVideos) {
		return videoScore > 0 && numberOfVideos > 0 && videoScore >= numberOfVideos ? videoScore / numberOfVideos
				: null;
	}

	/**
	 * Method to get the average score of the videos.
	 *
	 * @param context
	 * @return Integer: average score of videos.
	 */
	protected final Integer getAverageTotalScore(ChannelContextI context) {

		final List<Integer> videoScores = new ArrayList<>();

		// re-calculating scores of each video
		context.getDataSource().getStore().forEach((videoName, video) -> {
			videoScores.add(calculatePopularity(video.getViews(), video.getLikes(), video.getDislikes()));
		});

		Integer totalVideoScore = videoScores.stream().mapToInt(Integer::intValue).sum();

		Integer currAverageScore = findAverageScore(totalVideoScore, videoScores.size());

		return currAverageScore != null ? currAverageScore : channelScore;
	}

	/**
	 * Method to format the input string for add and remove video functionality.
	 *
	 * @param input
	 * @return String: name of the video.
	 */
	protected String formatter(String input) {

		String[] instructions = input.split("::");

		String videoName = instructions[1];
		return videoName;
	}

	/**
	 * Method to format the input string for metrics functionality.
	 *
	 * @param input
	 * @return metricsformatter: instance of the video.
	 * @throws ChannelPopularityException
	 */
	protected Video metricsformatter(String input) throws ChannelPopularityException {

		Video video = new Video();

		String[] instructions = input.split("::");
		String[] prefix = instructions[0].split("__");

		String temp = instructions[1];
		temp = temp.substring(1, temp.length() - 1);
		String[] arr = temp.split(",");

		video.setName(prefix[1]);

		for (int i = 0; i <= arr.length - 1; i++) {
			String[] curr = arr[i].split("=");
			try {
				if (curr[0].equalsIgnoreCase("VIEWS")) {
					Integer views = Integer.parseInt(curr[1]);

					if (views < 0)
						throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_FORMAT,
								"Negative value for number of views in an input line");

					video.setViews(views);
				} else if (curr[0].equalsIgnoreCase("LIKES")) {
					video.setLikes(Integer.parseInt(curr[1]));
				} else if (curr[0].equalsIgnoreCase("DISLIKES")) {
					video.setDislikes(Integer.parseInt(curr[1]));
				} else {
					throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_FORMAT,
							"Line in the input file does not follow the specified formats");
				}
			} catch (NumberFormatException e) {
				throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_FORMAT,
						"Values for views, likes, dislikes or advertisement length are not integers");
			}
		}

		return video;
	}

	/**
	 * Method to format the input string for Ad Request functionality.
	 *
	 * @param input
	 * @return Advertisement: instance of Advertisement.
	 * @throws ChannelPopularityException
	 */
	protected Advertisement requestformatter(String input) throws ChannelPopularityException {

		Advertisement advertisement = new Advertisement();

		String[] instructions = input.split("::");
		String[] prefix = instructions[0].split("__");

		if (prefix[1] == null || prefix[1].isEmpty())
			throw new ChannelPopularityException(ErrorCode.RESOURCE_NOT_FOUND,
					"Video associated with an advertisement request does not exist");

		String[] suffix = instructions[1].split("=");

		try {
			Integer length = Integer.parseInt(suffix[1]);

			if (length <= minAdvertisementLength || !(length > Integer.MIN_VALUE && length < Integer.MAX_VALUE)) {
				throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_FORMAT,
						"Values for views, likes, dislikes or advertisement length are not integers");
			}

			advertisement.setLength(length);
			advertisement.setVideoName(prefix[1]);

		} catch (NumberFormatException e) {
			throw new ChannelPopularityException(ErrorCode.INVALID_INPUT_FORMAT,
					"Values for views, likes, dislikes or advertisement length are not integers");
		}

		return advertisement;
	}

	protected abstract void updateScore();

	protected abstract void updateState();
}
