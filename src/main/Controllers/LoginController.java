package main.Controllers;

import java.util.Hashtable;

import javax.swing.JOptionPane;
import main.Views.Layouts.Window;

public class LoginController {
    protected Hashtable<String, String> data; // viene de la vista de Login
    
    public LoginController(Hashtable<String, String> data  ){
        this.data = data;
    }
    // TODO Validar Datos
    public boolean validateData ( Window window ) {

        if (data.get("CI").isEmpty() || data.get("password").isEmpty()) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Recuerda ingresar tus datos para iniciar sesión",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!data.get("CI").matches("\\d+")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Recuerda solo ingresar los números de tu cédula",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(data.get("CI"));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "La cédula debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
    // TODO Consultar Usuario
    public boolean  validateUser( Window frame ){
        // Si las credenciales son correctas, muestra un mensaje de éxito.
        JOptionPane.showMessageDialog(frame.getFrame(), "¡Inicio de sesión exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

        return  true;
    }
    // TODO Cambiar Vista
    public void ChageView( Window actualFrame, Window targetFrame ){

    }    
}
