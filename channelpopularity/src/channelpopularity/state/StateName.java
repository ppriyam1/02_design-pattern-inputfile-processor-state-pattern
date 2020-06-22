package channelpopularity.state;

import java.util.ArrayList;
import java.util.List;

public enum StateName {

	UNPOPULAR(1000), MILDLY_POPULAR(10000), HIGHLY_POPULAR(100000), ULTRA_POPULAR(Integer.MAX_VALUE);

	public Integer value;

	StateName(Integer value) {
		this.value = value;
	}

	public static List<StateName> getList() {
		List<StateName> list = new ArrayList<>();

		for (StateName state : values())
			list.add(state);

		return list;
	}

	public Integer getValue() {
		return value;
	}
}
