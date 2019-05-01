package application;

import java.sql.Time;

public class Person {
  //Instanzvariabeln
    private String benutzername;
    private Time time;
    
    //Konstruktor
    public Person(String benutzername, Time zeit) {
        this.setBenutzername(benutzername);
        this.setTime(zeit);
    }


    //Getter und Setter
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time zeit) {
        this.time = zeit;
    }    
}
