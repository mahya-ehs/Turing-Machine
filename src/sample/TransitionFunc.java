/**
 * the class of transition functions which holds
 * states, alphabet and direction in form of below:
 * q0 , symbol1 => q1 , symbol2, direction
 */
package sample;

public class TransitionFunc
{
    private String currentState;
    private String nextState;
    private int direction;      //L = 0 & R = 1
    private String symbol1;
    private String symbol2;

    public TransitionFunc(String currentState, String nextState, int direction, String symbol1, String symbol2)
    {
        this.currentState = currentState;
        this.nextState = nextState;
        this.direction = direction;
        this.symbol1 = symbol1;
        this.symbol2 = symbol2;
    }

    public String getCurrentState() {
        return currentState;
    }

    public String getNextState() {
        return nextState;
    }

    public int getDirection() {
        return direction;
    }

    public String getSymbol1() {
        return symbol1;
    }

    public String getSymbol2() {
        return symbol2;
    }
}
