package main.Views.CargarCostos;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import main.Controllers.CargarCostosController;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Size;
import main.Views.Layouts.Styles;
import main.Views.Layouts.Window;

public class CargarCostosView {
    private Panel mainPanel;
    public Window frame;
    private Panel formPanel;
    private Panel fieldsPanel;
    private JTextField CVField;
    private JTextField CFField;
    private JTextField NBField;
    private JTextField mermaField;
    private Panel buttonPanel;
    private JButton  VistaPreviaTrigger;
    private JButton guardarCambiosTrigger;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 40;
    private CargarCostosController controller;

    public CargarCostosView (){
        // inicializando los objetos de cada elemento grafico
        this.CFField = new JTextField(20);
        this.CVField = new JTextField(20);
        this.NBField = new JTextField(20);
        this.mermaField = new JTextField(20);
        this.VistaPreviaTrigger = new JButton("Vista Previa");
        this.guardarCambiosTrigger = new JButton("Guardar Cambios");
        this.frame = new Window( DEFAULT_WIDTH, DEFAULT_HEIGHT );

        // Inicializando los paneles 
        this.mainPanel = new Panel( 100.0f, 100.0f, this.frame.getSize() );
        this.formPanel = new Panel( 90.0f, 80.0f, this.mainPanel.getSize());
        this.buttonPanel = new Panel( 70.0f, 20.0f, this.formPanel.getSize() );
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
        this.frame.setTitle("Cargar Datos de Costos");
        this.frame.getFrame().setResizable(false);
        this.frame.setVisible(true);
        this.frame.getFrame().pack();
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
        
        Styles.stylizeButton( this.VistaPreviaTrigger, 0, buttonSize  );
        Styles.stylizeButton( this.guardarCambiosTrigger, 0, buttonSize  );
        
        buttonPanel.getPanel().add( this.VistaPreviaTrigger );
        buttonPanel.getPanel().add( this.guardarCambiosTrigger );

        this.formPanel.getPanel().add( buttonPanel.getPanel(), BorderLayout.CENTER );

        // Añadiendo un EventListeners a cada boton
        this.VistaPreviaTrigger.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e) {
                String cvValue = CVField.getText(); // Obtiene el texto del campo de usuario
                String cfValue = CFField.getText();
                String nbValue = NBField.getText();
                String mermaValue = mermaField.getText();

                Hashtable<String, String> data = new Hashtable<>();
                data.put("CV", cvValue);
                data.put("CF", cfValue);
                data.put("NB", nbValue);
                data.put("merma", mermaValue);
                
                // Validaciones 
                controller = new CargarCostosController( data );
                controller.ValidarDatos( frame );
                
            }
                 
        });
        this.guardarCambiosTrigger.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e) {
                
                // Enviar Datos al Sistema (Sucede en la clase controlador)
                // controller.CargarDatos();

                // Cerrar vista de Login
                frame.getFrame().dispose();

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
        Panel CVPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        Panel CFPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        Panel NBPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        Panel mermaPanel = new Panel ( 100.0f, 45.0f, this.fieldsPanel.getSize() );
        LayoutManager fieldManager = new GridLayout(
            2,
            1,
            300, 
            0
        );

        // agregamos un layour a cada panel de campo
        CVPanel.setLayout( fieldManager );
        CFPanel.setLayout( fieldManager );
        NBPanel.setLayout( fieldManager );
        mermaPanel.setLayout( fieldManager );
        
        // inicializamos tamaños 
        Size labelSize = new Size( 100.0f, 20.0f, CVPanel.getSize() );
        Size fieldsSize = new Size( 100.0f, 60.0f, CVPanel.getSize() );

        // inicializamos labels 
        JLabel CVLabel = new JLabel("CV:");
        JLabel CFLabel = new JLabel("CF:");
        JLabel NBLabel = new JLabel("NB:");
        JLabel mermaLabel = new JLabel("merma:");

        // agregando estilos a los Campos
        Styles.stylizeField( this.CVField, 0, fieldsSize );
        Styles.stylizeField( this.CFField, 0, fieldsSize );
        Styles.stylizeField( this.NBField, 0, fieldsSize );
        Styles.stylizeField( this.mermaField, 0, fieldsSize );
        
        // agregando estilos a los Labels de cada campo
        Styles.stylizeLabel( CVLabel, 0, labelSize );
        Styles.stylizeLabel( CFLabel, 0, labelSize );
        Styles.stylizeLabel( NBLabel, 0, labelSize );
        Styles.stylizeLabel( mermaLabel, 0, labelSize );

        // agregamos los elementos a los paneles correspondientes de cada campo y label
        CVPanel.getPanel().add(CVLabel, BorderLayout.NORTH);
        CVPanel.getPanel().add(this.CVField, BorderLayout.CENTER);
        CFPanel.getPanel().add(CFLabel, BorderLayout.NORTH);
        CFPanel.getPanel().add(this.CFField, BorderLayout.CENTER);
        NBPanel.getPanel().add(NBLabel, BorderLayout.NORTH);
        NBPanel.getPanel().add(this.NBField, BorderLayout.CENTER);
        mermaPanel.getPanel().add(mermaLabel, BorderLayout.NORTH);
        mermaPanel.getPanel().add(this.mermaField, BorderLayout.CENTER);

        // adgreganos ambos paneles de campo al panel general de campos
        fieldsPanel.getPanel().add( CVPanel.getPanel());
        fieldsPanel.getPanel().add( CFPanel.getPanel());
        fieldsPanel.getPanel().add( NBPanel.getPanel());
        fieldsPanel.getPanel().add( mermaPanel.getPanel());

        // agregamos el panel de formulario al panel de formulario
        this.formPanel.getPanel().add( fieldsPanel.getPanel(), BorderLayout.CENTER );
    }
    
    /**
     * Muestra la ventana instanciada de CargarCostosView
     */
    public void ShowCargarCostosView() {
        this.frame.setInstance();
    }
    public static void main(String[] args) {
        CargarCostosView test = new CargarCostosView();
        test.ShowCargarCostosView();
    }
}
