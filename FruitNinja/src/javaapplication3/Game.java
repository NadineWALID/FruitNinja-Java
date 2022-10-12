
package javaapplication3;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javax.swing.text.DefaultStyledDocument;


public class Game implements GameActions{
  private static Game firstInstance=null;
   public SpecialFruits coconut=new SpecialFruits();
   public SpecialFruits lemon=new SpecialFruits();
  Factory fc=new Factory();
  GameObject pineapple=fc.getclass("Fruits");
  GameObject watermelon=fc.getclass("Fruits");
  GameObject kiwi=fc.getclass("Fruits");
  GameObject dBomb=fc.getclass("Deadly");
  GameObject bomb=fc.getclass("Bomb");
     boolean mof=false;boolean movedUp=false;
      AnimationTimer timer; AnimationTimer timer2;
       boolean cutTheFruit=false;
      double speed;double falling;int i;
      public Sprite sprite;
       private int Score=0; private int life=3;
      // Timeline timeline2;
             
     // JavaApplication3 ap=new JavaApplication3();
    Game(){}
    public static Game getInstance(){
        if (firstInstance==null)//only way to creat an object if one doesnt exist already
        {
            firstInstance=new Game();
        }
        
        return firstInstance;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    public void setlife(int life) {
        this.life = life;
    }
      public int getlife() {
       return this.life;
    }

 
    public Momento save(){
        return new Momento(Score);
    }
    public void restore(Momento m){
        Score=m.getScore();
        System.out.println("state after storing from momento"+Score);
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
  @Override
     public void setSpritesAndObjects()
        {
           
             pineapple.setImage(new Image("pineapple.png")); pineapple.setSlicedImage(new Image("Slicedpineapple.png"));
             watermelon.setImage(new Image("watermelone.png"));watermelon.setSlicedImage(new Image("watermeloneSlice.png"));
             bomb.setImage(new Image("bomb.png"));bomb.setSlicedImage(new Image("explosion.png"));
             dBomb.setImage(new Image("deadlyBomb.png")); dBomb.setSlicedImage(new Image("deadlyExplosion.png"));
             kiwi.setImage(new Image("kiwi.png"));kiwi.setSlicedImage(new Image("SlicedKiwi.png"));
             lemon.setImage(new Image("lemon.png"));lemon.setSlicedImage(new Image("lemonSlice.png"));
             coconut.setImage(new Image("coconut.png"));coconut.setSlicedImage(new Image("slicedCoconut.png"));
             kiwi.setSprite(new Sprite(kiwi.getImage()));
             watermelon.setSprite(new Sprite(watermelon.getImage()));
             pineapple.setSprite(new Sprite(pineapple.getImage()));
             bomb.setSprite(new Sprite(bomb.getImage()));
             dBomb.setSprite(new Sprite(dBomb.getImage()));
             lemon.setSprite(new Sprite(lemon.getImage()));
              coconut.setSprite(new Sprite(coconut.getImage()));
             
             
            
        }
  @Override
      public void move(GraphicsContext gc,int i,Sprite s1,double x,double y)
    {
        List<GameObject> list=add();
        
        Sprite s2= list.get(i).getSprite();
        s2.setImage(list.get(i).getSlicedImage());
         s2.positionX=x; s2.positionY=y;
        double positiony=s1.positionY;
       
         Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), eventt -> {
       
        
       s2.positionY+=25;
         
            
           
        }));
          
        timeline.setCycleCount(100);
        timeline.play();
         timer = new AnimationTimer() {
 
            @Override
            public void handle(long arg0) {
                
                
                 Image image=new Image("background.png");
                 gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                 s2.render(gc);
               
                
            }
           
        };
        timer.start();
        
    }
     
     public void move2(GraphicsContext gc,Sprite s1,double speed,int mn,int mx)
     {
        
        double positiony=s1.positionY;
         s1.positionY=650;
         
          Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(speed), eventt -> {
     
       
       
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
           
             
           
                
      if (s1.positionY>700 && mof==false)
      {
          mof=true;   
      }
      else if (mof==true)
      {
          
          mof=false;
          setSpritesAndObjects();
          List <GameObject> list=add();
          Sprite s= list.get(rand(0,5)).getSprite();
                while(s==s1)
                {
                    s= list.get(rand(0,5)).getSprite();
                            
                }
                        
            s.setPositionX(rand(0,550));s.setPositionY(rand(0,300));
           
                movedUp=false;  
               timer2.stop();
          JavaApplication3 ap=new JavaApplication3();
               if (ap.life>0)
          {
               move2(gc,s,speed, mn, mx);
          }
               
        
        
         
      }
      
      JavaApplication3 ap=new JavaApplication3();
        if (ap.life<=0)
        {
            timer2.stop();
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
        list.add(lemon);
        list.add(coconut);
        list.add(bomb);
        list.add(dBomb);
        
       return list; 
    }
     public int rand(int min, int max) {
        return (int)(Math.random() * max + min);
    }
 public void setCutFruit(boolean n)
    {
        this.cutTheFruit=n;
    }
    public boolean getCutFruit()
    {
        return this.cutTheFruit;
    }
    @Override
    public void move2(GraphicsContext gc, Sprite s1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public static void main (String[] args){
        CareTaker caretaker = new CareTaker();
        Game game =new Game();
        game.setScore(7);
        game.setScore(9);
        caretaker.AddMomento(game.save());
        game.restore(caretaker.getmomento());
        
    }
}
