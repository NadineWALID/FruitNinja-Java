
package javaapplication3;
import static java.awt.Color.RED;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.omg.CORBA.TRANSACTION_MODE;
 


public class ArcadeMode extends Application{
        private void showAlertWithoutHeaderText1() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("sorry!");
	 
	        alert.setHeaderText(null);
	        alert.setContentText("Time  out");
	 
	        alert.showAndWait();
	 }
         private void showAlertWithoutHeaderText2() {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("congratulation!");
	 
	        alert.setHeaderText(null);
	        alert.setContentText("you won");
	 
	        alert.showAndWait();
	 }
        
   Label labelScore;int score=0;
   int highscore ;
   
   double speed;
   FilesHandling f =new FilesHandling();
Clock time = new Clock();
   
 
   Game game=new Game();
   Game2 game2=new Game2();
   
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
        Image image=new Image("start.png");
        Button start=new Button("ArcadeMode");
        
        Button reset=new Button("Reset");
        start.setLayoutX(450);start.setLayoutY(300);start.setMaxSize(1000,1000);
     
        reset.setLayoutX(600);reset.setLayoutY(0);reset.setMaxSize(1000,1000);
        root.getChildren().add(start);
        gc.drawImage(image,0,0);
        
        start.setOnAction(new EventHandler<ActionEvent>() {
                @Override
            public void handle(ActionEvent event) {
               score=0;
           gc.clearRect(0, 0, 1000, 642);start.setVisible(false);
          
           root.getChildren().add(reset);
           
           Image image=new Image("background.png");
            gc.drawImage(image,0,0);
             labelScore = new Label("Score: "+String.valueOf(score));labelScore.setLayoutX(10);labelScore.setLayoutY(10);  labelScore.setTextFill(Color.web("#0076a2"));
 Label labelHighscore = new Label ("highScore: "+String.valueOf(f.readFromFile()));
 
 labelHighscore.setLayoutX(10);labelHighscore.setLayoutY(40);
  root.getChildren().add(labelHighscore);
  root.getChildren().add(labelScore);
   root.getChildren().add(time);
 
 
            
             game.setSpritesAndObjects();
             List <GameObject> list=game.add();
             
               Sprite s= list.get(game.rand(0,4)).getSprite();
              
                while(s==list.get(game.i).getSprite())
                {
                    s= list.get(game.rand(0,4)).getSprite();
                            
                }
                        
            s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
           
                        
            game. move2(gc,s,50,0,4);
           
           
         
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
                      
                        gc.clearRect(0, 0, 1000, 642);gc.drawImage(image,0,0);
                        double px=list.get(game.i).getSprite().positionX; double py=list.get(game.i).getSprite().positionY;
                        game.setSpritesAndObjects();
                        Sprite s= list.get(game.rand(0,4)).getSprite();
                        while(s==list.get(game.i).getSprite())
                        {
                             s= list.get(game.rand(0,4)).getSprite();
                            
                        }
                        
                        s.setPositionX(game.rand(0,550));s.setPositionY(game.rand(0,300));
                        
                       game.move(gc,game.i,s,px,py);
                       
                       
                      
                        score=score+list.get(game.i).getPoints();
                 
                    
                        if (list.get(game.i)instanceof DeadlyBomb )
                        {
                            showAlertWithoutHeaderText1();
                                game.timer2.stop();game.i=0;
                            start.setVisible(true);
                        }
                  
                        
                       
                        labelScore.setText("Score: " + String.valueOf(score));
                        if(Integer.parseInt(f.readFromFile()) <score)
                        { f.saveToFile(score);
                        
                            showAlertWithoutHeaderText2();}
                            
                        labelHighscore.setText("highscore:"+String.valueOf(f.readFromFile()));
                        game.i=10;
                        
                        
                    }
                    
                   
                  if(time.getTap()==0)
                       { showAlertWithoutHeaderText1();
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
            score=0;
            labelScore.setText("Score: " + String.valueOf(score));
       
            }
               
           });
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   
    
}

