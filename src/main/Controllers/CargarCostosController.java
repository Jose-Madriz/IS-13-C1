package main.Controllers;

import java.util.Hashtable;
import javax.swing.JOptionPane;
import main.Views.Layouts.Window;

public class CargarCostosController {
    protected Hashtable<String, String> data; // viene de la vista
    // Aqui va una referencia a la clase Modelo
    public CargarCostosController( Hashtable<String, String> data ){
        this.data = data;
    }
    // TODO Validar Datos
    public void ValidarDatos( Window window ){
        if( data.get("CV").isEmpty() || data.get("CF").isEmpty() || data.get("NB").isEmpty() || data.get("merma").isEmpty() ) {
            JOptionPane.showMessageDialog(window.getFrame(), "Campos Incompletos", "Error de Carga de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        float NB = Float.parseFloat( data.get("NB") );
        
        
        if (NB <= 0.0f){
            JOptionPane.showMessageDialog( window.getFrame(), "Los valores son incorrectos", "Error de Carga de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String errorValues = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ;:,.{}()%$#@!`=+-_|\\/?<> ";  
        for( int i = 0; i < data.get("CV").length(); i++ ){
            if( errorValues.indexOf( data.get("CV").charAt(i) ) != -1
                || errorValues.indexOf( data.get("CF").charAt(i) ) != -1
                || errorValues.indexOf( data.get("NB").charAt(i) ) != -1
                || errorValues.indexOf( data.get("merma").charAt(i) ) != -1 ){    

                JOptionPane.showMessageDialog( window.getFrame(), "Los valores son incorrectos", "Error de Carga de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        
        
        float CV = Float.parseFloat( data.get("CV") );
        float CF = Float.parseFloat( data.get("CF") );
        float merma = Float.parseFloat( data.get("merma") );
        
        float costoTotal = CalcularCosto(CV, CF, NB, merma);

        if( costoTotal <= 0 ){
            JOptionPane.showMessageDialog(window.getFrame(), "El costo no puede ser 0", "Error de Carga de Datos", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(window.getFrame(), "El costo total es: " + costoTotal, "Vista Previa de Costo", JOptionPane.INFORMATION_MESSAGE);
    }
    // TODO Cargar Costo 
    // Esto se hace llamando a un metodo de la clase Modelo

    // TODO Calcular Costo
    public float CalcularCosto(float CV, float CF, float NB, float merma){
        float costo;

        costo = (float) Math.round(((CF + CV)/NB)*(1+merma));

        return costo;
    }   
}
