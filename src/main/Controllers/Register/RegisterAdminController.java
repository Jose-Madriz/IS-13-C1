package main.Controllers.Register;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.Usuario;

public class RegisterAdminController extends RegisterController {

    public RegisterAdminController(DatabaseManager dbManager) {
        super(dbManager);
    }

    @Override
    public boolean registerUser(JFrame frame, String nombre, String nombre2, String apellido,
            String apellido2, String cedula, String cargo, String password, File imageFile) {

        int cedulaInt;
        try {
            cedulaInt = Integer.parseInt(cedula.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame,
                    "Error en el formato de la cédula",
                    "Error de Registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Crear nuevo usuario con user = "VIP"
        Usuario newUser = new Usuario(
                nombre,
                nombre2,
                apellido,
                apellido2,
                cedulaInt,
                cargo,
                password,
                0.0, // Saldo inicial
                "VIP", // Establecer user como VIP
                null // ImageHash se establecerá después
        );

        // Intentar agregar usuario a la base de datos
        if (!dbManager.agregarUsuario(newUser)) {
            return false;
        }

        // Actualizar imagen del usuario
        if (!dbManager.updateUserImage(cedulaInt, imageFile)) {
            // Si falla la carga de la imagen, eliminar el usuario para mantener consistencia
            dbManager.getUsuarios().removeIf(u -> u.getCedula() == cedulaInt);
            dbManager.guardarUsuarios();
            return false;
        }

        return true;
    }
}