package net.ictcampus.RunFun.model;

//imports
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterPicker extends VBox {

    private ImageView circleImage;
    private ImageView characterImage;

    private String circleNotChoosen = "resources/characterchooser/grey_circle.png";
    private String circleChoosen = "resources/characterchooser/red_boxTick.png";

    private CHARACTER character;
    private boolean isCircleChoosen;

    public CharacterPicker(CHARACTER character) {
        circleImage = new ImageView(new Image(getClass().getResourceAsStream(circleNotChoosen)));
        characterImage = new ImageView(
                new Image(getClass().getResourceAsStream(character.getUrl())));
        this.character = character;
        isCircleChoosen = false;
        this.setLayoutX(260);
        this.setLayoutY(200);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
        this.getChildren().add(circleImage);
        this.getChildren().add(characterImage);

    }

    public CHARACTER getCharacter() {
        return character;
    }

    public boolean getIsCircleChoosen() {
        return isCircleChoosen;
    }

    public void setIsCircleChoosen(boolean isCircleChoosen) {
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(getClass().getResourceAsStream(imageToSet)));
    }

}
