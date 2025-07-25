package main.Views.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Views.Layouts.Window;
import main.Controllers.Login.LoginController;
import main.Models.Login.DatabaseManager;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Size;
import main.Views.Layouts.Styles;

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
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 40;
    private LoginController controller;
    private DatabaseManager dbManager;

    public LoginView() {
        // Inicializar DatabaseManager y Controller
        this.dbManager = new DatabaseManager();
        this.controller = new LoginController(dbManager);

        // Inicializando componentes gráficos
        this.CIField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.loginTrigger = new JButton("Iniciar Sesión");
        this.registerTrigger = new JButton("Registrarse");
        this.frame = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Inicializando paneles 
        this.mainPanel = new Panel(100.0f, 100.0f, this.frame.getSize());
        this.formPanel = new Panel(90.0f, 80.0f, this.mainPanel.getSize());
        this.buttonPanel = new Panel(50.0f, 20.0f, this.formPanel.getSize());
        this.fieldsPanel = new Panel(70.0f, 60.f, this.formPanel.getSize());

        this.initComponents();
    }

    private void initComponents() {
        LayoutManager formLayout = new FlowLayout(
                FlowLayout.CENTER,
                10,
                20
        );
        this.formPanel.setLayout(formLayout);

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
        Size buttonSize = new Size(60.0f, 100.0f, this.buttonPanel.getSize());

        LayoutManager buttonLayout = new FlowLayout(
                3,
                15,
                10
        );
        buttonPanel.setLayout(buttonLayout);

        Styles.stylizeButton(this.loginTrigger, 0, buttonSize);
        Styles.stylizeButton(this.registerTrigger, 0, buttonSize);

        buttonPanel.getPanel().add(this.loginTrigger);
        buttonPanel.getPanel().add(this.registerTrigger);

        this.formPanel.getPanel().add(buttonPanel.getPanel(), BorderLayout.CENTER);

        // ActionListener para el botón de login
        this.loginTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ci = CIField.getText();
                String password = new String(passwordField.getPassword());

                if (!controller.validateData(frame, ci, password)) {
                    return;
                }

                if (controller.validateUser(frame, ci, password)) {
                    frame.getFrame().dispose();
                    // Aquí iría la apertura de la siguiente ventana
                }
            }
        });

        // ActionListener para el botón de registro
        this.registerTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getFrame().dispose();
                // Aquí iría la apertura de la vista de registro
            }
        });
    }

    private void initFields() {
        LayoutManager fieldsLayout = new GridLayout(
                2,
                1,
                40,
                10
        );
        fieldsPanel.setLayout(fieldsLayout);

        Panel CIPanel = new Panel(100.0f, 45.0f, this.fieldsPanel.getSize());
        Panel passwordPanel = new Panel(100.0f, 45.0f, this.fieldsPanel.getSize());
        LayoutManager fieldManager = new GridLayout(
                2,
                1,
                300,
                0
        );

        CIPanel.setLayout(fieldManager);
        passwordPanel.setLayout(fieldManager);

        Size labelSize = new Size(100.0f, 20.0f, passwordPanel.getSize());
        Size fieldsSize = new Size(100.0f, 60.0f, passwordPanel.getSize());

        JLabel CILabel = new JLabel("Cedula de Identidad:");
        JLabel passwordLabel = new JLabel("Contraseña:");

        Styles.stylizeField(this.CIField, 0, fieldsSize);
        Styles.stylizeField(this.passwordField, 0, fieldsSize);

        Styles.stylizeLabel(CILabel, 0, labelSize);
        Styles.stylizeLabel(passwordLabel, 0, labelSize);

        CIPanel.getPanel().add(CILabel, BorderLayout.NORTH);
        CIPanel.getPanel().add(this.CIField, BorderLayout.CENTER);

        // Panel para contraseña con botón de visibilidad
        JPanel passwordContainer = new JPanel(new BorderLayout());
        passwordContainer.setOpaque(false);
        passwordContainer.setBorder(new EmptyBorder(0, 0, 0, 0));

        passwordContainer.add(passwordField, BorderLayout.CENTER);

        // Botón para mostrar/ocultar contraseña
        this.showPassButton = new JToggleButton("Ver");
        this.showPassButton.setFont(new Font("Arial", Font.PLAIN, 10));
        this.showPassButton.addActionListener(e -> {
            if (showPassButton.isSelected()) {
                passwordField.setEchoChar((char) 0);
                showPassButton.setText("Ocultar");
            } else {
                passwordField.setEchoChar('•');
                showPassButton.setText("Ver");
            }
        });

        JPanel buttonWrapper = new JPanel(new BorderLayout());
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(showPassButton, BorderLayout.EAST);
        passwordContainer.add(buttonWrapper, BorderLayout.EAST);

        passwordPanel.getPanel().add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.getPanel().add(passwordContainer, BorderLayout.CENTER);

        fieldsPanel.getPanel().add(CIPanel.getPanel());
        fieldsPanel.getPanel().add(passwordPanel.getPanel());

        this.formPanel.getPanel().add(fieldsPanel.getPanel(), BorderLayout.CENTER);
    }

    public void showLoginView() {
        this.frame.setInstance();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.showLoginView();
        });
    }
}
