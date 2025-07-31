package main.Views.VerificarUsuario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.Controllers.VerificarUsuarioController;
import main.Views.Layouts.Panel;
import main.Views.Layouts.Size;
import main.Views.Layouts.Styles;
import main.Views.Layouts.Window;
import main.Models.ModeloMenuPrincipal.Menu;

public class VerificarUsuarioView {
    private Panel mainPanel;
    public Window frame;
    private Panel formPanel;
    private Panel fieldsPanel;
    private JButton uploadFileButton;
    private JLabel selectedFileLabel;
    private File selectedFile;
    private JTextField cedulaField;
    private JTextField resultadoField;
    private Panel buttonPanel;
    private JButton verificarButton;
    private JButton cancelarButton;
    private final float DEFAULT_WIDTH = 30;
    private final float DEFAULT_HEIGHT = 40;
    private VerificarUsuarioController controller;
    private Menu menu;

    public VerificarUsuarioView( Menu menu ) {
        // inicializando los objetos de cada elemento grafico
        this.uploadFileButton = new JButton("Seleccionar Archivo");
        this.selectedFileLabel = new JLabel("Ningún archivo...");
        this.cedulaField = new JTextField(20);
        this.resultadoField = new JTextField(20);
        this.verificarButton = new JButton("Verificar Usuario");
        this.cancelarButton = new JButton("Cancelar");
        this.frame = new Window(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.menu = menu;
        this.controller = new VerificarUsuarioController(this, menu);

        // Inicializando los paneles 
        this.mainPanel = new Panel(100.0f, 100.0f, this.frame.getSize());
        this.formPanel = new Panel(90.0f, 80.0f, this.mainPanel.getSize());
        this.buttonPanel = new Panel(80.0f, 15.0f, this.formPanel.getSize());
        this.fieldsPanel = new Panel(70.0f, 60.f, this.formPanel.getSize());
        
        this.initComponents();
    }

    /**
     * Configura e inicializa todos los componentes de esta vista 
     */
    private void initComponents() {
        LayoutManager formLayout = new FlowLayout(
            FlowLayout.CENTER,
            10,
            20
        );
        
        this.formPanel.setLayout(formLayout);

        this.initFrame();
        this.initFields();
        this.initButtons();

        this.mainPanel.getPanel().add(this.formPanel.getPanel(), BorderLayout.CENTER);

        this.frame.setInstance();
    }

    /**
     * Configura e inicializa la ventana 
     */
    private void initFrame() {
        LayoutManager mainLayout = new FlowLayout(
            FlowLayout.CENTER,
            10,
            10
        );
        mainPanel.setLayout(mainLayout);
        mainPanel.getPanel().setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        this.frame.setPanel(mainPanel.panel);
        this.frame.setTitle("Verificar Usuario");
        this.frame.getFrame().setResizable(false);
        this.frame.setVisible(true);
    }

    /**
     * Configura e inicializa los botones 
     */
    private void initButtons() {
        Size buttonSize = new Size(45.0f, 100.0f, this.buttonPanel.getSize());
        
        LayoutManager buttonLayout = new FlowLayout(
            FlowLayout.CENTER, 
            15, 
            10
        );
        buttonPanel.setLayout(buttonLayout);
        
        Styles.stylizeButton(this.verificarButton, 0, buttonSize);
        Styles.stylizeButton(this.cancelarButton, 0, buttonSize);
        
        buttonPanel.getPanel().add(this.verificarButton);
        buttonPanel.getPanel().add(this.cancelarButton);

        this.formPanel.getPanel().add(buttonPanel.getPanel(), BorderLayout.CENTER);

        // Añadiendo un EventListeners a cada boton
        this.verificarButton.addActionListener(e -> {
            // 1. Validar que se haya seleccionado un archivo.
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(frame.getFrame(), "Por favor, seleccione un archivo de imagen primero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 2. Validar que se haya ingresado una cédula.
            String cedulaStr = cedulaField.getText().trim();
            if (cedulaStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame.getFrame(), "Por favor, ingrese la cédula del usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(this.controller.verificarUsuario(selectedFile, cedulaStr, resultadoField)){
                this.frame.getFrame().dispose();
            }
        });

        this.cancelarButton.addActionListener(e -> frame.getFrame().dispose());
    }
 
    /**
     * Inicializa los campos de entrada del usuario
     */
    private void initFields() {
        // Layout principal de campos: 2 filas, 1 columna
        LayoutManager fieldsLayout = new GridLayout(2, 1, 0, 15);

        // agregamos el layout al panel general de campos
        fieldsPanel.setLayout(fieldsLayout);

        // Celda 1 (superior): Carga de archivo
        fieldsPanel.getPanel().add(createFileUploadField());

        // Celda 2 (inferior): Panel con los otros dos campos
        JPanel bottomFieldsPanel = new JPanel(new GridLayout(1, 2, 15, 0)); // 1 fila, 2 columnas
        bottomFieldsPanel.setOpaque(false);
        bottomFieldsPanel.add(createFormField("Cédula del Usuario:", cedulaField));
        bottomFieldsPanel.add(createFormField("Descuento:", resultadoField));
        fieldsPanel.getPanel().add(bottomFieldsPanel);

        // Deshabilitar el campo de resultado
        resultadoField.setEditable(false);
        resultadoField.setBackground(Color.decode("#e0e0e0")); // Un gris claro

        // agregamos el panel de formulario al panel de formulario
        this.formPanel.getPanel().add(fieldsPanel.getPanel(), BorderLayout.CENTER);
    }
    
    private JPanel createFileUploadField() {
        Panel fieldPanel = new Panel(100.0f, 45.0f, this.fieldsPanel.getSize());
        fieldPanel.setLayout(new GridLayout(2, 1, 10, 0));

        JLabel label = new JLabel("Cargar Archivo:", SwingConstants.CENTER);
        Styles.stylizeLabel(label, 0, new Size(100.0f, 20.0f, fieldPanel.getSize()));

        JPanel uploadPanel = new JPanel(new BorderLayout(10, 0));
        uploadPanel.add(uploadFileButton, BorderLayout.WEST);
        uploadPanel.add(selectedFileLabel, BorderLayout.CENTER);

        uploadFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png"));
            int result = fileChooser.showOpenDialog(frame.getFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                selectedFileLabel.setText(selectedFile.getName());
            }
        });

        fieldPanel.getPanel().add(label);
        fieldPanel.getPanel().add(uploadPanel);
        return fieldPanel.getPanel();
    }

    private JPanel createFormField(String labelText, JTextField textField) {
        Panel fieldPanel = new Panel(100.0f, 45.0f, this.fieldsPanel.getSize());
        LayoutManager fieldManager = new GridLayout(2, 1, 10, 0);
        fieldPanel.setLayout(fieldManager);

        Size labelSize = new Size(100.0f, 20.0f, fieldPanel.getSize());
        Size fieldsSize = new Size(100.0f, 60.0f, fieldPanel.getSize());

        JLabel label = new JLabel(labelText, SwingConstants.CENTER);

        Styles.stylizeField(textField, 0, fieldsSize);
        Styles.stylizeLabel(label, 0, labelSize);

        fieldPanel.getPanel().add(label, BorderLayout.NORTH);
        fieldPanel.getPanel().add(textField, BorderLayout.CENTER);

        return fieldPanel.getPanel();
    }
}
