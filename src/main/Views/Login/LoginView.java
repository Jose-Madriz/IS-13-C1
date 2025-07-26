package main.Views.Login;

import java.awt.*;
import javax.swing.*;
import main.Controllers.Login.LoginController;
import main.Models.DatabaseManager;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Window;
import main.Views.Recover.RecoverPasswordView;
import main.Views.Register.RegisterView;

public class LoginView {

    private Panel mainPanel;
    public Window frame;
    private Panel formPanel;
    private Panel fieldsPanel;
    private JToggleButton showPassButton;
    private JPasswordField passwordField;
    private JTextField CIField;
    private Panel buttonPanel;
    private JButton loginTrigger;
    private JButton registerTrigger;
    private JButton recoverTrigger;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 50;
    private LoginController controller;
    private DatabaseManager dbManager;

    public LoginView() {
        // Inicializar DatabaseManager y Controller
        DatabaseManager dbManager = DatabaseManager.getInstance();
        this.controller = new LoginController(dbManager);

        // Inicializando componentes gráficos
        this.CIField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.passwordField.setEchoChar('•'); // Ensure password field masks input
        this.loginTrigger = new JButton("Iniciar Sesión");
        this.registerTrigger = new JButton("¿No tienes cuenta? Regístrate");
        this.recoverTrigger = new JButton("Recuperar Contraseña");
        this.frame = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Inicializando paneles 
        this.mainPanel = new Panel(100.0f, 100.0f, this.frame.getSize());
        this.formPanel = new Panel(90.0f, 80.0f, this.mainPanel.getSize());
        this.buttonPanel = new Panel(50.0f, 20.0f, this.formPanel.getSize());
        this.fieldsPanel = new Panel(70.0f, 60.f, this.formPanel.getSize());

        this.initComponents();
    }

    private void initComponents() {
        // Cambiamos a BorderLayout para mejor organización
        this.formPanel.setLayout(new BorderLayout(10, 20));
        this.formPanel.getPanel().setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.initFrame();
        this.initFields();
        this.initButtons();

        this.mainPanel.getPanel().add(this.formPanel.getPanel(), BorderLayout.CENTER);
        this.frame.setInstance();
    }

    private void initFrame() {
        LayoutManager mainLayout = new FlowLayout(
                FlowLayout.CENTER,
                10,
                10
        );
        mainPanel.setLayout(mainLayout);
        mainPanel.getPanel().setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        this.frame.setPanel(mainPanel.panel);
        this.frame.setTitle("Iniciar Sesion");
        this.frame.getFrame().setResizable(false);
        this.frame.setVisible(true);
    }

    private void initButtons() {
        // Panel principal para botones con BoxLayout vertical
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Botón de login
        loginTrigger.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginTrigger.setPreferredSize(new Dimension(200, 40));
        loginTrigger.setMaximumSize(new Dimension(200, 40));
        loginTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        loginTrigger.setBackground(new Color(58, 158, 110));
        loginTrigger.setForeground(Color.WHITE);
        loginTrigger.setFocusPainted(false);

        // Botón de registro (como enlace)
        registerTrigger.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerTrigger.setBorderPainted(false);
        registerTrigger.setContentAreaFilled(false);
        registerTrigger.setForeground(new Color(0, 100, 200));
        registerTrigger.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerTrigger.setFont(new Font("Arial", Font.PLAIN, 12));

        // Botón de recuperación de contraseña (como enlace)
        recoverTrigger.setAlignmentX(Component.CENTER_ALIGNMENT);
        recoverTrigger.setBorderPainted(false);
        recoverTrigger.setContentAreaFilled(false);
        recoverTrigger.setForeground(new Color(0, 100, 200));
        recoverTrigger.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        recoverTrigger.setFont(new Font("Arial", Font.PLAIN, 12));

        // Efecto hover para el botón de registro
        registerTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerTrigger.setForeground(new Color(0, 70, 200));
                registerTrigger.setText("¿No tienes cuenta? Regístrate aquí");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerTrigger.setForeground(new Color(0, 100, 200));
                registerTrigger.setText("¿No tienes cuenta? Regístrate aquí");
            }
        });

        // Efecto hover para el botón de recuperación
        recoverTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                recoverTrigger.setForeground(new Color(0, 70, 200));
                recoverTrigger.setText("Recuperar Contraseña");
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                recoverTrigger.setForeground(new Color(0, 100, 200));
                recoverTrigger.setText("Recuperar Contraseña");
            }
        });

        // Añadir botones al panel
        buttonsPanel.add(loginTrigger);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonsPanel.add(registerTrigger);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        buttonsPanel.add(recoverTrigger);

        // Añadir panel de botones al formulario
        this.formPanel.getPanel().add(buttonsPanel, BorderLayout.SOUTH);

        // ActionListeners
        loginTrigger.addActionListener(e -> {
            String ci = CIField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (!controller.validateData(frame, ci, password)) {
                return;
            }

            if (controller.validateUser(frame, ci, password)) {
                frame.getFrame().dispose();
                // Aquí iría la apertura de la siguiente ventana
            }
        });

        registerTrigger.addActionListener(e -> {
            frame.getFrame().setVisible(false);
            RegisterView registerView = new RegisterView(LoginView.this);
            registerView.ShowRegisterView();
        });

        recoverTrigger.addActionListener(e -> {
            frame.getFrame().setVisible(false);
            RecoverPasswordView recoverView = new RecoverPasswordView(LoginView.this);
            recoverView.showRecoverView();
        });
    }

    private void initFields() {
        JPanel fieldsContainer = new JPanel();
        fieldsContainer.setLayout(new BoxLayout(fieldsContainer, BoxLayout.Y_AXIS));
        fieldsContainer.setOpaque(false);

        // Campo CI
        JPanel ciPanel = new JPanel(new BorderLayout(5, 5));
        ciPanel.setOpaque(false);
        ciPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel ciLabel = new JLabel("Cédula de Identidad:");
        ciLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        CIField = new JTextField(20);
        CIField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        CIField.setFont(new Font("Arial", Font.PLAIN, 12));

        ciPanel.add(ciLabel, BorderLayout.NORTH);
        ciPanel.add(CIField, BorderLayout.CENTER);

        // Campo Contraseña
        JPanel passPanel = new JPanel(new BorderLayout(5, 5));
        passPanel.setOpaque(false);
        passPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));

        // Botón para mostrar contraseña
        showPassButton = new JToggleButton("Mostrar");
        showPassButton.setFont(new Font("Arial", Font.PLAIN, 10));
        showPassButton.setPreferredSize(new Dimension(80, 30)); // Tamaño fijo para el botón
        showPassButton.setMaximumSize(new Dimension(80, 30));
        showPassButton.addActionListener(e -> {
            if (showPassButton.isSelected()) {
                passwordField.setEchoChar((char) 0);
                showPassButton.setText("Ocultar");
            } else {
                passwordField.setEchoChar('•');
                showPassButton.setText("Mostrar");
            }
        });

        // Panel para el campo de contraseña y el botón, con márgenes
        JPanel passFieldPanel = new JPanel(new BorderLayout(5, 0));
        passFieldPanel.setOpaque(false);
        passFieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5)); // Margen derecho
        passFieldPanel.add(passwordField, BorderLayout.CENTER);
        passFieldPanel.add(showPassButton, BorderLayout.EAST);

        passPanel.add(passLabel, BorderLayout.NORTH);
        passPanel.add(passFieldPanel, BorderLayout.CENTER);

        // Añadir campos al contenedor
        fieldsContainer.add(ciPanel);
        fieldsContainer.add(Box.createRigidArea(new Dimension(0, 15)));
        fieldsContainer.add(passPanel);

        this.formPanel.getPanel().add(fieldsContainer, BorderLayout.CENTER);
    }

    public void showLoginView() {
        this.frame.setInstance();
    }

    public void setVisible(boolean visible) {
        frame.getFrame().setVisible(visible);
    }

    public Window getFrame() {
        return this.frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.showLoginView();
        });
    }
}
