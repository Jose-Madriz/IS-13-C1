package main.Controllers;

import java.util.Hashtable;
import main.Models.DatabaseManager;
import main.Models.Usuario;
import main.Models.ModeloMenuPrincipal.Menu;

public class MenuAdminController {

    private DatabaseManager dbManager;
    protected Hashtable<String, String> data; // viene de la clase Modelo
    private Menu menus;

    public MenuAdminController( String adminCI){
        this.dbManager = DatabaseManager.getInstance();

        int ci = Integer.parseInt(adminCI);

        Usuario user = dbManager.buscarPorCI(ci);
        Menu menus = new Menu();
        menus.fetchMenus(); 
        this.data = new Hashtable<String, String>( );
        this.data.put("nombre", user.getNombre());
        this.data.put("nombre2", user.getNombre2());
        this.data.put("apellido", user.getApellido());
        this.data.put("apellido2", user.getApellido2());
        this.data.put("cedula", String.valueOf(user.getCedula()));
    }
    // TODO Consultar Menu
    public Menu getMenu( int menuNumber ){
        switch(menuNumber){
            case 1:
                return menus.menu1;
            case 2:
                return menus.menu2;
            default:
                return null;
        }
    }
    
    public Hashtable<String, String> getData(  ){
        return this.data;
    }
    // TODO Consultar nombre de Usuario
    public String getNombre(  ){
        return this.data.get("nombre") + " " 
             + this.data.get("nombre2") + " " 
             + this.data.get("apellido") + " " 
             + this.data.get("apellido2");
    }
    // TODO: DescontarSaldo
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
}
