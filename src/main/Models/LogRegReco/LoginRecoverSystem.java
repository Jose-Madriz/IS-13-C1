package main.Models.LogRegReco;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class LoginRecoverSystem extends JFrame {

    // Clase para representar a un usuario
    static class Usuario {

        String nombre;
        String nombre2;
        String apellido;
        String apellido2;
        int cedula;
        String cargo;
        String pass;
        double saldo;
        String user;
    }

    private List<Usuario> usuarios = new ArrayList<>();
    private JTextField ciField;
    private JPasswordField passField;

    public LoginRecoverSystem() {
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

        // Panel para campo de contraseña y botón de visibilidad
        JPanel passFieldPanel = new JPanel(new BorderLayout());
        passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setBackground(Color.decode("#B2B2B2"));
        passFieldPanel.add(passField, BorderLayout.CENTER);

        // Botón para mostrar/ocultar contraseña
        JToggleButton showPassButton = new JToggleButton("👁️");
        showPassButton.setFont(new Font("Arial", Font.PLAIN, 10));
        showPassButton.setFocusPainted(false);
        showPassButton.setBackground(Color.decode("#B2B2B2"));
        showPassButton.setPreferredSize(new Dimension(40, passField.getPreferredSize().height));
        showPassButton.addActionListener(e -> {
            if (showPassButton.isSelected()) {
                passField.setEchoChar((char) 0); // Mostrar texto
            } else {
                passField.setEchoChar('•'); // Ocultar texto
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

        // Efecto hover para el botón
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

        // Efecto hover para el botón de recuperación
        recoverButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                recoverButton.setBackground(Color.decode("#3a6e9e"));
            }

            public void mouseExited(MouseEvent e) {
                recoverButton.setBackground(Color.decode("#2a4e6c"));
            }
        });

        //botón de registro:
        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.decode("#2a6c4e")); // Verde oscuro
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        registerButton.addActionListener(e -> openRegistrationWindow());

// Efecto hover para el botón de registro
        registerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                registerButton.setBackground(Color.decode("#3a9e6e")); // Verde más claro
            }

            public void mouseExited(MouseEvent e) {
                registerButton.setBackground(Color.decode("#2a6c4e")); // Verde oscuro
            }
        });

// Botón de cambio de contraseña
        JButton changePassButton = new JButton("Cambio de Pass");
        changePassButton.setFont(new Font("Arial", Font.BOLD, 14));
        changePassButton.setBackground(Color.decode("#6c2a4e")); // Morado oscuro
        changePassButton.setForeground(Color.WHITE);
        changePassButton.setFocusPainted(false);
        changePassButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        changePassButton.addActionListener(e -> openChangePasswordWindow());

// Efecto hover para el botón
        changePassButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                changePassButton.setBackground(Color.decode("#9e3a6e")); // Morado más claro
            }

            public void mouseExited(MouseEvent e) {
                changePassButton.setBackground(Color.decode("#6c2a4e")); // Morado oscuro
            }
        });
        formPanel.add(loginButton);
        formPanel.add(recoverButton);
        formPanel.add(registerButton);
        formPanel.add(changePassButton);
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

            // Limpiar la lista de usuarios antes de cargar
            usuarios.clear();

            // Verificar si el JSON está vacío
            if (jsonContent.toString().trim().isEmpty()) {
                return;
            }

            String jsonString = jsonContent.toString().trim();

            // Eliminar corchetes exteriores solo si existen
            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
            }

            // Manejar caso cuando no hay usuarios (solo [])
            if (jsonString.isEmpty()) {
                return;
            }

            // Dividir en objetos individuales correctamente
            String[] userObjects = jsonString.split("\\},\\s*\\{");

            for (String userObj : userObjects) {
                // Normalizar el formato del objeto
                userObj = userObj.trim();
                if (userObj.startsWith("{")) {
                    userObj = userObj.substring(1);
                }
                if (userObj.endsWith("}")) {
                    userObj = userObj.substring(0, userObj.length() - 1);
                }

                Usuario usuario = new Usuario();

                // Dividir en pares clave-valor
                String[] fields = userObj.split(",");

                for (String field : fields) {
                    field = field.trim();
                    String[] parts = field.split(":");
                    if (parts.length != 2) {
                        continue;
                    }

                    String key = parts[0].trim().replace("\"", "");
                    String value = parts[1].trim().replace("\"", "");

                    // Asignar valores a las propiedades del usuario
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

                // Solo agregar usuario si tiene datos válidos
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

        // Validaciones básicas
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

        // Convertir CI a número
        int cedula;
        try {
            cedula = Integer.parseInt(ci);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "La cédula debe contener solo números",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar credenciales
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
            // Crear arreglo con los datos del usuario
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
            // Mostrar mensaje de bienvenida (similar al actual)
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

// Nuevo método para mostrar el mensaje de bienvenida
    private void showWelcomeMessage(Object[] userData) {
        String message = "¡Bienvenido " + userData[0] + " " + userData[1] + " "
                + userData[2] + " " + userData[3] + "!\n"
                + "Cédula: " + userData[4] + "\n"
                + "Cargo: " + userData[5] + "\n"
                + "Contraseña: " + userData[6] + "\n"
                + "Saldo: $" + userData[7] + "\n"
                + "User: " + userData[8];

        JOptionPane.showMessageDialog(this,
                message,
                "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openRecoveryWindow() {
        RecoveryWindow recoveryWindow = new RecoveryWindow(usuarios, this);
        recoveryWindow.setVisible(true);
        this.dispose(); // Esta línea cierra la ventana actual
    }

    private void openRegistrationWindow() {
        RegistrationWindow registrationWindow = new RegistrationWindow(usuarios, this);
        registrationWindow.setVisible(true);
        this.setVisible(false);
    }

    private void openChangePasswordWindow() {
        ChangePasswordWindow changePassWindow = new ChangePasswordWindow(usuarios, this);
        changePassWindow.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginRecoverSystem().setVisible(true);
        });
    }
}

class RecoveryWindow extends JFrame {

    private List<LoginRecoverSystem.Usuario> usuarios;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ciField;
    private JComboBox<String> cargoComboBox;
    private LoginRecoverSystem parentFrame;

    public RecoveryWindow(List<LoginRecoverSystem.Usuario> usuarios, LoginRecoverSystem parent) {
        this.usuarios = usuarios;
        this.parentFrame = parent;

        setTitle("Recuperar Cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(672, 363);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        // Botón Continuar
        JButton continueButton = new JButton("Continuar");
        continueButton.setBounds(280, 217, 106, 29);
        continueButton.setBackground(Color.decode("#2e2e2e"));
        continueButton.setForeground(Color.decode("#D9D9D9"));
        continueButton.setFont(new Font("Arial", Font.PLAIN, 14));
        continueButton.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        continueButton.setFocusPainted(false);
        continueButton.addActionListener(this::verifyRecovery);

        continueButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                continueButton.setBackground(Color.decode("#232323"));
            }

            public void mouseExited(MouseEvent e) {
                continueButton.setBackground(Color.decode("#2e2e2e"));
            }
        });
        panel.add(continueButton);

        // Botón Regresar
        JButton backButton = new JButton("Regresar");
        backButton.setBounds(280, 250, 106, 29);
        backButton.setBackground(Color.decode("#2e2e2e"));
        backButton.setForeground(Color.decode("#D9D9D9"));
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                backButton.setBackground(Color.decode("#232323"));
            }

            public void mouseExited(MouseEvent e) {
                backButton.setBackground(Color.decode("#2e2e2e"));
            }
        });
        panel.add(backButton);

        // Campo Cedula
        ciField = new JTextField("");
        ciField.setBounds(228, 134, 227, 21);
        ciField.setFont(new Font("Arial", Font.PLAIN, 14));
        ciField.setBackground(Color.decode("#B2B2B2"));
        ciField.setForeground(Color.decode("#656565"));
        ciField.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        setPlaceholder(ciField, "Ingresa tu cedula");
        panel.add(ciField);

        // Campo Cargo (ahora ComboBox)
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setForeground(Color.WHITE);
        cargoLabel.setBounds(223, 150, 230, 20);
        panel.add(cargoLabel);

        String[] cargos = {"estudiante", "profesor", "empleado"};
        cargoComboBox = new JComboBox<>(cargos);
        cargoComboBox.setBounds(223, 169, 230, 21);
        cargoComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        cargoComboBox.setBackground(Color.decode("#B2B2B2"));
        cargoComboBox.setForeground(Color.decode("#353535"));
        panel.add(cargoComboBox);

        // Campo Primer Nombre
        firstNameField = new JTextField("");
        firstNameField.setBounds(227, 67, 229, 21);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        firstNameField.setBackground(Color.decode("#B2B2B2"));
        firstNameField.setForeground(Color.decode("#656565"));
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        setPlaceholder(firstNameField, "Ingresa tu Primer Nombre");
        panel.add(firstNameField);

        // Campo Primer Apellido
        lastNameField = new JTextField("");
        lastNameField.setBounds(224, 102, 229, 21);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        lastNameField.setBackground(Color.decode("#B2B2B2"));
        lastNameField.setForeground(Color.decode("#656565"));
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        setPlaceholder(lastNameField, "Ingresa tu Primer Apellido");
        panel.add(lastNameField);

        // Área de texto informativa
        JTextArea infoArea = new JTextArea("");
        infoArea.setBounds(37, 65, 155, 238);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));
        infoArea.setBackground(Color.decode("#B2B2B2"));
        infoArea.setForeground(Color.decode("#656565"));
        infoArea.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        infoArea.setEditable(false);
        infoArea.setText("Hola! Veo que estás recuperando tu cuenta. "
                + "Ingresa los datos solicitados, y si coinciden "
                + "te generaremos una nueva contraseña. "
                + "En cargo selecciona una de las opciones disponibles.");
        panel.add(infoArea);

        add(panel);
    }

    private void setPlaceholder(JTextField textField, String placeholder) {
        textField.setText(placeholder);
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.decode("#353535"));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(placeholder);
                    textField.setForeground(Color.decode("#656565"));
                }
            }
        });
    }

    private void verifyRecovery(ActionEvent e) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String ci = ciField.getText().trim();
        String cargo = cargoComboBox.getSelectedItem().toString();
        // Validar campos
        if (firstName.isEmpty() || lastName.isEmpty() || ci.isEmpty() || cargo.isEmpty()
                || firstName.equals("Ingresa tu Primer Nombre")
                || lastName.equals("Ingresa tu Primer Apellido")
                || ci.equals("Ingresa tu cedula")
                || cargo.equals("Ingresa Tu Cargo")) {

            JOptionPane.showMessageDialog(this,
                    "Por favor completa todos los campos",
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!ci.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "La cédula debe contener solo números",
                    "Cédula Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(ci);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar la cédula",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar usuario que coincida con los datos
        LoginRecoverSystem.Usuario usuarioRecuperado = null;
        for (LoginRecoverSystem.Usuario usuario : usuarios) {
            if (usuario.cedula == cedula
                    && usuario.nombre.equalsIgnoreCase(firstName)
                    && usuario.apellido.equalsIgnoreCase(lastName)
                    && usuario.cargo.equalsIgnoreCase(cargo)) {

                usuarioRecuperado = usuario;
                break;
            }
        }

        if (usuarioRecuperado == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró una cuenta que coincida con los datos proporcionados",
                    "Cuenta No Encontrada", JOptionPane.WARNING_MESSAGE);
        } else {
            // Generar nueva contraseña aleatoria
            String nuevaPassword = generarPasswordAleatoria(8);

            // Actualizar la contraseña en el objeto usuario
            usuarioRecuperado.pass = nuevaPassword;

            // Actualizar el archivo JSON
            actualizarPasswordEnJSON(usuarioRecuperado);

            // Mostrar la nueva contraseña con opción para copiar
            mostrarNuevaPassword(nuevaPassword);
        }
    }

    // Genera una contraseña aleatoria de longitud especificada
    private String generarPasswordAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(longitud);
        Random random = new Random();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        return sb.toString();
    }

    // Actualiza la contraseña en el archivo JSON
    private void actualizarPasswordEnJSON(LoginRecoverSystem.Usuario usuario) {
        try (FileWriter file = new FileWriter("DataBase.json")) {
            file.write("[\n");

            boolean first = true;
            for (LoginRecoverSystem.Usuario u : usuarios) {
                if (!first) {
                    file.write(",\n");
                }
                first = false;

                file.write("  {\n");
                file.write("    \"nombre\": \"" + (u.nombre != null ? u.nombre : "") + "\",\n");
                file.write("    \"nombre2\": \"" + (u.nombre2 != null ? u.nombre2 : "") + "\",\n");
                file.write("    \"apellido\": \"" + (u.apellido != null ? u.apellido : "") + "\",\n");
                file.write("    \"apellido2\": \"" + (u.apellido2 != null ? u.apellido2 : "") + "\",\n");
                file.write("    \"cedula\": " + u.cedula + ",\n");
                file.write("    \"cargo\": \"" + (u.cargo != null ? u.cargo : "") + "\",\n");
                file.write("    \"Pass\": \"" + (u.pass != null ? u.pass : "") + "\",\n");
                file.write("    \"saldo\": " + u.saldo + ",\n");
                file.write("    \"User\": \"" + (u.user != null ? u.user : "Common") + "\"\n");
                file.write("  }");
            }

            file.write("\n]");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar la contraseña: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Muestra la nueva contraseña con opción para copiar
    private void mostrarNuevaPassword(String nuevaPassword) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Etiqueta con la nueva contraseña
        JLabel passwordLabel = new JLabel("Tu nueva contraseña es:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel, BorderLayout.NORTH);

        // Panel para la contraseña y botón de copiar
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 10));

        // Campo de texto con la contraseña (no editable)
        JTextField passwordField = new JTextField(nuevaPassword);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        // Botón para copiar la contraseña
        JButton copyButton = new JButton("Copiar");
        copyButton.setFont(new Font("Arial", Font.PLAIN, 12));
        copyButton.addActionListener(ev -> {
            // Copiar al portapapeles
            StringSelection stringSelection = new StringSelection(nuevaPassword);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            // Cambiar texto del botón temporalmente
            copyButton.setText("¡Copiada!");
            Timer timer = new Timer(2000, event -> copyButton.setText("Copiar"));
            timer.setRepeats(false);
            timer.start();
        });
        passwordPanel.add(copyButton, BorderLayout.EAST);

        panel.add(passwordPanel, BorderLayout.CENTER);

        // Mensaje adicional
        JLabel messageLabel = new JLabel("Esta contraseña ha sido guardada en tu cuenta.");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(messageLabel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this,
                panel,
                "Recuperación Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}

class RegistrationWindow extends JFrame {

    private List<LoginRecoverSystem.Usuario> usuarios;
    private LoginRecoverSystem parentFrame;

    private JTextField nombreField;
    private JTextField nombre2Field;
    private JTextField apellidoField;
    private JTextField apellido2Field;
    private JTextField cedulaField;
    private JComboBox<String> cargoComboBox;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegistrationWindow(List<LoginRecoverSystem.Usuario> usuarios, LoginRecoverSystem parent) {
        this.usuarios = usuarios;
        this.parentFrame = parent;

        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.decode("#1e1e1e"));

        // Campos del formulario
        addLabelAndField(panel, "Primer Nombre:", nombreField = new JTextField());
        addLabelAndField(panel, "Segundo Nombre:", nombre2Field = new JTextField());
        addLabelAndField(panel, "Primer Apellido:", apellidoField = new JTextField());
        addLabelAndField(panel, "Segundo Apellido:", apellido2Field = new JTextField());
        addLabelAndField(panel, "Cédula:", cedulaField = new JTextField());

        // Combo box para cargo
        JLabel cargoLabel = new JLabel("Cargo:");
        cargoLabel.setForeground(Color.WHITE);
        panel.add(cargoLabel);

        String[] cargos = {"estudiante", "profesor", "empleado"};
        cargoComboBox = new JComboBox<>(cargos);
        cargoComboBox.setBackground(Color.decode("#B2B2B2"));
        panel.add(cargoComboBox);

        // Campos de contraseña
        addLabelAndPasswordField(panel, "Contraseña:", passwordField = new JPasswordField());
        addLabelAndPasswordField(panel, "Confirmar Contraseña:", confirmPasswordField = new JPasswordField());

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(Color.decode("#2a6c4e"));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this::registerUser);
        panel.add(registerButton);

        // Botón de cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(Color.decode("#6c2a2a"));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });
        panel.add(cancelButton);

        add(panel);
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        panel.add(label);

        textField.setBackground(Color.decode("#B2B2B2"));
        panel.add(textField);
    }

    private void addLabelAndPasswordField(JPanel panel, String labelText, JPasswordField passwordField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        panel.add(label);

        passwordField.setBackground(Color.decode("#B2B2B2"));
        panel.add(passwordField);
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

        // Validar campos vacíos
        if (nombre.isEmpty() || apellido.isEmpty() || cedulaStr.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los campos obligatorios",
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar cédula numérica
        if (!cedulaStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this,
                    "La cédula debe contener solo números",
                    "Cédula Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(cedulaStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar la cédula",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si la cédula ya existe
        for (LoginRecoverSystem.Usuario usuario : usuarios) {
            if (usuario.cedula == cedula) {
                JOptionPane.showMessageDialog(this,
                        "Esta cédula ya está registrada",
                        "Registro Fallido", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Verificar coincidencia de contraseñas
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Las contraseñas para registrarte no coinciden",
                    "Registro Fallido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Crear nuevo usuario
        LoginRecoverSystem.Usuario nuevoUsuario = new LoginRecoverSystem.Usuario();
        nuevoUsuario.nombre = nombre;
        nuevoUsuario.nombre2 = nombre2;
        nuevoUsuario.apellido = apellido;
        nuevoUsuario.apellido2 = apellido2;
        nuevoUsuario.cedula = cedula;
        nuevoUsuario.cargo = cargo;
        nuevoUsuario.pass = password;
        nuevoUsuario.saldo = 0.0;
        nuevoUsuario.user = "Common";

        // Añadir usuario a la lista
        usuarios.add(nuevoUsuario);

        // Guardar en JSON
        guardarUsuarioEnJSON(nuevoUsuario);

        JOptionPane.showMessageDialog(this,
                "Registro exitoso. Ahora puedes iniciar sesión con tu cédula y contraseña.",
                "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        this.dispose();
        parentFrame.setVisible(true);
    }

    private void guardarUsuarioEnJSON(LoginRecoverSystem.Usuario nuevoUsuario) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("DataBase.json"));
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();

            String newContent;
            if (jsonContent.length() > 0 && jsonContent.toString().trim().startsWith("[")) {
                String contentStr = jsonContent.toString().trim();
                if (contentStr.endsWith("]")) {
                    contentStr = contentStr.substring(0, contentStr.length() - 1);
                }

                if (contentStr.length() > 2) {
                    contentStr += ",";
                }

                contentStr += "\n  {\n";
                contentStr += "    \"nombre\": \"" + nuevoUsuario.nombre + "\",\n";
                contentStr += "    \"nombre2\": \"" + nuevoUsuario.nombre2 + "\",\n";
                contentStr += "    \"apellido\": \"" + nuevoUsuario.apellido + "\",\n";
                contentStr += "    \"apellido2\": \"" + nuevoUsuario.apellido2 + "\",\n";
                contentStr += "    \"cedula\": " + nuevoUsuario.cedula + ",\n";
                contentStr += "    \"cargo\": \"" + nuevoUsuario.cargo + "\",\n";
                contentStr += "    \"Pass\": \"" + nuevoUsuario.pass + "\",\n";
                contentStr += "    \"saldo\": " + nuevoUsuario.saldo + ",\n";
                contentStr += "    \"User\": \"Common\"\n"; // Nuevo campo con valor fijo
                contentStr += "  }";
                contentStr += "\n]";

                newContent = contentStr;
            } else {
                newContent = "[\n";
                newContent += "  {\n";
                newContent += "    \"nombre\": \"" + nuevoUsuario.nombre + "\",\n";
                newContent += "    \"nombre2\": \"" + nuevoUsuario.nombre2 + "\",\n";
                newContent += "    \"apellido\": \"" + nuevoUsuario.apellido + "\",\n";
                newContent += "    \"apellido2\": \"" + nuevoUsuario.apellido2 + "\",\n";
                newContent += "    \"cedula\": " + nuevoUsuario.cedula + ",\n";
                newContent += "    \"cargo\": \"" + nuevoUsuario.cargo + "\",\n";
                newContent += "    \"Pass\": \"" + nuevoUsuario.pass + "\",\n";
                newContent += "    \"saldo\": " + nuevoUsuario.saldo + ",\n";
                newContent += "    \"User\": \"Common\"\n"; // Nuevo campo con valor fijo
                newContent += "  }\n";
                newContent += "]";
            }

            FileWriter file = new FileWriter("DataBase.json");
            file.write(newContent);
            file.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar el usuario: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class ChangePasswordWindow extends JFrame {

    private List<LoginRecoverSystem.Usuario> usuarios;
    private LoginRecoverSystem parentFrame;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ciField;
    private JPasswordField currentPassField;
    private JPasswordField newPassField;
    private JPasswordField confirmPassField;

    public ChangePasswordWindow(List<LoginRecoverSystem.Usuario> usuarios, LoginRecoverSystem parent) {
        this.usuarios = usuarios;
        this.parentFrame = parent;

        setTitle("Cambio de Contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.decode("#1e1e1e"));

        // Campos del formulario
        addLabelAndField(panel, "Primer Nombre:", firstNameField = new JTextField());
        addLabelAndField(panel, "Primer Apellido:", lastNameField = new JTextField());
        addLabelAndField(panel, "Cédula:", ciField = new JTextField());
        addLabelAndPasswordField(panel, "Contraseña Actual:", currentPassField = new JPasswordField());
        addLabelAndPasswordField(panel, "Nueva Contraseña:", newPassField = new JPasswordField());
        addLabelAndPasswordField(panel, "Confirmar Nueva Contraseña:", confirmPassField = new JPasswordField());

        // Botón de cambiar contraseña
        JButton changeButton = new JButton("Cambiar Contraseña");
        changeButton.setFont(new Font("Arial", Font.BOLD, 14));
        changeButton.setBackground(Color.decode("#2a6c4e")); // Verde
        changeButton.setForeground(Color.WHITE);
        changeButton.addActionListener(this::changePassword);
        panel.add(changeButton);

        // Botón de cancelar
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setBackground(Color.decode("#6c2a2a")); // Rojo
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });
        panel.add(cancelButton);

        add(panel);
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        panel.add(label);

        textField.setBackground(Color.decode("#B2B2B2"));
        panel.add(textField);
    }

    private void addLabelAndPasswordField(JPanel panel, String labelText, JPasswordField passwordField) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        panel.add(label);

        passwordField.setBackground(Color.decode("#B2B2B2"));
        panel.add(passwordField);
    }

    private void changePassword(ActionEvent e) {
        // Obtener datos del formulario
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String ciStr = ciField.getText().trim();
        String currentPass = new String(currentPassField.getPassword());
        String newPass = new String(newPassField.getPassword());
        String confirmPass = new String(confirmPassField.getPassword());

        // Validar campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty() || ciStr.isEmpty()
                || currentPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
            showErrorMessage();
            return;
        }

        // Validar cédula numérica
        if (!ciStr.matches("\\d+")) {
            showErrorMessage();
            return;
        }

        int cedula;
        try {
            cedula = Integer.parseInt(ciStr);
        } catch (NumberFormatException ex) {
            showErrorMessage();
            return;
        }

        // Validar coincidencia de nuevas contraseñas
        if (!newPass.equals(confirmPass)) {
            showErrorMessage();
            return;
        }

        // Buscar usuario
        LoginRecoverSystem.Usuario usuario = null;
        for (LoginRecoverSystem.Usuario u : usuarios) {
            if (u.cedula == cedula
                    && u.nombre.equalsIgnoreCase(firstName)
                    && u.apellido.equalsIgnoreCase(lastName)
                    && u.pass.equals(currentPass)) {

                usuario = u;
                break;
            }
        }

        if (usuario == null) {
            showErrorMessage();
        } else {
            // Actualizar contraseña
            usuario.pass = newPass;

            // Actualizar JSON
            updatePasswordInJSON(usuario);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this,
                    "Contraseña cambiada exitosamente",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
            parentFrame.setVisible(true);
        }
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(this,
                "Si no recuerdas tus datos puedes recuperar tu Cuenta",
                "Error", JOptionPane.WARNING_MESSAGE);
    }

    private void updatePasswordInJSON(LoginRecoverSystem.Usuario usuario) {
        try (FileWriter file = new FileWriter("DataBase.json")) {
            file.write("[\n");

            for (int i = 0; i < usuarios.size(); i++) {
                LoginRecoverSystem.Usuario u = usuarios.get(i);
                file.write("  {\n");
                file.write("    \"nombre\": \"" + u.nombre + "\",\n");
                file.write("    \"nombre2\": \"" + u.nombre2 + "\",\n");
                file.write("    \"apellido\": \"" + u.apellido + "\",\n");
                file.write("    \"apellido2\": \"" + u.apellido2 + "\",\n");
                file.write("    \"cedula\": " + u.cedula + ",\n");
                file.write("    \"cargo\": \"" + u.cargo + "\",\n");
                file.write("    \"Pass\": \"" + u.pass + "\",\n");
                file.write("    \"saldo\": " + u.saldo + ",\n");
                file.write("    \"User\": \"" + (u.user != null ? u.user : "Common") + "\"\n");
                file.write("  }");

                if (i < usuarios.size() - 1) {
                    file.write(",");
                }
                file.write("\n");
            }

            file.write("]");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al actualizar la contraseña: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
