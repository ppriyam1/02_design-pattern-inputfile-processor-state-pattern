package channelpopularity.state.factory;

import channelpopularity.context.ChannelContextI;
import channelpopularity.state.StateI;
import channelpopularity.state.StateName;
import channelpopularity.state.data.HighlyPopularState;
import channelpopularity.state.data.MildlyPopularState;
import channelpopularity.state.data.UltraPopularState;
import channelpopularity.state.data.UnpopularState;

public class SimpleStateFactory implements SimpleStateFactoryI {

	public SimpleStateFactory() {
	}

	/**
	 * @param stateName
	 * @return
	 */
	public static StateI getStateInstance(ChannelContextI context, StateName stateName) {

		StateI state = null;

		switch (stateName) {

		case ULTRA_POPULAR:
			state = new UltraPopularState(context);
			break;

		case HIGHLY_POPULAR:
			state = new HighlyPopularState(context);
			break;

		case MILDLY_POPULAR:
			state = new MildlyPopularState(context);
			break;

		case UNPOPULAR:
			state = new UnpopularState(context);
			break;

		default:
			// TODO: throw custom exception
			break;

		}

		return state;
	}

	@Override
	public StateI create(ChannelContextI context, StateName stateName) {
		return SimpleStateFactory.getStateInstance(context, stateName);
	}

}
