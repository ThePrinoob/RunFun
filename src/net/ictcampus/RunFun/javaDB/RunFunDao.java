package net.ictcampus.RunFun.javaDB;

import java.util.List;

import net.ictcampus.RunFun.domain.Highscore;

public interface RunFunDao {
    public List<Highscore> findHighestScore();
}
