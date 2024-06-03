package Abstract;

public class AutomatonConstructor {
    public static void addState(Automaton automaton,State state){
        automaton.states.add(state);
    }
    public static void deleteState(Automaton automaton, String name){
        automaton.states.removeIf(state -> (state.name().equals(name)));
    }
    public static void addTransition(Automaton automaton,Transition transition, State state1, State state2){
        automaton.transitions.add(transition);
    }
    public static void deleteTransition(Automaton automaton,Transition transition){

    }
}
