package main.Views.MenuAdmin;

import javax.swing.*;
import main.Controllers.MenuAdminController;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Views.Layouts.*;


public class MenuPanel{
        private Panel mainPanel;
        private JButton  editMenu;
        private JButton  exitMenu;
        private Panel turnoPanel;
        private JLabel  turno;
        private JTextField turnoTxtField;
        private Panel platilloPanel;
        private JLabel  platillo;
        private JTextField platilloTxtField;
        private Panel horarioPanel;
        private JLabel  horario;
        private JTextField horarioTxtField;
        private Panel caloriasPanel;
        private JLabel  calorias;
        private JTextField caloriasTxtField;
        private Panel precioPanel;
        private JLabel  precio;
        private JTextField precioTxtField;
        private Panel tituloPanel;
        private JLabel  titulo;
        private JTextField tituloTxtField;
        private Menu menu;
        private int menuNumber;

        public MenuPanel( int menuNumber, Window frame , MenuAdminController controller){

            this.menu = controller.getMenu(menuNumber);

            this.editMenu = new JButton("Editar");
            this.turno = new JLabel("Turno: " + menu.turno);
            this.platillo = new JLabel("Platillo: " + menu.platillo);
            this.horario = new JLabel("Horario: " + menu.horario);
            this.calorias = new JLabel("Calorias: " + menu.calorias);
            this.precio = new JLabel("Precio: " + menu.precio);

            this.turnoTxtField = new JTextField( this.turno.getText() );
            this.platilloTxtField = new JTextField( this.platillo.getText() );
            this.horarioTxtField = new JTextField( this.horario.getText() );
            this.caloriasTxtField = new JTextField( this.calorias.getText() );
            this.precioTxtField = new JTextField( this.precio.getText() );
            this.tituloTxtField = new JTextField( this.titulo.getText() );

            this.hideTextfields();
            
            mainPanel = new Panel( 40.0f, 100.0f,frame.getSize() );

            this.mainPanel.getPanel().add(titulo);
            this.mainPanel.getPanel().add(turno);
            this.mainPanel.getPanel().add(platillo);
            this.mainPanel.getPanel().add(horario);
            this.mainPanel.getPanel().add(calorias);
            this.mainPanel.getPanel().add(precio);

            frame.getFrame();

            this.initButton();
       }

       private void initButton(){
            editMenu.addActionListener(e -> {
                java.util.Hashtable<String, String> data = new java.util.Hashtable<>();
            });
       }
       private void editMode(){
            
            mainPanel.getPanel().add(tituloTxtField);
            mainPanel.getPanel().add(turnoTxtField);
            mainPanel.getPanel().add(platilloTxtField);
            mainPanel.getPanel().add(horarioTxtField);
            mainPanel.getPanel().add(caloriasTxtField);
            mainPanel.getPanel().add(precioTxtField);
       }
       private void hideTextfields(){

            turnoTxtField.setVisible(false);
            platilloTxtField.setVisible(false);
            horarioTxtField.setVisible(false);
            caloriasTxtField.setVisible(false);
            precioTxtField.setVisible(false);
            tituloTxtField.setVisible(false);
       }
       private void showTextfields(){

            this.turnoTxtField.setText(this.turno.getText());
            this.platilloTxtField.setText(this.platillo.getText());
            this.horarioTxtField.setText(this.horario.getText());
            this.caloriasTxtField.setText(this.calorias.getText());
            this.precioTxtField.setText(this.precio.getText());

            turnoTxtField.setVisible(true);
            platilloTxtField.setVisible(true);
            horarioTxtField.setVisible(true);
            caloriasTxtField.setVisible(true);
            precioTxtField.setVisible(true);
            tituloTxtField.setVisible(true);
       }
       private void saveChanges(){
            showTextfields();
       }
       private void cancelChanges(){
            hideTextfields();
       }

}
