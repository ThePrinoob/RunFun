package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javaDB.FunRunSelect;
//imports
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.ViewManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
            String musicFile = "src/application/chooseyourcharacter.mp3";     // For example

            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

    public static void main(String[] args) {
        
        launch(args);
//        List<Person> player = new ArrayList<>();
//        FunRunSelect select = new FunRunSelect();
//        player = select.selectPlayerDB();
//        // Ausgeben der ausgelesenen Spieler
//        for (Person p : player) {
//            System.out.println( p.getBenutzername() + p.getTime());
  //  }

}}