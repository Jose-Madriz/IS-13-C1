package main.Controllers.Register;

import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.MasterBaseManager;
import main.Models.Usuario;
import main.Views.Layouts.Window;

public class RegisterController {

    private DatabaseManager dbManager;
    private MasterBaseManager masterBaseManager;

    public RegisterController(DatabaseManager dbManager) {
        this.dbManager = dbManager;
        this.masterBaseManager = MasterBaseManager.getInstance();
    }

    public boolean validateRegistrationData(Window window,
            String nombre, String nombre2,
            String apellido, String apellido2,
            String cedulaStr, String cargo,
            String password, String confirmPassword) {

        // Validar campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || cedulaStr.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Por favor complete todos los campos obligatorios (*)",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar cédula numérica
        if (!cedulaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "La cédula debe contener solo números",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "La cédula debe contener solo números",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Nueva validación contra MasterBase
        if (!masterBaseManager.existeEnMasterBase(cedula)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Esta cédula no está autorizada para registrarse",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Verificar si la cédula ya existe
        if (cedulaExiste(cedula)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Esta cédula ya está registrada",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar nombres y apellidos (solo letras)
        if (!nombre.matches("[a-zA-ZñÑ]+")
                || (!nombre2.isEmpty() && !nombre2.matches("[a-zA-ZñÑ]+"))
                || !apellido.matches("[a-zA-ZñÑ]+")
                || (!apellido2.isEmpty() && !apellido2.matches("[a-zA-ZñÑ]+"))) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Los nombres y apellidos solo pueden contener letras sin acentos",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar fortaleza de contraseña
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "La contraseña debe tener al menos 6 caracteres",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar coincidencia de contraseñas
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Las contraseñas no coinciden",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean cedulaExiste(int cedula) {
        return dbManager.buscarPorCI(cedula) != null;
    }

    public boolean registerUser(Window window,
            String nombre, String nombre2,
            String apellido, String apellido2,
            String cedulaStr, String cargo,
            String password) {

        int cedula = Integer.parseInt(cedulaStr);

        // Crear nuevo usuario con rol "Common" y saldo inicial 0.0
        Usuario nuevoUsuario = new Usuario(
                nombre,
                nombre2,
                apellido,
                apellido2,
                cedula,
                cargo,
                password,
                0.0,
                "Common"
        );

        return dbManager.agregarUsuario(nuevoUsuario);
    }
}
