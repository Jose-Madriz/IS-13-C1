package main.Views.MenuPrincipal;
import main.Views.Login.*;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Controllers.MenuUsuarioController;
import main.Controllers.Login.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class RecargarMenuUsuario extends JFrame {



    //iniciar los colores
    Color Boton = new Color(28,138,178);
    Color Fondo = new Color(217,217,217);
    Color titulos = new Color(167,167,167);
    Color recuadrito = new Color(244,244,244);
    // Inicialización del panel
    JPanel panel2= new JPanel();
    JLabel entrada = new JLabel("Seleccione su monto",SwingConstants.CENTER);
    JButton boton4 = new JButton("Recargar");
    JLabel recuadroRecarga = new JLabel();
    // Colores a emplear
    
     public RecargarMenuUsuario(){
    setSize(350,350);
    setTitle("Recargar saldo");

    setLocationRelativeTo(null);
    setResizable(false);
  
    }
    public void IniciarComponentes2(){
        
         panel2.setLayout(null);
         this.getContentPane().add(panel2);
         panel2.setBackground(Color.white);

         opcionesPrecio();
         colocarTitulos();
         recargarSaldo();
         recuadroSaldo();
    }
    
    public void colocarTitulos(){
        entrada.setBounds(55,15,220,70);
        entrada.setOpaque(true);
         entrada.setBackground(Fondo);
        entrada.setFont(new Font("Arial",Font.BOLD,  20));
        panel2.add(entrada);
       // entrada.setBackground(Fondo);
    }
   

    public void opcionesPrecio(){

        String [] precios = { "100bs" , "200bs","300bs","400bs" };
        JComboBox listaPrecios=new JComboBox(precios);
        listaPrecios.setBounds(115, 125,100,60);
        listaPrecios.setBackground(Color.white);
        listaPrecios.setFont(new Font("Arial",Font.BOLD,15));
        panel2.add(listaPrecios);
        
       
    }

    public void recargarSaldo(){
    boton4.setBounds(115,220,100,50);
    boton4.setFont(new Font("Arial",Font.BOLD,15));
    boton4.setForeground(Color.white);
    panel2.add(boton4);
     boton4.addActionListener(saldoVolver);
     boton4.setBackground(Boton);
    }

    public void recuadroSaldo(){
        recuadroRecarga.setOpaque(true);
    recuadroRecarga.setBounds(35,100,260,200);
    recuadroRecarga.setBackground(recuadrito);
  Border borde= BorderFactory.createLineBorder(Boton,4);
     recuadroRecarga.setBorder(borde);
    panel2.add(recuadroRecarga);
    }
     
ActionListener saldoVolver= new ActionListener() {

  @Override
  public void actionPerformed(ActionEvent e) {
    
    
    dispose();
  }
 
};
   

 
}