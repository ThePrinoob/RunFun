package view;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Karte;
import view.GameViewManager;

public class Map {
	
	// Map images
	/*
	 * Provisorisch, falls verlagerung
	 */
	private Image bildEsc = new Image(getClass().getResourceAsStream("textures/housi_esc.png"));
	private Image bildErde = new Image(getClass().getResourceAsStream("textures/housi_erde.png"));
	private int anfangKarte = 0;
	private int anzahlBloecke = 1;
	private int mitschiebenKarte = 0;
	private int laengeKartenArray;
	private Karte karte;
	private GameViewManager gameViewManager = getGameViewManager().getInstance();
	private GridPane gamePane = gameViewManager.getGamePane();


	private ImageView imv;
	
	
	public Map() {
		buildMap();
		
	}
	public void buildMap() {
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
						// Blöcke Deko
						case "-20":
							imv = new ImageView(bildEsc);
							gamePane.add(imv, zeilenNummer, spaltenNummer);
							System.out.println(zeilenNummer + spaltenNummer);
							break;
                        case "-19":
                        	imv = new ImageView(bildErde);
                        	gamePane.add(imv, zeilenNummer, spaltenNummer+1);
                            break;
//                        case "-18":
//                            g.drawImage(bildRechts, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-17":
//                            g.drawImage(bildLinks, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-16":
//                            g.drawImage(bildWeltall, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-15":
//                            g.drawImage(bildRaumschiff7, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-14":
//                            g.drawImage(bildRaumschiff6, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-13":
//                            g.drawImage(bildRaumschiff5, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-12":
//                            g.drawImage(bildRaumschiff4, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-11":
//                            g.drawImage(bildRaumschiff3, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-10":
//                            g.drawImage(bildRaumschiff2, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-09":
//                            g.drawImage(bildRaumschiff1, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-08":
//                            g.drawImage(bildPalme, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-07":
//                            g.drawImage(bildFeuer, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-06":
//                            g.drawImage(bildKFCOben, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-05":
//                            g.drawImage(bildWasserGanz, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-04":
//                            g.drawImage(bildWasser, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-03":
//                            g.drawImage(bildMond, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-02":
//                            g.drawImage(bildNachthimmel, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "-01":
//                            g.drawImage(bildTaghimmel, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//
//                        // Blöcke fest
//                        case "001":
//                            g.drawImage(bildErde, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "002":
//                            g.drawImage(bildStein, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "003":
//                            g.drawImage(bildWueste, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "004":
//                            g.drawImage(bildFlagge, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "005":
//                            g.drawImage(bildTrollface, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "006":
//                            g.drawImage(bildKFCUnten, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        case "007":
//                            g.drawImage(bildNachthimmelSolid, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
//                            break;
//                        default:
//                            g.drawImage(bildStein, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(), spaltenNummer * getHoeheBlock(), null);
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


	public Image getBildEsc() {
		return bildEsc;
	}


	public void setBildEsc(Image bildEsc) {
		this.bildEsc = bildEsc;
	}


	public Image getBildErde() {
		return bildErde;
	}


	public void setBildErde(Image bildErde) {
		this.bildErde = bildErde;
	}


	public ImageView getImv() {
		return imv;
	}


	public void setImv(ImageView imv) {
		this.imv = imv;
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
	public int getAnfangKarte() {
		return anfangKarte;
	}


	public void setAnfangKarte(int anfangKarte) {
		this.anfangKarte = anfangKarte;
	}


	public int getAnzahlBloecke() {
		return anzahlBloecke;
	}


	public void setAnzahlBloecke(int anzahlBloecke) {
		this.anzahlBloecke = anzahlBloecke;
	}


	public int getMitschiebenKarte() {
		return mitschiebenKarte;
	}


	public void setMitschiebenKarte(int mitschiebenKarte) {
		this.mitschiebenKarte = mitschiebenKarte;
	}
    public GameViewManager getGameViewManager() {
        return gameViewManager;
    }
}
