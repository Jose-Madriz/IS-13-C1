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
        
        if (!data.get("NB").matches("\\d+") ||
            !data.get("CV").matches("\\d+") || 
            !data.get("CF").matches("\\d+") ||
            !data.get("merma").matches("\\d+")  ) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Recuerda solo ingresar numeros",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if( data.get("CV").isEmpty() || data.get("CF").isEmpty() || data.get("NB").isEmpty() || data.get("merma").isEmpty() ) {
            JOptionPane.showMessageDialog(window.getFrame(), "Campos Incompletos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        float NB = Float.parseFloat( data.get("NB").strip() );
        float CV = Float.parseFloat( data.get("CV").strip() );
        float CF = Float.parseFloat( data.get("CF").strip() );
        float merma = Float.parseFloat( data.get("merma").strip() );

        if (NB <= 0.0f){
            JOptionPane.showMessageDialog( window.getFrame(), "Los valores son incorrectos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        float costoTotal = CalcularCosto(CV, CF, NB, merma);

        if( costoTotal <= 0 ){
            JOptionPane.showMessageDialog(window.getFrame(), "El costo no puede ser 0\n Costo total: " + costoTotal, "Advertencia", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(window.getFrame(), "El costo total es: " + costoTotal, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }
    // TODO Cargar Costo 
    // Esto se hace llamando a un metodo de la clase Modelo
    private void CargarCosto(){

    }

    // TODO Calcular Costo
    public float CalcularCosto(float CV, float CF, float NB, float merma){
        float costo;

        costo = (float) Math.round(((CF + CV)/NB)*(1+merma));

        return costo;
    }   
}
