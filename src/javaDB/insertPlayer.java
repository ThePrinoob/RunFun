package javaDB;


    import java.util.List;

    import application.Person;

    public interface insertPlayer {
        //Methode, um die Spieler anhand des Namens zu finden
            
            
     
            
            public List<Person> selectPlayerDB();

            public boolean insertPlayerDB(String name, String zeit);

            boolean updatePlayerDB(String name, int maxPunkte);
            
}
