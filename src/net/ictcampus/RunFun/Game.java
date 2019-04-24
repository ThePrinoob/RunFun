package net.ictcampus.RunFun;


import javafx.scene.canvas.Canvas;



public class Game extends Canvas implements Runnable{

    public void run() {
        
    }
public static void main(String args[]) {
    new Window(800, 600, "RunFun", new Game());
    
}


}
  