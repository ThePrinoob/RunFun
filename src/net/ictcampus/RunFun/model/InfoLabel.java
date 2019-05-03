package net.ictcampus.RunFun.model;

//~~~ Imports ~~~
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

/**
 * InfoLabel für Hauptemenu
 * @author ingoldd, leuenbergermik
 *
 */
public class InfoLabel extends Label {

    // ~~~ Instanzvariabeln ~~~
    public String FONT_PATH = "src/net/ictcampus/RunFun/model/resources/kenvector_future.ttf";
    public String BACKGROUND_IMAGE = "resources/red_button10.png";

    // ~~~ Konstruktor ~~~
    public InfoLabel(String text) {

        setPrefWidth(500);
        setPrefHeight(49);
        // setPadding(new Insets(40,40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE), 500, 49, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                null);

        setBackground(new Background(backgroundImage));

    }

    // ~~~ Methoden ~~~
    private void setLabelFont() {

        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }
}
