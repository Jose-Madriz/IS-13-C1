package main.Models.Login;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;
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
        
        formPanel.add(loginButton);
        
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
        
        formPanel.add(recoverButton);
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
            // Leer el archivo JSON
            BufferedReader reader = new BufferedReader(new FileReader("DataBase.json"));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            reader.close();
            
            // Parsear el JSON manualmente
            String jsonString = jsonContent.toString().trim();
            
            // Eliminar corchetes exteriores
            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1);
            }
            
            // Dividir en objetos individuales
            String[] userObjects = jsonString.split("\\},\\s*\\{");
            
            for (String userObj : userObjects) {
                // Normalizar el formato del objeto
                userObj = userObj.replaceAll("^\\{", "").replaceAll("\\}$", "");
                
                // Crear nuevo usuario
                Usuario usuario = new Usuario();
                
                // Dividir en pares clave-valor
                String[] fields = userObj.split(",");
                
                for (String field : fields) {
                    // Dividir cada campo en clave y valor
                    String[] parts = field.split(":");
                    if (parts.length != 2) continue;
                    
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
                    }
                }
                
                // Agregar usuario a la lista
                usuarios.add(usuario);
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
            JOptionPane.showMessageDialog(this, 
                "¡Bienvenido " + usuarioEncontrado.nombre + " " + usuarioEncontrado.apellido + "!\n" +
                "Cargo: " + usuarioEncontrado.cargo + "\n" +
                "Saldo: $" + usuarioEncontrado.saldo, 
                "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
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
    
    private void openRecoveryWindow() {
        RecoveryWindow recoveryWindow = new RecoveryWindow(usuarios, this);
        recoveryWindow.setVisible(true);
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
    private JTextField cargoField;
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
        
        // Efecto hover para el botón
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
        
        // Efecto hover para el botón de regresar
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
        
        // Campo Cargo
        cargoField = new JTextField("");
        cargoField.setBounds(223, 169, 230, 21);
        cargoField.setFont(new Font("Arial", Font.PLAIN, 14));
        cargoField.setBackground(Color.decode("#B2B2B2"));
        cargoField.setForeground(Color.decode("#656565"));
        cargoField.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        setPlaceholder(cargoField, "Ingresa Tu Cargo");
        panel.add(cargoField);
        
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
        infoArea.setText("Hola! Veo que estás recuperando tu cuenta. " +
                         "Ingresa los datos solicitados, y si coinciden " +
                         "te generaremos una nueva contraseña. " +
                         "En cargo recuerda escribir todo en minúscula " +
                         "(estudiante, profesor, trabajador). " +
                         "Recuerda escribir tu nombre con la inicial en mayúscula.");
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
        String cargo = cargoField.getText().trim().toLowerCase();
        
        // Validar campos
        if (firstName.isEmpty() || lastName.isEmpty() || ci.isEmpty() || cargo.isEmpty() ||
            firstName.equals("Ingresa tu Primer Nombre") || 
            lastName.equals("Ingresa tu Primer Apellido") ||
            ci.equals("Ingresa tu cedula") || 
            cargo.equals("Ingresa Tu Cargo")) {
            
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
            if (usuario.cedula == cedula && 
                usuario.nombre.equalsIgnoreCase(firstName) && 
                usuario.apellido.equalsIgnoreCase(lastName) && 
                usuario.cargo.equalsIgnoreCase(cargo)) {
                
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
            
            for (int i = 0; i < usuarios.size(); i++) {
                LoginRecoverSystem.Usuario u = usuarios.get(i);
                file.write("  {\n");
                file.write("    \"nombre\": \"" + u.nombre + "\",\n");
                file.write("    \"nombre2\": \"" + u.nombre2 + "\",\n");
                file.write("    \"apellido\": \"" + u.apellido + "\",\n");
                file.write("    \"apellido2\": \"" + u.apellido2 + "\",\n");
                file.write("    \"cedula\": " + u.cedula + ",\n");
                file.write("    \"cargo\": \"" + u.cargo + "\",\n");
                
                // Actualizar la contraseña solo para este usuario
                if (u.cedula == usuario.cedula) {
                    file.write("    \"Pass\": \"" + usuario.pass + "\",\n");
                } else {
                    file.write("    \"Pass\": \"" + u.pass + "\",\n");
                }
                
                file.write("    \"saldo\": " + u.saldo + "\n");
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