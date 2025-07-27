package main.Views.MenuPrincipal;
import main.Views.Login.*;
import main.Models.DatabaseManager;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Controllers.MenuUsuarioController;
import main.Controllers.Login.*;
import main.Views.Change.ChangePassView;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ReadOnlyBufferException;

import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuPrincipal extends JFrame {
    //Inicialización del panel
    JPanel panel = new JPanel();
    double saldo_auxiliar;
    int cedulaSaldo;
    
    //Colores a emplear
    Color Fondo = new Color(217,217,217);
    Color titulos = new Color(167,167,167);
    Color Boton = new Color(28,138,178);
    Color recuadrito = new Color(244,244,244);

    //Titulos y componentes
    JLabel titulo1 = new JLabel("",SwingConstants.CENTER);
    JLabel recuadro = new JLabel();
    JLabel saldo = new JLabel("",SwingConstants.CENTER);
    JLabel manana = new JLabel("MAÑANA",SwingConstants.CENTER);
    JLabel tarde = new JLabel("TARDE",SwingConstants.CENTER);
    JLabel turno1=new JLabel("",SwingConstants.CENTER);
    JLabel turno2=new JLabel("",SwingConstants.CENTER);
    JLabel recuadro1 = new JLabel();
    JLabel recuadro2 = new JLabel();

    //Inicializacion de imagen
    ImageIcon imagen= new ImageIcon("Logo.jgp");
    JLabel icono= new JLabel();

    //Boton
    JButton boton = new JButton("Cerrar Sesión");
    JButton boton2=new JButton("Recargar saldo"); 
    JButton boton3=new JButton("Perfil");

    //Controlador
    ActionListener botonAction= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            SwingUtilities.invokeLater(() -> {
              DatabaseManager dbManager = DatabaseManager.getInstance();
                LoginView loginSystem = new LoginView();
                loginSystem.setVisible(true);
            });
        }
    };

    ActionListener botonRecarga= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
           RecargarMenuUsuario recargaMenu=new RecargarMenuUsuario();
          recargaMenu.setVisible(true);
           recargaMenu.IniciarComponentes2(saldo,saldo_auxiliar,cedulaSaldo);
        }
    };

    ActionListener botonPerfil= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            ChangePassView changePasswordView = new ChangePassView(MenuPrincipal.this);
            changePasswordView.showChangePasswordView();
        }
    };

    Menu menu1=new Menu();
    Menu menu2=new Menu();

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
    public void IniciarComponentes(String _n,String _p,double _s,MenuUsuarioController _controller, int _c){
        //Añadir panel
        cedulaSaldo =_c;
        saldo_auxiliar=_s;
       
        panel.setLayout(null);
        this.getContentPane().add(panel);
        panel.setBackground(Fondo);
        ColocarBoton();
        _controller.CargarDatos(menu1, menu2);
        ColocarTextos(_n,_p,_s);
        
    }

    public void ColocarTextos(String __n,String __p,double __s){
        //Titulo 1

        titulo1.setText("Bienvenido, "+__n+ " "+__p);
        titulo1.setOpaque(true);
        titulo1.setBounds(180,40,620,70);
        titulo1.setForeground(Color.BLACK);
        titulo1.setBackground(titulos);
        titulo1.setFont(new Font("Arial",Font.PLAIN,35));
        panel.add(titulo1);

        //Saldo
        saldo.setText("Saldo: "+__s);
        saldo.setOpaque(true);
        saldo.setBounds(55,155,180,50);
        saldo.setForeground(Color.BLACK);
        saldo.setBackground(titulos);
        saldo.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(saldo);

        //Turno1
        turno1.setBounds(233,280,220,320);
        turno1.setFont(new Font("Arial",Font.PLAIN,22));
        Border borde4= BorderFactory.createLineBorder(titulos,5);
        turno1.setBorder(borde4);
        panel.add(turno1);

        //Turno2
        turno2.setBounds(610,280,220,320);
        turno2.setFont(new Font("Arial",Font.PLAIN,22));
        Border borde5= BorderFactory.createLineBorder(titulos,5);
        turno2.setBorder(borde5);
        panel.add(turno2);

        //Mañana
        manana.setBounds(249,230,180,50);
        manana.setForeground(Boton);
        manana.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(manana);

        //Tarde
        tarde.setBounds(630,230,180,50);
        tarde.setForeground(Boton);
        tarde.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(tarde);

        //Recuadro1
        recuadro1.setOpaque(true);
        recuadro1.setBounds(200,220,285,410);
        recuadro1.setBackground(Color.white);
        Border borde1= BorderFactory.createLineBorder(Fondo,14);
        recuadro1.setBorder(borde1);
        panel.add(recuadro1);

        //Recuadro2
        recuadro2.setOpaque(true);
        recuadro2.setBounds(580,220,285,410);
        recuadro2.setBackground(Color.white);
        Border borde2= BorderFactory.createLineBorder(Fondo,14);
        recuadro2.setBorder(borde2);
        panel.add(recuadro2);

        //Recuadro
        recuadro.setOpaque(true);
        recuadro.setBounds(35,140,1020,520);
        recuadro.setBackground(recuadrito);
        Border borde= BorderFactory.createLineBorder(Boton,4);
        recuadro.setBorder(borde);
        panel.add(recuadro);

        //Comprobación Turno1
  if((menu1.turno).equals("Manana")){
       turno1.setText("<html>Horario: <p>"+menu1.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu1.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu1.calorias);
}else if((menu1.turno).equals("Tarde")){
  turno2.setText("<html>Horario: <p>"+menu1.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu1.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu1.calorias);
}

//Verificar NO Disponible Turno1
  if((menu1.platillo).equals("No Disponible")&&(menu1.turno).equals("Manana")){
    turno1.setText("NO DISPONIBLE");
  }else if((menu1.platillo).equals("No Disponible")&&(menu1.turno).equals("Tarde")){
    turno2.setText("NO DISPONIBLE");
  }


//Comprobación Turno2
if((menu2.turno).equals("Manana")){
       turno1.setText("<html>Horario: <p>"+menu2.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu2.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu2.calorias);
}else if((menu2.turno).equals("Tarde")){
  turno2.setText("<html>Horario: <p>"+menu2.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu2.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu2.calorias);
}

//Verificar NO Disponible Turno2
  if((menu2.platillo).equals("No Disponible")&&(menu2.turno).equals("Manana")){
    turno1.setText("NO DISPONIBLE");
  }else if((menu2.platillo).equals("No Disponible")&&(menu2.turno).equals("Tarde")){
    turno2.setText("NO DISPONIBLE");
  }

        boton.addActionListener(botonAction);
        boton2.addActionListener(botonRecarga);
        boton3.addActionListener(botonPerfil);
    }

    public void ColocarBoton(){
        boton.setBounds(850,60,180,50);
        boton.setFont(new Font("Arial",Font.BOLD,20));
        boton.setForeground(Color.white);
        boton.setBackground(Boton);
        panel.add(boton);

        boton2.setBounds(850,153,180,50);
        boton2.setFont(new Font("Arial",Font.BOLD,20));
        boton2.setForeground(Color.white);
        boton2.setBackground(Boton);
        panel.add(boton2);

        boton3.setBounds(37,60,100,50);
        boton3.setFont(new Font("Arial",Font.BOLD,20));
        boton3.setForeground(Color.white);
        boton3.setBackground(Boton);
        panel.add(boton3);
    }
}