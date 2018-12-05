/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalintro;
import java.awt.Desktop;
import java.io.*;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *
 * @author glori
 */
public class ProyectoFinalIntro {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        StringTokenizer tokens;
        //String ruta;
        
        String linea="", txt;
        
    String ruta = "C:\\Users\\glori\\OneDrive\\Escritorio\\intro.txt";
//_____________________________________________________________________________
        
      //para leer el archivo txt. 
      try{
          FileReader f = new FileReader("C:\\Users\\glori\\OneDrive\\Escritorio\\intro.txt");
          BufferedReader b = new BufferedReader (f);
          while ((ruta=b.readLine())!=null){
              System.out.println(""+ruta);
          }
          
      }catch (Exception ex){
          
      }
//________________________________________________________________________________     
     
    Scanner sc=new Scanner(System.in);
    String respuesta;
    File fichero = new File("C:\\Users\\glori\\OneDrive\\Escritorio\\intro.txt");
    
        FileReader filr = new FileReader(fichero);
	BufferedReader buffr = new BufferedReader(filr);
	int lineacant = 0;			
	String text = "";
	String texto1 = "";	
        String nomtab1 = "";
        String nomtab2 = "";
        
    while ((linea = buffr.readLine()) != null) 
    {
        
			
	if (linea.contains(".") && linea.contains("com") && linea.contains("=") && linea.contains("ON")) 
        {
            
            text = text + " " + linea.substring(linea.indexOf(".") + 1, linea.indexOf("=") - 1) + " "+ linea.substring(linea.lastIndexOf(".") + 1, linea.length());
            texto1 =	 texto1 + " " + linea.substring(3, linea.indexOf(".")) + " "+ linea.substring(linea.indexOf("=") + 1, linea.lastIndexOf("."));
	} 
        
        else if (linea.contains(".") && !linea.endsWith(",")) 
        {
            
            if (linea.contains("WHERE")) 
            {
		text = text + " " + linea.substring(linea.indexOf(".") + 1, linea.indexOf("=") - 1);
		texto1 = texto1 + " " + linea.substring(linea.indexOf(" "), linea.indexOf("."));
            } 
            else if (linea.contains("ORDER")) 
            {
		text = text + " " + linea.substring(linea.indexOf(".") + 1, linea.lastIndexOf(" "));
		texto1 = texto1 + " " + linea.substring(linea.indexOf("Y ") + 1, linea.indexOf("."));
            }
            else 
            {
                texto1 = texto1 + " " + linea.substring(0, linea.indexOf("."));
		text = text + " " + linea.substring(linea.indexOf('.') + 1, linea.length());

            }
            

	} else if (linea.contains(".") && linea.endsWith(","))
        {
            text = text + " " + linea.substring(linea.indexOf('.') + 1, linea.length() - 1);
            texto1 = texto1 + " " + linea.substring(0, linea.indexOf("."));

	} 
        else if (linea.equals("FROM") || linea.equals("SELECT")) 
        {
            text = text;
	} 
        else if (linea.contains("JOIN")) 
        {
            
            text = text + linea.replace(linea.substring(linea.lastIndexOf("J"), linea.lastIndexOf("N") + 1), "");
            texto1 = texto1 + linea.substring(linea.indexOf(" "), linea.length());
	} 
        else
        {
            text = text + " " + linea;
            texto1 = texto1 + " " + linea;
	}

    }
    
    
    
    if (text.contains("ASC")) 
    {
	System.out.println(text.substring(0, text.length() - 4));
	text = text.substring(0, text.length() - 4);
    }
    System.out.println();
    System.out.println();
    System.out.println("COLUMNAS: "+text);	
    
    System.out.println(text);
            
    nomtab1=text;
    nomtab2=texto1;
		
    
    StringTokenizer st=new StringTokenizer (text);
    System.out.println("Tokens count: "+st.countTokens());
    
    while (st.hasMoreElements()){
        String token=st.nextElement().toString();
        System.out.println("token= "+token);
        
    }
    String [] tab1 = nomtab1.split(" ");
    for (String nombtab1: tab1){
        System.out.println(tab1);
    }
    
    String [] tab2 = nomtab2.split(" ");
    for (String nombtab2: tab2){
        System.out.println(tab2);
    }
    
	
                /*La ruta donde se creará el archivo*/
        String rutaArchivo = System.getProperty("user.home")+"/ejemploExcelJava.xls";
        /*Se crea el objeto de tipo File con la ruta del archivo*/
        File archivoXLS = new File(rutaArchivo);
        /*Si el archivo existe se elimina*/
        if(archivoXLS.exists()) archivoXLS.delete();
        /*Se crea el archivo*/
        archivoXLS.createNewFile();
        
        /*Se crea el libro de excel usando el objeto de tipo Workbook*/
        Workbook libro = new HSSFWorkbook();
        /*Se inicializa el flujo de datos con el archivo xls*/
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        
        /*Utilizamos la clase Sheet para crear una nueva hoja de trabajo dentro del libro que creamos anteriormente*/
        Sheet hoja = libro.createSheet("FINAL-INTRO");
        
        /*Hacemos un ciclo para inicializar los valores de 10 filas de celdas*/
        for(int f=0;f<5;f++){
            /*La clase Row nos permitirá crear las filas*/
            Row fila = hoja.createRow(f);
            
            /*Cada fila tendrá 5 celdas de datos*/
            for(int w=0;w<2;w++){
                /*Creamos la celda a partir de la fila actual*/
                Cell celda = fila.createCell(w);
                Cell celda2=fila.createCell(w);
                /*Si la fila es la número 0, estableceremos los encabezados*/
                if(f==0){
                    celda.setCellValue(tab1.toString());
                    celda2.setCellValue(tab1.toString());
                }
                if(w==0){
                    celda.setCellValue(tab2.toString());
                    celda2.setCellValue(tab2.toString());
                }
                //else{
                    /*Si no es la primera fila establecemos un valor*/
                    
                //}
            }
        }
        
        /*Escribimos en el libro*/
        libro.write(archivo);
        /*Cerramos el flujo de datos*/
        archivo.close();
        /*Y abrimos el archivo con la clase Desktop*/
        Desktop.getDesktop().open(archivoXLS);
       
        }
   }
    

    
      
        
        

   

