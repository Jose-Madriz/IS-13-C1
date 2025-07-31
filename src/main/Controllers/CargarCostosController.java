package main.Controllers;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.Layouts.Window;

public class CargarCostosController {
    protected Hashtable<String, String> data; // viene de la vista
    private Menu menus;
    // Aqui va una referencia a la clase Modelo
    public CargarCostosController( Hashtable<String, String> data ){
        this.data = data;
        this.menus = new Menu();
        menus.fetchMenus();
    }
    public void ValidarDatos( Window frame ){
        
        if (!data.get("NB").matches("\\d+") ||
            !data.get("CV").matches("\\d+") || 
            !data.get("CF").matches("\\d+") ||
            !data.get("merma").matches("\\d+")  ) {
            JOptionPane.showMessageDialog(frame.getFrame(),
                    "Recuerda solo ingresar numeros",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if( data.get("CV").isEmpty() || data.get("CF").isEmpty() || data.get("NB").isEmpty() || data.get("merma").isEmpty() ) {
            JOptionPane.showMessageDialog(frame.getFrame(), "Campos Incompletos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        float NB = Float.parseFloat( data.get("NB").strip() );
        float CV = Float.parseFloat( data.get("CV").strip() );
        float CF = Float.parseFloat( data.get("CF").strip() );
        float merma = Float.parseFloat( data.get("merma").strip() );

        if (NB <= 0.0f){
            JOptionPane.showMessageDialog( frame.getFrame(), "Los valores son incorrectos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        double costoTotal = CalcularCosto(CV, CF, NB, merma);
        
        if( costoTotal <= 0 ){
            JOptionPane.showMessageDialog(frame.getFrame(), "El costo no puede ser 0\n Costo total: " + costoTotal, "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(frame.getFrame(), "El costo total es: " + costoTotal, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

    public  void cargarCosto( int menuNumber ){

        float NB = Float.parseFloat( data.get("NB").strip() );
        float CV = Float.parseFloat( data.get("CV").strip() );
        float CF = Float.parseFloat( data.get("CF").strip() );
        float merma = Float.parseFloat( data.get("merma").strip() );

        double costoTotal = CalcularCosto(CV, CF, NB, merma);

        if( menuNumber > 2  || menuNumber < 1){
            System.err.println("El Numero de menu seleccionado no es valido");
            return;
        }
        if( menuNumber == 1 && menus.menu1Exist() ){
            
            this.menus.menu1.precio = costoTotal;
        }
        if( menuNumber == 2 && menus.menu2Exist() ){

            this.menus.menu2.precio = costoTotal;
        }
        
        menus.rewriteMenus();
    }

    public double CalcularCosto(float CV, float CF, float NB, float merma){
        double costo;

        costo = (double) Math.round(((CF + CV)/NB)*(1+merma));

        return costo;
    }   
}
