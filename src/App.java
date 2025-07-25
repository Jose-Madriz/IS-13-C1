import javax.swing.SwingUtilities;
import main.Views.Login.LoginView;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Solo iniciamos la vista de Login
            LoginView login = new LoginView();
            login.showLoginView();
        });
    }
}