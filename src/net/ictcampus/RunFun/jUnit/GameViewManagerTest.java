package net.ictcampus.RunFun.jUnit;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ictcampus.RunFun.javaDB.FunRunSelect;
import net.ictcampus.RunFun.javaDB.Player;

/**
 * GameViewManager Test (Like the Name already told you)
 * 
 * @author ingoldd, leuenbergermik
 * 
 */
public class GameViewManagerTest {
//    private static ViewManager viewManager;
//    private static GameViewManager gameViewManager;
//    private static CHARACTER character;
    private static FunRunSelect runfunselect;
    private static List<Player> player = new ArrayList<>();

    @BeforeClass
    public static void setUpBeforeClass() {
//        gameViewManager = new GameViewManager();
//        viewManager = new ViewManager();
        runfunselect = new FunRunSelect();
        player = runfunselect.selectPlayerDB();

        // Ausgeben der ausgelesenen Spieler
        int podest = 1;

        // Liste mit den Playern
        // Ausgeben der ausgelesenen Spieler
        for (Player p : player) {
            score.setText(score.getText() + "\n" + podest + ". " + p.getBenutzername());
            time.setText(time.getText() + "\n" + p.getTime());
            podest++;
        }
        

    }

//    @Test
//    public void testNewGame() {
//        gameViewManager.createNewGame(viewManager.getMainStage(), CHARACTER.COW, "Cow");
//        Assert.assertEquals(0, gameViewManager.getAnfangKarte());
//        Assert.assertEquals(100, gameViewManager.getAnzahlBloecke());
//        Assert.assertEquals(120, gameViewManager.getBreiteBlock());
//        Assert.assertFalse(gameViewManager.isZeitLaeuft());
//        Assert.assertFalse(gameViewManager.isZielErreicht());
//    }
    @Test
    public void testSelectDB() {
        runfunselect.selectPlayerDB();
    }

}
