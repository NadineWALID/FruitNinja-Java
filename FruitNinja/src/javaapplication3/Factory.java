
package javaapplication3;


public class Factory {

    public Factory() {
    }
    
    public GameObject getclass(String objectname)
    {
        if(objectname.equals("Fruits"))
        {
            return new Fruits();
        }
        else if(objectname.equals("Bomb"))
        {
           // Bomb1 bomb=new Bomb1();
           // return new AdapterBomb(bomb);
            return new Bomb();
        }
        else  if(objectname.equals("Deadly"))
        {
            return new DeadlyBomb();
        }
       
        else 
        {
            return new SpecialFruits();
        }
        
    
    }

   
    
    
}
