package main.Views.Recover;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.*;
import main.Controllers.Recover.RecoverPasswordController;
import main.Models.DatabaseManager;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Window;
import main.Views.Login.LoginView;

public class RecoverPasswordView {
    private Panel mainPanel;
    private Window frame;
    private Panel formPanel;
    private JTextField nombreField;
    private JTextField nombre2Field;
    private JTextField apellidoField;
    private JTextField apellido2Field;
    private JTextField cedulaField;
    private JComboBox<String> cargoComboBox;
    private JTextField generatedPasswordField;
    private JButton copyPasswordButton;
    private JButton recoverTrigger;
    private JButton cancelTrigger;
    private RecoverPasswordController controller;
    private LoginView loginView;
    private final float DEFAULT_WIDTH = 45;
    private final float DEFAULT_HEIGHT = 70;

    public RecoverPasswordView(LoginView loginView) {
        this.loginView = loginView;
        DatabaseManager dbManager = DatabaseManager.getInstance();
        this.controller = new RecoverPasswordController(dbManager);

        // Initialize fields
        this.nombreField = new JTextField(20);
        this.nombre2Field = new JTextField(20);
        this.apellidoField = new JTextField(20);
        this.apellido2Field = new JTextField(20);
        this.cedulaField = new JTextField(20);
        
        String[] cargos = {"Estudiante", "Profesor", "Trabajador"};
        this.cargoComboBox = new JComboBox<>(cargos);
        
        this.generatedPasswordField = new JTextField(20);
        this.generatedPasswordField.setEditable(false);
        this.generatedPasswordField.setVisible(false); // Hidden until password is generated
        this.copyPasswordButton = new JButton("Copiar");
        this.copyPasswordButton.setEnabled(false); // Disabled until password is generated
        this.copyPasswordButton.setVisible(false); // Hidden until password is generated
        this.recoverTrigger = new JButton("Recuperar Contraseña");
        this.cancelTrigger = new JButton("Cancelar");
        
        this.frame = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.mainPanel = new Panel(100.0f, 100.0f, this.frame.getSize());
        this.formPanel = new Panel(95.0f, 95.0f, this.mainPanel.getSize());
        
        this.initComponents();
    }

    private void initComponents() {
        this.formPanel.setLayout(new BorderLayout(10, 10));
        this.formPanel.getPanel().setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        this.formPanel.getPanel().setBackground(Color.decode("#D9D9D9"));

        this.initFrame();
        this.initFields();
        this.initButtons();

        this.mainPanel.getPanel().add(this.formPanel.getPanel(), BorderLayout.CENTER);
    }

    private void initFrame() {
        this.mainPanel.setLayout(new BorderLayout());
        this.frame.setPanel(mainPanel.panel);
        this.frame.setTitle("Recuperar Contraseña");
        this.frame.getFrame().setResizable(false);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);
        
        // Style buttons
        recoverTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        recoverTrigger.setBackground(Color.decode("#3a9e6e"));
        recoverTrigger.setForeground(Color.WHITE);
        recoverTrigger.setFocusPainted(false);
        
        cancelTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        cancelTrigger.setBackground(Color.decode("#2e2e2e"));
        cancelTrigger.setForeground(Color.WHITE);
        cancelTrigger.setFocusPainted(false);
        
        copyPasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        copyPasswordButton.setBackground(Color.decode("#4a90e2"));
        copyPasswordButton.setForeground(Color.WHITE);
        copyPasswordButton.setFocusPainted(false);
        
        // Hover effects
        recoverTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                recoverTrigger.setBackground(Color.decode("#2a6c4e"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                recoverTrigger.setBackground(Color.decode("#3a9e6e"));
            }
        });
        
        cancelTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelTrigger.setBackground(Color.decode("#9e3a3a"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelTrigger.setBackground(Color.decode("#2e2e2e"));
            }
        });
        
        copyPasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                copyPasswordButton.setBackground(Color.decode("#357abd"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                copyPasswordButton.setBackground(Color.decode("#4a90e2"));
            }
        });
        
        // ActionListener for Recover
        recoverTrigger.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String nombre2 = nombre2Field.getText().trim();
            String apellido = apellidoField.getText().trim();
            String apellido2 = apellido2Field.getText().trim();
            String cedula = cedulaField.getText().trim();
            String cargo = (String) cargoComboBox.getSelectedItem();

            String newPassword = controller.recoverPassword(frame, nombre, nombre2, apellido, apellido2, cedula, cargo);
            if (newPassword != null) {
                generatedPasswordField.setText(newPassword);
                generatedPasswordField.setVisible(true);
                copyPasswordButton.setEnabled(true);
                copyPasswordButton.setVisible(true);
                formPanel.getPanel().revalidate();
                formPanel.getPanel().repaint();
            }
        });
        
        // ActionListener for Copy
        copyPasswordButton.addActionListener(e -> {
            String password = generatedPasswordField.getText();
            if (!password.isEmpty()) {
                StringSelection stringSelection = new StringSelection(password);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "Contraseña copiada al portapapeles",
                        "Copiado", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // ActionListener for Cancel
        cancelTrigger.addActionListener(e -> {
            frame.getFrame().dispose();
            loginView.getFrame().setVisible(true);
        });
        
        buttonPanel.add(recoverTrigger);
        buttonPanel.add(copyPasswordButton);
        buttonPanel.add(cancelTrigger);
        
        this.formPanel.getPanel().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initFields() {
        JPanel fieldsContainer = new JPanel();
        fieldsContainer.setLayout(new BoxLayout(fieldsContainer, BoxLayout.Y_AXIS));
        fieldsContainer.setOpaque(false);
        
        addRequiredField(fieldsContainer, "Primer Nombre*:", nombreField);
        addRequiredField(fieldsContainer, "Segundo Nombre:", nombre2Field);
        addRequiredField(fieldsContainer, "Primer Apellido*:", apellidoField);
        addRequiredField(fieldsContainer, "Segundo Apellido:", apellido2Field);
        addRequiredField(fieldsContainer, "Cédula*:", cedulaField);
        
        // Cargo field
        JPanel cargoPanel = new JPanel(new BorderLayout(5, 5));
        cargoPanel.setOpaque(false);
        cargoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel cargoLabel = new JLabel("Cargo*:");
        cargoLabel.setForeground(Color.BLACK);
        cargoComboBox.setBackground(Color.WHITE);
        cargoComboBox.setForeground(Color.BLACK);
        cargoComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        cargoPanel.add(cargoLabel, BorderLayout.NORTH);
        cargoPanel.add(cargoComboBox, BorderLayout.CENTER);
        fieldsContainer.add(cargoPanel);
        fieldsContainer.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Generated password field
        JPanel passwordPanel = new JPanel(new BorderLayout(5, 5));
        passwordPanel.setOpaque(false);
        passwordPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel passwordLabel = new JLabel("Nueva Contraseña:");
        passwordLabel.setForeground(Color.BLACK);
        generatedPasswordField.setBackground(Color.WHITE);
        generatedPasswordField.setForeground(Color.BLACK);
        generatedPasswordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(generatedPasswordField, BorderLayout.CENTER);
        fieldsContainer.add(passwordPanel);
        
        this.formPanel.getPanel().add(fieldsContainer, BorderLayout.CENTER);
    }
    
    private void addRequiredField(JPanel container, String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);
        
        if (field instanceof JTextField) {
            ((JTextField)field).setBackground(Color.WHITE);
            ((JTextField)field).setForeground(Color.BLACK);
            ((JTextField)field).setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        }
        
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        
        container.add(panel);
        container.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public void showRecoverView() {
        this.frame.setInstance();
    }
}