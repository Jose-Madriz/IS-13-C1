package main.Views.MenuAdmin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import main.Controllers.MenuAdminController;
import main.Models.DatabaseManager;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.CargarCostos.CargarCostosView;
import main.Views.Layouts.*;

public class MenuPanel {
    private JButton editButton;
    private JButton cargarCostoButton;
    private JButton deleteButton;
    private main.Views.Layouts.Panel turnoPanel;
    private JLabel turno;
    private String turnoValue;
    private JTextField turnoTxtField;
    private main.Views.Layouts.Panel platilloPanel;
    private JLabel platillo;
    private JTextField platilloTxtField;
    private String platilloValue;
    private main.Views.Layouts.Panel horarioPanel;
    private JLabel horario;
    private JTextField horarioTxtField;
    private String horarioValue;
    private main.Views.Layouts.Panel caloriasPanel;
    private JLabel calorias;
    private JTextField caloriasTxtField;
    private String caloriasValue;
    private main.Views.Layouts.Panel precioPanel;
    private JLabel precio;
    private String precioValue;
    private JLabel precioValueLabel;
    private Menu menu;
    private main.Views.Layouts.Panel container;
    private int menuNumber;
    private MenuAdminController controller;
    private DatabaseManager dbManager;

    public MenuPanel(int menuNumber, main.Views.Layouts.Panel father, MenuAdminController controller) {
        this.controller = controller;
        this.menuNumber = menuNumber;
        this.dbManager = DatabaseManager.getInstance();

        // Inicialización de componentes
        this.editButton = new JButton("Editar");
        this.deleteButton = new JButton("Eliminar");
        this.cargarCostoButton = new JButton("Cargar Costo");
        this.turno = new JLabel("Turno: ");
        this.platillo = new JLabel("Platillo: ");
        this.horario = new JLabel("Horario: ");
        this.calorias = new JLabel("Calorías: ");
        this.precio = new JLabel("Precio: ");

        this.container = new main.Views.Layouts.Panel(40.0f, 80.0f, father.getSize());
        container.setLayout(new BorderLayout(10, 10));

        this.initButton();
        this.initFields();
        this.updateLabelsView();
        this.setNormalMode();
    }

    public void getData() {
        this.menu = controller.getMenu(this.menuNumber);
        if (this.menu == null) {
            // Valores por defecto si el menú es null
            this.caloriasValue = "0.0";
            this.horarioValue = "-";
            this.precioValue = "0.0";
            this.platilloValue = "No disponible";
            this.turnoValue = "-";
        } else {
            this.caloriasValue = String.valueOf(this.menu.calorias);
            this.horarioValue = this.menu.horario != null ? this.menu.horario : "-";
            this.precioValue = String.valueOf(this.menu.precio);
            this.platilloValue = this.menu.platillo != null ? this.menu.platillo : "No disponible";
            this.turnoValue = this.menu.turno != null ? this.menu.turno : "-";
        }
    }

    private void initFields() {
        JPanel fieldsGridPanel = new JPanel();
        fieldsGridPanel.setOpaque(false);
        fieldsGridPanel.setLayout(new GridLayout(5, 1, 0, 20)); // Espaciado vertical aumentado

        // Inicializar paneles para cada campo
        this.turnoPanel = new main.Views.Layouts.Panel(100.0f, 20.0f, this.container.getSize());
        this.caloriasPanel = new main.Views.Layouts.Panel(100.0f, 20.0f, this.container.getSize());
        this.horarioPanel = new main.Views.Layouts.Panel(100.0f, 20.0f, this.container.getSize());
        this.precioPanel = new main.Views.Layouts.Panel(100.0f, 20.0f, this.container.getSize());
        this.platilloPanel = new main.Views.Layouts.Panel(100.0f, 20.0f, this.container.getSize());

        Color panelColor = new Color(245, 245, 245); // Gris claro
        this.turnoPanel.getPanel().setBackground(panelColor);
        this.caloriasPanel.getPanel().setBackground(panelColor);
        this.horarioPanel.getPanel().setBackground(panelColor);
        this.precioPanel.getPanel().setBackground(panelColor);
        this.platilloPanel.getPanel().setBackground(panelColor);

        Size labelSize = new Size(120.0f, 30.0f, turnoPanel.getSize());
        Size fieldsSize = new Size(250.0f, 35.0f, turnoPanel.getSize());

        this.turnoTxtField = new JTextField(20);
        this.platilloTxtField = new JTextField(20);
        this.horarioTxtField = new JTextField(20);
        this.caloriasTxtField = new JTextField(20);
        this.precioValueLabel = new JLabel();

        Styles.stylizeField(this.horarioTxtField, 2, fieldsSize);
        Styles.stylizeField(this.platilloTxtField, 2, fieldsSize);
        Styles.stylizeField(this.turnoTxtField, 2, fieldsSize);
        Styles.stylizeField(this.caloriasTxtField, 2, fieldsSize);
        Styles.stylizeLabel(this.precioValueLabel, 2, fieldsSize);

        Styles.stylizeLabel(this.turno, 0, labelSize);
        Styles.stylizeLabel(this.horario, 0, labelSize);
        Styles.stylizeLabel(this.precio, 0, labelSize);
        Styles.stylizeLabel(this.platillo, 0, labelSize);
        Styles.stylizeLabel(this.calorias, 0, labelSize);

        // Usar BorderLayout para mejor control
        turnoPanel.setLayout(new BorderLayout(10, 10));
        caloriasPanel.setLayout(new BorderLayout(10, 10));
        horarioPanel.setLayout(new BorderLayout(10, 10));
        precioPanel.setLayout(new BorderLayout(10, 10));
        platilloPanel.setLayout(new BorderLayout(10, 10));

        turnoPanel.getPanel().add(turno, BorderLayout.WEST);
        turnoPanel.getPanel().add(turnoTxtField, BorderLayout.CENTER);
        horarioPanel.getPanel().add(horario, BorderLayout.WEST);
        horarioPanel.getPanel().add(horarioTxtField, BorderLayout.CENTER);
        platilloPanel.getPanel().add(platillo, BorderLayout.WEST);
        platilloPanel.getPanel().add(platilloTxtField, BorderLayout.CENTER);

        JPanel priceLinePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        priceLinePanel.setOpaque(false);
        priceLinePanel.add(precioValueLabel);
        priceLinePanel.add(cargarCostoButton);

        precioPanel.getPanel().add(precio, BorderLayout.WEST);
        precioPanel.getPanel().add(priceLinePanel, BorderLayout.CENTER);
        caloriasPanel.getPanel().add(calorias, BorderLayout.WEST);
        caloriasPanel.getPanel().add(caloriasTxtField, BorderLayout.CENTER);

        fieldsGridPanel.add(turnoPanel.getPanel());
        fieldsGridPanel.add(horarioPanel.getPanel());
        fieldsGridPanel.add(platilloPanel.getPanel());
        fieldsGridPanel.add(precioPanel.getPanel());
        fieldsGridPanel.add(caloriasPanel.getPanel());

        this.container.getPanel().add(fieldsGridPanel, BorderLayout.CENTER);
    }

    private void initButton() {
        JPanel buttonContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonContainer.setOpaque(false);

        Styles.stylizeButton(editButton, 2, new Size(30.0f, 30.0f, container.getSize()));
        Styles.stylizeButton(deleteButton, 2, new Size(30.0f, 30.0f, container.getSize()));
        Styles.stylizeButton(cargarCostoButton, 2, new Size(30.0f, 30.0f, container.getSize()));

        buttonContainer.add(editButton);
        buttonContainer.add(deleteButton);

        this.container.getPanel().add(buttonContainer, BorderLayout.NORTH);

        cargarCostoButton.addActionListener(e -> {
            CargarCostosView costView = new CargarCostosView(this.menuNumber);
            costView.getWindow().getFrame().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent we) {
                    controller.refreshMenus();
                    SwingUtilities.invokeLater(() -> {
                        updateLabelsView();
                        container.getPanel().revalidate();
                        container.getPanel().repaint();
                        JOptionPane.showMessageDialog(container.getPanel(), "Costo actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
            });
        });
    }

    private void editMode() {
        enableEditing();
        editButton.setText("Guardar Cambios");
        deleteButton.setText("Cancelar");

        for (ActionListener al : editButton.getActionListeners()) {
            editButton.removeActionListener(al);
        }
        for (ActionListener al : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(al);
        }

        editButton.addActionListener(e -> saveChanges());
        deleteButton.addActionListener(e -> cancelChanges());
    }

    private void disableEditing() {
        turnoTxtField.setEnabled(false);
        turnoTxtField.setBackground(Color.WHITE);
        platilloTxtField.setEnabled(false);
        platilloTxtField.setBackground(Color.WHITE);
        horarioTxtField.setEnabled(false);
        horarioTxtField.setBackground(Color.WHITE);
        caloriasTxtField.setEnabled(false);
        caloriasTxtField.setBackground(Color.WHITE);
    }

    private void enableEditing() {
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

    private void saveChanges() {
        String platilloStr = platilloTxtField.getText().trim();
        String turnoStr = turnoTxtField.getText().trim();
        String horarioStr = horarioTxtField.getText().trim();
        String caloriasStr = caloriasTxtField.getText().trim();

        if (platilloStr.isEmpty() || turnoStr.isEmpty() || horarioStr.isEmpty() || caloriasStr.isEmpty()) {
            JOptionPane.showMessageDialog(container.getPanel(), "Todos los campos (excepto precio) son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        // Verificación de imagen facial
        if (!verifyFace()) {
            JOptionPane.showMessageDialog(container.getPanel(), "Verificación facial fallida. No se guardaron los cambios.", "Error de Verificación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Menu updatedMenu = new Menu();
        updatedMenu.platillo = platilloStr;
        updatedMenu.turno = turnoStr;
        updatedMenu.horario = horarioStr;
        updatedMenu.calorias = caloriasDouble;
        updatedMenu.precio = (this.menu != null) ? this.menu.precio : 0.0;

        controller.setMenu(this.menuNumber, updatedMenu);
        this.menu = controller.getMenu(this.menuNumber);

        // Guardar el precio en DataBasePrice.txt
        if (!savePriceToFile(updatedMenu.precio)) {
            JOptionPane.showMessageDialog(container.getPanel(), "Error al guardar el precio en DataBasePrice.txt.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateLabelsView();
        setNormalMode();
        JOptionPane.showMessageDialog(container.getPanel(), "Menú actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean savePriceToFile(double precio) {
        String filePath = "DataBasePrice.txt";
        String menuKey = "menu" + menuNumber;
        Map<String, String> prices = new HashMap<>();

        // Leer precios existentes
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();
                        prices.put(key, value);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al leer DataBasePrice.txt: " + e.getMessage());
                // Continuar, ya que podemos crear un nuevo archivo
            }
        }

        // Actualizar el precio para el menú actual
        prices.put(menuKey, String.format("%.2f", precio));

        // Escribir todos los precios al archivo
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Map.Entry<String, String> entry : prices.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al guardar el precio en DataBasePrice.txt: " + e.getMessage());
            JOptionPane.showMessageDialog(container.getPanel(), "Error al escribir en DataBasePrice.txt: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private boolean verifyFace() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(container.getPanel());

        if (result != JFileChooser.APPROVE_OPTION) {
            return false;
        }

        File selectedImage = fileChooser.getSelectedFile();
        String adminCI = controller.getAdminData().get("cedula");
        int cedula;
        try {
            cedula = Integer.parseInt(adminCI);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(container.getPanel(), "Cédula del administrador inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return dbManager.verifyImageHash(cedula, selectedImage);
    }

    private void cancelChanges() {
        updateLabelsView();
        setNormalMode();
    }

    private void setNormalMode() {
        disableEditing();
        editButton.setText("Editar");
        deleteButton.setText("Eliminar");

        for (ActionListener al : editButton.getActionListeners()) {
            editButton.removeActionListener(al);
        }
        for (ActionListener al : deleteButton.getActionListeners()) {
            deleteButton.removeActionListener(al);
        }

        editButton.addActionListener(e -> editMode());
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
            this.menu = controller.getMenu(this.menuNumber);
            updateLabelsView();
            JOptionPane.showMessageDialog(container.getPanel(), "Menú eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public main.Views.Layouts.Panel getMenuPanel() {
        return this.container;
    }

    private void updateLabelsView() {
        getData();
        turnoTxtField.setText(turnoValue);
        platilloTxtField.setText(platilloValue);
        horarioTxtField.setText(horarioValue);
        caloriasTxtField.setText(caloriasValue);
        precioValueLabel.setText(precioValue);
        container.getPanel().revalidate();
        container.getPanel().repaint();
    }
}