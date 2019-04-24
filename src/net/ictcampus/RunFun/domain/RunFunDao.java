package net.ictcampus.RunFun.domain;

import java.util.List;

import net.ictcampus.RunFun.Player;

public interface RunFunDao {
    public List<Player> findAllPlayers();

    public Player findhighscoreByName(String name);
}




