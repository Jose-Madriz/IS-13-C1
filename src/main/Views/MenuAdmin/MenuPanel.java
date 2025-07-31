package main.Views.MenuAdmin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.LayoutManager;
import java.util.Hashtable;

import javax.swing.*;
import main.Controllers.MenuAdminController;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.CargarCostos.CargarCostosView;
import main.Views.Layouts.*;


public class MenuPanel{
        
    private JButton editButton;
    private JButton cargarCostoButton;
    private JButton deleteButton;
    private Panel turnoPanel;
    private JLabel  turno;
    private String turnoValue;
    private JTextField turnoTxtField;
    private Panel platilloPanel;
    private JLabel  platillo;
    private JTextField platilloTxtField;
    private String  platilloValue;
    private Panel horarioPanel;
    private JLabel  horario;
    private JTextField horarioTxtField;
    private String horarioValue;
    private Panel caloriasPanel;
    private JLabel  calorias;
    private JTextField caloriasTxtField;
    private String caloriasValue;
    private Panel precioPanel;
    private JLabel  precio;
    private String precioValue;
    private JLabel precioValueLabel;
    private Menu menu;
    private Panel container;
    private int menuNumber;
    private MenuAdminController controller;

    public MenuPanel( int menuNumber, Panel father, MenuAdminController controller){

        this.menu = controller.getMenu(menuNumber);
        this.controller = controller;

        this.editButton = new JButton("Editar");
        this.deleteButton = new JButton("Eliminar");
        this.cargarCostoButton = new JButton("Cargar Costo");
        this.turno = new JLabel("Turno: ");
        this.platillo = new JLabel("Platillo: ");
        this.horario = new JLabel("Horario: ");
        this.calorias = new JLabel("Calorias: ");
        this.precio = new JLabel("Precio: ");
        this.menuNumber = menuNumber;
        
        this.container = new Panel( 40.0f, 70.0f, father.getSize() );
        container.setLayout(new BorderLayout(0, 5));

        this.initButton();
        this.initFields();
        this.updateLabelsView();
        this.setNormalMode();
    }

    public void getData(){
        // Convierte los datos del objeto menu a String para poder mostrarlos
        this.menu = controller.getMenu(this.menuNumber);

        this.caloriasValue = String.valueOf(this.menu.calorias);
        this.horarioValue = this.menu.horario;
        this.precioValue = String.valueOf(this.menu.precio);
        this.platilloValue = this.menu.platillo;
        this.turnoValue = this.menu.turno;
    }

    private void initFields(){

        JPanel fieldsGridPanel = new JPanel();
        fieldsGridPanel.setOpaque(false);

        LayoutManager fieldsLayout = new GridLayout( 
            5, // 5 filas
            1, // 1 columna
            0, // Espaciado horizontal
            10 // Espaciado vertical
        );

        // agregamos el layout al panel general de campos
        fieldsGridPanel.setLayout( fieldsLayout );
        // inicializamos paneles para cada campo
        this.turnoPanel = new Panel ( 10.0f, 10.0f, this.container.getSize() );
        this.caloriasPanel = new Panel ( 10.0f, 10.0f, this.container.getSize() );
        this.horarioPanel = new Panel ( 10.0f, 10.0f, this.container.getSize() );
        this.precioPanel = new Panel ( 10.0f, 10.0f, this.container.getSize() );
        this.platilloPanel = new Panel ( 10.0f, 10.0f, this.container.getSize() );

        this.turnoPanel.getPanel().setBackground( Color.yellow );
        this.caloriasPanel.getPanel().setBackground( Color.yellow );
        this.horarioPanel.getPanel().setBackground( Color.yellow );
        this.precioPanel.getPanel().setBackground( Color.yellow );
        this.platilloPanel.getPanel().setBackground( Color.yellow );
        
        // inicializamos tamaños 
        Size labelSize = new Size( 100.0f, 20.0f, turnoPanel.getSize() );
        Size fieldsSize = new Size( 100.0f, 80.0f, turnoPanel.getSize() );

        this.turnoTxtField = new JTextField(20);
        this.platilloTxtField = new JTextField(20);
        this.horarioTxtField = new JTextField(20);
        this.caloriasTxtField = new JTextField(20);
        this.precioValueLabel = new JLabel();
        


        // agregando estilos a los Campos
        Styles.stylizeField( this.horarioTxtField, 0, fieldsSize );
        Styles.stylizeField( this.platilloTxtField, 0, fieldsSize );
        Styles.stylizeField( this.turnoTxtField, 0, fieldsSize );
        Styles.stylizeField( this.caloriasTxtField, 0, fieldsSize );
        Styles.stylizeLabel( this.precioValueLabel, 0, fieldsSize);
        
        
        // agregando estilos a los Labels de cada campo
        Styles.stylizeLabel( this.turno, 0, labelSize );
        Styles.stylizeLabel( this.horario, 0, labelSize );
        Styles.stylizeLabel( this.precio, 0, labelSize );
        Styles.stylizeLabel( this.platillo, 0, labelSize );
        Styles.stylizeLabel( this.calorias, 0, labelSize );
        

        // agregamos los elementos a los paneles correspondientes de cada campo y label
        LayoutManager inputLayout = new GridLayout(2, 1);

        turnoPanel.setLayout(inputLayout);
        caloriasPanel.setLayout(inputLayout);
        horarioPanel.setLayout(inputLayout);
        precioPanel.setLayout(inputLayout);
        platilloPanel.setLayout(inputLayout);

        turnoPanel.getPanel().add(turno);
        turnoPanel.getPanel().add(turnoTxtField);
        horarioPanel.getPanel().add(horario);
        horarioPanel.getPanel().add(horarioTxtField);
        platilloPanel.getPanel().add(platillo);
        platilloPanel.getPanel().add(platilloTxtField);

        // Panel especial para la línea de precio para incluir el botón
        JPanel priceLinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        priceLinePanel.setOpaque(false); // Para que herede el color de fondo amarillo
        priceLinePanel.add(precioValueLabel);
        priceLinePanel.add(cargarCostoButton);

        precioPanel.getPanel().add(precio);
        precioPanel.getPanel().add(priceLinePanel);
        caloriasPanel.getPanel().add(calorias);
        caloriasPanel.getPanel().add(caloriasTxtField);

        fieldsGridPanel.add(turnoPanel.getPanel());
        fieldsGridPanel.add(horarioPanel.getPanel());
        fieldsGridPanel.add(platilloPanel.getPanel());
        fieldsGridPanel.add(precioPanel.getPanel());
        fieldsGridPanel.add(caloriasPanel.getPanel());

        this.container.getPanel().add(fieldsGridPanel, BorderLayout.CENTER);
    }
    
    private void initButton(){

        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonContainer.setOpaque(false);

        buttonContainer.add(editButton);
        buttonContainer.add(deleteButton);

        this.container.getPanel().add(buttonContainer, BorderLayout.NORTH);

        cargarCostoButton.addActionListener(e -> {
            // Idealmente, la CargarCostosView debería recibir el número de menú
            // para saber a cuál menú aplicar el costo.
            CargarCostosView costView = new CargarCostosView(this.menuNumber);
            
            // Añadimos un listener a la ventana de CargarCostos para saber cuándo se cierra.
            costView.getWindow().getFrame().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent we) {
                    // 1. Le decimos al controlador que recargue los datos del archivo JSON.
                    controller.refreshMenus();
                    // 2. Ahora, actualizamos la vista con los datos frescos.
                    SwingUtilities.invokeLater(() -> updateLabelsView());
                }
            });
            System.out.println("Abriendo Cargar Costos para el menú: " + this.menuNumber);
        });
    }

    private void editMode(){
        enableEditing();

        editButton.setText("Guardar Cambios");
        deleteButton.setText("Cancelar");

        // Limpia los listeners anteriores para evitar eventos duplicados
        for (ActionListener al : editButton.getActionListeners()) {
            editButton.removeActionListener(al);
        }
        for (ActionListener al : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(al);
        }

        // Asigna los nuevos listeners para el modo de edición
        editButton.addActionListener(e -> saveChanges());
        deleteButton.addActionListener(e -> cancelChanges());
    }

    private void disableEditing(){
        turnoTxtField.setEnabled(false);
        turnoTxtField.setBackground(Color.WHITE);
        platilloTxtField.setEnabled(false);
        platilloTxtField.setBackground(Color.WHITE);
        horarioTxtField.setEnabled(false);
        horarioTxtField.setBackground(Color.WHITE);
        caloriasTxtField.setEnabled(false);
        caloriasTxtField.setBackground(Color.WHITE);
    }

    private void enableEditing(){
        Color editableColor = UIManager.getColor("TextField.background");

        turnoTxtField.setEnabled(true);
        turnoTxtField.setBackground(editableColor);
        platilloTxtField.setEnabled(true);
        platilloTxtField.setBackground(editableColor);
        horarioTxtField.setEnabled(true);
        horarioTxtField.setBackground(editableColor);
        caloriasTxtField.setEnabled(true);
        caloriasTxtField.setBackground(editableColor);
    }

    private void saveChanges(){
        // 1. Obtener y limpiar los datos de los campos de texto
        String platilloStr = platilloTxtField.getText().trim();
        String turnoStr = turnoTxtField.getText().trim();
        String horarioStr = horarioTxtField.getText().trim();
        String caloriasStr = caloriasTxtField.getText().trim();

        // 2. Validar que ningún campo esté vacío
        if (platilloStr.isEmpty() || turnoStr.isEmpty() || horarioStr.isEmpty() || caloriasStr.isEmpty()) {
            JOptionPane.showMessageDialog(container.getPanel(), "Todos los campos (excepto precio) son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Validar que calorías sea un número válido y no negativo
        double caloriasDouble;
        try {
            caloriasDouble = Double.parseDouble(caloriasStr);

            if (caloriasDouble < 0) {
                JOptionPane.showMessageDialog(container.getPanel(), "Las calorías no pueden ser un valor negativo.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(container.getPanel(), "Las calorías deben ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. Si todas las validaciones pasan, crear un nuevo objeto Menu y guardarlo
        Menu updatedMenu = new Menu();
        updatedMenu.platillo = platilloStr;
        updatedMenu.turno = turnoStr;
        updatedMenu.horario = horarioStr;
        updatedMenu.calorias = caloriasDouble;
        updatedMenu.precio = this.menu.precio; // Preservar el precio existente

        controller.setMenu(this.menuNumber, updatedMenu);
        this.menu = controller.getMenu(this.menuNumber); // Actualizar la copia local
        updateLabelsView();
        System.out.println("Guardando cambios para el menú " + this.menuNumber);
        setNormalMode();
    }
    private void cancelChanges(){    
        System.out.println("Cancelando edición para el menú " + this.menuNumber);
        updateLabelsView(); // Restaura los valores originales en la vista
        setNormalMode();
    }

    private void setNormalMode() {
        disableEditing();
        editButton.setText("Editar");
        deleteButton.setText("Eliminar");

        // Limpia los listeners anteriores para evitar eventos duplicados
        for (ActionListener al : editButton.getActionListeners()) {
            editButton.removeActionListener(al);
        }
        for (ActionListener al : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(al);
        }

        // Asigna el listener para entrar al modo de edición
        editButton.addActionListener(e -> editMode());
        // Asigna el listener para la acción de eliminar
        deleteButton.addActionListener(e -> deleteMenu());
    }

    private void deleteMenu() {
        int response = JOptionPane.showConfirmDialog(
            container.getPanel(), 
            "¿Estás seguro de que quieres eliminar este menú? Esta acción no se puede deshacer.",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            controller.deleteMenu(this.menuNumber);
            // Los datos del menú han cambiado, actualizamos la copia local y la vista
            this.menu = controller.getMenu(this.menuNumber);
            updateLabelsView();
            System.out.println("Menú " + this.menuNumber + " eliminado.");
        }
    }

    public Panel getMenuPanel(){
        return this.container;
    }

    private void updateLabelsView() {
        getData();

        turnoTxtField.setText(turnoValue);
        platilloTxtField.setText(platilloValue);
        horarioTxtField.setText(horarioValue);
        caloriasTxtField.setText(caloriasValue);
        precioValueLabel.setText(precioValue);
    }
}
