package net.ictcampus.RunFun.domain;

//imports
import java.sql.Time;

public class highscore {

    private String username;
    private Time time;
    private String name;

    public highscore(String username, Time time, String name) {
        this.username = username;
        this.time = time;
        this.name = name;
    }
}
