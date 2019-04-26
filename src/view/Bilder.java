package view;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Hier werden alle Bilder eingelesen und durch getter zur Verfügung gestellt.
 * 
 * @author housi, mrk., peschä
 *
 */
public class Bilder {
	// -----------INSTANZVARIABLEN------------
	// Bilder Menu
	private Image menuBild;
	private Image bildCreators;
	private Image bildNeuesSpiel;
	private Image bildHighscore;
	private Image bildCredits;
	private Image bildBeenden;
	private Image bildZurueck;
	// Buttons hover
	private Image hoverNeuesSpiel;
	private Image hoverHighscore;
	private Image hoverCredits;
	private Image hoverBeenden;
	private Image hoverZurueck;
	// Texturen Karte
	private Image bildNachthimmel;
	private Image bildTaghimmel;
	private Image bildErde;
	private Image bildStein;
	private Image bildTrollface;
	private Image bildFeuer;
	private Image bildWasser;
	private Image bildWasserGanz;
	private Image bildWueste;
	private Image bildPalme;
	private Image bildFlagge;
	private Image bildKFCUnten;
	private Image bildKFCOben;
	private Image bildMond;
	private Image bildRaumschiff1;
	private Image bildRaumschiff2;
	private Image bildRaumschiff3;
	private Image bildRaumschiff4;
	private Image bildRaumschiff5;
	private Image bildRaumschiff6;
	private Image bildRaumschiff7;
	private Image bildWeltall;
	// Bilder Anleitung
	private Image bildLinks;
	private Image bildRechts;
	private Image bildLeertaste;
	private Image bildEsc;
	private Image iconHousi;

	// Spielfiguren
	private List<Image> bildFigur = new ArrayList<Image>();

	// --------------KONSTRUKTOR--------------
	public Bilder() {
		bilderEinlesen();
	}

	// ---------------METHODEN----------------
	/**
	 * Alle Bilder werden eingelesen. Die Figurbilder werden der Figurenliste
	 * hinzugefügt.
	 */
	public void bilderEinlesen() {
		try {
		    // Bilder Menu
            menuBild = ImageIO.read(getClass().getResource("textures/H.O.U.S.I_Logo.png"));
            bildCreators = ImageIO.read(getClass().getResource("textures/borat.jpg"));
            bildNeuesSpiel = ImageIO.read(getClass().getResource("textures/housi_play.png"));
            bildHighscore = ImageIO.read(getClass().getResource("textures/housi_highscore.png"));
            bildCredits = ImageIO.read(getClass().getResource("textures/housi_credits.png"));
            bildBeenden = ImageIO.read(getClass().getResource("textures/housi_exit.png"));
            bildZurueck = ImageIO.read(getClass().getResource("textures/housi_pfeil.png"));
            // Buttons hover
            hoverNeuesSpiel = ImageIO.read(getClass().getResource("textures/housi_hover_play.png"));
            hoverHighscore = ImageIO.read(getClass().getResource("textures/housi_hover_highscore.png"));
            hoverCredits = ImageIO.read(getClass().getResource("textures/housi_hover_credits.png"));
            hoverBeenden = ImageIO.read(getClass().getResource("textures/housi_hover_exit.png"));
            hoverZurueck = ImageIO.read(getClass().getResource("textures/housi_hover_pfeil.png"));
            // Texturen Karte
            bildNachthimmel = ImageIO.read(getClass().getResource("textures/housi_nachthimmel.png"));
            bildTaghimmel = ImageIO.read(getClass().getResource("textures/housi_taghimmel.png"));
            bildErde = ImageIO.read(getClass().getResource("textures/housi_erde.png"));
            bildStein = ImageIO.read(getClass().getResource("textures/housi_stein.png"));
            bildFeuer = ImageIO.read(getClass().getResource("textures/housi_feuer.png"));
            bildWasser = ImageIO.read(getClass().getResource("textures/housi_wasser.png"));
            bildWasserGanz = ImageIO.read(getClass().getResource("textures/housi_wasser_full.png"));
            bildWueste = ImageIO.read(getClass().getResource("textures/housi_wueste.png"));
            bildPalme = ImageIO.read(getClass().getResource("textures/housi_palme.png"));
            bildFlagge = ImageIO.read(getClass().getResource("textures/housi_flagge.png"));
            bildKFCUnten = ImageIO.read(getClass().getResource("textures/housi_ziel_unten.png"));
            bildKFCOben = ImageIO.read(getClass().getResource("textures/housi_ziel_oben.png"));
            bildMond = ImageIO.read(getClass().getResource("textures/housi_mond.png"));
            bildRaumschiff1 = ImageIO.read(getClass().getResource("textures/housi_spaceship1.png"));
            bildRaumschiff2 = ImageIO.read(getClass().getResource("textures/housi_spaceship2.png"));
            bildRaumschiff3 = ImageIO.read(getClass().getResource("textures/housi_spaceship3.png"));
            bildRaumschiff4 = ImageIO.read(getClass().getResource("textures/housi_spaceship4.png"));
            bildRaumschiff5 = ImageIO.read(getClass().getResource("textures/housi_spaceship5.png"));
            bildRaumschiff6 = ImageIO.read(getClass().getResource("textures/housi_spaceship6.png"));
            bildRaumschiff7 = ImageIO.read(getClass().getResource("textures/housi_spaceship7.png"));
            bildWeltall = ImageIO.read(getClass().getResource("textures/housi_weltall.png"));
            bildTrollface = ImageIO.read(getClass().getResource("textures/housi_trollface.jpg"));
            // Bilder Anleitung
            bildLinks = ImageIO.read(getClass().getResource("textures/housi_links.png"));
            bildRechts = ImageIO.read(getClass().getResource("textures/housi_rechts.png"));
            bildLeertaste = ImageIO.read(getClass().getResource("textures/housi_leertaste.png"));
            bildEsc = ImageIO.read(getClass().getResource("textures/housi_esc.png"));
            iconHousi = ImageIO.read(getClass().getResource("textures/housi_icon.png"));
            // Bilder Spielfiguren          
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_darthmarc.png")));
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_peschy.png")));
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_faebicroft.png")));
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_luuthepuuh.png")));
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_oepfelchuenigin.png")));
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_chickensaimon.png")));
            bildFigur.add(ImageIO.read(getClass().getResource("textures/housi_ironmarc.png")));
			//bildFigur.add(ImageIO.read(getClass().getResource("textures/borat.jpg")));

		} catch (IOException e) {
			System.out.println("Oops, something went wrong. IOExeption bei Bildimport");
		} catch (IllegalArgumentException e) {
			System.out.println("Oops, something went wrong. Bild konnte nicht gefunden werden");
		} catch (NullPointerException e) {
			System.out.println("Oops, something went wrong. NullPointer bei Bildimport");
		}
	}

	// ------------GETTER & SETTER------------
	public Image getMenuBild() {
		return menuBild;
	}

	public Image getBildNeuesSpiel() {
		return bildNeuesSpiel;
	}

	public Image getBildHighscore() {
		return bildHighscore;
	}

	public Image getBildCredits() {
		return bildCredits;
	}

	public Image getBildBeenden() {
		return bildBeenden;
	}

	public Image getHoverNeuesSpiel() {
		return hoverNeuesSpiel;
	}

	public Image getHoverHighscore() {
		return hoverHighscore;
	}

	public Image getHoverCredits() {
		return hoverCredits;
	}

	public Image getHoverBeenden() {
		return hoverBeenden;
	}

	public Image getBildStein() {
		return bildStein;
	}

	public Image getBildWasser() {
		return bildWasser;
	}

	public Image getBildFeuer() {
		return bildFeuer;
	}

	public Image getBildFlagge() {
		return bildFlagge;
	}

	public Image getBildMond() {
		return bildMond;
	}

	public Image getBildErde() {
		return bildErde;
	}

	public Image getBildNachthimmel() {
		return bildNachthimmel;
	}

	public Image getBildZurueck() {
		return bildZurueck;
	}

	public Image getHoverZurueck() {
		return hoverZurueck;
	}

	public Image getBildCreators() {
		return bildCreators;
	}

    public Image getBildFigur(int figur) {
        return bildFigur.get(figur);
    }

	public int getAnzahlFiguren() {
		return bildFigur.size();
	}

	public Image getBildKFCUnten() {
		return bildKFCUnten;
	}

	public Image getBildKFCOben() {
		return bildKFCOben;
	}

	public Image getBildWasserGanz() {
		return bildWasserGanz;
	}

	public Image getBildWueste() {
		return bildWueste;
	}

	public Image getBildPalme() {
		return bildPalme;
	}

	public Image getBildRaumschiff1() {
		return bildRaumschiff1;
	}

	public Image getBildRaumschiff2() {
		return bildRaumschiff2;
	}

	public Image getBildRaumschiff3() {
		return bildRaumschiff3;
	}

	public Image getBildRaumschiff4() {
		return bildRaumschiff4;
	}

	public Image getBildRaumschiff5() {
		return bildRaumschiff5;
	}

	public Image getBildRaumschiff6() {
		return bildRaumschiff6;
	}

	public Image getBildRaumschiff7() {
		return bildRaumschiff7;
	}

	public Image getBildWeltall() {
		return bildWeltall;
	}

	public Image getBildTaghimmel() {
		return bildTaghimmel;
	}

	public Image getBildTrollface() {
		return bildTrollface;
	}

	public Image getBildLinks() {
		return bildLinks;
	}

	public Image getBildRechts() {
		return bildRechts;
	}

	public Image getBildLeertaste() {
		return bildLeertaste;
	}

	public Image getBildEsc() {
		return bildEsc;
	}

	public Image getIconHousi() {
		return iconHousi;
	}

	
}