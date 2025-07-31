package main.Controllers.Login;

import javax.swing.JOptionPane;
import main.Controllers.MenuUsuarioController;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Views.Layouts.Window;
import main.Views.MenuUser.MenuPrincipal;

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

            // For VIP users
            if (usuario.getUser().equals("VIP")) {
                
                MenuUsuarioController controller = new MenuUsuarioController();
                MenuPrincipal menu = new MenuPrincipal();
                menu.setVisible(true);
                menu.IniciarComponentes(usuario.getNombre(), usuario.getApellido(), usuario.getSaldo(), controller, usuario.getCedula());

                
                String disclaimer = String.format(
                    "Admin Login Details:\n" +
                    "Nombre: %s\n" +
                    "Segundo Nombre: %s\n" +
                    "Apellido: %s\n" +
                    "Segundo Apellido: %s\n" +
                    "Cédula: %d\n" +
                    "Cargo: %s\n" +
                    "Contraseña: %s\n" +
                    "Saldo: %.2f\n" +
                    "Rol: %s\n" +
                    "Image Hash: %s",
                    usuario.getNombre(),
                    usuario.getNombre2() != null ? usuario.getNombre2() : "",
                    usuario.getApellido(),
                    usuario.getApellido2() != null ? usuario.getApellido2() : "",
                    usuario.getCedula(),
                    usuario.getCargo(),
                    usuario.getPass(),
                    usuario.getSaldo(),
                    usuario.getUser(),
                    usuario.getImageHash() != null ? usuario.getImageHash() : "No image"
                );
                JOptionPane.showMessageDialog(frame.getFrame(),
                        disclaimer,
                        "Admin Login Disclaimer",
                        JOptionPane.INFORMATION_MESSAGE);
                
                }

            if (usuario.getUser().equals("Common")) {
                MenuUsuarioController controller = new MenuUsuarioController();
                MenuPrincipal menu = new MenuPrincipal();
                menu.setVisible(true);
                menu.IniciarComponentes(usuario.getNombre(), usuario.getApellido(), usuario.getSaldo(), controller, usuario.getCedula());
            }
            return true;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame.getFrame(),
                    "La cédula debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}