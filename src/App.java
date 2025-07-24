import main.Views.Login.LoginView;
import main.Views.Register.RegisterView;

public class App {
    public static void main(String args[]){
        LoginView login = new LoginView();
        RegisterView register = new RegisterView();

        login.ShowLoginView();
    }
}
