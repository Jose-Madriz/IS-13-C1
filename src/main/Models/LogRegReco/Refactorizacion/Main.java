package main.Models.LogRegReco.Refactorizacion;

import javax.swing.SwingUtilities;
import main.Models.LogRegReco.Refactorizacion.Login.LoginSystem;

public class Main {    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginSystem loginSystem = new LoginSystem();
            loginSystem.setVisible(true);
        });
    }
}
