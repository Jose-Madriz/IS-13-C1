package main.Views.Login;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Views.Layouts.Window;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Size;
import main.Views.Layouts.Styles;

// TODO: Como cambiar el boton de Registrarse por un label con "hipervinculo"?
// TODO: Arreglar cambio de tamaño en los Botones

/**
 * Clase principal que crea y muestra la ventana de login en Java Swing.
 */
public class LoginView {

    private Panel mainPanel;
    public Window frame;
    private Panel formPanel;
    private Panel fieldsPanel;
    private JPasswordField passwordField;
    private JTextField CIField;
    private Panel buttonPanel;
    private JButton  loginTrigger;
    private JButton registerTrigger;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 40;
    

    public LoginView (){
        // inicializando los objetos de cada elemento grafico
        this.CIField = new JTextField(20);
        this.passwordField = new JPasswordField(20); 
        this.loginTrigger = new JButton("Iniciar Sesión");
        this.registerTrigger = new JButton("Registrarse");
        this.frame = new Window( DEFAULT_WIDTH, DEFAULT_HEIGHT );

        // Inicializando los paneles 
        this.mainPanel = new Panel( 100.0f, 100.0f, this.frame.getSize() );
        this.formPanel = new Panel( 90.0f, 80.0f, this.mainPanel.getSize());
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
            20
        );
        this.formPanel.setLayout( formLayout );

        this.initFrame();
        this.initFields();
        this.initButtons();

        this.mainPanel.getPanel().add( this.formPanel.getPanel(), BorderLayout.CENTER );

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
        Size buttonSize = new Size( 60.0f, 100.0f, this.buttonPanel.getSize() );
        
        LayoutManager buttonLayout = new FlowLayout( 
            3, 
            15, 
            10 
        );
        buttonPanel.setLayout(buttonLayout);
        
        Styles.stylizeButton( this.loginTrigger, 0, buttonSize  );
        Styles.stylizeButton( this.registerTrigger, 0, buttonSize  );
        
        buttonPanel.getPanel().add( this.loginTrigger );
        buttonPanel.getPanel().add( this.registerTrigger );

        this.formPanel.getPanel().add( buttonPanel.getPanel(), BorderLayout.CENTER );

        // Añadiendo un EventListeners a cada boton
        this.loginTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userCI = CIField.getText(); // Obtiene el texto del campo de usuario
                // Obtiene la contraseña de forma segura como un array de caracteres y la convierte a String.
                String password = new String(passwordField.getPassword());

                // Validaciones
                String errorValues = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                for( int i = 0; i < userCI.length(); i++ ){
                    if( errorValues.indexOf( userCI.charAt(i) ) != -1 ){
                        JOptionPane.showMessageDialog( frame.getFrame(), "La Cedula es invalida.", "Error de Inicio de Sesion", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if( userCI.equals( "" ) || password.equals("") ){
                    JOptionPane.showMessageDialog(frame.getFrame(), "Campos Incompletos", "Error de Inicio de Sesion", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // validateCredentials es una llamada a la clase Controlador
                if ( /*validateCredentials()*/ true) {
                    // Si las credenciales son correctas, muestra un mensaje de éxito.
                    JOptionPane.showMessageDialog(frame.getFrame(), "¡Inicio de sesión exitoso!", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
                    // Aquí podrías cerrar la ventana de login y abrir la ventana principal de tu aplicación.
                    frame.getFrame().dispose(); // Cierra la ventana de login
                } else {
                    // Si las credenciales son incorrectas, muestra un mensaje de error.
                    JOptionPane.showMessageDialog(frame.getFrame(), "Cedula de Identidad o contraseña incorrectos.", "Error de Inicio de Sesion", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.registerTrigger.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e) {
                
                // Cerrar vista de Login
                frame.getFrame().dispose();

                // Llamar a la vista de Registro (Sucede en la clase controlador)
            }
        });
    }
    
    /**
     * Inicializa los campos de entrada del usuario
     */
    private void initFields(){
        // creamos un layout manager para panel general de campo
        LayoutManager fieldsLayout = new GridLayout( 
            2, 
            1, 
            40, 
            10 
        );
        // agregamos el layout al panel general de campos
        fieldsPanel.setLayout( fieldsLayout );

        // inicializamos paneles para cada campo
        Panel CIPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        Panel passwordPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        LayoutManager fieldManager = new GridLayout(
            2,
            1,
            300, 
            0
        );

        // agregamos un layour a cada panel de campo
        CIPanel.setLayout( fieldManager );
        passwordPanel.setLayout( fieldManager );
        
        // inicializamos tamaños 
        Size labelSize = new Size( 100.0f, 20.0f, passwordPanel.getSize() );
        Size fieldsSize = new Size( 100.0f, 60.0f, passwordPanel.getSize() );

        // inicializamos labels 
        JLabel CILabel = new JLabel("Cedula de Identidad:");
        JLabel passwordLabel = new JLabel("Contraseña:");

        // agregando estilos a los Campos
        Styles.stylizeField( this.CIField, 0, fieldsSize );
        Styles.stylizeField( this.passwordField, 0, fieldsSize );
        
        // agregando estilos a los Labels de cada campo
        Styles.stylizeLabel( CILabel, 0, labelSize );
        Styles.stylizeLabel( passwordLabel, 0, labelSize );

        // agregamos los elementos a los paneles correspondientes de cada campo y label
        CIPanel.getPanel().add(CILabel, BorderLayout.NORTH);
        CIPanel.getPanel().add(this.CIField, BorderLayout.CENTER);
        passwordPanel.getPanel().add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.getPanel().add(this.passwordField, BorderLayout.CENTER);

        // adgreganos ambos paneles de campo al panel general de campos
        fieldsPanel.getPanel().add( CIPanel.getPanel());
        fieldsPanel.getPanel().add( passwordPanel.getPanel());

        // agregamos el panel de formulario al panel de formulario
        this.formPanel.getPanel().add( fieldsPanel.getPanel(), BorderLayout.CENTER );
    }
    
    /**
     * Muestra la ventana instanciada de LoginView
     */
    public void ShowLoginView() {
        this.frame.setInstance();
    }

    public static void main(String[] args){
        LoginView test = new LoginView();
        test.ShowLoginView();
    }
}