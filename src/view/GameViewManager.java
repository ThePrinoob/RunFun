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

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;
    private final static String METEOR_BROWN_IMAGE = "/ShipChooser/meteor_brown.png";
    private final static String METEOR_GRAY_IMAGE = "/ShipChooser/meteor_gray.png";
    private final String BACKGROUND_IMAGE = "/blue.png";
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

    public void createNewGame(Stage menuStage, CHARACTER chosenCharacter) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createCharacter(chosenCharacter);
        createGameElements(chosenCharacter);

//        createGameLoop();
        gameStage.show();
    }

    private void createGameElements(CHARACTER chosenCharacter) {
        playerLife = 2;
        star = new ImageView(GOLD_STAR_IMAGE);
        setNewElementPosition(star);
        gamePane.getChildren().add(star);
        playerLives = new ImageView[3];

        for (int i = 0; i < playerLives.length; i++) {
            playerLives[i] = new ImageView(chosenCharacter.getUrlLife());
            playerLives[i].setLayoutX(455 + (i * 50));
            playerLives[i].setLayoutY(80);
            gamePane.getChildren().add(playerLives[i]);
        }

        brownMeteors = new ImageView[5];
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }

        grayMeteors = new ImageView[5];
        for (int i = 0; i < grayMeteors.length; i++) {
            grayMeteors[i] = new ImageView(METEOR_GRAY_IMAGE);
            setNewElementPosition(grayMeteors[i]);
            gamePane.getChildren().add(grayMeteors[i]);
        }
    }

    private void moveGameElements() {
//        gameAsteroidsElement(brownMeteors);
//        gameAsteroidsElement(grayMeteors);
//
//        star.setLayoutY(star.getLayoutY() + 7);
//        star.setRotate(star.getRotate() + 3);

    }


    private void checkIfTheCharacterIsBeforeAMountainAndEnable() {

//        if (star.getLayoutY() > 1200) {
//            setNewElementPosition(star);
//        }
//
//        for (ImageView brownMeteors : brownMeteors) {
//            if (brownMeteors.getLayoutY() > 900)
//                setNewElementPosition(brownMeteors);
//        }
//
//        for (ImageView grayMeteors : grayMeteors) {
//            if (grayMeteors.getLayoutY() > 900)
//                setNewElementPosition(grayMeteors);
//        }
    }

    private void setNewElementPosition(ImageView image) {
        image.setLayoutX(randomPositionGenerator.nextInt(580));
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
    }

//    private void createCharacter(CHARACTER chosenCharacter) {
//        ship = new ImageView(chosenCharacter.getUrlShip());
//        ship.setLayoutX(GAME_WIDTH >> 1);
//        ship.setLayoutY(GAME_HEIGHT - 90);
//        gamePane.getChildren().add(ship);
//    }



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
