package net.ictcampus.RunFun.model;

//~~~ Imports ~~~
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hier wird die Karte vom Textfile eingelesen und in eine Array Liste
 * gespeichert.
 * 
 * @author housi, mrk., peschä
 *
 */
public class Karte {

    // -----------INSTANZVARIABLEN------------
    private List<String[]> karteListe = new ArrayList<String[]>();
    private int laengeKarte;

    // --------------KONSTRUKTOR--------------
    public Karte() {
        karteEinlesen();
    }

    // ---------------METHODEN----------------
    /**
     * Liest Karte aus Textfile ein und speichert diese in die Array Liste.
     */
    public void karteEinlesen() {
        BufferedReader bufRead;
        FileReader fileRead;
        String zeile = null;
        try {
            fileRead = new FileReader("./assets/map.txt");
            bufRead = new BufferedReader(fileRead);
            // Zeile für Zeile einlesen
            while ((zeile = bufRead.readLine()) != null) {
                // Neues Array erstellen (gleiche Länge wie Zeile im Textfile)
                laengeKarte = zeile.split(",").length;
                String[] zeichenArray = new String[laengeKarte];
                // Einzelne Zeichen in Array abspeichern
                zeichenArray = zeile.split(",");
                // Array in Array Liste hinzufügen
                karteListe.add(zeichenArray);

            }
            if (bufRead != null) {
                bufRead.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ------------GETTER & SETTER------------
    public int getLaengeKarte() {
        return laengeKarte;
    }

    public void setLaengeKarte(int laengeKarte) {
        this.laengeKarte = laengeKarte;
    }

    public List<String[]> getKarteListe() {
        return karteListe;
    }

    public void setKarteListe(List<String[]> karteListe) {
        this.karteListe = karteListe;
    }

}