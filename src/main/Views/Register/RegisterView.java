package main.Views.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Views.Layouts.Window;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Size;
import main.Views.Layouts.Styles;

// TODO: Cambiar la fuente y el tamaño de texto

/**
 * Clase principal que crea y muestra la ventana de login en Java Swing.
 */
public class RegisterView {

    Panel mainPanel;
    Window frame;
    Panel formPanel;
    Panel fieldsPanel;
    JPasswordField passwordField;
    JPasswordField confirmPasswordField;
    JTextField CIField;
    Panel buttonPanel;
    JButton  loginViewTrigger;
    JButton registerTrigger;
    JDialog dialogScreen;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 50;
    

    public RegisterView (){
        this.CIField = new JTextField(20);
        this.passwordField = new JPasswordField(20);
        this.confirmPasswordField = new JPasswordField(20);
        this.loginViewTrigger = new JButton("Iniciar Sesión");
        this.registerTrigger = new JButton("Registrarse");
        this.frame = new Window( DEFAULT_WIDTH, DEFAULT_HEIGHT );
        this.mainPanel = new Panel( 100.0f, 100.0f, this.frame.getSize() );
        this.formPanel = new Panel( 90.0f, 90.0f, this.mainPanel.getSize());
        this.buttonPanel = new Panel( 50.0f, 20.0f, this.formPanel.getSize() );
        this.fieldsPanel = new Panel( 70.0f, 60.f, this.formPanel.getSize() );
        
        this.initComponents();
    }

    /*
     * ConFigura e inicializa todos los componentes de esta vista 
     */
    private void initComponents(){

        LayoutManager formLayout = new FlowLayout(
            FlowLayout.CENTER,
            10,
            10
        );
        this.formPanel.setLayout( formLayout );

        this.initFrame();
        this.initFields();
        this.initButtons();

        this.mainPanel.getPanel().add( this.formPanel.getPanel(), BorderLayout.CENTER );
        // this.initDialog();
        this.frame.setInstance();
    }

    /*
     * ConFigura e inicializa la ventana 
     */
    private void initFrame(){
        LayoutManager mainLayout = new FlowLayout(
            FlowLayout.CENTER,
            10,
            10
        );
        mainPanel.setLayout( mainLayout );
        mainPanel.getPanel().setBorder( BorderFactory.createEmptyBorder(20, 50, 20, 50 ) );

        this.frame.setPanel( mainPanel.panel );
        this.frame.setTitle("Iniciar Sesion");
        this.frame.getFrame().setResizable(false);
        this.frame.setVisible(true);
    }

    /*
     * ConFigura e inicializa los botones 
     */
    private void initButtons(){
        Size buttonSize = new Size( 40.0f, 100.0f, this.buttonPanel.getSize() );
        
        LayoutManager buttonLayout = new FlowLayout( 
            3, 
            15, 
            10 
        );
        buttonPanel.setLayout(buttonLayout);
        
        Styles.stylizeButton( this.loginViewTrigger, 0, buttonSize  );
        Styles.stylizeButton( this.registerTrigger, 0, buttonSize  );    
        
        buttonPanel.getPanel().add( this.loginViewTrigger );
        buttonPanel.getPanel().add( this.registerTrigger );

        this.formPanel.getPanel().add( buttonPanel.getPanel(), BorderLayout.CENTER );

        // Añadiendo un EventListeners a cada boton
        this.registerTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userCI = CIField.getText(); // Obtiene el texto del campo de usuario
                // Obtiene la contraseña de forma segura como un array de caracteres y la convierte a String.
                String password = new String(passwordField.getPassword());
                String passwordConfirm = new String( confirmPasswordField.getPassword());

                if(password.length() < 8){
                    JOptionPane.showMessageDialog(frame.getFrame(), "La contraseña debe tener al menos 8 caracteres.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if ( password.equals(passwordConfirm) == false ){
                    // Si las credenciales son incorrectas, muestra un mensaje de error.
                    JOptionPane.showMessageDialog(frame.getFrame(), "Las Contraseñas no son iguales.", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                }

                // Lógica de validación 
                // validateCredentials es una llamada a la clase Controlador
                if ( /*validateCI(userCI) && validatePassword(password) &&*/ password.equals(passwordConfirm)) {
                    // Si las credenciales son correctas, muestra un mensaje de éxito.
                    JOptionPane.showMessageDialog(frame.getFrame(), "Registro exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                    frame.getFrame().dispose(); // Cierra la ventana de login
                } else {
                    JOptionPane.showMessageDialog(frame.getFrame(), ".", "Error de Registro: Cedula de Identidad invalida", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.loginViewTrigger.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getFrame().dispose();

            }
        });
    }
    
    /**
     * Inicializa los campos de entrada del usuario
     */
    private void initFields(){
        LayoutManager fieldsLayout = new GridLayout( 
            3, 
            1, 
            40, 
            10 
        );
        fieldsPanel.setLayout( fieldsLayout );
        

        Panel CIPanel = new Panel ( 100.0f, 30.0f, this.fieldsPanel.getSize() );
        Panel passwordPanel = new Panel ( 100.0f, 30.0f, this.fieldsPanel.getSize() );
        Panel confirmPasswordPanel = new Panel ( 100.0f, 30.0f, this.fieldsPanel.getSize() );
        LayoutManager fieldManager = new GridLayout(
            2,
            1,
            300, 
            0
        );

        CIPanel.setLayout( fieldManager );
        passwordPanel.setLayout( fieldManager );
        confirmPasswordPanel.setLayout( fieldManager );

        JLabel CILabel = new JLabel("Cedula de Identidad:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        JLabel confirmPasswordLabel = new JLabel("Confirmar Contraseña:");
        
        Size labelSize = new Size( 100.0f, 20.0f, passwordPanel.getSize() );
        Size fieldsSize = new Size( 100.0f, 60.0f, passwordPanel.getSize() );


        // agregando estilos a los Campos
        Styles.stylizeField( this.CIField, 0, fieldsSize );
        Styles.stylizeField( this.passwordField, 0, fieldsSize );
        Styles.stylizeField( this.confirmPasswordField, 0, fieldsSize );
        
        // agregando estilos a los Labels de cada campo
        Styles.stylizeLabel( CILabel, 0, labelSize );
        Styles.stylizeLabel( passwordLabel, 0, labelSize );
        Styles.stylizeLabel( confirmPasswordLabel, 0, labelSize );
        

        CIPanel.getPanel().add(CILabel, BorderLayout.NORTH);
        CIPanel.getPanel().add(this.CIField, BorderLayout.CENTER);
        passwordPanel.getPanel().add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.getPanel().add(this.passwordField, BorderLayout.CENTER);
        confirmPasswordPanel.getPanel().add(confirmPasswordLabel, BorderLayout.CENTER);
        confirmPasswordPanel.getPanel().add(this.confirmPasswordField, BorderLayout.CENTER);
        

        fieldsPanel.getPanel().add( CIPanel.getPanel());
        fieldsPanel.getPanel().add( passwordPanel.getPanel());
        fieldsPanel.getPanel().add( confirmPasswordPanel.getPanel());

        this.formPanel.getPanel().add( fieldsPanel.getPanel(), BorderLayout.CENTER );
    }
    
    /**
     * Muestra la ventana instanciada de RegisterView
     */
    public void ShowRegisterView() {
        this.frame.setInstance();
    }

    public static void main(String[] args){
        RegisterView test = new RegisterView();
        test.ShowRegisterView();
    }
}