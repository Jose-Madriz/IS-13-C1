package main.Controllers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.Models.ModeloMenuPrincipal.Menu;


public class MenuUsuarioController{

    public MenuUsuarioController usuario;

   public MenuUsuarioController getInstance(){
    
    return usuario;
  }
     //Controlador
    public void CargarDatos(Menu menu1,Menu menu2){
      //Contador
      int i=0;
    try{
       
        BufferedReader lector=new BufferedReader(new FileReader("DataBaseMenu.txt"));
        String linea="";
        while ((linea=lector.readLine())!=null) {
            String [] bloques=linea.split(",");
            if( bloques.length==4){
            if(i==0){
            String turno= bloques[0];
            String platillo=bloques[1];
            String horario= bloques[2];
            double calorias=Double.parseDouble(bloques[3]);
            menu1.turno=turno;
           menu1.platillo=platillo;
            menu1.horario=horario;
           menu1.calorias=calorias;
           i++;
            }else if(i==1){
                String turno= bloques[0];
            String platillo=bloques[1];
            String horario= bloques[2];
            double calorias=Double.parseDouble(bloques[3]);
            menu2.turno=turno;
           menu2.platillo=platillo;
            menu2.horario=horario;
           menu2.calorias=calorias; 
             i++; 
        
            }
             }
       
        }
        lector.close();
    }catch(IOException e){
        System.out.println("Error al cargar el archivo. "+e.getMessage());
    }



    }
  

}
