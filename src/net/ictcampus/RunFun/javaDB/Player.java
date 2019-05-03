package net.ictcampus.RunFun.javaDB;

public class Player {
    // Instanzvariabeln
    private String benutzername;
    private String time;

    // Konstruktor
    public Player(String benutzername, String zeit) {
        this.setBenutzername(benutzername);
        this.setTime(zeit);
    }

    // Getter und Setter
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String zeit) {
        this.time = zeit;
    }
}
