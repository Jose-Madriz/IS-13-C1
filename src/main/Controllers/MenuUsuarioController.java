package main.Controllers;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.Models.ModeloMenuPrincipal.Menu;


public class MenuUsuarioController{

    public MenuUsuarioController controller;
    public Menu menu1;
    public Menu menu2;

    //Controlador
    public void CargarDatos(Menu menu1,Menu menu2){
        Menu menus = new Menu();
        menus.fetchMenus();
        this.menu1 = menus.menu1;
        this.menu2 = menus.menu2;
    }
  
    
}
