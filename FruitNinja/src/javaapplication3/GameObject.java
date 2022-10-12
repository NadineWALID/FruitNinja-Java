
package javaapplication3;

import javafx.scene.image.Image;




public interface GameObject {
   // public ENUM getObjectType();
    public double getXLocation();
    public double getYLocation();
    public double getMaxHeight();
    public double getInitialVelocity();
    public double getFallingVelocity();
    public boolean isSliced();
    public boolean hasMovedOffSceen();
    public void slice();
    public void move(double time);
    public Image getImage();
    public Image getSlicedImage();
    public void setImage(Image image);
    public void setSprite(Sprite sprite);
    public Sprite getSprite();
    public void setSlicedImage(Image image);
    public int getPoints();
  
    
}
