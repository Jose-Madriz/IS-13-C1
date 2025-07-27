package main.Controllers;

import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Views.MenuPrincipal.RecargarMenuUsuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Locale;

public class RecargarMenuController {
    private final RecargarMenuUsuario view;
    private final DatabaseManager dbManager;

    public RecargarMenuController(RecargarMenuUsuario view, DatabaseManager dbManager) {
        this.view = view;
        this.dbManager = dbManager;
    }

    public void handleRecargarSaldo(int cedula, String montoStr, JLabel saldoLabel) {
        try {
            // Parse the selected monto
            int monto = parseMonto(montoStr);
            if (monto <= 0) {
                showError("Monto inválido seleccionado.");
                return;
            }

            // Fetch the user from DatabaseManager
            Usuario usuario = dbManager.buscarPorCI(cedula);
            if (usuario == null) {
                showError("Usuario no encontrado.");
                return;
            }

            // Update user balance
            double nuevoSaldo = usuario.getSaldo() + monto;
            usuario.setSaldo(nuevoSaldo);

            // Save updated user to database
            if (dbManager.actualizarUsuario(usuario)) {
                saldoLabel.setText(String.format(Locale.US, "Saldo: %.2f", nuevoSaldo));
                JOptionPane.showMessageDialog(view,
                        "Saldo recargado exitosamente. Nuevo saldo: " + String.format(Locale.US, "%.2f", nuevoSaldo),
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showError("Error al guardar el nuevo saldo.");
            }
        } catch (Exception e) {
            showError("Error al procesar la recarga: " + e.getMessage());
        }
    }

    private int parseMonto(String montoStr) {
        if (montoStr == null) return 0;
        switch (montoStr) {
            case "100bs": return 100;
            case "200bs": return 200;
            case "300bs": return 300;
            case "400bs": return 400;
            default: return 0;
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}