package net.ictcampus.RunFun;

public class Player {
    private String name;
    private int geschwindigkeit = 1;
    private String item;
    private int score;

    public Player(String name, int geschwindigkeit, String item, int score) {
        this.name = name;
        this.geschwindigkeit = geschwindigkeit;
        this.item = item;
        this.score = score;
    }

    public void itemWerfen(String name) {

    }

    public void springen() {

    }

    public void ducken() {

    }

    // getter & setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGeschwindigkeit() {
        return geschwindigkeit;
    }

    public void setGeschwindigkeit(int geschwindigkeit) {
        this.geschwindigkeit = geschwindigkeit;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
