
package javaapplication3;
import static java.awt.Color.RED;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.omg.CORBA.TRANSACTION_MODE;
 


public class JavaApplication3 extends Application{
        private void showAlertWithoutHeaderText1() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("sorry!");
	 
	        alert.setHeaderText(null);
	        alert.setContentText("Game over");
	 
	        alert.showAndWait();
	 }
        private void instructions() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Levels!");
	 
	        alert.setHeaderText(null);
	        alert.setContentText("Level 1: A low speed, 1 fruit at a time, no deadly bomb \n"
                        +"Level 2: A high speed  1 fruit at a time, no deadly bomb \n" 
                        +"Level 3: An average speed 1 fruit at a time, deadly bomb added \n"
                        +"Level3: An average speed ,2 fruits at a time with both of the bombs\n "
                        +"Arcade mode : Average speed ,no bombs goal is to slice as many fruits as possible within 1 min\n"
                        +"In all 3 levels: \n"
                        + "You lose when slicing a deadly bomb\n"
                        + "Or when your life reaches Zero\n"
                        + "You win when beating your high score");
	 
	        alert.showAndWait();
	 }
         private void showAlertWithoutHeaderText2() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Congratulation!");
	 
	        alert.setHeaderText(null);
	        alert.setContentText("you won");
	 
	        alert.showAndWait();
	 }
        
   Label labelScore;int score=0;
   int highscore ;
   Label labelLife;public int life;
   double speed;
  
   FilesHandling f =new FilesHandling();

   Game game=Game.getInstance();
   Game2 game2=Game2.getInstance();
   
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
     
       
        Group root =new Group();//stuff added to the screen as a group
        Scene scene =new Scene(root,1000,642);//inserting stuff in the group and setting dimentions of the scene
        Canvas canvas=new Canvas(1000,642);//allows us to draw in screen
        root.getChildren().add(canvas);//inserting canvas in group which is inserted in scene
        GraphicsContext gc=canvas.getGraphicsContext2D();
        Image image2=new Image("start.png");
        Button Arcade=new Button("Arcade Mode");
        Button start=new Button("Level 1");
        Button level2=new Button("Level 2");
        Button level3=new Button("Level 3");
        Button level4=new Button("Level 4");
        Button reset=new Button("Reset");
        Button instructions=new Button("Instructions");
       
        start.setLayoutX(450);start.setLayoutY(300);start.setMaxSize(1000,1000);
        Arcade.setLayoutX(425);Arcade.setLayoutY(200);Arcade.setMaxSize(1000,1000);
        level2.setLayoutX(450);level2.setLayoutY(400);level2.setMaxSize(1000,1000);
        level3.setLayoutX(450);level3.setLayoutY(500);level3.setMaxSize(1000,1000);
        level4.setLayoutX(450);level4.setLayoutY(600);level4.setMaxSize(1000,1000);
        reset.setLayoutX(600);reset.setLayoutY(0);reset.setMaxSize(1000,1000);
        instructions.setLayoutX(450);instructions.setLayoutY(100);instructions.setMaxSize(1000,1000);
        root.getChildren().add(start); root.getChildren().add(level2);root.getChildren().add(level3);root.getChildren().add(instructions);
        gc.drawImage(image2,0,0);root.getChildren().add(Arcade);root.getChildren().add(level4);root.getChildren().add(reset);reset.setVisible(false);
        
        Arcade.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
              score=0;
           gc.clearRect(0, 0, 1000, 642);start.setVisible(false);Arcade.setVisible(false);
           level2.setVisible(false);level3.setVisible(false);level4.setVisible(false);instructions.setVisible(false);
          reset.setVisible(false);
           
           Image image=new Image("background.png");
            gc.drawImage(image,0,0);
             labelScore = new Label("Score: "+String.valueOf(score));labelScore.setLayoutX(10);labelScore.setLayoutY(10);  labelScore.setTextFill(Color.web("#0076a2"));
             //labelLife = new Label("Lives: "+String.valueOf(life));labelLife.setLayoutX(10);labelLife.setLayoutY(600);labelLife.setTextFill(Color.web("#0076a2"));
             Label labelHighscore = new Label ("highScore: "+String.valueOf(f.readFromFile()));
             labelHighscore.setLayoutX(10);labelHighscore.setLayoutY(40);
             root.getChildren().add(labelHighscore);
             root.getChildren().add(labelScore);//root.getChildren().add(labelLife);
             game.setSpritesAndObjects();
             List <GameObject> list=game.add();
             
            Sprite s= list.get(game.rand(0,4)).getSprite();
            s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                     
            game. move2(gc,s,30,0,5);
           
           
         
             scene.setOnMouseMoved(new EventHandler<MouseEvent>(){

               @Override
               public void handle(MouseEvent event) {
                    double y=event.getY();
                    double x=event.getX();
                   game.i=0;
                     
                Rectangle2D r=new Rectangle2D(x,y,1,1);
                for (game.i=0;game.i<=4;game.i++)
                {
                   
                   
                    if(list.get(game.i).getSprite().Intersects(r))
                    {
                      
                        String bip = "sound.wav";
                        Media hit = new Media(new File(bip).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();
                        gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                        double px=list.get(game.i).getSprite().positionX; double py=list.get(game.i).getSprite().positionY;
                        game.setSpritesAndObjects();
                        Sprite s= list.get(game.rand(0,5)).getSprite();
                    
                        
                        s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                        
                       game.move(gc,game.i,s,px,py);
                       
                       
                      
                        score=score+list.get(game.i).getPoints();
                  
                    
                      
                        
                       
                        labelScore.setText("Score: " + String.valueOf(score));
                        if(Integer.parseInt(f.readFromFile()) <score)
                        { f.saveToFile(score);
                        
                            showAlertWithoutHeaderText2();}
                            
                     
                        labelHighscore.setText("highscore:"+String.valueOf(f.readFromFile()));
                      
                      
                    }
                     
                  
                }
                 
               }
               
             });
             
             
             
            
            
             
             
            }
               
           });
        start.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
                life =3;score=0;
                game.setScore(3);
                game.setlife(3);
           gc.clearRect(0, 0, 1000, 642);start.setVisible(false);Arcade.setVisible(false);
           level2.setVisible(false);level3.setVisible(false);level4.setVisible(false);instructions.setVisible(false);
           reset.setVisible(true);
           
           Image image=new Image("background.png");
            gc.drawImage(image,0,0);
             labelScore = new Label("Score: "+String.valueOf(score));labelScore.setLayoutX(10);labelScore.setLayoutY(10);  labelScore.setTextFill(Color.web("#0076a2"));
             labelLife = new Label("Lives: "+String.valueOf(life));labelLife.setLayoutX(10);labelLife.setLayoutY(600);labelLife.setTextFill(Color.web("#0076a2"));
             Label labelHighscore = new Label ("highScore: "+String.valueOf(f.readFromFile()));
             labelHighscore.setLayoutX(10);labelHighscore.setLayoutY(40);
             root.getChildren().add(labelHighscore);
             root.getChildren().add(labelScore);root.getChildren().add(labelLife);
             game.setSpritesAndObjects();
             List <GameObject> list=game.add();
             Sprite s= list.get(game.rand(0,5)).getSprite();
             s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));  
             game.sprite=s;
             game. move2(gc,s,50,0,6);
                
           
           
         
             scene.setOnMouseMoved(new EventHandler<MouseEvent>(){

               @Override
               public void handle(MouseEvent event) {
                    double y=event.getY();
                    double x=event.getX();
                   game.i=0;
                       Rectangle2D r2=new Rectangle2D(0,700,1000,1);     
                Rectangle2D r=new Rectangle2D(x,y,1,1);
                
                for (game.i=0;game.i<=5;game.i++)
                {
                   
                   
                    if(list.get(game.i).getSprite().Intersects(r))
                    {
                        game.setCutFruit(true);
                        String bip = "sound.wav";
                        Media hit = new Media(new File(bip).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();
                        gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                        double px=list.get(game.i).getSprite().positionX; double py=list.get(game.i).getSprite().positionY;
                        game.setSpritesAndObjects();
                        Sprite s= list.get(game.rand(0,6)).getSprite();
                        list.get(game.i).getSprite().isSliced=true;
                        s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                        
                       game.move(gc,game.i,s,px,py);
                       score=score+list.get(game.i).getPoints();
                       
                    
                       if (list.get(game.i)instanceof DeadlyBomb )
                        {
                            showAlertWithoutHeaderText1();
                                game.timer2.stop();game.i=0;
                            start.setVisible(true);
                        }
                         if (list.get(game.i)instanceof Bomb )
                        {
                          
                           life=life-1;
                           game.setlife(life);
                        }
                         if(life==0)
                         {
                             showAlertWithoutHeaderText1();
                              game.timer2.stop();game.i=0;
                             
                              game.setScore(0);
                               gc.clearRect(0, 0, 1000, 642);start.setVisible(true);Arcade.setVisible(true);
                            level2.setVisible(true);level3.setVisible(true);level4.setVisible(true);instructions.setVisible(true);
                            reset.setVisible(false);
                              
                              
                         }
                        
                       
                        labelScore.setText("Score: " + String.valueOf(score));
                        if(Integer.parseInt(f.readFromFile()) <score)
                        { f.saveToFile(score);
                        
                            showAlertWithoutHeaderText2();}
                            
                        labelScore.setText("Score: " + String.valueOf(score));
                        labelLife.setText("life: " + String.valueOf(life));
                        labelHighscore.setText("highscore:"+String.valueOf(f.readFromFile()));
                     
                         
                        
                    }
              
              
                  
                }
                 
               }
               
             });
             
            
             
             
             
            
            
             
             
            }
               
           });
        
         level2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
                   life =3;score=0;
                game.setScore(3);
                game.setlife(3);
           gc.clearRect(0, 0, 1000, 642);start.setVisible(false);Arcade.setVisible(false);
           level2.setVisible(false);level3.setVisible(false);level4.setVisible(false);instructions.setVisible(false);
           reset.setVisible(true);
           
           Image image=new Image("background.png");
            gc.drawImage(image,0,0);
             labelScore = new Label("Score: "+String.valueOf(score));labelScore.setLayoutX(10);labelScore.setLayoutY(10);  labelScore.setTextFill(Color.web("#0076a2"));
             labelLife = new Label("Lives: "+String.valueOf(life));labelLife.setLayoutX(10);labelLife.setLayoutY(600);labelLife.setTextFill(Color.web("#0076a2"));
             Label labelHighscore = new Label ("highScore: "+String.valueOf(f.readFromFile()));
             labelHighscore.setLayoutX(10);labelHighscore.setLayoutY(40);
             root.getChildren().add(labelHighscore);
             root.getChildren().add(labelScore);root.getChildren().add(labelLife);
             game.setSpritesAndObjects();
             List <GameObject> list=game.add();
             Sprite s= list.get(game.rand(0,5)).getSprite();
             s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));          
             game. move2(gc,s,35,0,6);
                
           
           
         
             scene.setOnMouseMoved(new EventHandler<MouseEvent>(){

               @Override
               public void handle(MouseEvent event) {
                    double y=event.getY();
                    double x=event.getX();
                   game.i=0;
                     
                Rectangle2D r=new Rectangle2D(x,y,1,1);
                for (game.i=0;game.i<=5;game.i++)
                {
                   
                   
                    if(list.get(game.i).getSprite().Intersects(r))
                    {
                      
                        String bip = "sound.wav";
                        Media hit = new Media(new File(bip).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();
                        gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                        double px=list.get(game.i).getSprite().positionX; double py=list.get(game.i).getSprite().positionY;
                        game.setSpritesAndObjects();
                        Sprite s= list.get(game.rand(0,6)).getSprite();
                         game.setCutFruit(true);
                        s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                        
                       game.move(gc,game.i,s,px,py);
                       score=score+list.get(game.i).getPoints();
                   
                    
                       if (list.get(game.i)instanceof DeadlyBomb )
                        {
                            showAlertWithoutHeaderText1();
                                game.timer2.stop();game.i=0;
                            start.setVisible(true);
                        }
                         if (list.get(game.i)instanceof Bomb )
                        {
                          
                           life=life-1;
                           game.setlife(life);
                        }
                         if(life==0)
                         {
                             showAlertWithoutHeaderText1();
                              game.timer2.stop();game.i=0;
                             
                              game.setScore(0);
                               gc.clearRect(0, 0, 1000, 642);start.setVisible(true);Arcade.setVisible(true);
                            level2.setVisible(true);level3.setVisible(true);level4.setVisible(true);instructions.setVisible(true);
           
                              
                              
                         }
                        
                       
                        labelScore.setText("Score: " + String.valueOf(score));
                        if(Integer.parseInt(f.readFromFile()) <score)
                        { f.saveToFile(score);
                        
                            showAlertWithoutHeaderText2();}
                            
                        labelScore.setText("Score: " + String.valueOf(score));
                        labelLife.setText("life: " + String.valueOf(life));
                        labelHighscore.setText("highscore:"+String.valueOf(f.readFromFile()));
                     
                         
                        
                    }
                     
                  
                }
                 
               }
               
             });
             
             
             
             
            }
               
           });
          level3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
                   life=3;score=0;
                   game.setlife(3);
           gc.clearRect(0, 0, 1000, 642);start.setVisible(false);Arcade.setVisible(false);
           level2.setVisible(false);level3.setVisible(false);level4.setVisible(false);instructions.setVisible(false);
           instructions.setVisible(false);reset.setVisible(true);
           
           Image image=new Image("background.png");
            gc.drawImage(image,0,0);
             labelScore = new Label("Score: "+String.valueOf(score));labelScore.setLayoutX(10);labelScore.setLayoutY(10);  labelScore.setTextFill(Color.web("#0076a2"));
             labelLife = new Label("Lives: "+String.valueOf(life));labelLife.setLayoutX(10);labelLife.setLayoutY(600);labelLife.setTextFill(Color.web("#0076a2"));
             Label labelHighscore = new Label ("highScore: "+String.valueOf(f.readFromFile()));
             labelHighscore.setLayoutX(10);labelHighscore.setLayoutY(40);
             root.getChildren().add(labelHighscore);
             root.getChildren().add(labelScore);root.getChildren().add(labelLife);
             game.setSpritesAndObjects();
             List <GameObject> list=game.add();
             Sprite s= list.get(game.rand(0,5)).getSprite();
             s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));   
             game.sprite=s;
             game. move2(gc,s,35,0,7);
             Rectangle2D r2=new Rectangle2D(0,700,1000,50);
                
           
           
         
             scene.setOnMouseMoved(new EventHandler<MouseEvent>(){

               @Override
               public void handle(MouseEvent event) {
                    double y=event.getY();
                    double x=event.getX();
                   game.i=0;
                     
                Rectangle2D r=new Rectangle2D(x,y,1,1);
                for (game.i=0;game.i<=6;game.i++)
                {
                   
                   
                    if(list.get(game.i).getSprite().Intersects(r))
                    {
                      
                        String bip = "sound.wav";
                        Media hit = new Media(new File(bip).toURI().toString());
                        MediaPlayer mediaPlayer = new MediaPlayer(hit);
                        mediaPlayer.play();
                        gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                        double px=list.get(game.i).getSprite().positionX; double py=list.get(game.i).getSprite().positionY;
                        game.setSpritesAndObjects();
                        Sprite s= list.get(game.rand(0,7)).getSprite();
                    
                        s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                        
                       game.move(gc,game.i,s,px,py);
                       score=score+list.get(game.i).getPoints();
                       
                    
                       if (list.get(game.i)instanceof DeadlyBomb )
                        {
                            showAlertWithoutHeaderText1();
                                game.timer2.stop();game.i=0;
                            start.setVisible(true);
                        }
                         if (list.get(game.i)instanceof Bomb )
                        {
                          
                           life=life-1;
                        }
                         if(life==0)
                         {
                             showAlertWithoutHeaderText1();
                              game.timer2.stop();game.i=0;
                              game.setScore(0);
                               gc.clearRect(0, 0, 1000, 642);start.setVisible(true);Arcade.setVisible(true);
                               level2.setVisible(true);level3.setVisible(true);level4.setVisible(true);instructions.setVisible(true);
                              
                         }
                        
                       
                        labelScore.setText("Score: " + String.valueOf(score));
                        if(Integer.parseInt(f.readFromFile()) <score)
                        { f.saveToFile(score);
                        
                            showAlertWithoutHeaderText2();}
                            
                        labelScore.setText("Score: " + String.valueOf(score));
                        labelLife.setText("life: " + String.valueOf(life));
                        labelHighscore.setText("highscore:"+String.valueOf(f.readFromFile()));
                     
                         
                        
                    }
                     
                  
                     
                  
                }
                 
               }
               
             });
             
             
             
            }
               
           });
          level4.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
                life=3;score=0;
                game.setScore(score);
           gc.clearRect(0, 0, 1000, 642);start.setVisible(false);instructions.setVisible(false);
           level2.setVisible(false);level3.setVisible(false);level4.setVisible(false);Arcade.setVisible(false);
           reset.setVisible(true);
           
           Image image=new Image("background.png");
            gc.drawImage(image,0,0);
             labelScore = new Label("Score: "+String.valueOf(score));labelScore.setLayoutX(10);labelScore.setLayoutY(10);  labelScore.setTextFill(Color.web("#0076a2"));
             labelLife = new Label("Lives: "+String.valueOf(life));labelLife.setLayoutX(10);labelLife.setLayoutY(600);labelLife.setTextFill(Color.web("#0076a2"));
             root.getChildren().add(labelScore);root.getChildren().add(labelLife);
             game.setSpritesAndObjects();
             List <GameObject> list=game.add();
             
               Sprite s= list.get(game.rand(0,5)).getSprite();
              
                while(s==list.get(game.i).getSprite())
                {
                    s= list.get(game.rand(0,5)).getSprite();
                            
                }
            Sprite s2= list.get(game.rand(0,5)).getSprite();          
            s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
           
                        
            game2. move2(gc,s,s2);
           
           
         
             scene.setOnMouseClicked(new EventHandler<MouseEvent>(){

               @Override
               public void handle(MouseEvent event) {
                    double y=event.getY();
                    double x=event.getX();
                   game.i=0;
                     
                Rectangle2D r=new Rectangle2D(x,y,1,1);
                for (game.i=0;game.i<=4;game.i++)
                {
                   
                   
                    if(list.get(game.i).getSprite().Intersects(r) )
                    {
                      
                        gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                        double px=list.get(game.i).getSprite().positionX; double py=list.get(game.i).getSprite().positionY;
                        game.setSpritesAndObjects();
                        Sprite s= list.get(game.rand(0,5)).getSprite();
                        while(s==list.get(game.i).getSprite())
                        {
                             s= list.get(game.rand(0,5)).getSprite();
                            
                        }
                        
                        s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                        
                       game.move(gc,game.i,s,px,py);
                       
                       
                      
                        score=score+list.get(game.i).getPoints();
                       
                        if (list.get(game.i)instanceof DeadlyBomb )
                        {
                            showAlertWithoutHeaderText1();
                                game2.timer2.stop();game.i=0;
                            start.setVisible(true);
                        }
                         if (list.get(game2.i)instanceof Bomb )
                        {
                          
                           life=life-1;
                        }
                         if(life==0)
                         {
                             showAlertWithoutHeaderText1();
                              game.timer2.stop();game.i=0;
                         }
                        
                       
                        labelScore.setText("Score: " + String.valueOf(score));
                        labelLife.setText("life: " + String.valueOf(life));
                       
                    }
                    
                  
                }
                 
               }
             });
             
             
             
             
            
            
             
             
            }
               
           });
          
      
        reset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
                
            game.resetGame(gc);
            score=0;life=3;
            labelScore.setText("Score: " + String.valueOf(score));
            labelLife.setText("life: " + String.valueOf(3));
            }
               
           });
         instructions.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
                
             instructions();
            }
               
           });
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   
    public int getlife()
    {
        return life;
    }
   
    
     public int rand(int min, int max) {
        return (int)(Math.random() * max + min);
    }
    
}

