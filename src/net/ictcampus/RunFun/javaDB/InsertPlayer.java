package net.ictcampus.RunFun.javaDB;


    import java.util.List;

    public interface InsertPlayer {
        //Methode, um die Spieler anhand des Namens zu finden

            public List<Player> selectPlayerDB();

            public boolean insertPlayerDB(String name, String zeit);

            public boolean updatePlayerDB(String name, int maxPunkte);
            
}
