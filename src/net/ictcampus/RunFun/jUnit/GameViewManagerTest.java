package net.ictcampus.RunFun.jUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ictcampus.RunFun.javaDB.RunFunInsert;

/**
 * GameViewManager Test (Like the Name already told you)
 * Leider keine Zeit mehr gehabt, hier wäre aber die Datei :)
 * 
 * @author ingoldd, leuenbergermik
 * 
 */
public class GameViewManagerTest {
    
    private static RunFunInsert runfuninsert = new RunFunInsert();

    @BeforeClass
    public static void setUpBeforeClass() {
        
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
        System.out.println("Start testSelectDB...");
        runfuninsert.insertPlayerDB("hund", "1:05:03");
        
        assertEquals("1:05:03", null);
    }

}
