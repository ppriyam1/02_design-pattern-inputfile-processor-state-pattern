package channelpopularity.state;

import channelpopularity.entity.Advertisement;
import channelpopularity.entity.Video;

public abstract class AbstractState implements StateI {

	protected final String CONSTANT_VIDEO_ADDED = "VIDEO_ADDED";
	protected final String CONSTANT_VIDEO_REMOVED = "VIDEO_REMOVED";

	protected final String CONSTANT_SCORE_UPDATE = "POPULARITY_SCORE_UPDATE";

	protected final String CONSTANT_AD_REQUEST = "AD_REQUEST";
	protected final String CONSTANT_AD_REQUEST_APPROVED = "APPROVED";
	protected final String CONSTANT_AD_REQUEST_REJECTED = "REJECTED";

	protected Integer channelScore = 0;
	protected final int minAdvertisementLength = 1;

	/**
	 * @param views
	 * @param likes
	 * @param dislikes
	 * @return
	 */
	protected final synchronized Integer calculatePopularity(Integer views, Integer likes, Integer dislikes) {

		Integer currScore = views + 2 * (likes - dislikes);

		final Integer score = currScore > 0 ? currScore : 0;

		return score;
	}

	/**
	 * @param input
	 * @return Name of the video
	 */
	protected String formatter(String input) {

		String[] instructions = input.split("::");

		String videoName = instructions[1];
		return videoName;
	}

	/**
	 * @param input
	 * @return
	 */
	protected Video metricsformatter(String input) {

		Video video = new Video();

		String[] instructions = input.split("::");
		String[] prefix = instructions[0].split("__");

		String temp = instructions[1];
		temp = temp.substring(1, temp.length() - 1);
		String[] arr = temp.split(",");

		video.setName(prefix[1]);

		for (int i = 0; i <= arr.length - 1; i++) {
			String[] curr = arr[i].split("=");
			if (curr[0].equalsIgnoreCase("VIEWS")) {
				video.setViews(Integer.parseInt(curr[1]));
			} else if (curr[0].equalsIgnoreCase("LIKES")) {
				video.setLikes(Integer.parseInt(curr[1]));
			} else if (curr[0].equalsIgnoreCase("DISLIKES")) {
				video.setDislikes(Integer.parseInt(curr[1]));
			} else {
				// TODO: Custom Exception
				System.out.println("exception: Invalid input");
			}
		}

		return video;
	}

	/**
	 * @param input
	 * @return
	 */
	protected Advertisement requestformatter(String input) {

		Advertisement advertisement = new Advertisement();

		String[] instructions = input.split("::");
		String[] prefix = instructions[0].split("__");
		String[] suffix = instructions[1].split("=");

		Integer length = Integer.parseInt(suffix[1]);

		if (length <= minAdvertisementLength) {
			// TODO: throw new Custom Invalid exception
			System.out.println("exception: Invalid Input!");

			// remove the return once exception is added here
			return null;
		}

		advertisement.setLength(length);
		advertisement.setVideoName(prefix[1]);

		return advertisement;
	}

	protected abstract void refresh();

	protected abstract void update();

}
