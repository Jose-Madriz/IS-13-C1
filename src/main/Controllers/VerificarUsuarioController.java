package main.Controllers;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.VerificarUsuario.VerificarUsuarioView;

public class VerificarUsuarioController {
    
    private VerificarUsuarioView view;
    private DatabaseManager dbManager;
    private Usuario user;
    private Menu menu;
    private double costoTotal;
    private double costoBandeja;

    public VerificarUsuarioController(VerificarUsuarioView view, Menu menu) {
        this.dbManager = DatabaseManager.getInstance();
        this.view = view;
        this.menu = menu;
        this.costoBandeja = this.menu.precio;
    }
    
    public double aplicarDescuento(){
        double discount = 30;
        switch( user.getCargo() ){
            case "Estudiante":
                discount = 30;
                break;
            case "Profesor":
                discount = 90;
                break;
            case "Trabajador":
                discount = 110;
                break;
        }
        return this.menu.precio * discount / 100;
    } 

    public boolean verificarUsuario(File imageFile, String cedulaStr, JTextField resultadoField) {
        // Validar cédula
        if (!cedulaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(view.frame.getFrame(), "La cédula debe contener solo números.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            resultadoField.setText("Error: Cédula inválida.");
            return false;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.frame.getFrame(), "Error al procesar la cédula.", "Error", JOptionPane.ERROR_MESSAGE);
            resultadoField.setText("Error: Cédula inválida.");
            return false;
        }

        // Buscar usuario
        this.user = dbManager.buscarPorCI(cedula);
        if (user == null) {
            JOptionPane.showMessageDialog(view.frame.getFrame(), "Usuario con la cédula " + cedula + " no encontrado.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
            resultadoField.setText("Usuario no encontrado.");
            return false;
        }

        // Verificar hash de la imagen
        if (dbManager.verifyImageHash(user.getCedula(), imageFile)) {
            JOptionPane.showMessageDialog(view.frame.getFrame(), "Verificación exitosa para el usuario: " + user.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            resultadoField.setText("Verificación Exitosa.");
            this.costoTotal = this.aplicarDescuento( );
            this.descontarSaldo( this.costoTotal );
            return true;
        } else {
            JOptionPane.showMessageDialog(view.frame.getFrame(), "La imagen no coincide con el registro del usuario.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
            resultadoField.setText("Verificación Fallida.");
            return false;
        }
    }

    private void descontarSaldo(double costoTotal) {
        if( this.user.getSaldo() < costoTotal ){
            JOptionPane.showMessageDialog(view.frame.getFrame(), "Saldo insuficiente, El usuario debe recargar Saldo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.user.setSaldo(this.user.getSaldo() - costoTotal);
        dbManager.actualizarUsuario(this.user);
    }
}
