package view;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.RunFunButton;

public class ViewManager {
    private static final int WIDTH = 1024, HEIGHT = 768;
    private final static int MENU_BUTTONS_START_X = 100;
    private AnchorPane mainPane;
    private Stage mainStage;
    private Scene mainScene;
    
    public ViewManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButton();
    }
    public Stage getMainStage() {
        return mainStage;
    }

    private void createButton() {
        RunFunButton button = new RunFunButton("click me!");
        mainPane.getChildren().add(button);
    }
}
