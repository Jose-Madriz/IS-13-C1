package main.Views.MenuAdmin;


import javax.swing.*;
import java.awt.*;

import main.Controllers.MenuAdminController;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Window;

public class MenuAdminView {
    
    private Panel mainPanel;
    private Panel buttonPanel;
    private Panel userPanel;
    private Panel menusPanel;
    private JLabel adminName;
    private Window prev;
    
    private JButton logOutButton;

    private final float DEFAULT_WIDTH = 80;
    private final float DEFAULT_HEIGHT = 80;
    private String adminCI;
    private Window frame;
    private MenuPanel menu1;
    private MenuPanel menu2;
    private MenuAdminController controller;
    
    public MenuAdminView( String adminCI, Window prev ){

        this.frame = new Window( DEFAULT_WIDTH, DEFAULT_HEIGHT );
        this.adminCI = adminCI;
        this.controller = new MenuAdminController(this.adminCI);
        this.adminName = new JLabel( controller.getNombre() );
        this.logOutButton = new JButton("Cerrar Sesion");
        this.prev = prev;
        
        // Inicializando Paneles
        this.mainPanel = new Panel( 100.0f, 100.0f, this.frame.getSize() );
        this.buttonPanel = new Panel( 50.0f, 10.0f, this.mainPanel.getSize() );
        this.userPanel = new Panel( 30.0f, 20.f, this.mainPanel.getSize() );
        this.menusPanel = new Panel( 70.0f, 70.f, this.mainPanel.getSize() );
        
        initFrame();
        initMenus();
        initButtons();
        initComponents();
    }

    private void initFrame(){
        LayoutManager mainLayout = new BorderLayout();

        mainPanel.setLayout( mainLayout );
        mainPanel.getPanel().setBorder( BorderFactory.createEmptyBorder(20, 50, 20, 50 ) );

        this.frame.setPanel( mainPanel.panel );
        
        this.frame.setTitle("Menu Principal");
        this.frame.getFrame().setResizable(false);
    }
    /*
     * añade todos los componentes a sus paneles
     */
    private void initComponents(){
        LayoutManager userLayout = new FlowLayout(FlowLayout.LEADING); 

        this.userPanel.setLayout(userLayout);
        this.userPanel.getPanel().add(this.adminName);

        this.mainPanel.getPanel().add(this.userPanel.getPanel(), BorderLayout.NORTH); 
        this.mainPanel.getPanel().add(this.buttonPanel.getPanel(), BorderLayout.SOUTH);
        this.mainPanel.getPanel().add(this.menusPanel.getPanel(), BorderLayout.CENTER);
    }

    /*
     * ConFigura e inicializa los botones 
     */
    private void initButtons(){
        // Botones del panel de usuario (superior)
        this.userPanel.getPanel().add(logOutButton);
        
        logOutButton.addActionListener(e -> {
            if (this.prev != null) {
                this.prev.setInstance();
            }
            this.frame.getFrame().dispose();
        });
    }
    /**
     * Inicializa los campos de entrada del usuario
     */
    private void initMenus(){
        // creamos un layout manager para panel general de campo
        LayoutManager menusLayout = new FlowLayout( 
            FlowLayout.CENTER
        );
        // agregamos el layout al panel general de campos
        menusPanel.setLayout( menusLayout );

        // inicializamos paneles para cada Menu
        menu1 = new MenuPanel(1, this.menusPanel, this.controller);
        menu2 = new MenuPanel(2, this.menusPanel, this.controller);

        this.menusPanel.getPanel().add(menu1.getMenuPanel().getPanel());
        this.menusPanel.getPanel().add(menu2.getMenuPanel().getPanel());
    }
    
    /**
     * Muestra la ventana instanciada de CargarCostosView
     */
    public void ShowMenuAdminView() {
        this.frame.setInstance();
    }
    
    public Window getWindow(){
        return this.frame;
    }

    public static void main( String[] args ){
        MenuAdminView view = new MenuAdminView( "123456789", null );

        view.ShowMenuAdminView();
    }
}