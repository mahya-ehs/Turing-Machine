/**
 * this class is written for different boxes
 * that has been used in this program
 * for example the alert boxes
 */

package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmBox
{
    //exit stage
    public static void warning()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("warning!");
        window.setMinWidth(250);

        Label label = new Label("Please fill in the blanks");
        label.setFont(Font.font("Aldhabi", 40));
        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Button btn1 = new Button("OK");
        btn1.setFont(Font.font("Tahoma", 15));
        btn1.setPrefSize(80, 30);
        btn1.setStyle("-fx-background-color :#F08080 ");
        btn1.setEffect(new DropShadow());

        btn1.setOnAction(e -> window.close());
        hbox.getChildren().addAll(btn1);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        vbox.getChildren().addAll(label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        window.setResizable(false);
        window.setScene(new Scene(vbox, 450, 200));
        window.showAndWait();
    }
    //alert box(warnings)
    public static void reject()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("result");
        window.setMinWidth(250);

        Image image = new Image("file:reject.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(300);
        iv.setFitWidth(460);

        Button alright = new Button("Alright");
        alright.setFont(Font.font("Tahoma", 15));
        alright.setPrefSize(80, 30);
        alright.setStyle("-fx-background-color :#F08080 ");
        alright.setEffect(new DropShadow());
        alright.setOnAction(e -> window.close());

        HBox hbox = new HBox();
        hbox.getChildren().addAll(alright);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(10, 10, 80, 10));

        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, hbox);

        window.setResizable(false);
        window.setScene(new Scene(layout, 450, 300));
        window.showAndWait();
    }

    public static void accept()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("result");
        window.setMinWidth(250);

        Image image = new Image("file:accept.png");
        ImageView iv = new ImageView(image);
        iv.setFitHeight(300);
        iv.setFitWidth(460);

        Button alright = new Button("Alright");
        alright.setPrefSize(80, 30);
        alright.setFont(Font.font("Tahoma", 15));
        alright.setStyle("-fx-background-color :#F08080 ");
        alright.setEffect(new DropShadow());
        alright.setOnAction(e -> window.close());

        HBox hbox = new HBox();
        hbox.getChildren().addAll(alright);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        hbox.setPadding(new Insets(10, 10, 80, 10));

        StackPane layout = new StackPane();
        layout.getChildren().addAll(iv, hbox);

        window.setResizable(false);
        window.setScene(new Scene(layout, 450, 300));
        window.showAndWait();
    }

}
