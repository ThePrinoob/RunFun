package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Main extends Application implements EventHandler<ActionEvent> {
    // Elemente deklarieren
    Button btn1, btn2, btn3, btn4;
    Label labl1, labl2, labl3, labl4, labl5;
    Stage fenster;
    Scene scene, scene2;
    TextField txtfield;
    PasswordField passwfield;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            fenster = stage;
            GridPane root = new GridPane();
            GridPane root2 = new GridPane();
            btn1 = new Button("Erste Seite");
            btn1.setPrefHeight(50);
            btn1.setPrefWidth(400);
            btn1.setOnAction(this);

            btn2 = new Button("Zweite Seite");
            btn2.setPrefHeight(50);
            btn2.setPrefWidth(400);
            btn2.setOnAction(this);
            
            btn3 = new Button("Spiel starten");
            btn3.setPrefHeight(50);
            btn3.setPrefWidth(400);
            btn3.setOnAction(this);
            
            btn4 = new Button("Highscore anschauen");
            btn4.setPrefHeight(50);
            btn4.setPrefWidth(400);
            btn4.setOnAction(this);
            
            
            labl1 = new Label("RunFun");
            final double MAX_FONT_SIZE = 30.0; // define max font size you need
            labl1.setFont(new Font(MAX_FONT_SIZE));

            labl2 = new Label("Das ist die zweite Szene");
            labl3 = new Label("Benutzername");
            labl4 = new Label("Passwort: ");
            labl4 = new Label("Passwort: ");
            labl5 = new Label("Der Name und/oder das Password ist falsch!");
            txtfield = new TextField();
            txtfield.setPrefHeight(25);
            txtfield.setPrefWidth(150);
            passwfield = new PasswordField();
            passwfield.setPrefHeight(25);
            passwfield.setPrefWidth(150);
            // Szenen erstellen
            scene = new Scene(root, 300, 200);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            scene2 = new Scene(root2, 300, 200);
            scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            root.add(labl1, 0, 0);
            //root.add(labl3, 0, 1);
            //root.add(labl4, 0, 2);
            //root.add(labl5, 0, 4);
            root.add(btn2, 0, 3);
            root.add(btn3, 0, 4);
            root.add(btn4, 0, 5);
            //root.add(txtfield, 1, 1);
            //root.add(passwfield, 1, 2);
            //root2.add(labl2, 1, 2);
            root2.add(btn1, 1, 3);
            stage.setScene(scene);
            stage.show();
            stage.setTitle("RunFun");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handle(ActionEvent arg0) {
        if (arg0.getSource() == txtfield) {
            txtfield.getText();
        }
        if (arg0.getSource() == btn2) {
            fenster.setScene(scene2);
            fenster.show();
        } else if (arg0.getSource() == btn1) {
            fenster.setScene(scene);
            fenster.show();
        }
    }
}