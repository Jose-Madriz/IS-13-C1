package main.Controllers.Register;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.imageio.ImageIO;
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

    public boolean validateRegistrationData(Window window, String nombre, String nombre2,
            String apellido, String apellido2, String cedulaStr, String cargo,
            String password, String confirmPassword, File imageFile) {

        // Validar campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || cedulaStr.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty() || imageFile == null) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Por favor complete todos los campos obligatorios (*) y seleccione una imagen",
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

        // Validar formato de imagen
        String fileExtension = getFileExtension(imageFile);
        if (!fileExtension.equalsIgnoreCase("jpg") && !fileExtension.equalsIgnoreCase("png")) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Solo se permiten imágenes en formato JPG o PNG",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean cedulaExiste(int cedula) {
        return dbManager.buscarPorCI(cedula) != null;
    }

    public boolean registerUser(Window window, String nombre, String nombre2,
            String apellido, String apellido2, String cedulaStr, String cargo,
            String password, File imageFile) {

        int cedula = Integer.parseInt(cedulaStr);
        String imageHash = generateImageHash(imageFile);
        if (imageHash == null) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error al procesar la imagen",
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Guardar la imagen en la carpeta DataBaseImg
        String fileExtension = getFileExtension(imageFile);
        String imagePath = "DataBaseImg/" + cedula + "." + fileExtension;
        try {
            BufferedImage image = ImageIO.read(imageFile);
            File outputFile = new File(imagePath);
            ImageIO.write(image, fileExtension, outputFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(window.getFrame(),
                    "Error al guardar la imagen: " + e.getMessage(),
                    "Error en el registro", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Crear nuevo usuario con rol "Common", saldo inicial 0.0 y el hash de la imagen
        Usuario nuevoUsuario = new Usuario(
                nombre,
                nombre2,
                apellido,
                apellido2,
                cedula,
                cargo,
                password,
                0.0,
                "Common",
                imageHash
        );

        return dbManager.agregarUsuario(nuevoUsuario);
    }

    private String generateImageHash(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, getFileExtension(imageFile), outputStream);
            byte[] imageBytes = outputStream.toByteArray();

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(imageBytes);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            return null;
        }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        return name.substring(lastIndexOf + 1);
    }
}