package view;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.CHARACTER;

public class GameViewManager {

    private static final int GAME_WIDTH = 1080;
    private static final int GAME_HEIGHT = 720;
    private final String BACKGROUND_IMAGE = "view/resources/background/backgroundColorGrass.png";
    Random randomPositionGenerator;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private ImageView character;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private static int time = 0;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
    }

    public static int getTime() {
        return time;
    }

    public static void setPoints(int time) {
        GameViewManager.time = time;
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                isUpKeyPressed = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed = true;
            }
        });

        gameScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.UP) {
                isUpKeyPressed = false;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed = false;
            }
        });

    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

    public void createNewGame(Stage menuStage, CHARACTER choosenCharacter) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createCharacter(choosenCharacter);
//        createGameElements(choosenCharacter);

        createGameLoop();
        gameStage.show();
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                // TODO Auto-generated method stub
                moveCharacter();
                characterRun();
            }
        };
        gameTimer.start();
    }

    private void createCharacter(CHARACTER choosenCharacter) {
        character = new ImageView(choosenCharacter.getUrl());
        character.setLayoutX(0);
        character.setLayoutY(0);
        gamePane.getChildren().add(character);
    }

    private void createBotPlayers(CHARACTER choosenCharacter) {

    }

    private void moveGameElements() {

    }

    private void checkIfTheCharacterIsBeforeAMountainAndEnable() {

    }

    private void characterRun() {
        if (angle < 30) {
            angle += 5;
        }
        character.setRotate(angle);
        if (character.getLayoutX() < 950) {
            character.setLayoutX(character.getLayoutX() + 7);
        }
    }

    private void moveCharacter() {

        if (isUpKeyPressed && !isDownKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            character.setRotate(angle);
            if (character.getLayoutX() > -20) {
                character.setLayoutX(character.getLayoutX() - 7);
            }
        }
        if (isDownKeyPressed && !isUpKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            character.setRotate(angle);
            if (character.getLayoutX() < 522) {
                character.setLayoutX(character.getLayoutX() + 7);
            }

        }
        if (!isUpKeyPressed && !isDownKeyPressed || isUpKeyPressed && isDownKeyPressed) {
            if (angle < 0)
                angle += 5;
            else if (angle > 0)
                angle -= 5;
            character.setRotate(angle);
        }

    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 1);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 1);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

}
