package channelpopularity.operation;

public enum Operation {

	ADD_VIDEO("ADD_VIDEO"), REMOVE_VIDEO("REMOVE_VIDEO"), METRICS("METRICS"), AD_REQUEST("AD_REQUEST");

	public String value;

	Operation(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Operation find(String value) {
		for (Operation op : values()) {
			if (op.getValue().compareTo(value) == 0) {
				return op;
			}
		}

		throw new IllegalArgumentException(value);
	}

}
