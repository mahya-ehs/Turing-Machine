/**
 * the class of turing machine which contains
 * things like current symbol and state and the
 * total states and alphabet of the language
 */
package sample;

import java.util.ArrayList;

public class TuringMachine
{
    private ArrayList<State> states;
    private ArrayList<String> alphabet;
    private State currentState;
    private String currentSymbol;
    private ArrayList<State> finalState;
    private State initialState;

    public TuringMachine()
    {
        states = new ArrayList<>();
        alphabet = new ArrayList<>();
        finalState = new ArrayList<>();
    }

    public void addState(State s)
    {
        states.add(s);
    }

    public void addAlphabet(String s)
    {
        alphabet.add(s);
    }
    public State getState(String name)
    {
        for (State state : states)
            if (state.getName().equals(name))
                return state;

        return null;
    }

    public ArrayList<State> getStates()
    {
        return states;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public String getCurrentSymbol() {
        return currentSymbol;
    }

    public void setCurrentSymbol(String currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    public ArrayList<State> getFinalState() {
        return finalState;
    }

    public void setFinalState(State fState) {
        finalState.add(fState);
    }

    public State getInitialState() {
        return initialState;
    }

    public void setInitialState(State initialState) {
        this.initialState = initialState;
    }

    public ArrayList<String> getAlphabet() {
        return alphabet;
    }
}
