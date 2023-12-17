/**
 * a class for tape of the machine
 * which extends stack pane because
 * it is a board made up of rectangles
 */

package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class Tape extends StackPane
{
    private String input;
    private int length;
    private int size = 50;
    private VBox ROWS = new VBox();
    private ArrayList<Cell> cells = new ArrayList<>(length);

    public Tape(int length)
    {
        this.length = length;
        if (length > 27)
            size = 20;

        else if (length >= 19)
            size = 35;

        for (int i = 0; i < 1; i++)
        {
            HBox eachRow = new HBox();
            for (int j = 0; j < length; j++)
            {
                Cell rec = new Cell(" ", size, size);
                cells.add(rec);
                eachRow.getChildren().add(rec);
            }
            ROWS.getChildren().add(eachRow);
        }

        getChildren().add(ROWS);
    }

    /*this is the class of every cell of the tape*/
    public class Cell extends StackPane
    {
        private String symbol;
        private Rectangle rectangle;
        private Label label;
        private int x, y;
        private boolean pointer;

        private Cell(String text, int x, int y)
        {
            this.symbol = text;
            this.x = x;
            this.y = y;
            rectangle = new Rectangle(x, y);
            rectangle.setFill(Color.THISTLE);
            label = new Label(text);
            getChildren().addAll(rectangle, label);
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol)
        {
            this.symbol = symbol;
            label.setText(symbol);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public boolean isPointer() {
            return pointer;
        }

        public void setPointer(boolean pointer) {
            this.pointer = pointer;
            if (pointer)
                rectangle.setFill(Color.WHEAT);
            else
                rectangle.setFill(Color.THISTLE);
        }
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input)
    {
        this.input = input;
        for (int i = 0; i < cells.size(); i++)
        {
            cells.get(i).setSymbol(String.valueOf(input.charAt(i)));
        }
    }

    public void pointerIsOn(int index)
    {
        cells.get(index).setPointer(true);
    }
    public void pointerIsOff(int index)
    {
        cells.get(index).setPointer(false);
    }
    public void changeTheSymbol(int index, String string)
    {
        cells.get(index).setSymbol(string);
    }
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
