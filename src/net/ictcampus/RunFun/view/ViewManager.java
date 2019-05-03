package net.ictcampus.RunFun.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import net.ictcampus.RunFun.javaDB.FunRunSelect;
import net.ictcampus.RunFun.javaDB.Player;
import net.ictcampus.RunFun.model.CHARACTER;
import net.ictcampus.RunFun.model.CharacterPicker;
import net.ictcampus.RunFun.model.InfoLabel;
import net.ictcampus.RunFun.model.InfoLabel2;
import net.ictcampus.RunFun.model.RunFunButton;
import net.ictcampus.RunFun.model.RunFunSubScene;

public class ViewManager {
    private static final int WIDTH = 1040, HEIGHT = 700;
    private final static int MENU_BUTTONS_START_X = 450;
    private final static int MENU_BUTTONS_START_Y = 250;

    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;

    private RunFunSubScene highscoreSubScene;
    private RunFunSubScene characterChooserScene;
    private RunFunSubScene creditsSubScene;

    private RunFunSubScene sceneToHide;

    private AudioClip bangClip;

    // private RunFunSubScene creditsSubScene;

//    String hitNormal = ("choose_your_character.mp3");
//    Media sound = new Media(new File(hitNormal).toURI().toString());
//    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    List<RunFunButton> menuButtons;

    List<CharacterPicker> characterList;
    private CHARACTER choosenCharacter;

    public String username;
    // Audio
//    private AudioPlayer chooseYourCharacter;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setTitle("RunFun");
        mainStage.setResizable(false);
        mainStage.setScene(mainScene);
        createButtons();
        createSubScenes();
        createBackground();
    }

    private void showSubScene(RunFunSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createSubScenes() {
        highscoreSubScene = new RunFunSubScene();
        mainPane.getChildren().add(highscoreSubScene);
        createCharacterChooserSubScene();
        creditsSubScene = new RunFunSubScene();
        mainPane.getChildren().add(creditsSubScene);
    }

    public void createCharacterChooserSubScene() {
        characterChooserScene = new RunFunSubScene();
        mainPane.getChildren().add(characterChooserScene);
        InfoLabel chooseCharacterLabel = new InfoLabel("Wähle deinen Charakter");
        chooseCharacterLabel.setLayoutX(260);
        chooseCharacterLabel.setLayoutY(25);
        characterChooserScene.getPane().getChildren().add(chooseCharacterLabel);
        characterChooserScene.getPane().getChildren().add(createCharacterToChoose());
        characterChooserScene.getPane().getChildren().add(createButtonToStart());

    }

    private HBox createCharacterToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        characterList = new ArrayList<>();
        for (CHARACTER character : CHARACTER.values()) {
            CharacterPicker characterToPick = new CharacterPicker(character);
            characterList.add(characterToPick);
            box.getChildren().add(characterToPick);
            characterToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {

                    for (CharacterPicker character : characterList) {
                        character.setIsCircleChoosen(false);
                    }
                    characterToPick.setIsCircleChoosen(true);
                    choosenCharacter = characterToPick.getCharacter();
                }
            });
        }
        box.setLayoutX(400 - (118 * 2));
        box.setLayoutY(250);
        return box;
    }

    TextField name = new TextField("");

    public RunFunButton createButtonToStart() {
        RunFunButton startButton = new RunFunButton("Start");
        startButton.setLayoutX(400);
        startButton.setLayoutY(500);

        name.setPromptText("Gib deinen Namen ein");
        name.setLayoutX(260);
        name.setLayoutY(100);
        name.setPrefSize(500, 50);
        name.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        characterChooserScene.getPane().getChildren().add(name);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
//
            @Override
            public void handle(ActionEvent event) {
                if (name.getText().isEmpty()) {
                    Label labelresponse = new Label();
                    labelresponse.setLayoutX(200);
                    labelresponse.setLayoutY(175);
                    labelresponse.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                    labelresponse.setText("Du musst einen Namen eingeben, um das Spiel zu starten");
                    characterChooserScene.getPane().getChildren().add(labelresponse);
                } else {
                    if (choosenCharacter != null) {
                        GameViewManager gameManager = new GameViewManager();
                        username = name.getText();
                        gameManager.createNewGame(mainStage, choosenCharacter, username);

                        // Background Musik abspielen
                        String musicFile2 = "src/net/ictcampus/RunFun/sounds/backgroundMusic.mp3"; // For
                                                                                                   // example
                        Media sound2 = new Media(new File(musicFile2).toURI().toString());
                        MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                        mediaPlayer2.play();
                        mainPane.getChildren().clear();

                    } else {
                        Label labelresponse2 = new Label();
                        labelresponse2.setLayoutX(180);
                        labelresponse2.setLayoutY(225);
                        labelresponse2.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
                        labelresponse2.setText(
                                "Du musst einen Charakter auswählen, um das Spiel zu starten");
                        characterChooserScene.getPane().getChildren().add(labelresponse2);

                    }
                }
            }
        });
        return startButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(RunFunButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createScoreButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton() {
        RunFunButton startButton = new RunFunButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(characterChooserScene);

                // Play Sound
                String musicFile = "src/net/ictcampus/RunFun/sounds/loose.mp3"; // For example

                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
            }
        });
    }

    private void createScoreButton() {
        RunFunButton highscoreButton = new RunFunButton("SCORE");
        addMenuButton(highscoreButton);
        highscoreButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(highscoreSubScene);

                Label score = new Label();

                score.setLayoutX(150);
                score.setLayoutY(150);
                score.setFont(Font.font("Verdana", FontWeight.BOLD, 50));

                Label time = new Label();

                time.setLayoutX(500);
                time.setLayoutY(150);
                time.setFont(Font.font("Verdana", FontWeight.BOLD, 50));

                List<Player> player = new ArrayList<>();

                // Objekt erstellen
                FunRunSelect select = new FunRunSelect();
                player = select.selectPlayerDB();

                // Ausgeben der ausgelesenen Spieler
                int podest = 1;

                // Liste mit den Playern
                // Ausgeben der ausgelesenen Spieler
                for (Player p : player) {
                    score.setText(score.getText() + "\n" + podest + ". " + p.getBenutzername());
                    time.setText(time.getText() + "\n" + p.getTime());
                    podest++;
                }

                highscoreSubScene.getPane().getChildren().add(score);
                highscoreSubScene.getPane().getChildren().add(time);

            }
        });
    }

    private void createCreditsButton() {
        RunFunButton creditsButton = new RunFunButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubScene);

                InfoLabel2 credits = new InfoLabel2("Dieses Programm wurde von:");
                credits.setLayoutX(260);
                credits.setLayoutY(100);

                InfoLabel2 mika = new InfoLabel2("Mika");
                mika.setLayoutX(260);
                mika.setLayoutY(200);

                InfoLabel2 und = new InfoLabel2("und");
                und.setLayoutX(260);
                und.setLayoutY(300);

                InfoLabel2 dominik = new InfoLabel2("Dominik");
                dominik.setLayoutX(260);
                dominik.setLayoutY(400);

                InfoLabel2 entwickelt = new InfoLabel2("entwickelt");
                entwickelt.setLayoutX(260);
                entwickelt.setLayoutY(500);

                creditsSubScene.getPane().getChildren().add(credits);
                creditsSubScene.getPane().getChildren().add(mika);
                creditsSubScene.getPane().getChildren().add(und);
                creditsSubScene.getPane().getChildren().add(dominik);

                creditsSubScene.getPane().getChildren().add(entwickelt);
            }
        });
    }

    private void createExitButton() {
        RunFunButton exitButton = new RunFunButton("EXIT");
        addMenuButton(exitButton);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }

        });
    }

    private void createBackground() {
        Image backgroundImage = new Image(
                getClass().getResourceAsStream("resources/background/runfunTitelbildschirm.png"),
                1080, 720, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                null);
        mainPane.setBackground(new Background(background));
    }

    public AudioClip getBangClip() {
        return bangClip;
    }

    public void setBangClip(AudioClip bangClip) {
        this.bangClip = bangClip;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

}
