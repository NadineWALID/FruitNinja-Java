/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.ArrayList;

/**
 *
 * @author Jesus
 */
public class CareTaker {
    private ArrayList<Momento> m = new ArrayList<>();
    public void AddMomento(Momento x){
        m.add(x);}
        public Momento getmomento (){
            return m.get(m.size()-1) ;
        }
    
}
