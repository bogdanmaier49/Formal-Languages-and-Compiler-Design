package com.bogdanmaier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Automata {

    public List<State> states;
    public List<String> alphabet;
    public State initialState;
    public List<State> finalStates;
    public List<Transition> transitions;

    public static final String BASE_PATH = "/Users/bogdanmaier/Documents/faculta/Limbaje formale/lab4/src/";

    public Automata () {
        this.transitions = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.states = new ArrayList<>();
        try {
            this.readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkSequence (String sequence) {

        State currentState = initialState;

        for (int i = 0; i < sequence.length(); i++) {
            String sequenceItem = String.valueOf(sequence.charAt(i));

            List<Transition> possibleTransitions = findTransitionsFrom(currentState);
            boolean foundNewTransition = false;
            for (Transition t : possibleTransitions) {
                if (t.getValue().equals(sequenceItem)) {
                    currentState = t.getTo();
                    foundNewTransition = true;
                }
            }

            if (!foundNewTransition) {
                return false;
            }
        }

        if (isFinalState(currentState)) {
            return true;
        }

        return false;
    }

    public List<State> getStates () {
        return this.states;
    }

    public List<String> getAlphabet () {
        return this.alphabet;
    }

    public State getInitialState () {
        return this.initialState;
    }

    public List<State> getFinalStates () {
        return this.finalStates;
    }

    public List<Transition> getTransitions () {
        return this.transitions;
    }

    private void readFromFile () throws IOException {
        File file = new File(BASE_PATH + "FA.in");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String statesLine = br.readLine();
        String[] sStatesLine = statesLine.split(",");
        for (String s : sStatesLine) {
            states.add(new State(s));
        }

        String alphabetLine = br.readLine();
        this.alphabet = Arrays.asList(alphabetLine.split(","));

        String initialStateValue = br.readLine();
        this.initialState = findStateByString(initialStateValue);

        String transitionLine;
        while ((transitionLine = br.readLine()) != null) {
            String[] splited = transitionLine.split(",");

            State from = findStateByString(splited[0]);
            State to = findStateByString(splited[2]);

            if (from != null && to != null && isInAlphabet(splited[1])) {
                this.transitions.add(new Transition(from, to, splited[1]));
            }
        }

        for (Transition transition : transitions) {
            if (findTransitionFrom(transition.getTo()) == null) {
                finalStates.add(transition.getTo());
            }
        }
    }

    private State findStateByString (String st) {
        for (State state : states) {
            if (st.equals(state.getValue())) {
                return state;
            }
        }
        return null;
    }

    private boolean isInAlphabet (String value) {
        for (String s : this.alphabet) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private Transition findTransitionFrom (State s) {
        for (Transition transition : transitions) {
            if (transition.getFrom().getValue().equals(s.getValue())) {
                return transition;
            }
        }
        return null;
    }

    private List<Transition> findTransitionsFrom (State s) {
        List<Transition> result = new ArrayList<>();
        for (Transition transition : transitions) {
            if (transition.getFrom().getValue().equals(s.getValue())) {
                result.add(transition);
            }
        }
        return result;
    }

    private boolean isFinalState (State state) {
        for (State finalState : finalStates) {
            if (state.getValue().equals(finalState.getValue())) {
                return true;
            }
        }

        return false;
    }

}
