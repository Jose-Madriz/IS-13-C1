package main.Models.LogRegReco.Refactorizacion.Recover;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import main.Models.LogRegReco.Refactorizacion.Login.LoginSystem;
import main.Models.LogRegReco.Refactorizacion.Usuario;

public class RecoverySystem extends JFrame {
    private List<Usuario> usuarios;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField ciField;
    private JComboBox<String> cargoComboBox;
    private LoginSystem parentFrame;

    // Constructor único y completo
    public RecoverySystem(List<Usuario> usuarios, LoginSystem parent) {
        this.usuarios = usuarios;
        this.parentFrame = parent;
        
        initComponents();
        setupWindow();
    }

    private void initComponents() {
        setTitle("Recuperar Cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(672, 363);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        setupButtons(panel);
        setupFields(panel);
        setupInfoArea(panel);

        add(panel);
    }

    private void setupWindow() {
        setVisible(true);
    }

    private void setupButtons(JPanel panel) {
        // Botón Continuar
        JButton continueButton = createButton("Continuar", 280, 217, this::verifyRecovery);
        panel.add(continueButton);

        // Botón Regresar
        JButton backButton = createButton("Regresar", 280, 250, e -> {
            this.dispose();
            parentFrame.setVisible(true);
        });
        panel.add(backButton);
    }

    private JButton createButton(String text, int x, int y, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 106, 29);
        button.setBackground(Color.decode("#2e2e2e"));
        button.setForeground(Color.decode("#D9D9D9"));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        button.setFocusPainted(false);
        button.addActionListener(listener);

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.decode("#232323"));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.decode("#2e2e2e"));
            }
        });
        
        return button;
    }

    private void setupFields(JPanel panel) {
        // Campo Cedula
        ciField = createTextField("Ingresa tu cedula", 228, 134);
        panel.add(ciField);

        // Campo Cargo
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
        firstNameField = createTextField("Ingresa tu Primer Nombre", 227, 67);
        panel.add(firstNameField);

        // Campo Primer Apellido
        lastNameField = createTextField("Ingresa tu Primer Apellido", 224, 102);
        panel.add(lastNameField);
    }

    private JTextField createTextField(String placeholder, int x, int y) {
        JTextField textField = new JTextField("");
        textField.setBounds(x, y, 227, 21);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.decode("#B2B2B2"));
        textField.setForeground(Color.decode("#656565"));
        textField.setBorder(BorderFactory.createLineBorder(Color.decode("#979797"), 1));
        setPlaceholder(textField, placeholder);
        return textField;
    }

    private void setupInfoArea(JPanel panel) {
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
        
        if (firstName.isEmpty() || lastName.isEmpty() || ci.isEmpty() || cargo.isEmpty()
                || firstName.equals("Ingresa tu Primer Nombre")
                || lastName.equals("Ingresa tu Primer Apellido")
                || ci.equals("Ingresa tu cedula")) {

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
        Usuario usuarioRecuperado = null;
        for (Usuario usuario : usuarios) {
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

    private void actualizarPasswordEnJSON(Usuario usuario) {
        try (FileWriter file = new FileWriter("DataBase.json")) {
            file.write("[\n");

            boolean first = true;
            for (Usuario u : usuarios) {
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

    private void mostrarNuevaPassword(String nuevaPassword) {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel passwordLabel = new JLabel("Tu nueva contraseña es:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(passwordLabel, BorderLayout.NORTH);

        JPanel passwordPanel = new JPanel(new BorderLayout(10, 10));

        JTextField passwordField = new JTextField(nuevaPassword);
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Arial", Font.BOLD, 16));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        JButton copyButton = new JButton("Copiar");
        copyButton.setFont(new Font("Arial", Font.PLAIN, 12));
        copyButton.addActionListener(ev -> {
            StringSelection stringSelection = new StringSelection(nuevaPassword);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            copyButton.setText("¡Copiada!");
            Timer timer = new Timer(2000, event -> copyButton.setText("Copiar"));
            timer.setRepeats(false);
            timer.start();
        });
        passwordPanel.add(copyButton, BorderLayout.EAST);

        panel.add(passwordPanel, BorderLayout.CENTER);

        JLabel messageLabel = new JLabel("Esta contraseña ha sido guardada en tu cuenta.");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panel.add(messageLabel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this,
                panel,
                "Recuperación Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}