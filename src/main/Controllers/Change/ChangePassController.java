package main.Controllers.Change;

import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Views.Layouts.Window;

public class ChangePassController {
    private DatabaseManager dbManager;

    public ChangePassController(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean changePassword(Window window, String cedulaStr, String currentPassword, String newPassword, String confirmNewPassword) {
        // Validar campos obligatorios
        if (cedulaStr.isEmpty() || currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Por favor complete todos los campos obligatorios (*)",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar cédula numérica
        if (!cedulaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe números en tu cédula",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe números en tu cédula",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar fortaleza de la nueva contraseña
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "La nueva contraseña debe tener al menos 6 caracteres",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar coincidencia de contraseñas
        if (!newPassword.equals(confirmNewPassword)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Las contraseñas no coinciden",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Buscar usuario en la base de datos
        Usuario usuario = dbManager.buscarPorCI(cedula);
        if (usuario == null) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Usuario no encontrado",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Validar contraseña actual
        if (!usuario.getPass().equals(currentPassword)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "La contraseña actual es incorrecta",
                    "Error en el cambio de contraseña", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Actualizar contraseña
        usuario.setPass(newPassword);

        // Guardar cambios en la base de datos
        if (dbManager.actualizarUsuario(usuario)) {
            return true;
        } else {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error al actualizar la contraseña",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}