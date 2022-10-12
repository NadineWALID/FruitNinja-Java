/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.*;

public class FilesHandling {
    String line ;
    
     public void saveToFile(int Score)  {

        File file = new File("BestScore.txt");

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(""+Score);
            bufferedWriter.newLine();
             bufferedWriter.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
     }
    
    
    

  public String readFromFile() {

        File file = new File("BestScore.txt");

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while(bufferedReader.ready()) {
                 this.line = bufferedReader.readLine();
              
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return line;

    }
 /* public static void main(String[] args) {
      FilesHandling f =new FilesHandling();
       f.saveToFile(5);
       f.saveToFile(6);
       f.saveToFile(18);
       f.saveToFile(500);
      System.out.println( f.readFromFile()); 
       
    } */


}
