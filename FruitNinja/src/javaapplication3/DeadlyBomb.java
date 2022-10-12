
package javaapplication3;

import javafx.scene.image.Image;




public class DeadlyBomb implements GameObject {
   
    int points=-3;
    Sprite sprite;
    Image image;
    private Image silcedImage;
    @Override
    public double getXLocation() {
        return this.sprite.positionX;
    }

    @Override
    public double getYLocation() {
        return this.sprite.positionY;
    }

    @Override
    public double getMaxHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getInitialVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getFallingVelocity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isSliced() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hasMovedOffSceen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void slice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move(double time) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public Image getImage() {
       return this.image;
    }

    @Override
    public Image getSlicedImage() {
        return this.silcedImage;
    }

    @Override
    public void setImage(Image image) {
        this.image=image;
    }

    @Override
    public void setSlicedImage(Image image) {
       this.silcedImage=image;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.sprite=sprite;
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }
}
