package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import model.Karte;

/**
 * Karte zeichnen, Bewegung der Figur, Kollisionserkennung
 * 
 * @author housi, mrk., peschä
 *
 */

public class Zeichnung extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 5987188146066588179L;
    // -----------INSTANZVARIABLEN------------
    private Karte karte;
    private GameViewManager gameViewManager = GameViewManager.getInstance();

    private int hoeheBlock = 120;
    private int breiteBlock = 120;
    // Bilder
    private Image bildNachthimmel = getGameViewManager().getBilder().getBildNachthimmel()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildNachthimmelSolid = getBildNachthimmel();
    private Image bildTaghimmel = getGameViewManager().getBilder().getBildTaghimmel()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildErde = getGameViewManager().getBilder().getBildErde().getScaledInstance(getBreiteBlock(),
            getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildStein = getGameViewManager().getBilder().getBildStein()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildFeuer = getGameViewManager().getBilder().getBildFeuer()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWasser = getGameViewManager().getBilder().getBildWasser()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWasserGanz = getGameViewManager().getBilder().getBildWasserGanz()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildFlagge = getGameViewManager().getBilder().getBildFlagge()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildKFCUnten = getGameViewManager().getBilder().getBildKFCUnten()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildKFCOben = getGameViewManager().getBilder().getBildKFCOben()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWueste = getGameViewManager().getBilder().getBildWueste()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildPalme = getGameViewManager().getBilder().getBildPalme()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff1 = getGameViewManager().getBilder().getBildRaumschiff1()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff2 = getGameViewManager().getBilder().getBildRaumschiff2()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff3 = getGameViewManager().getBilder().getBildRaumschiff3()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff4 = getGameViewManager().getBilder().getBildRaumschiff4()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff5 = getGameViewManager().getBilder().getBildRaumschiff5()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff6 = getGameViewManager().getBilder().getBildRaumschiff6()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildRaumschiff7 = getGameViewManager().getBilder().getBildRaumschiff7()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildWeltall = getGameViewManager().getBilder().getBildWeltall()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildMond = getGameViewManager().getBilder().getBildMond().getScaledInstance(getBreiteBlock(),
            getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildTrollface = getGameViewManager().getBilder().getBildTrollface()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildLinks = getGameViewManager().getBilder().getBildLinks()
            .getScaledInstance(getBreiteBlock(), getBreiteBlock(), Image.SCALE_SMOOTH);
    private Image bildRechts = getGameViewManager().getBilder().getBildRechts()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildLeertaste = getGameViewManager().getBilder().getBildLeertaste()
            .getScaledInstance(getBreiteBlock(), getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildEsc = getGameViewManager().getBilder().getBildEsc().getScaledInstance(getBreiteBlock(),
            getHoeheBlock(), Image.SCALE_SMOOTH);
    private Image bildFigur;

    private int posX = getBreiteBlock();
    private int posY = getHoeheBlock() * 7 - 1;
    private int beschleunigungX = 0;
    private double beschleunigungY = 2;
    private int geschwindigkeit = getGameViewManager().getGeschwindigkeit();

    private int anfangKarte = 0;
    private int anzahlBloecke = 17;
    private int mitschiebenKarte = 0;
    private int laengeKartenArray;
    private int anzahlSpruenge = 0;
    private int zeit = 0;
    private boolean istZielErreicht = false;
    private boolean zeitLaeuft = false;
    
    // --------------KONSTRUKTOR--------------
    public Zeichnung() {
        setKarte(new Karte());
        setBildFigur(getGameViewManager().getBilder().getBildFigur(1).getScaledInstance(getBreiteBlock() / 2, getHoeheBlock(), Image.SCALE_SMOOTH));
    }
    // ---------------METHODEN----------------
    /**
     * Karte wird anhand der Array Liste gezeichnet. Je nach Zahl, die im Textfile
     * steht, wird ein anderes Bild gezeichnet. Negative Zahlen sind Blöcke ohne
     * Kollision, posivite Zahlen sind Blöcke mit Kollision.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int spaltenNummer = 0;
        for (String[] zeile : getKarte().getKarteListe()) {
            setLaengeKartenArray(zeile.length);
            int zeilenNummer = 0;
            for (int i = getAnfangKarte(); i < getAnfangKarte() + getAnzahlBloecke(); i++) {
                String block = zeile[i];
                switch (block) {
                // Blöcke Deko
                case "-20":
                    g.drawImage(bildEsc, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-19":
                    g.drawImage(bildLeertaste,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-18":
                    g.drawImage(bildRechts, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-17":
                    g.drawImage(bildLinks, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-16":
                    g.drawImage(bildWeltall,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-15":
                    g.drawImage(bildRaumschiff7,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-14":
                    g.drawImage(bildRaumschiff6,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-13":
                    g.drawImage(bildRaumschiff5,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-12":
                    g.drawImage(bildRaumschiff4,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-11":
                    g.drawImage(bildRaumschiff3,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-10":
                    g.drawImage(bildRaumschiff2,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-09":
                    g.drawImage(bildRaumschiff1,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-08":
                    g.drawImage(bildPalme, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-07":
                    g.drawImage(bildFeuer, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-06":
                    g.drawImage(bildKFCOben,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-05":
                    g.drawImage(bildWasserGanz,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-04":
                    g.drawImage(bildWasser, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-03":
                    g.drawImage(bildMond, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-02":
                    g.drawImage(bildNachthimmel,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "-01":
                    g.drawImage(bildTaghimmel,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;

                // Blöcke fest
                case "001":
                    g.drawImage(bildErde, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "002":
                    g.drawImage(bildStein, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "003":
                    g.drawImage(bildWueste, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "004":
                    g.drawImage(bildFlagge, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "005":
                    g.drawImage(bildTrollface,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "006":
                    g.drawImage(bildKFCUnten,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                case "007":
                    g.drawImage(bildNachthimmelSolid,
                            zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                    break;
                default:
                    g.drawImage(bildStein, zeilenNummer * getBreiteBlock() + getMitschiebenKarte(),
                            spaltenNummer * getHoeheBlock(), null);
                }
                zeilenNummer++;
            }
            spaltenNummer++;
        }
        g.drawImage(bildFigur, getPosX(), getPosY(), null);
        g.setFont(new Font("Orbitron", Font.PLAIN, 50));
        g.setColor(Color.yellow);
        g.drawString((getZeit() / 100 / 60) + ":" + ((getZeit() / 100) % 60) + "."
                + (getZeit() % 100) / 10, 20, 40);
    }

    /**
     * Wird alle 10 ms vom ActionListenerAktualisieren aufgerufen. Wenn eine
     * Pfeiltaste gedrückt wird, wird die BeschleunigungX verändert. Beim Springen
     * mit Leertaste wird die BeschleunigungY verändert.
     */
    public void bewegen() {
        // Position wird um Beschleunigung verändert.
        if (!kollisionErkennenX()) {
            setPosX(getPosX() + getBeschleunigungX());
        }
        if (!kollisionErkennenY()) {
            setPosY(getPosY() + (int) getBeschleunigungY());
        }

        // Sobald sich die Spielfigur über die Hälfte bewegt, soll die Karte
        // mitschieben
        if (getAnfangKarte() < getLaengeKartenArray() - getAnzahlBloecke()) {
            if (getPosX() > 8 * getBreiteBlock()) {
                setMitschiebenKarte(getMitschiebenKarte() - getGeschwindigkeit());
                // Verhindert das permanente Mitschieben der Karte, wenn der
                // Spieler die Mitte erreicht hat.
                setPosX(getPosX() - getGeschwindigkeit());
                // Wenn ein ganzer neuer Block dargestellt wurde...
                if (getMitschiebenKarte() <= -120) {
                    setMitschiebenKarte(getMitschiebenKarte() + 120);
                    setAnfangKarte(getAnfangKarte() + 1);
                }
            }
        }
        if (isZeitLaeuft()) {
            setZeit(getZeit() + 1);
        }
    }

    /**
     * Rechteckige Hitbox. Es wird für jede Ecke Kollision festgestellt. Diese
     * Methode stellt Kollisionen am rechten und linken Rand fest. Wenn der Block
     * solid ist (>0), dann return true, sonst passiert nichts. Kollisionen mit der
     * Fahne (ruft zielErreichen() auf) oder dem Trollface werden speziell
     * behandelt.
     */
    public boolean kollisionErkennenX() {
        int xLinks = (getPosX() + getBeschleunigungX() - getMitschiebenKarte()) / getBreiteBlock()
                + getAnfangKarte();
        int xRechts = (getPosX() + getBeschleunigungX() + getBreiteBlock() / 2
                - getMitschiebenKarte()) / getBreiteBlock() + getAnfangKarte();
        int yOben = (getPosY() + getHoeheBlock() / 12) / getHoeheBlock();
        int yUnten = getPosY() / getHoeheBlock() + 1;

        // Kollision mit Fahne erkennen
        if (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xRechts]) == 4
                || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xRechts]) == 4
                || (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xLinks]) == 4
                        || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xLinks]) == 4)) {
            zielErreichen();
        }

        // Kollision mit Trollface erkennen
        else if (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xRechts]) == 7
                || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xRechts]) == 7
                || (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xLinks]) == 7
                        || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xLinks]) == 7)) {
            setBildNachthimmelSolid(getBildTrollface());
        }

        // Kollision rechts und Kollision links
        return (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xRechts]) > 0
                || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xRechts]) > 0)
                || (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xLinks]) > 0
                        || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xLinks]) > 0);

    }

    /**
     * Rechteckige Hitbox. Es wird für jede Ecke Kollision festgestellt. Diese
     * Methode stellt Kollisionen am oberen und unteren Rand fest. Wenn der Block
     * solid ist (>0), dann return true, sonst passiert nichts. Kollisionen mit der
     * Fahne (ruft zielErreichen() auf) oder dem Trollface werden speziell
     * behandelt.
     */
    public boolean kollisionErkennenY() {
        int xLinks = (getPosX() - getMitschiebenKarte()) / getBreiteBlock() + getAnfangKarte();
        int xRechts = (getPosX() + getBreiteBlock() / 2 - getMitschiebenKarte()) / getBreiteBlock()
                + getAnfangKarte();
        int yOben = (getPosY() + (int) getBeschleunigungY() + getHoeheBlock() / 12)
                / getHoeheBlock();
        int yUnten = (getPosY() + (int) getBeschleunigungY()) / getHoeheBlock() + 1;

        // Kollision unten
        if (Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xRechts]) > 0
                || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xLinks]) > 0) {
            // Kollision mit Fahne erkennen
            if (Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xRechts]) == 4
                    || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xLinks]) == 4) {
                zielErreichen();
                // Kollision mit Trollface erkennen
            } else if (Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xRechts]) == 7
                    || Integer.parseInt(getKarte().getKarteListe().get(yUnten)[xLinks]) == 7) {
                setBildNachthimmelSolid(getBildTrollface());
            }

            setBeschleunigungY(1);
            setAnzahlSpruenge(2);
            return true;
        }
        // Kollision oben
        else if (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xRechts]) > 0
                || Integer.parseInt(getKarte().getKarteListe().get(yOben)[xLinks]) > 0) {
            // Kollision mit Fahne erkennen
            if (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xRechts]) == 4
                    || Integer.parseInt(getKarte().getKarteListe().get(yOben)[xLinks]) == 4) {
                zielErreichen();
                // Kollision mit Trollface erkennen
            } else if (Integer.parseInt(getKarte().getKarteListe().get(yOben)[xRechts]) == 7
                    || Integer.parseInt(getKarte().getKarteListe().get(yOben)[xLinks]) == 7) {
                setBildNachthimmelSolid(getBildTrollface());
            }
            setBeschleunigungY(0);
            return true;
        }
        return false;
    }

    /**
     * Wird bei einer Kollision mit der Fahne aufgerufen. Eine Eingabeaufforderung
     * für den Spielernamen erscheint, das Resultat wird abgespeichert.
     */
    public void zielErreichen() {
//        if (!isIstZielErreicht()) {
//            String name;
//            try {
//                name = JOptionPane.showInputDialog("Your Name: ").replace(";", "");
//                if (name.length() > 10) {
//                    name = name.substring(0, 10);
//                }
//            } catch (NullPointerException e) {// Eingabe abbrechen
//                name = "404: NoName";
//            } catch (StringIndexOutOfBoundsException e) {// leere Eingabe
//                name = "Guest";
//            }
//
//            getGame().highscoreHinzufuegen(name, (getZeit() / 100 / 60) + ":"
//                    + ((getZeit() / 100) % 60) + "." + (getZeit() % 100) / 10);
//            getGame().getHintergrundmusik().stop();
//            getFigurauswahlGui().dispose();
//            setIstZielErreicht(true);
//        }
    }


    // ------------GETTER & SETTER------------
    public Image getBildFigur() {
        return bildFigur;
    }

    public void setBildFigur(Image bildFigur) {
        this.bildFigur = bildFigur;
    }

    public void setAnfangKarte(int anfangKarte) {
        this.anfangKarte = anfangKarte;
    }

    public void setMitschiebenKarte(int mitschiebenKarte) {
        this.mitschiebenKarte = mitschiebenKarte;
    }

    public void setPosX(int posX) {
        // Spielfigur darf den linken und rechten Rand nicht überschreiten
        if (posX > 0 && posX < 15.5 * getBreiteBlock()) {
            this.posX = posX;
        }
    }

    public void setPosY(int posY) {
        // Spielfigur darf den unteren Rand nicht überschreiten
        if (posY < 7.5 * getHoeheBlock()) {
            // Spielfigur darf den oberen Rand nicht überschreiten
            if (posY > 0) {
                setBeschleunigungY(getBeschleunigungY() + getGeschwindigkeit() / 4);
                this.posY = posY;
            } else {
                setBeschleunigungY(0);
            }
        } 
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

    public void setAnzahlSpruenge(int anzahlSpruenge) {
        this.anzahlSpruenge = anzahlSpruenge;
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

    public void setKarte(Karte karte) {
        this.karte = karte;
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


    public int getAnzahlSpruenge() {
        return anzahlSpruenge;
    }

    public boolean isIstZielErreicht() {
        return istZielErreicht;
    }

    public void setIstZielErreicht(boolean istZielErreicht) {
        this.istZielErreicht = istZielErreicht;
    }
    public GameViewManager getGameViewManager() {
        return gameViewManager;
    }

}