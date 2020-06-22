package channelpopularity.entity;

public class Video {

	private String name;

	private Integer views;
	private Integer likes;
	private Integer dislikes;

	public Video() {
	}

	public Video(String name) {
		this(name, 0, 0, 0);
	}

	/**
	 * @param views
	 * @param likes
	 * @param dislikes
	 */
	public Video(String name, Integer views, Integer likes, Integer dislikes) {
		this.name = name;

		this.views = views;
		this.likes = likes;
		this.dislikes = dislikes;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(Integer views) {
		this.views = views;
	}

	/**
	 * @param likes the likes to set
	 */
	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	/**
	 * @param dislikes the dislikes to set
	 */
	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the views
	 */
	public Integer getViews() {
		return views;
	}

	/**
	 * @return the likes
	 */
	public Integer getLikes() {
		return likes;
	}

	/**
	 * @return the dislikes
	 */
	public Integer getDislikes() {
		return dislikes;
	}

	@Override
	public String toString() {
		return "Video [views=" + views + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dislikes == null) ? 0 : dislikes.hashCode());
		result = prime * result + ((likes == null) ? 0 : likes.hashCode());
		result = prime * result + ((views == null) ? 0 : views.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (dislikes == null) {
			if (other.dislikes != null)
				return false;
		} else if (!dislikes.equals(other.dislikes))
			return false;
		if (likes == null) {
			if (other.likes != null)
				return false;
		} else if (!likes.equals(other.likes))
			return false;
		if (views == null) {
			if (other.views != null)
				return false;
		} else if (!views.equals(other.views))
			return false;
		return true;
	}

	public void update(final Integer views, final Integer likes, final Integer dislikes) {

		// TODO: check if the values of params > existing member variables and throw
		// exception
		synchronized (this) {

			Integer currViews = views > 0 ? (this.getViews() + views) : this.getViews();

			Integer currLikes = likes != 0 ? (this.getLikes() + likes) : this.getLikes();
			Integer currDislikes = dislikes != 0 ? (this.getDislikes() + dislikes) : this.getDislikes();

			currViews = currViews >= 0 ? currViews : this.getViews();
			currLikes = currLikes >= 0 ? currLikes : this.getLikes();
			currDislikes = currDislikes >= 0 ? currDislikes : this.getDislikes();

			this.setViews(currViews);
			this.setLikes(currLikes);
			this.setDislikes(currDislikes);
		}
	}
}
