package main.Views.Register;

import javax.swing.*;
import main.Controllers.Register.RegisterAdminController;
import main.Models.DatabaseManager;
import main.Views.Login.LoginView;

public class RegisterAdminView extends RegisterView {
    private RegisterAdminController adminController;

    public RegisterAdminView(LoginView loginView) {
        super(loginView); // Llama al constructor de RegisterView
        DatabaseManager dbManager = DatabaseManager.getInstance();
        this.adminController = new RegisterAdminController(dbManager);

        // Sobrescribir el ActionListener del botón "Registrarse" para usar el nuevo controlador
        registerTrigger.removeActionListener(registerTrigger.getActionListeners()[0]);
        registerTrigger.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String nombre2 = nombre2Field.getText().trim();
            String apellido = apellidoField.getText().trim();
            String apellido2 = apellido2Field.getText().trim();
            String cedula = cedulaField.getText().trim();
            String cargo = (String) cargoComboBox.getSelectedItem();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (adminController.validateRegistrationData(frame, nombre, nombre2, apellido, apellido2,
                    cedula, cargo, password, confirmPassword)) {
                
                if (adminController.registerUser(frame, nombre, nombre2, apellido, apellido2,
                        cedula, cargo, password)) {
                    
                    JOptionPane.showMessageDialog(frame.getFrame(),
                            "¡Registro VIP exitoso! Ahora puedes iniciar sesión",
                            "Registro Completado", JOptionPane.INFORMATION_MESSAGE);
                    
                    frame.getFrame().dispose();
                    loginView.getFrame().setVisible(true);
                }
            }
        });

        // Actualizar el título de la ventana
        frame.setTitle("Registro de Usuario VIP");
    }

    public void ShowRegisterAdminView() {
        this.frame.setInstance();
    }
}