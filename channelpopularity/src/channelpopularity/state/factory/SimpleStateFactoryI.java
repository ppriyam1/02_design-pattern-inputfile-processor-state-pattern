package channelpopularity.state.factory;

import channelpopularity.context.ContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface SimpleStateFactoryI {

	/**
	 * @param context
	 * @param stateName
	 * @return
	 */
	public StateI create(ContextI context, StateName stateName);
}
