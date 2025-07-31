package main.Views.MenuAdmin;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;
import main.Controllers.MenuAdminController;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Window;

public class MenuAdminView {
    
    private Panel mainPanel;
    private Panel buttonPanel;
    private Panel userPanel;
    private Panel menusPanel;
    private JLabel adminName;
    private JLabel profileImageLabel;
    private Window prev;
    private JButton logOutButton;
    private final float DEFAULT_WIDTH = 80;
    private final float DEFAULT_HEIGHT = 80;
    private String adminCI;
    private Window frame;
    private MenuPanel menu1;
    private MenuPanel menu2;
    private MenuAdminController controller;
    
    public MenuAdminView(String adminCI, Window prev) {
        this.frame = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.adminCI = adminCI;
        this.controller = new MenuAdminController(this.adminCI);
        this.adminName = new JLabel(controller.getNombre());
        this.logOutButton = new JButton("Cerrar Sesión");
        this.profileImageLabel = new JLabel();
        this.prev = prev;
        
        // Inicializando Paneles
        this.mainPanel = new Panel(100.0f, 100.0f, this.frame.getSize());
        this.buttonPanel = new Panel(50.0f, 10.0f, this.mainPanel.getSize());
        this.userPanel = new Panel(30.0f, 20.0f, this.mainPanel.getSize());
        this.menusPanel = new Panel(70.0f, 70.0f, this.mainPanel.getSize());
        
        initFrame();
        initMenus();
        initButtons();
        initProfileImage();
        initComponents();
    }

    private void initFrame() {
        LayoutManager mainLayout = new BorderLayout();
        mainPanel.setLayout(mainLayout);
        mainPanel.getPanel().setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        this.frame.setPanel(mainPanel.panel);
        this.frame.setTitle("Menú Principal");
        this.frame.getFrame().setResizable(false);
    }

    private void initComponents() {
        LayoutManager userLayout = new FlowLayout(FlowLayout.LEADING);
        this.userPanel.setLayout(userLayout);
        this.userPanel.getPanel().add(this.adminName);
        this.userPanel.getPanel().add(this.profileImageLabel);
        this.mainPanel.getPanel().add(this.userPanel.getPanel(), BorderLayout.NORTH);
        this.mainPanel.getPanel().add(this.buttonPanel.getPanel(), BorderLayout.SOUTH);
        this.mainPanel.getPanel().add(this.menusPanel.getPanel(), BorderLayout.CENTER);
    }

    private void initButtons() {
        this.userPanel.getPanel().add(logOutButton);
        
        logOutButton.addActionListener(e -> {
            if (this.prev != null) {
                this.prev.setInstance();
            }
            this.frame.getFrame().dispose();
        });
    }

    private void initMenus() {
        LayoutManager menusLayout = new FlowLayout(FlowLayout.CENTER);
        menusPanel.setLayout(menusLayout);
        menu1 = new MenuPanel(1, this.menusPanel, this.controller);
        menu2 = new MenuPanel(2, this.menusPanel, this.controller);
        this.menusPanel.getPanel().add(menu1.getMenuPanel().getPanel());
        this.menusPanel.getPanel().add(menu2.getMenuPanel().getPanel());
    }

    private void initProfileImage() {
        profileImageLabel.setPreferredSize(new Dimension(100, 100));
        Border border = BorderFactory.createLineBorder(new Color(28, 138, 178), 2);
        profileImageLabel.setBorder(border);
        profileImageLabel.setOpaque(true);
        profileImageLabel.setBackground(Color.WHITE);

        loadProfileImage();
    }

    private void loadProfileImage() {
        try {
            int cedula = Integer.parseInt(adminCI);
            File imageFile = new File("DataBaseImg/" + cedula + ".jpg");
            if (!imageFile.exists()) {
                imageFile = new File("DataBaseImg/" + cedula + ".png");
            }
            if (imageFile.exists()) {
                ImageIcon profileImage = new ImageIcon(imageFile.getPath());
                Image scaledImage = profileImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                profileImageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                profileImageLabel.setText("Sin imagen");
            }
        } catch (NumberFormatException e) {
            profileImageLabel.setText("Cédula inválida");
        }
    }
    
    public void ShowMenuAdminView() {
        this.frame.setInstance();
    }
    
    public Window getWindow() {
        return this.frame;
    }

    public static void main(String[] args) {
        MenuAdminView view = new MenuAdminView("123456789", null);
        view.ShowMenuAdminView();
    }
}