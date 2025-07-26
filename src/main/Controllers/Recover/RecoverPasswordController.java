package main.Controllers.Recover;

import java.security.SecureRandom;
import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Views.Layouts.Window;

public class RecoverPasswordController {
    private DatabaseManager dbManager;
    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 8;
    private static final String ACCENTED_CHARS = "[áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙäëïöüÄËÏÖÜâêîôûÂÊÎÔÛãõÃÕñÑ]";

    public RecoverPasswordController(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public String recoverPassword(Window window, String nombre, String nombre2, String apellido, String apellido2, String cedulaStr, String cargo) {
        // Validar campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || cedulaStr.isEmpty() || cargo.isEmpty()) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Por favor complete todos los campos obligatorios (*)",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Validar cédula numérica
        if (!cedulaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe números en tu cédula",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe números en tu cédula",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Validar nombres y apellidos (solo letras, sin acentos)
        if (nombre.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe letras en tu nombre",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (!nombre2.isEmpty() && nombre2.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe letras en tu nombre",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (apellido.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe letras en tu apellido",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (!apellido2.isEmpty() && apellido2.matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo escribe letras en tu apellido",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Validar acentos
        if (nombre.matches(ACCENTED_CHARS) || (!nombre2.isEmpty() && nombre2.matches(ACCENTED_CHARS)) ||
            apellido.matches(ACCENTED_CHARS) || (!apellido2.isEmpty() && apellido2.matches(ACCENTED_CHARS))) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "No se permiten acentos en nombres o apellidos",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Buscar usuario en la base de datos
        Usuario usuario = dbManager.buscarPorCI(cedula);
        if (usuario == null) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Regístrate primero por favor",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Validar que los datos coincidan
        if (!usuario.getNombre().equalsIgnoreCase(nombre) ||
            (!nombre2.isEmpty() && !usuario.getNombre2().equalsIgnoreCase(nombre2)) ||
            (nombre2.isEmpty() && !usuario.getNombre2().isEmpty()) ||
            !usuario.getApellido().equalsIgnoreCase(apellido) ||
            (!apellido2.isEmpty() && !usuario.getApellido2().equalsIgnoreCase(apellido2)) ||
            (apellido2.isEmpty() && !usuario.getApellido2().isEmpty()) ||
            !usuario.getCargo().equalsIgnoreCase(cargo)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Los datos ingresados no coinciden con el usuario registrado",
                    "Error en la recuperación", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        // Generar nueva contraseña
        String newPassword = generateRandomPassword();
        usuario.setPass(newPassword);

        // Actualizar usuario en la base de datos
        if (dbManager.actualizarUsuario(usuario)) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Contraseña recuperada exitosamente",
                    "Recuperación Completada", JOptionPane.INFORMATION_MESSAGE);
            return newPassword;
        } else {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error al actualizar la contraseña",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(ALPHA_NUMERIC.length());
            password.append(ALPHA_NUMERIC.charAt(index));
        }
        return password.toString();
    }
}