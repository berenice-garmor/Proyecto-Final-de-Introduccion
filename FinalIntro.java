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
     
   File fichero = new File("C:\\Users\\Hp\\Desktop\\bere.txt");
    
        FileReader filr = new FileReader(fichero);
	BufferedReader buffr = new BufferedReader(filr);
	int lineacant = 0;			
	String txt1 = "";
	String txt2 = "";				
    while ((linea = buffr.readLine()) != null) 
    {
        /*el +1 es para encontrar sucesivas apariciones en la cadena*/
            if ( linea.contains("com") && linea.contains(".") &&  linea.contains("=") && linea.contains("ON") )
            {
               txt1 = txt1 + " " + linea.substring(linea.indexOf(".") + 1, linea.indexOf("=") - 1) + " "
                      + linea.substring(linea.lastIndexOf(".") + 1, linea.length());
               
               /*Se le pone el substring desde 3 porque desde ahi empieza lo que esta despues del ON,
               los lastindex son para encontrar el ultimo lugar donde aparecio*/
               txt2 = txt2 + " " + linea.substring(3, linea.indexOf(".")) + " "
                      + linea.substring(linea.indexOf("=") + 1, linea.lastIndexOf("."));
            } 
        
            else if (linea.contains(".") && !linea.endsWith(",")) 
            {
		if (linea.contains("WHERE")) {
                    txt1 = txt1 + " " + linea.substring(linea.indexOf(".") + 1, linea.indexOf("=")  -1);
                    txt2 = txt2 + " " + linea.substring(linea.indexOf(" "), linea.indexOf("."));
		} 
                else if (linea.contains("ORDER")) {
		txt1 = txt1 + " " + linea.substring(linea.indexOf(".") + 1, linea.lastIndexOf(" "));
		txt2 = txt2 + " " + linea.substring(linea.indexOf("Y ") + 1, linea.indexOf("."));
		} 
                else {
                txt2 = txt2 + " " + linea.substring(0, linea.indexOf("."));
		txt1 = txt1 + " " + linea.substring(linea.indexOf('.') + 1, linea.length());
		}
            }
            /*Este else es para decir que entonces estan despues del SELECT*/
            else if (linea.contains(".") && linea.endsWith(","))
            {
		txt1 = txt1 + " " + linea.substring(linea.indexOf('.') + 1, linea.length() - 1);
		txt2 = txt2 + " " + linea.substring(0, linea.indexOf("."));
            } 
            else if (linea.equals("FROM") || linea.equals("SELECT")) 
            {
		txt1 = txt1;
            } 
            else if (linea.contains("JOIN")) 
            {
		txt1 = txt1 + linea.replace(linea.substring(linea.lastIndexOf("J"), linea.lastIndexOf("N") + 1), "");
		txt2 = txt2 + linea.substring(linea.indexOf(" "), linea.length());
	    } 
               else 
            {
		txt1 = txt1 + " " + linea;
		txt2 = txt2 + " " + linea;
            }

    }//llave del while
    
    
    if (txt1.contains("ASC")) 
    {
	System.out.println(txt1.substring(0, txt1.length() - 4));
	txt1 = txt1.substring(0, txt1.length() - 4);
    }
    System.out.println();
    System.out.println();
    System.out.println("Resultado: "+txt1);	
    
    //________________________________________________________________________________

    //Exportar a excel
    
       //La ruta donde se creará el archivo
        String rutaArchivo = System.getProperty("user.home")+"/ExportacionJava.xls";
        //Se crea el objeto de tipo File con la ruta del archivo
        File archivoXLS = new File(rutaArchivo);
        //Si el archivo existe se elimina
        if(archivoXLS.exists()) archivoXLS.delete();
        //Se crea el archivo
        archivoXLS.createNewFile();
        //Se crea el libro de excel usando el objeto de tipo Workbook
        Workbook libro = new HSSFWorkbook();
        //Se inicializa el flujo de datos con el archivo xls
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        //Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente
        Sheet hoja = libro.createSheet("Exportacion");
        
        //Hacemos un ciclo para inicializar los valores de 10 filas de celdas
        for(int f=0;f<4;f++){
            //La clase Row nos permitirá crear las filas
            Row fila = hoja.createRow(f);
            
            //Cada fila tendrá 5 celdas de datos
            for(int c=0;c<2;c++){
                //Creamos la celda a partir de la fila actual
                Cell celda = fila.createCell(c);
                
                //Si la fila es la número 0, estableceremos los encabezados
                if(f==0&&c==0){
                    celda.setCellValue(txt1+c);
                }
                else{
                    //Si no es la primera fila establecemos un valor
                    celda.setCellValue("Valor celda "+c+","+f);
                }
            }
        }
        
        //Escribimos en el libro
        libro.write(archivo);
        //Cerramos el flujo de datos
        archivo.close();
        //Y abrimos el archivo con la clase Desktop
        Desktop.getDesktop().open(archivoXLS);
        
    }//llave del main
    
}
