package application;

public class Person {
  //Instanzvariabeln
    private String benutzername;
    private int maxPunkte;
    
    //Konstruktor
    public Person(String benutzername, int punkte) {
        this.setBenutzername(benutzername);
        this.setMaxPunkte(punkte);
    }


    //Getter und Setter
    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public int getMaxPunkte() {
        return maxPunkte;
    }

    public void setMaxPunkte(int maxPunkte) {
        this.maxPunkte = maxPunkte;
    }    
}
