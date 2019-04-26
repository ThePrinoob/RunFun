package javaDB;


    import java.util.List;

    import application.Person;

    public interface insertPlayer {
        //Methode, um die Spieler anhand des Namens zu finden
            public boolean insertPlayerDB(String name, int maxPunkte);
            
            public boolean updatePlayerDB(String name, int maxPunkte);
            
            public List<Person> selectPlayerDB();
            
}
