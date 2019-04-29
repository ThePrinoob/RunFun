package view;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
	private Image bildAbschraegungLinks = new Image(getClass().getResourceAsStream("resources/tiles/runfun_abschraegung_links.png"));
	private Image bildErdeOben = new Image(getClass().getResourceAsStream("resources/tiles/runfun_grass_oben.png"));
	private Image bildErdeRechts = new Image(getClass().getResourceAsStream("resources/tiles/runfun_grass_rechts.png"));
	private Image bildErde = new Image(getClass().getResourceAsStream("resources/tiles/runfun_erde.png"));
	private Image bildPunktRechtsOben = new Image(getClass().getResourceAsStream("resources/tiles/runfun_ecke_rechtsoben.png"));
	private Image bildBg = new Image(getClass().getResourceAsStream("resources/tiles/runfun_bg.png"));
	private Image bildRutscheUnten = new Image(getClass().getResourceAsStream("resources/tiles/runfun_rutsche_unten.png"));
	private Image bildErdeLinks = new Image(getClass().getResourceAsStream("resources/tiles/runfun_grass_links.png"));
	
	private ImageView imv;

	private int posX = getBreiteBlock();
	private int posY = getHoeheBlock() * 7 - 1;
	private int beschleunigungX = 0;
	private double beschleunigungY = 2;

	private int anfangKarte = 0;
	private int anzahlBloecke = 20;
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

	private void buildMap() {
		new AnimationTimer() {
			@Override
			public void handle(long now) {

				int spaltenNummer = 0;
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
							break;
                        case "131":
                        	imv = new ImageView(bildErdeRechts);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer);
                            break;
                        case "164":
                        	imv = new ImageView(bildErde);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer);
                            break;
                        case "145":
                        	imv = new ImageView(bildPunktRechtsOben);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer);
                            break;
                        case "154":
                        	imv = new ImageView(bildErdeOben);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer);
                            break;
                        case "133":
                        	imv = new ImageView(bildRutscheUnten);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer);
                            break;
                        case "138":
                        	imv = new ImageView(bildErdeLinks);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer);
                            break;
                            
                        // Blöcke Decko
                        case "0":
                            break; 
                       
                            
                            
                        default:

						}
						zeilenNummer++;
					}
					spaltenNummer++;
				}
//                g.drawImage(bildFigur, getPosX(), getPosY(), null);
//                g.setFont(new Font("Orbitron", Font.PLAIN, 50));
//                g.setColor(Color.yellow);
//                g.drawString((getZeit() / 100 / 60) + ":" + ((getZeit() / 100) % 60) + "." + (getZeit() % 100) / 10, 20, 40);
			}
		}.start();
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
		gamePane = new GridPane();
		backPane = new AnchorPane();
		VBox box = new VBox();
		gameScene = new Scene(backPane, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}

	public void createNewGame(Stage menuStage, CHARACTER choosenCharacter) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBackground();
		createCharacter(choosenCharacter);
//      createGameElements(choosenCharacter);
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
		character.setFitHeight(70);
		character.setFitWidth(70);
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
		VBox box = new VBox();
		box.getChildren().add(gamePane);
		backPane.getChildren().addAll(gridPane1, gridPane2, box);
		
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
}
