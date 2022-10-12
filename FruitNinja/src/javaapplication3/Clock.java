/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import static javafx.application.Application.launch;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Jesus
 */
public class Clock  extends Pane {
    private Timeline annimation ;
    private int tap=60;
    private String s ="";
    Label label = new Label("60sec");
 

    public int getTap() {
        return tap;
    }
 

    public Clock() {
        
        
        
        label.setFont(javafx.scene.text.Font.font(80));
        label.setTranslateX(10);
        label.setTranslateY(300);
        getChildren().add(label);
        annimation=new Timeline(new KeyFrame(Duration.seconds(1),e->Timelabel()));
        annimation.setCycleCount(Timeline.INDEFINITE);
        annimation.play();
        
    }
    private void Timelabel(){
        if (tap>0)
            tap--;
   else
        {String k = "Time out";
            label.setText(k);
        System.out.println(k);
        annimation.stop();
        }
        s=tap+"";
        label.setText(s);
      
        
        
    }
   
    
    
}
