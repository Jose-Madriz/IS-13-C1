package main.Controllers;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.Layouts.Panel;
import main.Views.MenuAdmin.MenuAdminView;
import main.Views.MenuAdmin.MenuPanel;

public class MenuAdminController {

    private DatabaseManager dbManager;
    protected Hashtable<String, String> adminData; // viene de la clase Modelo
    private Menu menus;

    public MenuAdminController( String adminCI){
        this.dbManager = DatabaseManager.getInstance();

        int ci = Integer.parseInt(adminCI);
        this.menus = new Menu();
        menus.fetchMenus(); 
        
        Usuario user = dbManager.buscarPorCI(ci);

        this.adminData = new Hashtable<String, String>( );
        this.adminData.put("nombre", user.getNombre());
        this.adminData.put("nombre2", user.getNombre2());
        this.adminData.put("apellido", user.getApellido());
        this.adminData.put("apellido2", user.getApellido2());
        this.adminData.put("cedula", String.valueOf(user.getCedula()));
    }
    // Consultar Menu
    public Menu getMenu( int menuNumber ){        
        
        switch (menuNumber) {
            case 1:
                return this.menus.menu1;
            case 2:
                return this.menus.menu2;
            default:
                System.err.println("El Numero de menu seleccionado no es valido");
                return  null;
        }
    }
    public void setMenu( int menuNumber, Menu menu ){
        switch (menuNumber) {
            case 1:
                this.menus.menu1 = menu;
                break;
            case 2:
                this.menus.menu2 = menu;
                break;
        }
        menus.rewriteMenus();
    }

    public void refreshMenus() {
        this.menus.fetchMenus();
    }

    public void deleteMenu(int menuNumber) {
        // Crea un nuevo objeto Menu con valores por defecto para representar un menú eliminado
        Menu emptyMenu = new Menu();
        emptyMenu.platillo = "No disponible";
        emptyMenu.turno = "-";
        emptyMenu.horario = "-";
        emptyMenu.calorias = 0.0;
        emptyMenu.precio = 0.0;

        // Utiliza la lógica existente de setMenu para reemplazar el menú y reescribir el archivo
        setMenu(menuNumber, emptyMenu);
    }
    // Consultar Datos de Usuario
    public Hashtable<String, String> getAdminData(  ){
        return this.adminData;
    }
    // Consultar nombre de Usuario
    public String getNombre(  ){
        return this.adminData.get("nombre") + " " 
             + this.adminData.get("nombre2") + " " 
             + this.adminData.get("apellido") + " " 
             + this.adminData.get("apellido2");
    }
    // DescontarSaldo
    public boolean descontarSaldo( Usuario user, double monto ){
        user.setSaldo(user.getSaldo() - monto);
        try{
            return dbManager.actualizarUsuario(user);
        } catch (Exception e){
            System.out.println("No se completo el descuento de saldo");
            return false;
        }
    }
    // TODO Cerrar Sesion

    public static void main( String args[] ){
        MenuAdminController controller = new MenuAdminController("123456789");
        System.out.println(controller.getNombre());
        System.out.println(controller.getAdminData());
        System.out.println(controller.getMenu(1)); 
        System.out.println(controller.getMenu(2));
    }
}
