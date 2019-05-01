package view;

import java.io.File;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.CHARACTER;
import model.Karte;

public class GameViewManager {

    private static final int GAME_WIDTH = 1080;
    private static final int GAME_HEIGHT = 720;
    private final String BACKGROUND_IMAGE = "view/resources/background/backgroundColorGrass.png";
    Random randomPositionGenerator;
    private GridPane gamePane;
    private AnchorPane backPane;
    private StackPane stackPane;
    private VBox box;
    private int[][] blocks = new int[10][22]; // 11 Zeilen und 22 Spalten
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
    private Bilder bilder;
    private static int geschwindigkeit = 5;
    private static int time = 0;
    private Karte karte;
    private static GameViewManager gameViewManager = new GameViewManager();

    private int hoeheBlock = 20;
    private int breiteBlock = 20;

    // Map images
    private Image bildAbschraegungLinks = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_abschraegung_links.png"), 125,
            125, false, false);
    private Image bildErdeOben = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_grass_oben.png"), 125, 125,
            false, false);
    private Image bildErdeRechts = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_grass_rechts.png"), 125, 125,
            false, false);
    private Image bildErde = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_erde.png"), 125, 125, false,
            false);
    private Image bildPunktRechtsOben = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_ecke_rechtsoben.png"), 125, 125,
            false, false);
    private Image bildRutscheUnten = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_rutsche_unten.png"), 125, 125,
            false, false);
    private Image bildErdeLinks = new Image(
            getClass().getResourceAsStream("resources/tiles/runfun_grass_links.png"), 125, 125,
            false, false);

    private ImageView imv;

    private int posX = getBreiteBlock();
    private int posY = getHoeheBlock() * 7 - 1;
    private int beschleunigungX = 0;
    private double beschleunigungY = 2;

    private int anfangKarte = 0;
    private int anzahlBloecke = 21;
    private int mitschiebenKarte = 0;
    private int laengeKartenArray;
    private int zeit = 0;
    private boolean zeitLaeuft = false;

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
                for (String[] zeile : getKarte().getKarteListe()) {
                    setLaengeKartenArray(zeile.length);
                    int zeilenNummer = 0;
//                    System.out.println(zeilenNummer + " " + spaltenNummer);
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
                            System.out.println(zeilenNummer + " " + spaltenNummer);

                            break;
                        case "145":
                            imv = new ImageView(bildPunktRechtsOben);
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
                        case "138":
                            imv = new ImageView(bildErdeLinks);
                            gamePane.add(imv, zeilenNummer, spaltenNummer);
                            blocks[spaltenNummer][zeilenNummer] = 138;
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
                for (int zeile = 0; zeile < blocks.length; zeile++) {
                    System.out.print("Zeile " + zeile + ": ");
                    for (int spalte = 0; spalte < blocks[zeile].length; spalte++)
                        System.out.print(blocks[zeile][spalte] + " ");
                    System.out.println();
                }

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
        gameStage = new Stage();
        gameStage.setFullScreen(true);
        gameStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        gameStage.setResizable(true);
        gameStage.setScene(gameScene);

    }

    /**
     * Neues Game erstellen und alles starten.
     * 
     * @param menuStage
     * @param choosenCharacter
     */
    public void createNewGame(Stage menuStage, CHARACTER choosenCharacter) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createCharacter(choosenCharacter);
        createBackground();
//      createGameElements(choosenCharacter);
        createGameLoop();
        gameStage.show();

        String musicFile2 = "src/sounds/backgroundMusic.mp3"; // For example

        Media sound2 = new Media(new File(musicFile2).toURI().toString());
        MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
        mediaPlayer2.play();

    }

    /**
     * Durchgehend Character rennen lassen
     */
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                moveCharacter();
                characterRun();
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
        character = new ImageView(choosenCharacter.getUrl());
    }

//    private void createBotPlayers(CHARACTER choosenCharacter) {
//
//    }
//
//    private void checkIfTheCharacterIsBeforeAMountainAndEnable() {
//
//    }

    /**
     * Character rennen lassen
     */
    private void characterRun() {
        if (angle < 30) {
            angle += 5;
        }

        character.setRotate(angle);
//      moveBackground();

        // -------------------------------------------------

        double modY = character.getLayoutY() % 125;
        character.setLayoutY(character.getLayoutY() + modY);

        int column = (int) (character.getLayoutX() / 125);
        int  row= (int) (character.getLayoutY() / 125);
        boolean foundGround = false;
        if (blocks[row+1][column] != 154 || blocks[row+1][column] != 133) {
            for (int i = 1; i < 9; i++) {
                if (blocks[row+1][column] != 154 || blocks[row+1][column] != 133) {
                    
                
                if (row+i<10 && !foundGround ) {
                    if (blocks[row + i][column] == 154 || blocks[row + i][column] == 133) {
                        foundGround = true;
                         character.setLayoutY(character.getLayoutY() + 125);
                        character.setLayoutX(character.getLayoutX() + 7);
    
                    }
                }
                }
            }
        } else {
            character.setLayoutX(character.getLayoutX() + 7);

        }

        // -------------------------------------------------
//        if (getAnfangKarte() < getLaengeKartenArray() - getAnzahlBloecke()) {
//            if (getPosX() > 8 * getBreiteBlock()) {
//                setMitschiebenKarte(getMitschiebenKarte() - getGeschwindigkeit());
//                // Verhindert das permanente Mitschieben der Karte, wenn der
//                // Spieler die Mitte erreicht hat.
//                setPosX(getPosX() - getGeschwindigkeit());
//                // Wenn ein ganzer neuer Block dargestellt wurde...
//                if (getMitschiebenKarte() <= -120) {
//                    setMitschiebenKarte(getMitschiebenKarte() + 120);
//                    setAnfangKarte(getAnfangKarte() + 1);
//                }
//            }
//        }
    }

    /**
     * Character bewegen
     */
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

    /**
     * Hintergrund erstellen
     */
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

        gridPane2.setLayoutX(1024);
        Pane pane = new Pane();
        Pane pane2 = new Pane();
        pane.getChildren().addAll(gridPane1, gridPane2);

        pane2.getChildren().add(character);
        stackPane.getChildren().addAll(pane, gamePane, pane2);

    }

    @SuppressWarnings("unused")
    private void moveBackground() {
        gridPane1.setLayoutX(gridPane1.getLayoutX() - 10);
        gridPane2.setLayoutX(gridPane2.getLayoutX() - 10);
        if (gridPane1.getLayoutX() < -1024) {
            gridPane1.setLayoutX(0);
        }

        if (gridPane2.getLayoutX() < 0) {
            gridPane2.setLayoutX(1024);
        }
    }

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

    public Bilder getBilder() {
        return bilder;
    }

    public void setBilder(Bilder bilder) {
        this.bilder = bilder;
    }

    public static GameViewManager getInstance() {
        return gameViewManager;
    }

    public int getAnfangKarte() {
        return anfangKarte;
    }

    public void setBeschleunigungX(int beschleunigungX) {
        this.beschleunigungX = beschleunigungX;
    }

    public void setBeschleunigungY(double beschleunigungY) {
        this.beschleunigungY = beschleunigungY;
    }

    public void setZeit(int zeit) {
        this.zeit = zeit;
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

    public int getZeit() {
        return zeit;
    }

    public void setZeitLaeuft(boolean zeitLaeuft) {
        this.zeitLaeuft = zeitLaeuft;
    }

    public boolean isZeitLaeuft() {
        return zeitLaeuft;
    }

//    public Image getBildTrollface() {
//        return bildTrollface;
//    }
//
//    public void setBildNachthimmelSolid(Image bildNachthimmelSolid) {
//        this.bildNachthimmelSolid = bildNachthimmelSolid;
//    }
//
//    public Image getBildNachthimmel() {
//        return bildNachthimmel;
//    }

    public int getMitschiebenKarte() {
        return mitschiebenKarte;
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

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
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

    public int getBeschleunigungX() {
        return beschleunigungX;
    }

    public double getBeschleunigungY() {
        return beschleunigungY;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setMitschiebenKarte(int mitschiebenKarte) {
        this.mitschiebenKarte = mitschiebenKarte;
    }

    public void setAnfangKarte(int anfangKarte) {
        this.anfangKarte = anfangKarte;
    }
}
