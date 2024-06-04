package Implementation;

import java.util.LinkedList;
import java.util.List;

public class App {
    
    record Transition(String name, State s1, State s2, Runnable action){}

    static class Automat{
        List<Transition> transisions = new LinkedList<>();
        State currentState;

        public Automat(State startState){
            this.currentState = startState;
        }

        Automat addTransition(String name, State s1, State s2, Runnable action){
            transisions.add(new Transition(name, s1, s2, action));
            return this;
        }

        void runTransition(String name){
            List<Transition> transition = transisions.stream()
                .filter(t -> t.s1 == currentState && t.name() == name)
                .toList();

            if(transition.isEmpty()){
                throw new IllegalArgumentException("No transiotn with name ....");
            }


            State newState = transition.get(0).s2();

            boolean callOnLeaveAndOnEnter = newState != currentState;

            if(callOnLeaveAndOnEnter) currentState.onLeave();
            transition.get(0).action().run();
            if(callOnLeaveAndOnEnter) newState.onEnter();
            
            currentState = newState;
        }
    }


    static class  State{
        String name;
        public State(String name){
            this.name = name;
        }

        public void onEnter(){}
        public void onLeave(){}
    }


    public void example(){
        State state1 = new State("1");
        State state2 = new State("2");
        State state3 = new State("3"){
            public void onEnter(){
                System.out.print("3 entered");
            }
            public void onLeave(){
                System.out.print("3 left");
            }
        };

        Automat a = new Automat(state1)
            .addTransition("a",state1, state2, ()-> {System.out.print("Action");})
            .addTransition("a",state2, state3, ()-> {System.out.print("Action 2");})
            .addTransition("b",state3, state1, ()-> {System.out.print("Action 3");});

        a.runTransition("a");
        a.runTransition("a");
        a.runTransition("b");
    }

    public static void main(String[] args) throws Exception {
        State state1 = new State("1");
        State state2 = new State("2");
        State state3 = new State("3"){
            public void onEnter(){
                System.out.println("3 entered");
            }
            public void onLeave(){
                System.out.println("3 left");
            }
        };
         Automat a = new Automat(state1)
            .addTransition("a",state1, state2, ()-> {System.out.println("Action 1");})
            .addTransition("a",state2, state3, ()-> {System.out.println("Action 2");})
            .addTransition("b",state3, state1, ()-> {System.out.println("Action 3");});

        a.runTransition("a");
        a.runTransition("a");
        a.runTransition("b");
        a.runTransition("b");
    }
}
