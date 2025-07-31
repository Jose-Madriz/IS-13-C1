package main.Models.ModeloMenuPrincipal;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Menu {
    public String turno;
    public String platillo;
    public String horario;
    public double calorias;
    public double precio;
    private String dbPath;

    public Menu(){
        
        this.turno = "";
        this.platillo = "";
        this.horario = "";
        this.calorias = 0;
        this.precio = 0;
        this.dbPath ="DataBaseMenu.txt";

        this.fetchMenus();
    }
    public Menu( String _turno,String _platillo, String _horario, double _calorias, double _precio ){
        
        this.turno = _turno;
        this.platillo = _platillo;
        this.horario = _horario; // Assign _horario to this.horario
        this.calorias = _calorias; // Assign _calorias to this.calorias
        this.precio = _precio; // Assign _precio to this.precio
        this.dbPath = "DataBaseMenu.txt"; // Initialize dbPath
    }

    public Menu menu1 = null;
    public Menu menu2 = null;

    public void fetchMenus(){
        int i = 0;

        try {
            BufferedReader lector = new BufferedReader(new FileReader(this.dbPath));
            String linea="";
            while ((linea = lector.readLine()) != null) {
                String [] bloques = linea.split(",");
                if( bloques.length == 5){
                    if(i==0){
                        String turno; 
                        if (bloques[0].toLowerCase().equals("manana")) {
                            turno = "Mañana";
                        }
                        else{
                            turno = bloques[0];
                        }
                        String platillo = bloques[1];
                        String horario =  bloques[2];
                        double calorias = Double.parseDouble(bloques[3]);
                        Double precio = Double.parseDouble(bloques[4]);

                        this.menu1 = new Menu(turno, platillo, horario, calorias, precio);
                        i++;
                    }else if(i == 1){
                        String turno = bloques[0];
                        String platillo = bloques[1];
                        String horario = bloques[2];
                        double calorias = Double.parseDouble(bloques[3]);
                        Double precio = Double.parseDouble(bloques[4]);
                        
                        this.menu2 = new Menu(turno, platillo, horario, calorias, precio);
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
        String filePath = this.dbPath + ".tmp";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {

            if (menu1Exist()) {
                if(menu1.turno.toLowerCase().equals("mañana")){
                    menu1.turno = "Manana";
                }
                String line = menu1.turno + "," + menu1.platillo + "," + menu1.horario + "," + menu1.calorias + "," + menu1.precio;
                writer.write(line);
                writer.newLine();
            }
            if (menu2Exist()) {
                String line = menu2.turno + "," + menu2.platillo + "," + menu2.horario + "," + menu2.calorias + "," + menu2.precio;
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
            Files.deleteIfExists(Paths.get("DataBaseMenu.txt"));
            Files.move(Paths.get(filePath), Paths.get("DataBaseMenu.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
