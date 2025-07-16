package main.Views.MenuPrincipal;


import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MenuPrincipal extends JFrame{

  //Inicialización del panel
 JPanel panel = new JPanel();

 //Colores a emplear
 Color Fondo = new Color(217,217,217);
Color titulos = new Color(167,167,167);
Color Boton = new Color(28,138,178);
Color recuadrito = new Color(244,244,244);

  //Titulos y componentes
    JLabel titulo1 = new JLabel("Bienvenido, Jose Luis",SwingConstants.CENTER);
    JLabel recuadro = new JLabel();
    JLabel saldo = new JLabel("Saldo: 0.00 Bs.",SwingConstants.CENTER);
    JLabel recuadro1 = new JLabel();
    JLabel recuadro2 = new JLabel();
    JLabel recuadro3 = new JLabel();

  //Boton
JButton boton = new JButton("Cerrar Sesión");

//Constructor de la ventana
public MenuPrincipal(){
    setSize(1100,750);
    setTitle("Menú Principal");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
}

//Aqui se inicializa los componentes pincipales, por el momento va a estar de esta manera, pero después 
//lo ordenaremos
    public void IniciarComponentes(){
    
    //Añadir panel
    panel.setLayout(null);
    this.getContentPane().add(panel);
    panel.setBackground(Fondo);
  
    ColocarTextos();
    ColocarBoton();
 
    }

    public void ColocarTextos(){


   //Titulo 1
    titulo1.setOpaque(true);
    titulo1.setBounds(35,40,780,70);
    titulo1.setForeground(Color.BLACK);
    titulo1.setBackground(titulos);
    titulo1.setFont(new Font("Arial",Font.PLAIN,35));
    panel.add(titulo1);

    
    //Saldo

     saldo.setOpaque(true);
    saldo.setBounds(822,155,180,50);
    saldo.setForeground(Color.BLACK);
    saldo.setBackground(titulos);
    saldo.setFont(new Font("Arial",Font.BOLD,20));
    panel.add(saldo);

    
    //Recuadro1
    recuadro1.setOpaque(true);
   recuadro1.setBounds(65,220,280,410);
   recuadro1.setBackground(Fondo);
  panel.add(recuadro1);

    //Recuadro2
  recuadro2.setOpaque(true);
   recuadro2.setBounds(400,220,285,410);
   recuadro2.setBackground(Fondo);
    panel.add(recuadro2);

    
    //Recuadro2
  recuadro3.setOpaque(true);
   recuadro3.setBounds(725,220,280,410);
   recuadro3.setBackground(Fondo);
    panel.add(recuadro3);



    //Recuadro
    recuadro.setOpaque(true);
   recuadro.setBounds(35,140,1000,520);
   recuadro.setBackground(recuadrito);
  Border borde= BorderFactory.createLineBorder(Boton,4);
    recuadro.setBorder(borde);
    panel.add(recuadro);


    }
   
    public void ColocarBoton(){

  boton.setBounds(850,60,180,50);
   boton.setFont(new Font("Arial",Font.BOLD,20));
   boton.setForeground(Color.white);
   boton.setBackground(Boton);
    
    panel.add(boton);


    }


}
