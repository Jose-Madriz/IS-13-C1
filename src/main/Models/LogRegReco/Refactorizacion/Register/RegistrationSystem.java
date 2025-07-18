package main.Models.LogRegReco.Refactorizacion.Register;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import javax.swing.*;
import main.Models.LogRegReco.Refactorizacion.Login.LoginSystem;
import main.Models.LogRegReco.Refactorizacion.Usuario;

public class RegistrationSystem extends JFrame {

    private List<Usuario> usuarios;
    private LoginSystem parentFrame;

    private JTextField nombreField;
    private JTextField nombre2Field;
    private JTextField apellidoField;
    private JTextField apellido2Field;
    private JTextField cedulaField;
    private JComboBox<String> cargoComboBox;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegistrationSystem(List<Usuario> usuarios, LoginSystem parent) {

        this.usuarios = usuarios;
        this.parentFrame = parent;

        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.decode("#D9D9D9"));

        // Campos del formulario
        addLabelAndField(panel, "Primer Nombre*:", nombreField = new JTextField());
        addLabelAndField(panel, "Segundo Nombre:", nombre2Field = new JTextField());
        addLabelAndField(panel, "Primer Apellido*:", apellidoField = new JTextField());
        addLabelAndField(panel, "Segundo Apellido:", apellido2Field = new JTextField());
        addLabelAndField(panel, "Cédula*:", cedulaField = new JTextField());

        // Combo box para cargo
        JLabel cargoLabel = new JLabel("Cargo*:");
        cargoLabel.setForeground(Color.BLACK);
        panel.add(cargoLabel);

        String[] cargos = {"estudiante", "profesor", "empleado"};
        cargoComboBox = new JComboBox<>(cargos);
        cargoComboBox.setBackground(Color.decode("#FFFFFF"));
        cargoComboBox.setForeground(Color.BLACK);
        panel.add(cargoComboBox);

        // Campos de contraseña
        addLabelAndPasswordField(panel, "Contraseña*:", passwordField = new JPasswordField());
        addLabelAndPasswordField(panel, "Confirmar Contraseña*:", confirmPasswordField = new JPasswordField());

        // Espaciador
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.decode("#3a9e6e"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.addActionListener(this::registerUser);
        panel.add(registerButton);

        // Botón de cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(Color.decode("#2e2e2e"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.addActionListener(e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });
        panel.add(cancelButton);
        parentFrame.pack();

        // Configurar efectos hover para los botones
        setupButtonHoverEffects(registerButton, cancelButton);

        add(panel);
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        panel.add(label);

        textField.setBackground(Color.decode("#FFFFFF"));
        textField.setForeground(Color.BLACK);
        panel.add(textField);
    }

    private void addLabelAndPasswordField(JPanel panel, String labelText, JPasswordField passwordField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        panel.add(label);

        passwordField.setBackground(Color.decode("#FFFFFF"));
        passwordField.setForeground(Color.BLACK);
        panel.add(passwordField);
    }

    private void setupButtonHoverEffects(JButton registerButton, JButton cancelButton) {
        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(Color.decode("#3a9e6e"));
            }

            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(Color.decode("#2a6c4e"));
            }
        });

        cancelButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cancelButton.setBackground(Color.decode("#2e2e2e"));
            }

            public void mouseExited(MouseEvent e) {
                cancelButton.setBackground(Color.decode("#9e3a3a"));
            }
        });
    }

    private void registerUser(ActionEvent e) {
        // Obtener datos del formulario
        String nombre = nombreField.getText().trim();
        String nombre2 = nombre2Field.getText().trim();
        String apellido = apellidoField.getText().trim();
        String apellido2 = apellido2Field.getText().trim();
        String cedulaStr = cedulaField.getText().trim();
        String cargo = (String) cargoComboBox.getSelectedItem();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validar campos obligatorios
        if (nombre.isEmpty() || apellido.isEmpty() || cedulaStr.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("Por favor complete todos los campos obligatorios (*)");
            return;
        }

        // Validar cédula numérica
        if (!cedulaStr.matches("\\d+")) {
            showError("La cédula debe contener solo números");
            return;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            showError("Error al procesar la cédula");
            return;
        }

        // Verificar si la cédula ya existe
        if (cedulaExiste(cedula)) {
            showError("Esta cédula ya está registrada");
            return;
        }

        // Validar coincidencia de contraseñas
        if (!password.equals(confirmPassword)) {
            showError("Las contraseñas no coinciden");
            return;
        }

        // Validar fortaleza de contraseña
        if (password.length() < 6) {
            showError("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        // Crear y guardar nuevo usuario
        if (crearYGuardarUsuario(nombre, nombre2, apellido, apellido2, cedula, cargo, password)) {
            JOptionPane.showMessageDialog(this,
                    "Registro exitoso. Ahora puedes iniciar sesión con tu cédula y contraseña.",
                    "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
            parentFrame.setVisible(true);
        }
    }

    private boolean cedulaExiste(int cedula) {
        for (Usuario usuario : usuarios) {
            if (usuario.cedula == cedula) {
                return true;
            }
        }
        return false;
    }

    private void showError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error en el registro", JOptionPane.WARNING_MESSAGE);
    }

    private boolean crearYGuardarUsuario(String nombre, String nombre2, String apellido,
            String apellido2, int cedula, String cargo,
            String password) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.nombre = nombre;
        nuevoUsuario.nombre2 = nombre2;
        nuevoUsuario.apellido = apellido;
        nuevoUsuario.apellido2 = apellido2;
        nuevoUsuario.cedula = cedula;
        nuevoUsuario.cargo = cargo;
        nuevoUsuario.pass = password;
        nuevoUsuario.saldo = 0.0;
        nuevoUsuario.user = "Common";

        usuarios.add(nuevoUsuario);

        try {
            // Leer contenido actual del archivo
            StringBuilder jsonContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("DataBase.json"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonContent.append(line);
                }
            }

            // Preparar nuevo contenido
            String newContent;
            if (jsonContent.length() > 0 && jsonContent.toString().trim().startsWith("[")) {
                String contentStr = jsonContent.toString().trim();
                if (contentStr.endsWith("]")) {
                    contentStr = contentStr.substring(0, contentStr.length() - 1);
                }

                if (contentStr.length() > 2) {
                    contentStr += ",";
                }

                contentStr += "\n  " + usuarioToJsonString(nuevoUsuario) + "\n]";
                newContent = contentStr;
            } else {
                newContent = "[\n  " + usuarioToJsonString(nuevoUsuario) + "\n]";
            }

            // Escribir en el archivo
            try (FileWriter file = new FileWriter("DataBase.json")) {
                file.write(newContent);
            }

            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el usuario: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private String usuarioToJsonString(Usuario usuario) {
        return "{\n"
                + "    \"nombre\": \"" + usuario.nombre + "\",\n"
                + "    \"nombre2\": \"" + usuario.nombre2 + "\",\n"
                + "    \"apellido\": \"" + usuario.apellido + "\",\n"
                + "    \"apellido2\": \"" + usuario.apellido2 + "\",\n"
                + "    \"cedula\": " + usuario.cedula + ",\n"
                + "    \"cargo\": \"" + usuario.cargo + "\",\n"
                + "    \"Pass\": \"" + usuario.pass + "\",\n"
                + "    \"saldo\": " + usuario.saldo + ",\n"
                + "    \"User\": \"Common\"\n"
                + "  }";
    }
}
