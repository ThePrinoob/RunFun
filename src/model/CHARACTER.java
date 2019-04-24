package model;

public enum CHARACTER {

       PANDA("view/resources/characterchooser/panda.png"),
       COW("view/resources/characterchooser/cow.png"),
       WALRUS("view/resources/characterchooser/walrus.png"),
       MONKEY("view/resources/characterchooser/monkey.png");
       
    
    private String urlCharacter;
    
    private CHARACTER(String urlCharacter) {
        this.urlCharacter = urlCharacter;
    }
    
    
    public String getUrl() {
        return this.urlCharacter;
    }
}
