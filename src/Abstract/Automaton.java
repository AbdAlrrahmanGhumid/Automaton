package Abstract;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Automaton {
    String name;
    State startingState = null;
    State endingState = null;
    LinkedList<State> states = null;
    LinkedList<Transition> transitions = null;
    List<List<Object>> table = new ArrayList<>();
    
    public Automaton(String name, State startingState, State endingState){
        states = new LinkedList<>();
        transitions = new LinkedList<>();
        states.add(startingState);
        states.add(endingState);
        this.endingState= endingState;
        this.startingState = startingState;
        this.name = name;
    }
}



