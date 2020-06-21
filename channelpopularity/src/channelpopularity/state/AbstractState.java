package channelpopularity.state;

//import channelpopularity.state;

public abstract class AbstractState implements StateI {
  public State ADD_VIDEO(){
    //implementation
    return State.UnpopularStateVar;
  }
  public State REMOVE_VIDEO(){
    //implementaion
    return State.UnpopularStateVar;
  }
  public State METRICS(){
    //implementation
    return State.UnpopularStateVar;
  }
}

/* As the logic for calculating the popularity score for videos, and in turn of the channel, is the same regardless
of the current state, this can be put in a method in an abstract class that implements the StateI interface.
Note that this would result in the states extending the abstract class instead of directly implementing the StateI interface,
which okay. */
