package main.Controllers;

import java.util.Hashtable;
import javax.swing.JOptionPane;
import main.Models.DatabaseManager;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Models.Usuario;

public class MenuAdminController {

    private DatabaseManager dbManager;
    protected Hashtable<String, String> adminData;
    private Menu menus;

    public MenuAdminController(String adminCI) {
        this.dbManager = DatabaseManager.getInstance();

        int ci;
        try {
            ci = Integer.parseInt(adminCI);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cédula inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            ci = 0; // Valor por defecto para evitar errores
        }

        this.menus = new Menu();
        menus.fetchMenus();

        // Verificar que los menús estén inicializados
        if (menus.menu1 == null) {
            menus.menu1 = createDefaultMenu();
        }
        if (menus.menu2 == null) {
            menus.menu2 = createDefaultMenu();
        }

        Usuario user = dbManager.buscarPorCI(ci);
        if (user == null) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            this.adminData = new Hashtable<>();
            this.adminData.put("nombre", "Administrador");
            this.adminData.put("nombre2", "");
            this.adminData.put("apellido", "Desconocido");
            this.adminData.put("apellido2", "");
            this.adminData.put("cedula", String.valueOf(ci));
        } else {
            this.adminData = new Hashtable<>();
            this.adminData.put("nombre", user.getNombre());
            this.adminData.put("nombre2", user.getNombre2());
            this.adminData.put("apellido", user.getApellido());
            this.adminData.put("apellido2", user.getApellido2());
            this.adminData.put("cedula", String.valueOf(user.getCedula()));
        }
    }

    private Menu createDefaultMenu() {
        Menu defaultMenu = new Menu();
        defaultMenu.platillo = "No disponible";
        defaultMenu.turno = "-";
        defaultMenu.horario = "-";
        defaultMenu.calorias = 0.0;
        defaultMenu.precio = 0.0;
        return defaultMenu;
    }

    public Menu getMenu(int menuNumber) {
        switch (menuNumber) {
            case 1:
                return this.menus.menu1;
            case 2:
                return this.menus.menu2;
            default:
                JOptionPane.showMessageDialog(null, "Número de menú inválido: " + menuNumber, "Error", JOptionPane.ERROR_MESSAGE);
                return createDefaultMenu();
        }
    }

    public void setMenu(int menuNumber, Menu menu) {
        switch (menuNumber) {
            case 1:
                this.menus.menu1 = menu;
                break;
            case 2:
                this.menus.menu2 = menu;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Número de menú inválido: " + menuNumber, "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        menus.rewriteMenus();
    }

    public void refreshMenus() {
        this.menus.fetchMenus();
        // Re-verificar que los menús no sean null después de actualizar
        if (menus.menu1 == null) {
            menus.menu1 = createDefaultMenu();
        }
        if (menus.menu2 == null) {
            menus.menu2 = createDefaultMenu();
        }
    }

    public void deleteMenu(int menuNumber) {
        Menu emptyMenu = createDefaultMenu();
        setMenu(menuNumber, emptyMenu);
    }

    public Hashtable<String, String> getAdminData() {
        return this.adminData;
    }

    public String getNombre() {
        return this.adminData.get("nombre") + " " 
             + this.adminData.get("nombre2") + " " 
             + this.adminData.get("apellido") + " " 
             + this.adminData.get("apellido2");
    }

    public boolean descontarSaldo(Usuario user, double monto) {
        user.setSaldo(user.getSaldo() - monto);
        try {
            return dbManager.actualizarUsuario(user);
        } catch (Exception e) {
            System.out.println("No se completó el descuento de saldo: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        MenuAdminController controller = new MenuAdminController("123456789");
        System.out.println(controller.getNombre());
        System.out.println(controller.getAdminData());
        System.out.println(controller.getMenu(1)); 
        System.out.println(controller.getMenu(2));
    }
}