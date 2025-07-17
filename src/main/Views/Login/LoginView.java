package main.Views.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.Views.Layouts.Window;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Size;
import main.Views.Layouts.Styles;

// TODO: Cambiar la fuente y el tamaño de texto
// TODO: Como cambiar el boton de Registrarse por un label con "hipervinculo"?
// TODO: Crear EventListeners para las vistas
// TODO: Arreglar cambio de tamaño en los Botones

/**
 * Clase principal que crea y muestra la ventana de login en Java Swing.
 */
public class LoginView {

    Panel mainPanel;
    Window frame;
    Panel formPanel;
    Panel fieldsPanel;
    JPasswordField passwordField;
    JTextField CIField;
    Panel buttonPanel;
    JButton  loginTrigger;
    JButton registerTrigger;
    JDialog dialogScreen;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 40;
    

    public LoginView (){
        this.CIField = new JTextField(20);
        this.passwordField = new JPasswordField(20); 
        this.loginTrigger = new JButton("Iniciar Sesión");
        this.registerTrigger = new JButton("Registrarse");
        this.frame = new Window( DEFAULT_WIDTH, DEFAULT_HEIGHT );
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
        
        this.loginTrigger.setSize( buttonSize.getDimension() );
        this.registerTrigger.setSize( buttonSize.getDimension() );        
        
        buttonPanel.getPanel().add( this.loginTrigger );
        buttonPanel.getPanel().add( this.registerTrigger );

        this.formPanel.getPanel().add( buttonPanel.getPanel(), BorderLayout.CENTER );
    }
    
    /**
     * Inicializa los campos de entrada del usuario
     */
    private void initFields(){
        LayoutManager fieldsLayout = new GridLayout( 
            2, 
            1, 
            40, 
            10 
        );
        fieldsPanel.setLayout( fieldsLayout );
        

        Panel CIPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        Panel passwordPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        LayoutManager fieldManager = new GridLayout(
            2,
            1,
            300, 
            0
        );

        CIPanel.setLayout( fieldManager );
        passwordPanel.setLayout( fieldManager );

        JLabel CILabel = new JLabel("Cedula de Identidad:");
        JLabel passwordLabel = new JLabel("Contraseña:");
        
        Size labelSize = new Size( 100.0f, 20.0f, passwordPanel.getSize() );
        Size fieldsSize = new Size( 100.0f, 60.0f, passwordPanel.getSize() );


        this.CIField.setSize( fieldsSize.getDimension() );
        this.passwordField.setSize( fieldsSize.getDimension() );
        CILabel.setSize( labelSize.getDimension() );
        passwordLabel.setSize( labelSize.getDimension() );
        

        CIPanel.getPanel().add(CILabel, BorderLayout.NORTH);
        CIPanel.getPanel().add(this.CIField, BorderLayout.CENTER);
        passwordPanel.getPanel().add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.getPanel().add(this.passwordField, BorderLayout.CENTER);
        

        fieldsPanel.getPanel().add( CIPanel.getPanel());
        fieldsPanel.getPanel().add( passwordPanel.getPanel());

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