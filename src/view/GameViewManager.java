package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.CHARACTER;
import model.Karte;

public class GameViewManager {

    private static final int GAME_WIDTH = 1080;
    private static final int GAME_HEIGHT = 720;
    private final String BACKGROUND_IMAGE = "view/resources/background/backgroundColorGrass.png";
    Random randomPositionGenerator;
    private BorderPane gamePane;
    private Canvas canvas;
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
    private Zeichnung zeichnung;
    private Karte karte;
    private static GameViewManager gameViewManager = new GameViewManager();
    
    private int hoeheBlock = 120;
    private int breiteBlock = 120;
    
    
    
    
    
    private Image bildNachthimmel = gameViewManager.getBilder().getBildNachthimmel()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildNachthimmelSolid = getBildNachthimmel();
    private Image bildTaghimmel = gameViewManager.getBilder().getBildTaghimmel()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildErde = gameViewManager.getBilder().getBildErde().getScaledInstance(getBreiteBlock(),
            getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildStein = gameViewManager.getBilder().getBildStein()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildFeuer = gameViewManager.getBilder().getBildFeuer()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWasser = gameViewManager.getBilder().getBildWasser()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWasserGanz = gameViewManager.getBilder().getBildWasserGanz()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildFlagge = gameViewManager.getBilder().getBildFlagge()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildKFCUnten = gameViewManager.getBilder().getBildKFCUnten()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildKFCOben = gameViewManager.getBilder().getBildKFCOben()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWueste = gameViewManager.getBilder().getBildWueste()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildPalme = gameViewManager.getBilder().getBildPalme()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff1 = gameViewManager.getBilder().getBildRaumschiff1()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff2 = gameViewManager.getBilder().getBildRaumschiff2()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff3 = gameViewManager.getBilder().getBildRaumschiff3()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff4 = gameViewManager.getBilder().getBildRaumschiff4()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff5 = gameViewManager.getBilder().getBildRaumschiff5()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff6 = gameViewManager.getBilder().getBildRaumschiff6()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff7 = gameViewManager.getBilder().getBildRaumschiff7()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWeltall = gameViewManager.getBilder().getBildWeltall()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildMond = gameViewManager.getBilder().getBildMond().getScaledInstance(getBreiteBlock(),
            getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildTrollface = gameViewManager.getBilder().getBildTrollface()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildLinks = gameViewManager.getBilder().getBildLinks()
            .getScaledInstance(getBreiteBlock(), getBreiteBlock(), Image.SCALE_SMOOTH);
    private Image bildRechts = gameViewManager.getBilder().getBildRechts()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildLeertaste = gameViewManager.getBilder().getBildLeertaste()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildEsc = gameViewManager.getBilder().getBildEsc().getScaledInstance(getBreiteBlock(),
            getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildFigur;
    private int posX = getBreiteBlock();
    private int posY = getHoeheBlock() * 7 - 1;
    private int beschleunigungX = 0;
    private double beschleunigungY = 2;

    private int anfangKarte = 0;
    private int anzahlBloecke = 17;
    private int mitschiebenKarte = 0;
    private int laengeKartenArray;
    private int zeit = 0;
    private boolean zeitLaeuft = false;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        setBilder(new Bilder());
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                Graphics g = null;
                int spaltenNummer = 0;
                for (String[] zeile : getKarte().getKarteListe()) {
                    setLaengeKartenArray(zeile.length);
                    int zeilenNummer = 0;
                    for (int i = getAnfangKarte(); i < getAnfangKarte() + getAnzahlBloecke(); i++) {
                        String block = zeile[i];
                        switch (block) {
                        // Blöcke Deko
                        case "-20":
                            canvas.getGraphics().drawImage(bildEsc, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-19":
                            g.drawImage(bildLeertaste, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-18":
                            g.drawImage(bildRechts, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-17":
                            g.drawImage(bildLinks, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-16":
                            g.drawImage(bildWeltall, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-15":
                            g.drawImage(bildRaumschiff7, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-14":
                            g.drawImage(bildRaumschiff6, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-13":
                            g.drawImage(bildRaumschiff5, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-12":
                            g.drawImage(bildRaumschiff4, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-11":
                            g.drawImage(bildRaumschiff3, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-10":
                            g.drawImage(bildRaumschiff2, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-09":
                            g.drawImage(bildRaumschiff1, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-08":
                            g.drawImage(bildPalme, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-07":
                            g.drawImage(bildFeuer, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-06":
                            g.drawImage(bildKFCOben, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-05":
                            g.drawImage(bildWasserGanz, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-04":
                            g.drawImage(bildWasser, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-03":
                            g.drawImage(bildMond, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-02":
                            g.drawImage(bildNachthimmel, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "-01":
                            g.drawImage(bildTaghimmel, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;

                        // Blöcke fest
                        case "001":
                            g.drawImage(bildErde, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "002":
                            g.drawImage(bildStein, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "003":
                            g.drawImage(bildWueste, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "004":
                            g.drawImage(bildFlagge, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "005":
                            g.drawImage(bildTrollface, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "006":
                            g.drawImage(bildKFCUnten, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        case "007":
                            g.drawImage(bildNachthimmelSolid, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                            break;
                        default:
                            g.drawImage(bildStein, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
                        }
                        zeilenNummer++;
                    }
                    spaltenNummer++;
                }
                g.drawImage(bildFigur, getPosX(), getPosY(), null);
                g.setFont(new Font("Orbitron", Font.PLAIN, 50));
                g.setColor(Color.yellow);
                g.drawString((getZeit() / 100 / 60) + ":" + ((getZeit() / 100) % 60) + "." + (getZeit() % 100) / 10, 20, 40);
            }
        }.start();
    }
    public void zeichne(int i) {
        gamePane.setVisible(false);
        setZeichnung(new Zeichnung());
        getZeichnung();

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
        gamePane = new BorderPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

    public void createNewGame(Stage menuStage, CHARACTER choosenCharacter) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createCharacter(choosenCharacter);
//      createGameElements(choosenCharacter);
        
        zeichne(1);
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
        character.setLayoutY(GAME_HEIGHT - 130);
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

    public BorderPane getGamePane() {
        return gamePane;
    }

    public void setGamePane(BorderPane gamePane) {
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
    public void setZeichnung(Zeichnung zeichnung) {
        this.zeichnung = zeichnung;
    }

    public Zeichnung getZeichnung() {
        return zeichnung;
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

    public int getZeit() {
        return zeit;
    }

    public void setZeitLaeuft(boolean zeitLaeuft) {
        this.zeitLaeuft = zeitLaeuft;
    }

    public boolean isZeitLaeuft() {
        return zeitLaeuft;
    }

    public Image getBildTrollface() {
        return bildTrollface;
    }

    public void setBildNachthimmelSolid(Image bildNachthimmelSolid) {
        this.bildNachthimmelSolid = bildNachthimmelSolid;
    }

    public Image getBildNachthimmel() {
        return bildNachthimmel;
    }

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
}
