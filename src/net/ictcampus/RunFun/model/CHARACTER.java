package net.ictcampus.RunFun.model;

public enum CHARACTER {

    PANDA("/net/ictcampus/RunFun/model/resources/characterchooser/panda.png"),
    COW("/net/ictcampus/RunFun/model/resources/characterchooser/cow.png"),
    WALRUS("/net/ictcampus/RunFun/model/resources/characterchooser/walrus.png"),
    MONKEY("/net/ictcampus/RunFun/model/resources/characterchooser/monkey.png");

    private String urlCharacter;

    private CHARACTER(String urlCharacter) {
        this.urlCharacter = urlCharacter;
    }

    public String getUrl() {
        return this.urlCharacter;
    }
}
