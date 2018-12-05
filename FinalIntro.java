/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalintro;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/*Librerías para trabajar con archivos excel*/
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Hp
 */
public class FinalIntro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        StringTokenizer tokens;
        String linea="";
        
        String ruta = "C:\\Users\\Hp\\Desktop\\bere.txt";
//_____________________________________________________________________________
        
      //para leer el archivo txt. 
      try{
          FileReader f = new FileReader("C:\\Users\\Hp\\Desktop\\bere.txt");
          BufferedReader b = new BufferedReader (f);
          while ((ruta=b.readLine())!=null){
              System.out.println(""+ruta);
          }
          
      }catch (Exception ex){
          
      }
//________________________________________________________________________________     
     
  
    
    
        
    }//llave del main
    
}
