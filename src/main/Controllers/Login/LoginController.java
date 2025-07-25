package main.Controllers.Login;

import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Views.Layouts.Window;

public class LoginController {
    private DatabaseManager dbManager;
    
    public LoginController(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean validateData(Window window, String ci, String password) {
        if (ci.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Recuerda ingresar tus datos para iniciar sesión",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!ci.matches("\\d+")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Recuerda solo ingresar los números de tu cédula",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean validateUser(Window frame, String ci, String password) {
        try {
            int cedula = Integer.parseInt(ci);
            Usuario usuario = dbManager.buscarPorCI(cedula);

            if (usuario == null) {
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Usuario no encontrado",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if (!usuario.getPass().equals(password)) {
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Contraseña incorrecta",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            JOptionPane.showMessageDialog(frame.getFrame(), 
                    "¡Bienvenido " 
                    + usuario.getNombre() + " " 
                    + usuario.getNombre2() + " "  
                    + usuario.getApellido()+ " "  
                    + usuario.getApellido2() + " " 
                    + usuario.getCedula() + " " 
                    + usuario.getCargo() + " " 
                    + usuario.getPass() + " " 
                    + usuario.getSaldo() + " " 
                    + usuario.getUser() + " " + "!", 
                    "Inicio exitoso", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame.getFrame(),
                    "La cédula debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}