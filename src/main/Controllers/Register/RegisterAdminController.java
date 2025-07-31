package main.Controllers.Register;

import java.io.File;
import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Views.Layouts.Window;

public class RegisterAdminController extends RegisterController {

    public RegisterAdminController(DatabaseManager dbManager) {
        super(dbManager);
    }

    @Override
    public boolean registerUser(Window window, String nombre, String nombre2, String apellido,
            String apellido2, String cedula, String cargo, String password, File imageFile) {

        // Reutilizar la validación del padre (RegisterController)
        if (!validateRegistrationData(window, nombre, nombre2, apellido, apellido2, cedula, cargo, password, password, imageFile)) {
            return false;
        }

        int cedulaInt;
        try {
            cedulaInt = Integer.parseInt(cedula.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error en el formato de la cédula",
                    "Error de Registro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Generar hash de la imagen (reutilizando el método de RegisterController)
        String imageHash = generateImageHash(imageFile);
        if (imageHash == null) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error al procesar la imagen",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
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
                imageHash
        );

        // Intentar agregar usuario a la base de datos
        if (!dbManager.agregarUsuario(newUser)) {
            return false;
        }

        // Guardar la imagen en la carpeta DataBaseImg
        String fileExtension = getFileExtension(imageFile);
        String imagePath = "DataBaseImg/" + cedulaInt + "." + fileExtension;
        try {
            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(imageFile);
            File outputFile = new File(imagePath);
            javax.imageio.ImageIO.write(image, fileExtension, outputFile);
        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error al guardar la imagen: " + e.getMessage(),
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            // Eliminar el usuario si falla la carga de la imagen para mantener consistencia
            dbManager.getUsuarios().removeIf(u -> u.getCedula() == cedulaInt);
            dbManager.guardarUsuarios();
            return false;
        }

        return true;
    }
}