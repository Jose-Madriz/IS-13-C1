package main.Views.Change;

import java.awt.*;
import javax.swing.*;
import main.Controllers.Change.ChangePassController;
import main.Models.DatabaseManager;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Window;
import main.Views.MenuUser.MenuPrincipal;

public class ChangePassView {
    private Panel mainPanel;
    private Window frame;
    private Panel formPanel;
    private JTextField cedulaField;
    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmNewPasswordField;
    private JButton saveTrigger;
    private JButton cancelTrigger;
    private ChangePassController controller;
    private MenuPrincipal menuPrincipal;
    private final float DEFAULT_WIDTH = 35;
    private final float DEFAULT_HEIGHT = 55;

    public ChangePassView(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        DatabaseManager dbManager = DatabaseManager.getInstance();
        this.controller = new ChangePassController(dbManager);

        // Inicializar campos
        this.cedulaField = new JTextField(20);
        this.currentPasswordField = new JPasswordField(20);
        this.newPasswordField = new JPasswordField(20);
        this.confirmNewPasswordField = new JPasswordField(20);
        this.saveTrigger = new JButton("Guardar Cambios");
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
        this.frame.setTitle("Cambiar Contraseña");
        this.frame.getFrame().setResizable(false);
    }

    private void initButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        // Estilizar botones
        saveTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        saveTrigger.setBackground(Color.decode("#3a9e6e"));
        saveTrigger.setForeground(Color.WHITE);
        saveTrigger.setFocusPainted(false);

        cancelTrigger.setFont(new Font("Arial", Font.BOLD, 14));
        cancelTrigger.setBackground(Color.decode("#2e2e2e"));
        cancelTrigger.setForeground(Color.WHITE);
        cancelTrigger.setFocusPainted(false);

        // Efectos hover
        saveTrigger.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveTrigger.setBackground(Color.decode("#2a6c4e"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveTrigger.setBackground(Color.decode("#3a9e6e"));
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

        // ActionListener para Guardar
        saveTrigger.addActionListener(e -> {
            String cedula = cedulaField.getText().trim();
            String currentPassword = new String(currentPasswordField.getPassword()).trim();
            String newPassword = new String(newPasswordField.getPassword()).trim();
            String confirmNewPassword = new String(confirmNewPasswordField.getPassword()).trim();

            if (controller.changePassword(frame, cedula, currentPassword, newPassword, confirmNewPassword)) {
                JOptionPane.showMessageDialog(frame.getFrame(),
                        "¡Contraseña cambiada exitosamente!",
                        "Cambio Exitoso", JOptionPane.INFORMATION_MESSAGE);
                frame.getFrame().dispose();
                menuPrincipal.setVisible(true);
            }
        });

        // ActionListener para Cancelar
        cancelTrigger.addActionListener(e -> {
            frame.getFrame().dispose();
            menuPrincipal.setVisible(true);
        });

        buttonPanel.add(saveTrigger);
        buttonPanel.add(cancelTrigger);

        this.formPanel.getPanel().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void initFields() {
        JPanel fieldsContainer = new JPanel();
        fieldsContainer.setLayout(new BoxLayout(fieldsContainer, BoxLayout.Y_AXIS));
        fieldsContainer.setOpaque(false);

        addRequiredField(fieldsContainer, "Cédula*:", cedulaField);
        addRequiredField(fieldsContainer, "Contraseña Actual*:", currentPasswordField);
        addRequiredField(fieldsContainer, "Nueva Contraseña*:", newPasswordField);
        addRequiredField(fieldsContainer, "Confirmar Nueva Contraseña*:", confirmNewPasswordField);

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

    public void showChangePasswordView() {
        this.frame.setInstance();
    }
}