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

//Inicializar la Ventana
public MenuPrincipal(){
    setSize(1100,750);
    setTitle("Menú Principal");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
}


//Aqui se inicializa los componentes pincipales, por el momento va a estar de esta manera, pero después 
//lo ordenaremos
    public void IniciarComponentes(String _nombre){
    String copia = _nombre;
    Color Fondo = new Color(217,217,217);
    Color titulos = new Color(167,167,167);
    Color Boton = new Color(28,138,178);
    //Panel
  JPanel panel = new JPanel();
  
    //Añadir panel
    panel.setLayout(null);
    this.getContentPane().add(panel);
    panel.setBackground(Fondo);
  
    //Titulos
    JLabel titulo1 = new JLabel("Bienvenido, "+copia,SwingConstants.CENTER);
    JLabel recuadro = new JLabel();
    JLabel saldo = new JLabel("Saldo: 0.00 Bs.",SwingConstants.CENTER);


   //Titulo 1
    titulo1.setOpaque(true);
    titulo1.setBounds(35,40,780,70);
    titulo1.setForeground(Color.BLACK);
    titulo1.setBackground(titulos);
    titulo1.setFont(new Font("Arial",Font.ITALIC,35));
    panel.add(titulo1);

    
    
    //Saldo

     saldo.setOpaque(true);
    saldo.setBounds(820,155,200,50);
    saldo.setForeground(Color.BLACK);
    saldo.setBackground(titulos);
    saldo.setFont(new Font("Arial",Font.BOLD,25));
    panel.add(saldo);

    //Recuadro
    recuadro.setOpaque(true);
   recuadro.setBounds(35,140,1000,520);
   recuadro.setBackground(Color.white);
  Border borde= BorderFactory.createLineBorder(Boton,4);
    recuadro.setBorder(borde);
    panel.add(recuadro);
      
    
//Boton
JButton boton = new JButton("Cerrar Sesión");
    //boton.setOpaque(true);

   boton.setBounds(850,60,180,50);
   boton.setFont(new Font("Arial",Font.BOLD,20));
   boton.setForeground(Color.white);
   boton.setBackground(Boton);
    
panel.add(boton);
 
    
    }

}
