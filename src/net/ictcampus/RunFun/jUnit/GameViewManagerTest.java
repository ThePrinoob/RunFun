package net.ictcampus.RunFun.jUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import net.ictcampus.RunFun.model.CHARACTER;
import net.ictcampus.RunFun.view.GameViewManager;
import net.ictcampus.RunFun.view.ViewManager;

/**
 * GameViewManager Test (Like the Name already told you)
 * @author ingoldd, leuenbergermik
 * 
 */
public class GameViewManagerTest {
    private static ViewManager viewManager;
    private static GameViewManager gameViewManager;
    private static CHARACTER character;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        gameViewManager = new GameViewManager();
        viewManager = new ViewManager();
        gameViewManager.createNewGame(viewManager.getMainStage(), character.COW, "Cow");
        

    }
    @Test
    public void testNewGame() {
        Assert.assertEquals(0, gameViewManager.getAnfangKarte());
        Assert.assertEquals(100, gameViewManager.getAnzahlBloecke());
        Assert.assertEquals(120, gameViewManager.getBreiteBlock());
        Assert.assertFalse(gameViewManager.isZeitLaeuft());
        Assert.assertFalse(gameViewManager.isZielErreicht());
    }
    
}
