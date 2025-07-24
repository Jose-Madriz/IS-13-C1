package main.Controllers;

import main.Views.Layouts.Window;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import main.Models.LogRegReco.Refactorizacion.Usuario;

public class RegisterController {
    protected Hashtable<String, String> data; 
    // TODO Validar Campos
    
    // Validar campos obligatorios
    public void ValidarDatos( Window frame ){
        String nombre = data.get("nombre");
        String apellido = data.get("apellido");
        String cedulaStr = data.get("cedula");
        String password = data.get("password");
        String confirmPassword = data.get("confirmPassword");

        if (nombre.isEmpty() || apellido.isEmpty() || cedulaStr.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            showError( frame, "Por favor complete todos los campos obligatorios (*)");
            return;
        }

        // Validar cédula numérica
        if (!cedulaStr.matches("\\d+")) {
            showError( frame, "La cédula debe contener solo números");
            return;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            showError( frame, "Error al procesar la cédula");
            return;
        }

        // Verificar si la cédula ya existe
        if ( CIExistent(cedula)) {
            showError( frame, "Esta cédula ya está registrada");
            return;
        }

        // Validar coincidencia de contraseñas
        if (!password.equals(confirmPassword)) {
            showError( frame, "Las contraseñas no coinciden");
            return;
        }

        // Validar fortaleza de contraseña
        if (password.length() < 6) {
            showError( frame, "La contraseña debe tener al menos 6 caracteres");
            return;
        }
    }

    private void showError( Window window, String message ) {
        JOptionPane.showMessageDialog( window.getFrame(),
                message,
                "Error en el registro", JOptionPane.WARNING_MESSAGE);
    }
    // TODO Validar CI
    private boolean CIExistent( int ci ){
        return true;
    }
    // TODO Consultar Existencia de Usuario
    // TODO Cambiar de Vista
}
