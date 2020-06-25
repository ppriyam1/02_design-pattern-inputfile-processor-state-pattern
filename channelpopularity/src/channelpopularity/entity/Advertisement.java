package channelpopularity.entity;

/**
 * @author preetipriyam
 *
 */
public class Advertisement {

	private Integer length;
	private String videoName;

	public Advertisement() {
	}

	public Advertisement(Integer length, String name) {
		super();
		this.length = length;
		this.videoName = name;
	}

	/**
	 * @return Integer: length.
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return String: videoName.
	 */
	public String getVideoName() {
		return videoName;
	}

	/**
	 * @param videoName
	 */
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((length == null) ? 0 : length.hashCode());
		result = prime * result + ((videoName == null) ? 0 : videoName.hashCode());
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
		Advertisement other = (Advertisement) obj;
		if (length == null) {
			if (other.length != null)
				return false;
		} else if (!length.equals(other.length))
			return false;
		if (videoName == null) {
			if (other.videoName != null)
				return false;
		} else if (!videoName.equals(other.videoName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Advertisement [length=" + length + ", videoName=" + videoName + "]";
	}
}
