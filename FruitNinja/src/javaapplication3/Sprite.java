
package javaapplication3;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Sprite {
     private Image image;
    public double positionX;
    public double positionY;
    private double width;
    private double height;
    private double DeltaX;
    boolean isSliced;
    
    public Sprite(Image image){//constructer to initialize values followed by setters
        this.image=image;
        this.positionX=0;
        this.positionY=0;
        width=image.getWidth();
        height=image.getHeight();
        
    }
    
 public Image getImage(){
    return this.image;
    
    }
    public void addDeltaX(int x){
    this.DeltaX=x;
    }
        
public void update(){
this.positionX+=DeltaX;
DeltaX=0;

}

    
    public void setImage(Image image){
        this.image=image;
    }
    public void setPositionX(double positionX){
        this.positionX=positionX;
    }
    
    public void setPositionY(double positionY){
        this.positionY=positionY;
    }
    public void setWidth(double width){
        this.width=width;
    }
     public void setHeight(double height){
        this.height=height;
    }

    void render(GraphicsContext gc) {
        gc.drawImage(image,positionX,positionY);
         
    }
    Rectangle2D getBoundry(){
        return new Rectangle2D(positionX,positionY,width,height);
        
    }
    
    
    public boolean Intersects(Rectangle2D r)
    {
        return this.getBoundry().intersects(r);
    }
   /* public void add(Sprite sprite,GraphicsContext gc,double x,double y,Sprite s1,Sprite s2,Sprite s3,Sprite s4,Sprite s7,Sprite s5,Sprite s6,int scene){
        gc.clearRect(0, 0, 2000, 1000);
        s5.render(gc);
        s6.render(gc);
        s2.render(gc);
        s1.render(gc);
        s3.render(gc);
        s4.render(gc);
        if (scene==2)
        {
             s7.render(gc);
        }
       
        sprite.setPositionX(x);
        sprite.setPositionY(y);
        sprite.render(gc);
    }*/

    
 

    
}
