package net.ictcampus.RunFun.domain;


import java.util.ArrayList;
import java.util.List;

import application.Person;
import javafx.scene.control.Label;
import javaDB.*;

public class highscore {

    private int podest =1;

    public void ausgebenRangliste(Label name, Label punkte) {
        // Liste mit den Personen
        List<Person> person = new ArrayList<>();

        // Objekt erstellen
        FunRunSelect select = new FunRunSelect();
        person = select.selectPlayerDB();

        // Ausgeben der ausgelesenen Spieler
        for (Person p : person) {
            name.setText(name.getText() + "\n" + podest + ". " + p.getBenutzername());
            punkte.setText(punkte.getText() + "\n" + p.getTime());
            podest++;
        }

    }
}