package channelpopularity.state;

import java.util.ArrayList;
import java.util.List;

public enum StateName {

	UNPOPULAR, MILDLY_POPULAR, HIGHLY_POPULAR, ULTRA_POPULAR;

	public static List<StateName> getList() {
		List<StateName> list = new ArrayList<>();

		for (StateName state : values())
			list.add(state);

		return list;
	}
}
