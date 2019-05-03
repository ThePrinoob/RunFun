package net.ictcampus.RunFun.view;

// ~~~ Imports ~~~
import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.ictcampus.RunFun.javaDB.RunFunInsert;
import net.ictcampus.RunFun.model.CHARACTER;
import net.ictcampus.RunFun.model.Karte;

public class GameViewManager {
    // ~~~ Instanzvariabeln ~~~
    private int GAME_WIDTH = 1920;
    private int GAME_HEIGHT = 1080;
    private String BACKGROUND_IMAGE = "resources/background/backgroundColorGrass.png";
    private StackPane stackPane;
    private GridPane gamePane;
    private Pane nameBox;
    private Pane timeBox;
    private Label username2;
    private int[][] blocks = new int[10][150]; // 11 Zeilen und 22 Spalten
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
    private double geschwindigkeit = 5;
    private static int time = 0;
    private Karte karte;
    private static GameViewManager gameViewManager = new GameViewManager();
    private ViewManager viewManager;

    private int hoeheBlock = 125;
    private int breiteBlock = 125;

    // Map images
    private Image bildAbschraegungLinks = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_abschraegung_links.png"),
            hoeheBlock, breiteBlock, false, false);
    private Image bildErdeOben = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_grass_oben.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildErdeRechts = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_grass_rechts.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildErde = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_erde.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildPunktRechtsOben = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_ecke_rechtsoben.png"),
            hoeheBlock, breiteBlock, false, false);
    private Image bildPunktLinksOben = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_ecke_linksoben.png"), hoeheBlock,
            breiteBlock, false, false);

    private Image bildRutscheUnten = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_rutsche_unten.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildRutscheHoch = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_rutsche_hoch.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildErdeLinks = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_grass_links.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildZiel = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_ziel.png"), hoeheBlock,
            breiteBlock, false, false);
    private Image bildTreppeLinksOben = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_links_oben.png"), hoeheBlock,
            breiteBlock, false, false);

    private ImageView imv;

    private int anfangKarte = 0;
    private int anzahlBloecke = 100;
    private int laengeKartenArray;
    private static int zeit = 0;
    private boolean zeitLaeuft = false;
    private boolean isWaiting;
    private boolean zielErreicht;
    private String username;

    // ~~~ Methoden ~~~
    public GameViewManager() {
        setKarte(new Karte());
        initializeStage();
        createKeyListeners();
        buildMap();
    }

    /**
     * Map erstellen und Blöcke anzeigen
     */
    private void buildMap() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                int spaltenNummer = 0;
                gamePane.getChildren().clear();
                for (String[] zeile : getKarte().getKarteListe()) {
                    setLaengeKartenArray(zeile.length);
                    int zeilenNummer = 0;
                    for (int i = getAnfangKarte(); i < getAnfangKarte() + getAnzahlBloecke(); i++) {
                        String block = zeile[i];
                        switch (block) {
                        // Blöcke Fest
                        case "-20":
                            imv = new ImageView(bildAbschraegungLinks);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = -20;
                            break;
                        case "131":
                            imv = new ImageView(bildErdeRechts);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 131;
                            break;
                        case "164":
                            imv = new ImageView(bildErde);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 164;
                            break;
                        case "132":
                            imv = new ImageView(bildPunktRechtsOben);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 132;
                            break;
                        case "134":
                            imv = new ImageView(bildPunktLinksOben);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 145;
                            break;
                        case "154":
                            imv = new ImageView(bildErdeOben);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 154;
                            break;
                        case "133":
                            imv = new ImageView(bildRutscheUnten);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 133;
                            break;
                        case "174":
                            imv = new ImageView(bildRutscheHoch);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 174;
                            break;
                        case "138":
                            imv = new ImageView(bildErdeLinks);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 138;
                            break;
                        case "150":
                            imv = new ImageView(bildZiel);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 150;
                            break;
                        case "166":
                            imv = new ImageView(bildTreppeLinksOben);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 166;
                            break;

                        // Blöcke Decko
                        case "000":
                            break;

                        default:

                        }
                        zeilenNummer++;
                    }
                    spaltenNummer++;
                }
                // print Blocks
//                for (int zeile = 0; zeile < blocks.length; zeile++) {
//                    System.out.print("Zeile " + zeile + ": ");
//                    for (int spalte = 0; spalte < blocks[zeile].length; spalte++)
//                        System.out.print(blocks[zeile][spalte] + " ");
//                    System.out.println();
//                }

            }

        }.start();

    }

    /**
     * Key Listener erstellen
     */
    private void createKeyListeners() {

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                isUpKeyPressed = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed = true;
            } else if (event.getCode() == KeyCode.ESCAPE) {
                gameStage.close();
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

    /**
     * Game Fenster initalisieren
     */
    private void initializeStage() {
        gamePane = new GridPane();
        gamePane.setMaxSize(1920, 1080);
        gamePane.setMinSize(1920, 1080);
        stackPane = new StackPane();
        gameScene = new Scene(stackPane, GAME_WIDTH, GAME_HEIGHT);
        gameScene.setCursor(Cursor.NONE);
        gameStage = new Stage();
        gameStage.setFullScreen(true);
        gameStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        gameStage.setResizable(false);
        gameStage.setScene(gameScene);

    }

    /**
     * Neues Game erstellen und alles starten.
     * 
     * @param menuStage
     * @param choosenCharacter
     */
    public void createNewGame(Stage menuStage, CHARACTER choosenCharacter, String username) {
        this.menuStage = menuStage;
        this.menuStage.close();
        createCharacter(choosenCharacter);
        createScene();
        createGameLoop(username);
        gameStage.show();
        // Musik
        // Background
        String musicFile2 = "src/net/ictcampus/RunFun/sounds/backgroundMusic.mp3";
        Media sound2 = new Media(new File(musicFile2).toURI().toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
        mediaPlayer2.play();

        Label username2 = new Label();
        username2.setLayoutX(0);
        username2.setLayoutY(0);
        username2.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        username2.setText(username);
        nameBox.getChildren().add(username2);

    }

    /**
     * Character steuern
     */
    private void createGameLoop(String username) {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long arg0) {
                characterRun(username);
                jumpCharacter();
                sneakCharacter();
            }
        };
        gameTimer.start();
    }

    /**
     * Character
     * 
     * @param choosenCharacter
     */
    private void createCharacter(CHARACTER choosenCharacter) {
        character = new ImageView(
                new Image(getClass().getResourceAsStream(choosenCharacter.getUrl())));
        character.prefWidth(125);
        character.prefHeight(125);
    }

    /**
     * Character rennen lassen
     */
    private void characterRun(String username) {
        if (!zielErreicht) {
            if (angle < 30) {
                angle += 5;
            }
            zeitLaeuft = true;
            if (isZeitLaeuft()) {
                timeBox.getChildren().clear();
                setZeit(getZeit() + 4);
                Label timer = new Label();
                timer.setLayoutX(0);
                timer.setLayoutY(50);
                timer.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
                timer.setText((getZeit() / 100 / 60) + ":" + ((getZeit() / 100) % 60) + "."
                        + (getZeit() % 100) / 10);
                timeBox.getChildren().add(timer);
            }
            character.setRotate(angle);

            // Boden und wand erkennen

            int column = (int) ((character.getLayoutX() + Math.abs(gamePane.getLayoutX())
                    + (character.getBoundsInLocal().getWidth()) / 4) / 125);
            int row = (int) (character.getLayoutY() / 125);
            boolean foundGround = false;
            if (blocks[row][column + 1] == 150) {
                zielErreicht = true;
                RunFunInsert dao = new RunFunInsert();
                //Funktioniert nicht richtig
                String finishTime = (getZeit() / 100 / 60) + ":" + ((getZeit() / 100) % 60) + "."
                        + (getZeit() % 100) / 10;
                
                dao.insertPlayerDB(username, finishTime);
            } else if (blocks[row + 1][column] != 154
                    || blocks[row + 1][column] != 133 && blocks[row][column + 1] == 000) {
                for (int i = 1; i < 9; i++) {
                    column = (int) ((character.getLayoutX() + Math.abs(gamePane.getLayoutX())
                            + (character.getBoundsInLocal().getWidth()) / 4) / 125);
                    row = (int) (character.getLayoutY() / 125);
                    if (row + i < 10 && !foundGround) {
                        if (blocks[row + i][column] == 154 || blocks[row + i][column] == 133 || blocks[row + 1][column] == 174) {
                            if (blocks[row + 1][column] == 154 || blocks[row + 1][column] == 133 || blocks[row + 1][column] == 174) {
                                foundGround = true;
                            } else {
                                if (!isWaiting) {
                                    character.setLayoutY(character.getLayoutY() + 125);
                                }
                            }
                        }
                    }
                }
                // Garage
                // || blocks[row + 1][column] != 174
                //|| blocks[row + 1][column] == 174
                //  || blocks[row + 1][column] == 174
                
                // Map bewegen
                if (character.getLayoutX() + 1 >= (stackPane.getWidth() / 2)) {
                    gamePane.setLayoutX(gamePane.getLayoutX() - getGeschwindigkeit());
                } else {
                    character.setLayoutX(character.getLayoutX() + getGeschwindigkeit());

                }
                moveBackground();
                // RunFunInsert dao = new RunFunInsert();
                // dao.insertPlayerDB(getViewManager().username);
            }

            // -------------------------------------------------

        } else {
            gameStage.close();
        }
    }

    /**
     * Character springen lassen
     */
    private void jumpCharacter() {
        if (!isWaiting) {
            if (isUpKeyPressed && !isDownKeyPressed) {
                this.setIsWaiting(true);
                character.setLayoutY(character.getLayoutY() - 125);
                // macht einen Delay, dass er nicht ganz schnell nachinander springen kann.
                PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

                delay.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        setIsWaiting(false);
                    }
                });
                delay.play();
            }
        }
    }

    /**
     * Character sneaken lassen
     */
    private void sneakCharacter() {
        if (!isWaiting) {
            if (isDownKeyPressed && !isUpKeyPressed) {
                this.setIsWaiting(true);
                // Character halb so gross
                character.setFitHeight(62.5);
                character.setLayoutY(character.getLayoutY() + 62.5);
                angle = -30;
                character.setRotate(angle);
                // macht einen Delay, dass er nicht ganz schnell nachinander springen kann.
                PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
                delay.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        setIsWaiting(false);
                        character.setFitHeight(125);
                        character.setLayoutY(character.getLayoutY() - 62.5);
                    }
                });
                delay.play();
            }
        }
    }

    /**
     * Hintergrund erstellen
     */
    private void createScene() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        nameBox = new Pane();
        timeBox = new Pane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(
                    new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE)));
            ImageView backgroundImage2 = new ImageView(
                    new Image(getClass().getResourceAsStream(BACKGROUND_IMAGE)));
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutX(1024);
        Pane pane = new Pane();
        Pane pane2 = new Pane();
        Pane pane3 = new Pane();
        pane.getChildren().addAll(gridPane1, gridPane2);
        pane3.getChildren().add(gamePane);
        pane2.getChildren().add(character);

        character.setLayoutX(125);
        character.setLayoutY(0);
        stackPane.getChildren().addAll(pane, pane3, pane2, nameBox, timeBox);
    }

    /**
     * Hintergrund bewegen und Hintergrund zurücksetzen
     */
    private void moveBackground() {
        // Hintergrund bewegen
        if (character.getLayoutX() + 1 >= (stackPane.getWidth() / 2)) {
            gridPane1.setLayoutX(gridPane1.getLayoutX() - getGeschwindigkeit() * 2);
            gridPane2.setLayoutX(gridPane2.getLayoutX() - getGeschwindigkeit() * 2);
        } else {
            gridPane1.setLayoutX(gridPane1.getLayoutX() - getGeschwindigkeit());
            gridPane2.setLayoutX(gridPane2.getLayoutX() - getGeschwindigkeit());
        }
        // Hintegrundzurücksetzen
        if (gridPane1.getLayoutX() < -1024) {
            gridPane1.setLayoutX(0);
        }

        if (gridPane2.getLayoutX() < 0) {
            gridPane2.setLayoutX(1024);
        }
    }

    // ~~~ Getter && Setter ~~~
    public GridPane getGamePane() {
        return gamePane;
    }

    public void setGamePane(GridPane gamePane) {
        this.gamePane = gamePane;
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void setGameScene(Scene gameScene) {
        this.gameScene = gameScene;
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public void setMenuStage(Stage menuStage) {
        this.menuStage = menuStage;
    }

    public static GameViewManager getInstance() {
        return gameViewManager;
    }

    public int getAnfangKarte() {
        return anfangKarte;
    }

    public void setZeit(int zeit) {
        GameViewManager.zeit = zeit;
    }

    public void setKarte(Karte karte) {
        this.karte = karte;
    }

    public static int getTime() {
        return time;
    }

    public static void setPoints(int time) {
        GameViewManager.time = time;
    }

    public static int getZeit() {
        return zeit;
    }

    public void setZeitLaeuft(boolean zeitLaeuft) {
        this.zeitLaeuft = zeitLaeuft;
    }

    public boolean isZeitLaeuft() {
        return zeitLaeuft;
    }

    public int getHoeheBlock() {
        return hoeheBlock;
    }

    public void setHoeheBlock(int hoeheBlock) {
        this.hoeheBlock = hoeheBlock;
    }

    public int getBreiteBlock() {
        return breiteBlock;
    }

    public void setBreiteBlock(int breiteBlock) {
        this.breiteBlock = breiteBlock;
    }

    public int getAnzahlBloecke() {
        return anzahlBloecke;
    }

    public Karte getKarte() {
        return karte;
    }

    public void setLaengeKartenArray(int laengeKartenArray) {
        this.laengeKartenArray = laengeKartenArray;
    }

    public int getLaengeKartenArray() {
        return laengeKartenArray;
    }

    public double getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setAnfangKarte(int anfangKarte) {
        this.anfangKarte = anfangKarte;
    }

    public Label getUsername2() {
        return username2;
    }

    public void setUsername2(Label username2) {
        this.username2 = username2;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isWaiting() {
        return isWaiting;
    }

    public void setIsWaiting(boolean isWaiting) {
        this.isWaiting = isWaiting;
    }

    public boolean isZielErreicht() {
        return zielErreicht;
    }

    public void setZielErreicht(boolean zielErreicht) {
        this.zielErreicht = zielErreicht;
    }
}
