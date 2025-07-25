import javax.swing.SwingUtilities;
import main.Models.DatabaseManager;
import main.Views.Login.LoginView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Solo iniciamos la vista de Login
            DatabaseManager dbManager = DatabaseManager.getInstance();
            LoginView login = new LoginView();
            login.showLoginView();
        });
    }
}