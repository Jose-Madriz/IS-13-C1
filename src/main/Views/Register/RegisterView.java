package main.Views.Register;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import main.Controllers.Register.RegisterController;
import main.Models.DatabaseManager;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Window;
import main.Views.Login.LoginView;

public class RegisterView {

    protected Panel mainPanel;
    protected Window frame;
    protected Panel formPanel;
    protected JTextField nombreField;
    protected JTextField nombre2Field;
    protected JTextField apellidoField;
    protected JTextField apellido2Field;
    protected JTextField cedulaField;
    protected JComboBox<String> cargoComboBox;
    protected JPasswordField passwordField;
    protected JPasswordField confirmPasswordField;
    protected JButton loginViewTrigger;
    protected JButton registerTrigger;
    protected JButton uploadImageButton;
    protected JLabel imageLabel;
    protected File selectedImageFile;
    protected RegisterController controller;
    protected DatabaseManager dbManager;
    protected LoginView loginView;
    protected final float DEFAULT_WIDTH = 50;
    protected final float DEFAULT_HEIGHT = 80;

    public RegisterView(LoginView loginView) {
        this.loginView = loginView;
        DatabaseManager dbManager = DatabaseManager.getInstance();
        this.controller = new RegisterController(dbManager);

        // Inicialización de campos
        this.nombreField = new JTextField(20);
        this.nombre2Field = new JTextField(20);
        this.apellidoField = new JTextField(20);
        this.apellido2Field = new JTextField(20);
        this.cedulaField = new JTextField(20);

        String[] cargos = {"Estudiante", "Profesor", "Trabajador"};
        this.cargoComboBox = new JComboBox<>(cargos);

        this.passwordField = new JPasswordField(20);
        this.confirmPasswordField = new JPasswordField(20);

        this.loginViewTrigger = new JButton("Cancelar");
        this.registerTrigger = new JButton("Registrarse");
        this.uploadImageButton = new JButton("Cargar Imagen *");
        this.imageLabel = new JLabel("Ninguna imagen seleccionada");

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
        this.frame.setTitle("Registro de Usuario");
        this.frame.getFrame().setResizable(false);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        // Estilizar botones
        registerTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        registerTrigger.setBackground(Color.decode("#3a9e6e"));
        registerTrigger.setForeground(Color.WHITE);
        registerTrigger.setFocusPainted(false);

        loginViewTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        loginViewTrigger.setBackground(Color.decode("#2e2e2e"));
        loginViewTrigger.setForeground(Color.WHITE);
        loginViewTrigger.setFocusPainted(false);

        uploadImageButton.setFont(new Font("Arial", Font.BOLD, 14));
        uploadImageButton.setBackground(Color.decode("#3a9e6e"));
        uploadImageButton.setForeground(Color.WHITE);
        uploadImageButton.setFocusPainted(false);

        // Efectos hover
        registerTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerTrigger.setBackground(Color.decode("#2a6c4e"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerTrigger.setBackground(Color.decode("#3a9e6e"));
            }
        });

        loginViewTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginViewTrigger.setBackground(Color.decode("#9e3a3a"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginViewTrigger.setBackground(Color.decode("#2e2e2e"));
            }
        });

        uploadImageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                uploadImageButton.setBackground(Color.decode("#2a6c4e"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                uploadImageButton.setBackground(Color.decode("#3a9e6e"));
            }
        });

        // ActionListener para cargar imagen
        uploadImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png"));
            int result = fileChooser.showOpenDialog(frame.getFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                imageLabel.setText(selectedImageFile.getName());
            }
        });

        // ActionListener para Registrarse
        registerTrigger.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String nombre2 = nombre2Field.getText().trim();
            String apellido = apellidoField.getText().trim();
            String apellido2 = apellido2Field.getText().trim();
            String cedula = cedulaField.getText().trim();
            String cargo = (String) cargoComboBox.getSelectedItem();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            // Llamada al método validateRegistrationData con el parámetro imageFile
            if (controller.validateRegistrationData(frame, nombre, nombre2, apellido, apellido2,
                    cedula, cargo, password, confirmPassword, selectedImageFile)) {

                if (controller.registerUser(frame, nombre, nombre2, apellido, apellido2,
                        cedula, cargo, password, selectedImageFile)) {

                    JOptionPane.showMessageDialog(frame.getFrame(),
                            "¡Registro exitoso! Ahora puedes iniciar sesión",
                            "Registro Completado", JOptionPane.INFORMATION_MESSAGE);

                    frame.getFrame().dispose();
                    loginView.getFrame().setVisible(true);
                }
            }
        });

        // ActionListener para Cancelar
        loginViewTrigger.addActionListener(e -> {
            frame.getFrame().dispose();
            loginView.getFrame().setVisible(true);
        });

        buttonPanel.add(registerTrigger);
        buttonPanel.add(loginViewTrigger);

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

        // Campo de cargo
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

        addRequiredField(fieldsContainer, "Contraseña*:", passwordField);
        addRequiredField(fieldsContainer, "Confirmar Contraseña*:", confirmPasswordField);

        // Campo para la imagen
        JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
        imagePanel.setOpaque(false);
        imagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JLabel imageLabelTitle = new JLabel("Imagen de Perfil*:");
        imageLabelTitle.setForeground(Color.BLACK);
        imageLabel.setForeground(Color.BLACK);
        imagePanel.add(imageLabelTitle, BorderLayout.NORTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(uploadImageButton, BorderLayout.EAST);
        fieldsContainer.add(imagePanel);
        fieldsContainer.add(Box.createRigidArea(new Dimension(0, 5)));

        this.formPanel.getPanel().add(fieldsContainer, BorderLayout.CENTER);
    }

    private void addRequiredField(JPanel container, String labelText, JComponent field) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.BLACK);

        if (field instanceof JTextField) {
            ((JTextField) field).setBackground(Color.WHITE);
            ((JTextField) field).setForeground(Color.BLACK);
            ((JTextField) field).setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        } else if (field instanceof JPasswordField) {
            ((JPasswordField) field).setBackground(Color.WHITE);
            ((JPasswordField) field).setForeground(Color.BLACK);
            ((JPasswordField) field).setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        }

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);

        container.add(panel);
        container.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public void ShowRegisterView() {
        this.frame.setInstance();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegisterView registerView = new RegisterView(null);
            registerView.ShowRegisterView();
        });
    }
}