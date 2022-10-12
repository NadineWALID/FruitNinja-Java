
package javaapplication3;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;


public interface GameActions {
     public void setSpritesAndObjects();
    public void updateObjectsLocations();
    public void sliceObjects();
    //public void saveGame();
    //public void loadGame();
    public List add();
      public void move(GraphicsContext gc,int i,Sprite s1,double x,double y);
     public void move2(GraphicsContext gc,Sprite s1);
    public void resetGame(GraphicsContext gc);
}
