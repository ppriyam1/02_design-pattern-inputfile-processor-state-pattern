package channelpopularity.state.factory;

import channelpopularity.context.ChannelContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;

public interface SimpleStateFactoryI {

	/**
	 * Method to create instance of the states.
	 * @param context
	 * @param stateName
	 * @return
	 */
	public StateI create(ChannelContextI context, StateName stateName);
}
