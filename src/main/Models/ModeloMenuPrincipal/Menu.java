package main.Models.ModeloMenuPrincipal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Menu {
    public String turno;
    public String platillo;
    public String horario;
    public double calorias;
    public double precio;

    public Menu(){
        
        this.turno = "";
        this.platillo = "";
        this.horario = "";
        this.calorias = 0;
        this.precio = 0;
    }

    public Menu( String _turno,String _platillo, String _horario, double _calorias, double _precio ){

        this.turno = _turno;
        this.platillo = _platillo;
        this.horario = _horario;
        this.calorias = _calorias;
    }

    public Menu menu1 = null;
    public Menu menu2 = null;

    public void fetchMenus(){
        int i = 0;
        try{
            BufferedReader lector = new BufferedReader(new FileReader("DataBaseMenu.txt"));
            String linea="";
            while ((linea = lector.readLine()) != null) {
                String [] bloques = linea.split(",");
                if( bloques.length == 4){
                    if(i==0){
                        String turno = bloques[0];
                        String platillo = bloques[1];
                        String horario =  bloques[2];
                        double calorias = Double.parseDouble(bloques[3]);

                        menu1.turno = turno;
                        menu1.platillo = platillo;
                        menu1.horario = horario;
                        menu1.calorias = calorias;
                        i++;
                    }else if(i == 1){
                        String turno = bloques[0];
                        String platillo = bloques[1];
                        String horario = bloques[2];
                        double calorias = Double.parseDouble(bloques[3]);

                        menu2.turno = turno;
                        menu2.platillo = platillo;
                        menu2.horario = horario;
                        menu2.calorias = calorias; 
                        i++; 
                    }
                }
                
            }
            lector.close();
        }catch(IOException e){
            System.out.println("Error al cargar el archivo. "+e.getMessage());
        }
    }
    public boolean menu1Exist(){
        return (menu1.turno != "" && menu1.platillo != "" && menu1.horario != "" && menu1.calorias != 0);
    }
    public boolean menu2Exist(){
        return (menu2.turno != "" && menu2.platillo != "" && menu2.horario != "" && menu2.calorias != 0);
    }
    public void rewriteMenus() {
        // TODO implementar
    }
}
