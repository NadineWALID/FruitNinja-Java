/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Jesus
 */
public abstract class Decorator implements GameActions  {

   protected GameActions gameAction ;

    public Decorator(GameActions gameAction) {
         super();
        this.gameAction = gameAction;
    }

    public Decorator() {
    }
   
    
} 
