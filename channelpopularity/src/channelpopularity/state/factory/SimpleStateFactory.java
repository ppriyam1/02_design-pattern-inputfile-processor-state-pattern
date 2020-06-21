package channelpopularity.state.factory;

import channelpopularity.state.UnpopularState;
import channelpopularity.state.MildlyPopularState;
import channelpopularity.state.HighlyPopularState;
import channelpopularity.state.UltraPopularState;
import channelpopularity.state.StateI;

public class SimpleStateFactory implements SimpleStateFactoryI {
private UnpopularState UnpopularStateVar;
private MildlyPopularState MildlyPopularStateVar;
private HighlyPopularState HighlyPopularStateVar;
private UltraPopularState UltraPopularStateVar;

//The create(...) method of SimpleStateFactory accepts an enum representing the state to be instantiated.
public StateI getStartState(){
  UnpopularStateVar = new UnpopularState();
  return UnpopularStateVar;
}
}
