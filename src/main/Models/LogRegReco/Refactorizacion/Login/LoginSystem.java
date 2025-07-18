package main.Models.LogRegReco.Refactorizacion.Login;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import main.Models.LogRegReco.Refactorizacion.Recover.RecoverySystem;
import main.Models.LogRegReco.Refactorizacion.Register.RegistrationSystem;
import main.Models.LogRegReco.Refactorizacion.Usuario;

public class LoginSystem extends JFrame {

    private List<Usuario> usuarios = new ArrayList<>();
    private JTextField ciField;
    private JPasswordField passField;

    public LoginSystem() {
        // Configuración de la ventana principal
        super("Sistema de Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        // Panel principal con diseño
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.decode("#1e1e1e"));

        // Título
        JLabel titleLabel = new JLabel("INICIO DE SESIÓN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel de formulario
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 1, 10, 15));
        formPanel.setBackground(Color.decode("#1e1e1e"));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Campo CI
        JPanel ciPanel = new JPanel(new BorderLayout(5, 5));
        ciPanel.setBackground(Color.decode("#1e1e1e"));
        JLabel ciLabel = new JLabel("Cédula de Identidad:");
        ciLabel.setForeground(Color.WHITE);
        ciLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        ciField = new JTextField();
        ciField.setFont(new Font("Arial", Font.PLAIN, 14));
        ciField.setBackground(Color.decode("#B2B2B2"));
        ciPanel.add(ciLabel, BorderLayout.NORTH);
        ciPanel.add(ciField, BorderLayout.CENTER);

        // Campo Contraseña con botón de visibilidad
        JPanel passPanel = new JPanel(new BorderLayout(5, 5));
        passPanel.setBackground(Color.decode("#1e1e1e"));
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel passFieldPanel = new JPanel(new BorderLayout());
        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setBackground(Color.decode("#B2B2B2"));
        passFieldPanel.add(passField, BorderLayout.CENTER);

        JToggleButton showPassButton = new JToggleButton("👁️");
        showPassButton.setFont(new Font("Arial", Font.PLAIN, 10));
        showPassButton.setFocusPainted(false);
        showPassButton.setBackground(Color.decode("#B2B2B2"));
        showPassButton.setPreferredSize(new Dimension(40, passField.getPreferredSize().height));
        showPassButton.addActionListener(e -> {
            if (showPassButton.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('•');
            }
        });
        passFieldPanel.add(showPassButton, BorderLayout.EAST);

        passPanel.add(passLabel, BorderLayout.NORTH);
        passPanel.add(passFieldPanel, BorderLayout.CENTER);

        formPanel.add(ciPanel);
        formPanel.add(passPanel);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.decode("#2e2e2e"));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        loginButton.addActionListener(this::loginAction);

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(Color.decode("#3a3a3a"));
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(Color.decode("#2e2e2e"));
            }
        });

        // Botón de recuperar cuenta
        JButton recoverButton = new JButton("Recuperar cuenta");
        recoverButton.setFont(new Font("Arial", Font.BOLD, 14));
        recoverButton.setBackground(Color.decode("#2a4e6c"));
        recoverButton.setForeground(Color.WHITE);
        recoverButton.setFocusPainted(false);
        recoverButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        recoverButton.addActionListener(e -> openRecoveryWindow());

        recoverButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                recoverButton.setBackground(Color.decode("#3a6e9e"));
            }

            public void mouseExited(MouseEvent e) {
                recoverButton.setBackground(Color.decode("#2a4e6c"));
            }
        });

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.decode("#2a6c4e"));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        registerButton.addActionListener(e -> openRegistrationWindow());

        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(Color.decode("#3a9e6e"));
            }

            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(Color.decode("#2a6c4e"));
            }
        });

        formPanel.add(loginButton);
        formPanel.add(recoverButton);
        formPanel.add(registerButton);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Mensaje de estado
        JLabel statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);

        add(mainPanel);

        // Cargar usuarios desde el archivo JSON
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("DataBase.json"));
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            usuarios.clear();

            if (jsonContent.toString().trim().isEmpty()) {
                return;
            }

            String jsonString = jsonContent.toString().trim();

            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
            }

            if (jsonString.isEmpty()) {
                return;
            }

            String[] userObjects = jsonString.split("\\},\\s*\\{");

            for (String userObj : userObjects) {
                userObj = userObj.trim();
                if (userObj.startsWith("{")) {
                    userObj = userObj.substring(1);
                }
                if (userObj.endsWith("}")) {
                    userObj = userObj.substring(0, userObj.length() - 1);
                }

                Usuario usuario = new Usuario();

                String[] fields = userObj.split(",");

                for (String field : fields) {
                    field = field.trim();
                    String[] parts = field.split(":");
                    if (parts.length != 2) {
                        continue;
                    }

                    String key = parts[0].trim().replace("\"", "");
                    String value = parts[1].trim().replace("\"", "");

                    switch (key) {
                        case "nombre":
                            usuario.nombre = value;
                            break;
                        case "nombre2":
                            usuario.nombre2 = value;
                            break;
                        case "apellido":
                            usuario.apellido = value;
                            break;
                        case "apellido2":
                            usuario.apellido2 = value;
                            break;
                        case "cedula":
                            try {
                                usuario.cedula = Integer.parseInt(value);
                            } catch (NumberFormatException e) {
                                usuario.cedula = 0;
                            }
                            break;
                        case "cargo":
                            usuario.cargo = value;
                            break;
                        case "Pass":
                            usuario.pass = value;
                            break;
                        case "saldo":
                            try {
                                usuario.saldo = Double.parseDouble(value);
                            } catch (NumberFormatException e) {
                                usuario.saldo = 0.0;
                            }
                            break;
                        case "User":
                            usuario.user = value;
                            break;
                    }
                }

                if (usuario.cedula != 0 && usuario.nombre != null) {
                    usuarios.add(usuario);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error al leer la base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginAction(ActionEvent e) {
        String ci = ciField.getText().trim();
        String password = new String(passField.getPassword()).trim();

        if (ci.isEmpty() && password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Recuerda ingresar tus datos para iniciar sesión",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (ci.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Recuerda ingresar tu cédula",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Recuerda ingresar tu contraseña",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ci.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "Recuerda solo ingresar los números de tu cédula",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(ci);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "La cédula debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean cedulaExiste = false;
        boolean credencialesValidas = false;
        Usuario usuarioEncontrado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.cedula == cedula) {
                cedulaExiste = true;
                if (usuario.pass.equals(password)) {
                    credencialesValidas = true;
                    usuarioEncontrado = usuario;
                    break;
                }
            }
        }

        if (credencialesValidas) {
            Object[] userData = {
                usuarioEncontrado.nombre,
                usuarioEncontrado.nombre2,
                usuarioEncontrado.apellido,
                usuarioEncontrado.apellido2,
                usuarioEncontrado.cedula,
                usuarioEncontrado.cargo,
                usuarioEncontrado.pass,
                usuarioEncontrado.saldo,
                usuarioEncontrado.user
            };
            showWelcomeMessage(userData);
        } else {
            if (cedulaExiste) {
                JOptionPane.showMessageDialog(this,
                        "Comprueba tu password",
                        "Contraseña Incorrecta", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Recuerda haberte registrado anteriormente",
                        "Credenciales Inválidas", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void showWelcomeMessage(Object[] userData) {
        String message = "¡Bienvenido " + userData[0] + " " + userData[1] + " "
                + userData[2] + " " + userData[3] + "!\n"
                + "Cédula: " + userData[4] + "\n"
                + "Cargo: " + userData[5] + "\n"
                + "Contraseña: " + userData[6] + "\n"
                + "Saldo:" + userData[7] + "\n"
                + "User: " + userData[8];

        JOptionPane.showMessageDialog(this,
                message,
                "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openRecoveryWindow() {
        RecoverySystem recoveryWindow = new RecoverySystem(usuarios, this);
        recoveryWindow.setVisible(true);
        this.dispose();
    }

    private void openRegistrationWindow() {
        RegistrationSystem registrationWindow = new RegistrationSystem(usuarios, this);
        registrationWindow.setVisible(true);
        this.setVisible(false);
    }
}
