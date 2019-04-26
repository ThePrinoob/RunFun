package application;

import java.sql.Time;

public class Person {
  //Instanzvariabeln
    private String benutzername;
    private Time maxPunkte;
    
    //Konstruktor
    public Person(String benutzername, Time zeit) {
        this.setBenutzername(benutzername);
        this.setMaxPunkte(zeit);
    }


    //Getter und Setter
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public Time getMaxPunkte() {
        return maxPunkte;
    }

    public void setMaxPunkte(Time zeit) {
        this.maxPunkte = zeit;
    }    
}
