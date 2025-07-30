package main.Views.MenuAdmin;

import javax.swing.*;
import javax.swing.JTextField;

import main.Controllers.CargarCostosController;
import main.Controllers.MenuAdminController;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.MenuAdmin.*;
import main.Views.Layouts.*;

public class MenuAdminView {
    
    private Panel mainPanel;
    private Panel buttonPanel;
    private Panel userPanel;
    private Panel menusPanel;
    
    private JButton editMenu1;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 40;
    private String adminCI;
    private Window frame;
    private Menu menu1 = new Menu();
    private Menu menu2 = new Menu();
    private MenuAdminController controller;
    
    public MenuAdminView( String adminCI ){

        this.frame = new Window();
        this.adminCI = adminCI;

    }
}