
package javaapplication3;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
 
    

public class Game2 extends Decorator{
   protected Game game ;

    public Game2(GameActions gameAction,Game game ) {
        super(gameAction);
        this.game=game ;
    }

    public Game2() {
    }
   
   public Fruits pineapple=new Fruits();
     public Fruits watermelon=new Fruits();
     public Fruits kiwi=new Fruits();
     public Fruits lemon=new Fruits();
     public DeadlyBomb dBomb=new DeadlyBomb();
     public Bomb bomb=new Bomb();
     private static Game2 firstInstance=null;
    Factory fc=new Factory();
 /*   GameObject watermelon=fc.getclass("Fruits");
     GameObject pineapple=fc.getclass("Fruits");
      GameObject kiwi=fc.getclass("Fruits");
       GameObject lemon=fc.getclass("Fruits");
       GameObject dBomb=fc.getclass("Deadly");
       GameObject bomb=fc.getclass("Bomb");*/
     boolean mof=false;boolean movedUp=false;
     boolean mof2=false;boolean movedUp2=false;
      AnimationTimer timer; AnimationTimer timer2;
      double speed;double falling;int i;
       public static Game2 getInstance(){
        if (firstInstance==null)//only way to creat an object if one doesnt exist already
        {
            firstInstance=new Game2();
        }
        
        
        return firstInstance;
    }
    @Override
    public void updateObjectsLocations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sliceObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resetGame(GraphicsContext gc) {
         gc.clearRect(0, 0, 1000, 642);
         Image image=new Image("background.png");
         JavaApplication3 ap=new JavaApplication3();
         timer2.stop();
    }
     public void move2(GraphicsContext gc,Sprite s1,Sprite s2)
     {
        double positiony=s1.positionY;
        double positiony2=s2.positionY;
        s1.positionY=650;s2.positionY=650;
         
          Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(60), eventt -> {
     
       
       
        if (s1.positionY>positiony && movedUp==false)
        {
             s1.positionY-=10;
             s1.positionX+=5;
        }
         
       
         if ( movedUp==true )
        {
             s1.positionY+=10;
             s1.positionX+=5;
            
        }
           else  if (s1.positionY<=positiony)
       {
           movedUp=true;
          
       }
         if (s2.positionY>positiony && movedUp2==false)
        {
             s2.positionY-=10;
             s2.positionX+=5;
        }
          if ( movedUp2==true )
        {
             s2.positionY+=10;
             s2.positionX+=5;
            
        }
      
          
        else  if (s2.positionY<=positiony2)
       {
           movedUp2=true;
          
       }
               
      if (s1.positionY>700 && mof==false)
      {
          mof=true;   
      }
       if (s2.positionY>700 && mof2==false)
      {
          mof2=true;   
      }
      else if (mof==true )
      {
          
          mof=false;mof2=false;
          setSpritesAndObjects();
          List <GameObject> list=add();
          Sprite s= list.get(rand(0,5)).getSprite();
          Sprite s3= list.get(rand(0,5)).getSprite();
                while(s==s1 && s==s3)
                {
                    s= list.get(rand(0,5)).getSprite();
                            
                }
                        
            s.setPositionX(rand(0,550));s.setPositionY(rand(0,300));
            s3.setPositionX(rand(0,550));s3.setPositionY(rand(0,300));
           
                movedUp=false; movedUp2=false;  
               timer2.stop();
                move2(gc,s,s3);
        
        
         
      }
      
         
         
             
           
        }));
          
        timeline2.setCycleCount(150);
        timeline2.play();
         timer2 = new AnimationTimer() {
 
            @Override
            public void handle(long arg0) {
                
               
                 Image image=new Image("background.png");
                 gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                 s1.render(gc);
                 s2.render(gc);
               
         
                
            }
           
        };
        timer2.start();
        
        
     }
   @Override
     public List add()
    {
       List <GameObject> list=new ArrayList<>();
        list.add(kiwi);
        list.add(pineapple);
        list.add(watermelon);
        list.add(bomb);
       // list.add(lemon);
        list.add(dBomb);
        
       return list; 
    }
   @Override
     public void setSpritesAndObjects()
        {
           
             pineapple.setImage(new Image("pineapple.png")); pineapple.setSlicedImage(new Image("Slicedpineapple.png"));
             watermelon.setImage(new Image("watermelone.png"));watermelon.setSlicedImage(new Image("watermeloneSlice.png"));
             bomb.setImage(new Image("bomb.png"));bomb.setSlicedImage(new Image("explosion.png"));
             dBomb.setImage(new Image("deadlyBomb.png")); dBomb.setSlicedImage(new Image("deadlyExplosion.png"));
             kiwi.setImage(new Image("kiwi.png"));kiwi.setSlicedImage(new Image("SlicedKiwi.png"));
             lemon.setImage(new Image("lemon.png"));lemon.setSlicedImage(new Image("lemonSlice.png"));
             
             kiwi.setSprite(new Sprite(kiwi.getImage()));
             watermelon.setSprite(new Sprite(watermelon.getImage()));
             pineapple.setSprite(new Sprite(pineapple.getImage()));
             bomb.setSprite(new Sprite(bomb.getImage()));
             dBomb.setSprite(new Sprite(dBomb.getImage()));
             lemon.setSprite(new Sprite(lemon.getImage()));
             
             
            
        }
     
     public int rand(int min, int max) {
        return (int)(Math.random() * max + min);
    }

    @Override
    public void move(GraphicsContext gc, int i, Sprite s1, double x, double y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void move2(GraphicsContext gc, Sprite s1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
}
