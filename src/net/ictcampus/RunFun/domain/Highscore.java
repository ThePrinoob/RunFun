package net.ictcampus.RunFun.domain;

//~~~ Imports ~~~
import java.util.ArrayList;
import java.util.List;

import net.ictcampus.RunFun.javaDB.Player;
import javafx.scene.control.Label;
import net.ictcampus.RunFun.javaDB.*;

public class Highscore {

    private int podest = 1;

    public void ausgebenRangliste(Label name, Label punkte) {
        // Liste mit den Personen
        List<Player> person = new ArrayList<>();

        // Objekt erstellen
        FunRunSelect select = new FunRunSelect();
        person = select.selectPlayerDB();

        // Ausgeben der ausgelesenen Spieler
        for (Player p : person) {
            name.setText(name.getText() + "\n" + podest + ". " + p.getBenutzername());
            punkte.setText(punkte.getText() + "\n" + p.getTime());
            podest++;
        }

    }
}