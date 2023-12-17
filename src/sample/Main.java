package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class Main extends Application
{
    private Stage window;
    private TuringMachine TM;
    private Tape tape;
    private int counter;
    private Label currentState;

    public static void main(String[] args)
    {
        launch(args);
    }

    /*this is the first scene*/
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = new Stage();
        window.setTitle("Turing Machine");
        window.setResizable(false);
        TM = new TuringMachine();

        Image image = new Image("file:by.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(600);
        iv.setFitWidth(960);

        Label label = new Label("Turing Machine Simulator");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        label.setEffect(new Glow());

        Button button = new Button("START");
        button.setFont(Font.font("Tahoma", 22));
        button.setPrefSize(120,23);
        button.setOnAction(e -> window.setScene(new Scene(statesAndAlphabet(),960, 600)));
        button.setEffect(new InnerShadow());
        button.setStyle("-fx-background-color:#C0C0C0");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(button);
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(10, 10, 140, 10));
        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, vbox);
        window.setScene(new Scene(layout, 960, 600));
        window.show();

    }

    /*second scene : choosing the alphabet and states of machine*/
    private Parent statesAndAlphabet()
    {
        Image image = new Image("file:1.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(600);
        iv.setFitWidth(960);

        VBox options = new VBox();
        HBox totalBox = new HBox();

        totalBox.setSpacing(30);
        totalBox.setAlignment(Pos.CENTER);

        TextArea tx1 = new TextArea();
        tx1.setPromptText("please use ',' to separate states");
        tx1.setPrefSize(220, 120);
        Label lb1 = new Label("states");
        lb1.setTextFill(Color.WHITE);
        lb1.setFont(Font.font("Aldhabi", 36));
        VBox vBox1 = new VBox();
        vBox1.getChildren().addAll(lb1, tx1);
        vBox1.setAlignment(Pos.CENTER_LEFT);
        vBox1.setSpacing(10);

        TextArea tx2 = new TextArea();
        tx2.setPromptText("please use ',' to separate alphabet");
        tx2.setPrefSize(220, 120);
        Label lb2 = new Label("machine alphabet");
        lb2.setTextFill(Color.WHITE);
        lb2.setFont(Font.font("Aldhabi",36));
        VBox vBox2 = new VBox();
        vBox2.getChildren().addAll(lb2, tx2);
        vBox2.setAlignment(Pos.CENTER_LEFT);
        vBox2.setSpacing(10);

        TextArea tx3 = new TextArea();
        tx3.setPrefSize(220, 120);
        tx3.setPromptText("blank is already considered");
        Label lb3 = new Label("tape alphabet");
        lb3.setTextFill(Color.WHITE);
        lb3.setFont(Font.font("Aldhabi",36));
        VBox vBox3 = new VBox();
        vBox3.getChildren().addAll(lb3, tx3);
        vBox3.setAlignment(Pos.CENTER_LEFT);
        vBox3.setSpacing(10);

        totalBox.getChildren().addAll(vBox1, vBox2, vBox3);

        TM = new TuringMachine();

        Button finish = new Button("Finish");
        finish.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        finish.setPrefSize(120,23);
        finish.setOnAction(e -> {
            if (tx1.getText().equals("") || tx2.getText().equals(""))
                ConfirmBox.warning();
            else
            {
                getStatesAndAlphabet(tx1, tx2, tx3);
                window.setScene(new Scene(transitionFunctions(),960, 600));
            }
        });
        finish.setEffect(new InnerShadow());
        finish.setStyle("-fx-background-color:#C0C0C0");

        options.getChildren().addAll(totalBox, finish);
        options.setSpacing(50);
        options.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(options);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, root);
        return layout;

    }

    /*a function for saving the states and alphabet in the machine class*/
    private void getStatesAndAlphabet(TextArea tx1, TextArea tx2, TextArea tx3)
    {
        String[] states = tx1.getText().split(",");
        String[] machine_alphabet = tx2.getText().split(",");
        String[] tape_alphabet = tx3.getText().split(",");

        for (String state : states)
        {
            TM.addState(new State(state));
        }
        for (String s : machine_alphabet)
        {
            TM.addAlphabet(s);
        }
        if ( !tx3.getText().equals(""))
        {
            for (String s : tape_alphabet)
            {
                TM.addAlphabet(s);
            }
        }
    }

    /*third scene : choosing the transitions functions*/
    private Parent transitionFunctions()
    {
        Image image = new Image("file:2.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(600);
        iv.setFitWidth(960);

        VBox options = new VBox();

        ArrayList<State> statesArray = TM.getStates();
        String[] states = new String[statesArray.size()];
        for (int i = 0; i < statesArray.size(); i++)
        {
            states[i] = statesArray.get(i).getName();
        }
        ArrayList<String> alphabetArray = TM.getAlphabet();
        String[] alphabet = new String[alphabetArray.size() + 1];
        for (int i = 0; i < alphabetArray.size(); i++)
        {
            alphabet[i] = alphabetArray.get(i);
        }
        alphabet[alphabetArray.size()] = "blank";

        ComboBox<String> statesCombo1 = new ComboBox<>(FXCollections
                .observableArrayList(states));

        ComboBox<String> statesCombo2 = new ComboBox<>(FXCollections
                .observableArrayList(states));

        ComboBox<String> alphabetCombo1 = new ComboBox<>(FXCollections
                .observableArrayList(alphabet));

        ComboBox<String> alphabetCombo2 = new ComboBox<>(FXCollections
                .observableArrayList(alphabet));

        ComboBox<String> directionCombo = new ComboBox<>();
        directionCombo.getItems().addAll("Right", "Left");

        Label lb1 = new Label("current state");
        lb1.setTextFill(Color.WHITE);
        lb1.setFont(Font.font("Aldhabi", 30));
        VBox vbox1 = new VBox();
        vbox1.getChildren().addAll(lb1, statesCombo1);
        vbox1.setAlignment(Pos.CENTER);
        vbox1.setSpacing(10);

        Label lb2 = new Label("symbol");
        lb2.setTextFill(Color.WHITE);
        lb2.setFont(Font.font("Aldhabi",30));
        VBox vbox2 = new VBox();
        vbox2.getChildren().addAll(lb2, alphabetCombo1);
        vbox2.setAlignment(Pos.CENTER);
        vbox2.setSpacing(10);

        Label lb3 = new Label("next state");
        lb3.setTextFill(Color.WHITE);
        lb3.setFont(Font.font("Aldhabi", 30));
        VBox vbox3 = new VBox();
        vbox3.getChildren().addAll(lb3, statesCombo2);
        vbox3.setAlignment(Pos.CENTER);
        vbox3.setSpacing(10);

        Label lb4 = new Label("symbol");
        lb4.setTextFill(Color.WHITE);
        lb4.setFont(Font.font("Aldhabi",30));
        VBox vbox4 = new VBox();
        vbox4.getChildren().addAll(lb4, alphabetCombo2);
        vbox4.setAlignment(Pos.CENTER);
        vbox4.setSpacing(10);

        Label lb5 = new Label("direction");
        lb5.setTextFill(Color.WHITE);
        lb5.setFont(Font.font("Aldhabi",30));
        VBox vbox5 = new VBox();
        vbox5.getChildren().addAll(lb5, directionCombo);
        vbox5.setAlignment(Pos.CENTER);
        vbox5.setSpacing(10);

        Image imag = new Image("file:arrow1.png");
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(imag);
        imageView.setFitHeight(40);
        imageView.setFitWidth(60);

        HBox totalHbox = new HBox();
        totalHbox.getChildren().addAll(vbox1, vbox2, imageView, vbox3, vbox4, vbox5);
        totalHbox.setAlignment(Pos.CENTER);
        totalHbox.setSpacing(20);

        Button nextButton = new Button("Next");
        nextButton.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        nextButton.setPrefSize(120,23);
        nextButton.setOnAction(e -> {
            getTransitionFunction(statesCombo1.getValue(), alphabetCombo1.getValue(),
                    statesCombo2.getValue(), alphabetCombo2.getValue(), directionCombo.getValue());
        });
        nextButton.setEffect(new InnerShadow());
        nextButton.setStyle("-fx-background-color:#C0C0C0");

        Button finishButton = new Button("Finish");
        finishButton.setFont(Font.font("Tahoma", FontWeight.BOLD,20));
        finishButton.setPrefSize(120,23);
        finishButton.setOnAction(e -> {
            boolean result = getTransitionFunction(statesCombo1.getValue(), alphabetCombo1.getValue(),
                    statesCombo2.getValue(), alphabetCombo2.getValue(), directionCombo.getValue());
            if (result)
                chooseFinal_Initial();
        });
        finishButton.setEffect(new InnerShadow());
        finishButton.setStyle("-fx-background-color:#C0C0C0");

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(finishButton, nextButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(40);

        options.getChildren().addAll(totalHbox, buttonBox);
        options.setSpacing(70);
        options.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(options);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, root);
        return layout;

    }

    /*a function for saving the transition functions*/
    private boolean getTransitionFunction(String state1, String character1, String state2, String character2, String dir)
    {
        if (state1 == null || character1 == null || state2 == null
            || character2 == null || dir == null)
        {
            ConfirmBox.warning();
            return false;
        }
        else
        {
            int direction;
            if (dir.equals("Left"))
                direction = 0;
            else
                direction = 1;

            TransitionFunc TF = new TransitionFunc(state1, state2, direction, character1, character2);
            ArrayList<State> allStates = TM.getStates();
            //adding a transition function to a state
            for (State allState : allStates)
            {
                if (allState.getName().equals(state1))
                    allState.addFunction(TF);
            }
            System.out.println(state1 + " " + character1 + " => " +
                    state2 + " "+ character2 + " " + dir);
            return true;
        }
    }

    /*forth scene(stage) : choosing the final and initial stages
    (there could be several final states)*/
    private void chooseFinal_Initial()
    {
        Stage stage = new Stage();
        stage.setResizable(false);
        VBox layout = new VBox();
        VBox bottom = new VBox();

        ArrayList<State> arr = TM.getStates();
        String[] states = new String[arr.size()];

        for (int i = 0; i < arr.size(); i++)
        {
            states[i] = arr.get(i).getName();
        }

        ListView<String> listView = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(states);
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label label = new Label();
        label.setTextFill(Color.RED);

        VBox initBox = new VBox();
        Label initLabel = new Label("initial");
        ComboBox<String> initCombo = new ComboBox<>(FXCollections
                        .observableArrayList(states));
        initBox.getChildren().addAll(initLabel, initCombo);

        VBox finalBox = new VBox();
        Label finalLabel = new Label("final");
        finalBox.getChildren().addAll(finalLabel, listView);

        HBox combos = new HBox();
        combos.getChildren().addAll(initBox, finalBox);
        combos.setAlignment(Pos.CENTER);
        combos.setSpacing(25);

        Button pVp = new Button("submit");
        pVp.setFont(Font.font("Comic Sans MS"));
        pVp.setStyle("-fx-background-color: #F08080 ");
        pVp.setEffect(new DropShadow());
        pVp.setOnAction(e -> {
            ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
            if (initCombo.getValue() == null || selectedItems.isEmpty())
                ConfirmBox.warning();
            else
            {
                for (State state : arr)
                {
                    if (state.getName().equals(initCombo.getValue()))
                    {
                        state.setInitialS(true);
                        TM.setInitialState(state);
                    }
                    for (String selectedItem : selectedItems)
                    {
                        if (state.getName().equals(selectedItem))
                        {
                            state.setFinalS(true);
                            TM.setFinalState(state);
                        }
                    }
                }
                stage.close();
                window.setScene(new Scene(inputSection(), 960, 600));
            }
        });

        bottom.getChildren().addAll(combos, pVp);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(13);
        bottom.setPadding(new Insets(5, 5, 5, 45));

        Label Question = new Label("please choose the initial and final states");
        Question.setFont(Font.font("Aldhabi", 30));

        layout.getChildren().addAll(Question, bottom);
        layout.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(layout, 420, 250));
        stage.showAndWait();
    }

    /*fifth scene : receiving the string from user*/
    private Parent inputSection()
    {
        //this is the counter of tape which should be 0 every time this scene is called
        counter = 0;

        Image image = new Image("file:3.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(600);
        iv.setFitWidth(960);

        VBox options = new VBox();

        TextField input = new TextField();
        input.setPrefSize(200, 40);

        HBox inputBox = new HBox();
        inputBox.setAlignment(Pos.CENTER);
        inputBox.getChildren().addAll(input);

        Button startButton = new Button("Confirm");
        startButton.setFont(Font.font("Tahoma", FontWeight.BOLD,18));
        startButton.setPrefSize(120,23);
        startButton.setOnAction(e -> {
            if (input.getText().equals(""))
                ConfirmBox.warning();
            else
            {
                tape = new Tape(input.getText().length());
                tape.setInput(input.getText());
                window.setScene(new Scene(traverseInput(), 960, 600));
            }
        });
        startButton.setEffect(new InnerShadow());
        startButton.setStyle("-fx-background-color:#C0C0C0");

        options.getChildren().addAll(inputBox, startButton);
        options.setSpacing(30);
        options.setAlignment(Pos.BOTTOM_CENTER);
        options.setPadding(new Insets(10, 10, 200, 10));
        BorderPane root = new BorderPane();
        root.setCenter(options);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, root);
        return layout;

    }

    /*sixth scene : the final step is to traversing the string and informing the result*/
    private Parent traverseInput()
    {
        Image image = new Image("file:5.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(600);
        iv.setFitWidth(960);

        //this is the Array of tape in which the string is stored
        String[] string = new String[tape.getLength() + 1];
        for (int i = 0; i < tape.getLength(); i++)
            string[i] = String.valueOf(tape.getInput().charAt(i));

        //we set the initial state and the first symbol of string
        TM.setCurrentState(TM.getInitialState());
        TM.setCurrentSymbol(string[0]);

        //the first symbol of string is on
        tape.pointerIsOn(0);

        currentState = new Label();
        currentState.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));
        currentState.setText(TM.getCurrentState().getName());
        currentState.setTextFill(Color.WHEAT);

        HBox labelBox = new HBox();
        labelBox.getChildren().add(currentState);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setPadding(new Insets(100, 10, 10, 10));

        VBox options = new VBox();
        HBox tapeBox = new HBox();

        tapeBox.getChildren().add(tape);
        tapeBox.setAlignment(Pos.CENTER);
        tapeBox.setSpacing(20);

        Button startButton = new Button("start");
        startButton.setFont(Font.font("Tahoma", FontWeight.BOLD,18));
        startButton.setPrefSize(120,50);
        startButton.setEffect(new InnerShadow());
        startButton.setStyle("-fx-background-color:#C0C0C0");

        Button againButton = new Button("new input");
        againButton.setFont(Font.font("Tahoma", FontWeight.BOLD,18));
        againButton.setPrefSize(120,50);
        againButton.setDisable(true);
        againButton.setEffect(new InnerShadow());
        againButton.setStyle("-fx-background-color:#C0C0C0");

        startButton.setOnAction(e -> {
            boolean endOfMachine = startMachine(string);
            if (endOfMachine)
            {
                startButton.setDisable(true);
                againButton.setDisable(false);
            }
        });
        againButton.setOnAction(e -> {
            window.setScene(new Scene(inputSection(), 960, 600));
        });

        //startButton.setOpacity(0);
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(30);
        buttonBox.getChildren().addAll(startButton, againButton);

        options.getChildren().addAll(tapeBox, buttonBox);
        options.setSpacing(70);
        options.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(options);
        root.setTop(labelBox);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, root);
        return layout;
    }

    /* a function for choosing the transition functions and traverse the result*/
    private boolean startMachine(String[] string)
    {
        //getting the current state of machine
        State state = TM.getCurrentState();
        String symbol = TM.getCurrentSymbol();

        //finding the matching transition function
        TransitionFunc tf = state.searchTransition(symbol);

        //if there exists a transition function ...
        if (tf != null)
        {
            //get the info
            String nextState = tf.getNextState();
            String nextSymbol = tf.getSymbol2();
            int dir = tf.getDirection();

            currentState.setText(nextState);

            //turn off the pointer of the last symbol
            if (counter < tape.getLength())
            {
                tape.pointerIsOff(counter);
                string[counter] = nextSymbol;
                tape.changeTheSymbol(counter, nextSymbol);
            }

            //if it's left then go back if it's right then go further
            if (dir == 0)       //Left
                counter --;

            else                //Right
                counter ++;
            if (nextSymbol.equals("blank")
                    && isFinalState(nextSymbol))
            {
                ConfirmBox.accept();
                return true;
            }
            if (counter < tape.getLength() && counter >= 0)
                tape.pointerIsOn(counter);

            //change the state of machine
            TM.setCurrentState(TM.getState(nextState));
            if (counter >= tape.getLength() || counter < 0)
                TM.setCurrentSymbol("blank");
            else
                TM.setCurrentSymbol(string[counter]);

            return false;
        }
        else
        {
            if (isFinalState(TM.getCurrentState().getName()))
                ConfirmBox.accept();
            else
                ConfirmBox.reject();
            return true;
        }
    }

    /* a function for declaring whether a state is final or not*/
    private boolean isFinalState(String state)
    {
        ArrayList<State> fStates = TM.getFinalState();
        for (State fState : fStates)
        {
            if (state.equals(fState.getName()))
                return true;
        }
        return false;
    }

}