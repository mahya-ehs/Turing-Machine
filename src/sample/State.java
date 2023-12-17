package sample;

import java.util.ArrayList;

public class State
{
    private String name;
    private boolean finalS;
    private boolean initialS;

    private ArrayList<TransitionFunc> transitions;

    public State(String name) {
        this.name = name;
        transitions = new ArrayList<>();
    }

    public void addFunction(TransitionFunc transition)
    {
        transitions.add(transition);
    }

    //searching for a transition based on the current symbol and state
    public TransitionFunc searchTransition(String symbol)
    {
        for (int i = 0; i < transitions.size(); i++)
        {
            if (transitions.get(i).getSymbol1().equals(symbol))
                return transitions.get(i);
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinalS() {
        return finalS;
    }

    public void setFinalS(boolean finalS) {
        this.finalS = finalS;
    }

    public boolean isInitialS() {
        return initialS;
    }

    public void setInitialS(boolean initialS) {
        this.initialS = initialS;
    }

    public ArrayList<TransitionFunc> getTransitions() {
        return transitions;
    }

    public void setTransitions(ArrayList<TransitionFunc> transitions) {
        this.transitions = transitions;
    }
}
