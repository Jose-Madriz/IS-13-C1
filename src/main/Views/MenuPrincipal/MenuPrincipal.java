package main.Views.MenuPrincipal;


import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

 //Menus
 public Menu menu1 = new Menu();
 public Menu menu2 = new Menu();
 public Menu menu3 = new Menu();

 //Colores a emplear
 Color Fondo = new Color(217,217,217);
Color titulos = new Color(167,167,167);
Color Boton = new Color(28,138,178);
Color recuadrito = new Color(244,244,244);

  //Titulos y componentes
    JLabel titulo1 = new JLabel("Bienvenido, Jose Luis",SwingConstants.CENTER);
    JLabel recuadro = new JLabel();
    JLabel saldo = new JLabel("Saldo: 0.00 Bs.",SwingConstants.CENTER);
    JLabel manana = new JLabel("MAÑANA",SwingConstants.CENTER);
    JLabel tarde = new JLabel("TARDE",SwingConstants.CENTER);
    JLabel noche = new JLabel("NOCHE",SwingConstants.CENTER);
    JLabel turno1=new JLabel("",SwingConstants.CENTER);
    JLabel turno2=new JLabel("",SwingConstants.CENTER);
    JLabel turno3=new JLabel("",SwingConstants.CENTER);
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

    CargarDatos(menu1, menu2, menu3);
    ColocarBoton();
    ColocarTextos();
    
    
 
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

    
     //Turno1
    
    turno1.setBounds(95,280,220,320);
    turno1.setFont(new Font("Arial",Font.PLAIN,22));
     Border borde4= BorderFactory.createLineBorder(titulos,5);
    turno1.setBorder(borde4);
    panel.add(turno1);

    //Turno2
    turno2.setBounds(425,280,220,320);
    turno2.setFont(new Font("Arial",Font.PLAIN,22));
    Border borde5= BorderFactory.createLineBorder(titulos,5);
    turno2.setBorder(borde5);
    panel.add(turno2);

    //Turno3
    turno3.setBounds(755,280,220,320);
    turno3.setFont(new Font("Arial",Font.PLAIN,22));
    Border borde6= BorderFactory.createLineBorder(titulos,5);
    turno3.setBorder(borde6);
    panel.add(turno3);
   
    //Mañana
    manana.setBounds(108,230,180,50);
    manana.setForeground(Boton);
    manana.setFont(new Font("Arial",Font.BOLD,20));
    panel.add(manana);
    
   

    //Tarde
    tarde.setBounds(438,230,180,50);
    tarde.setForeground(Boton);
    tarde.setFont(new Font("Arial",Font.BOLD,20));
    panel.add(tarde);

    //Noche
    noche.setBounds(775,230,180,50);
    noche.setForeground(Boton);
    noche.setFont(new Font("Arial",Font.BOLD,20));
    panel.add( noche);

    //Recuadro1
    recuadro1.setOpaque(true);
   recuadro1.setBounds(65,220,280,410);
   recuadro1.setBackground(Color.white);
   Border borde1= BorderFactory.createLineBorder(Fondo,14);
    recuadro1.setBorder(borde1);
  panel.add(recuadro1);

    //Recuadro2
  recuadro2.setOpaque(true);
   recuadro2.setBounds(390,220,285,410);
   recuadro2.setBackground(Color.white);
   Border borde2= BorderFactory.createLineBorder(Fondo,14);
    recuadro2.setBorder(borde2);
    panel.add(recuadro2);

    
    //Recuadro3
  recuadro3.setOpaque(true);
   recuadro3.setBounds(725,220,280,410);
   recuadro3.setBackground(Color.white);
   Border borde3= BorderFactory.createLineBorder(Fondo,14);
    recuadro3.setBorder(borde3);
    panel.add(recuadro3);



    //Recuadro
    recuadro.setOpaque(true);
   recuadro.setBounds(35,140,1000,520);
   recuadro.setBackground(recuadrito);
  Border borde= BorderFactory.createLineBorder(Boton,4);
    recuadro.setBorder(borde);
    panel.add(recuadro);

    //Comprobación Turno1
  if((menu1.turno).equals("Manana")){
       turno1.setText("<html>Horario: <p>"+menu1.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu1.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu1.calorias);
}else if((menu1.turno).equals("Tarde")){
  turno2.setText("<html>Horario: <p>"+menu1.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu1.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu1.calorias);
}else if((menu1.turno).equals("Noche")){
   turno3.setText("<html>Horario: <p>"+menu1.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu1.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu1.calorias);
}

//Verificar NO Disponible Turno1
  if((menu1.platillo).equals("No Disponible")&&(menu1.turno).equals("Manana")){
    turno1.setText("NO DISPONIBLE");
  }else if((menu1.platillo).equals("No Disponible")&&(menu1.turno).equals("Tarde")){
    turno2.setText("NO DISPONIBLE");
  }else if((menu1.platillo).equals("No Disponible")&&(menu1.turno).equals("Noche")){
    turno3.setText("NO DISPONIBLE");
  }


//Comprobación Turno2
if((menu2.turno).equals("Manana")){
       turno1.setText("<html>Horario: <p>"+menu2.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu2.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu2.calorias);
}else if((menu2.turno).equals("Tarde")){
  turno2.setText("<html>Horario: <p>"+menu2.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu2.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu2.calorias);
}else if((menu2.turno).equals("Noche")){
   turno3.setText("<html>Horario: <p>"+menu2.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu2.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu2.calorias);
}

//Verificar NO Disponible Turno2
  if((menu2.platillo).equals("No Disponible")&&(menu2.turno).equals("Manana")){
    turno1.setText("NO DISPONIBLE");
  }else if((menu2.platillo).equals("No Disponible")&&(menu2.turno).equals("Tarde")){
    turno2.setText("NO DISPONIBLE");
  }else if((menu2.platillo).equals("No Disponible")&&(menu2.turno).equals("Noche")){
    turno3.setText("NO DISPONIBLE");
  }



//Comprobación Turno3
if((menu3.turno).equals("Manana")){
       turno1.setText("<html>Horario: <p>"+menu3.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu3.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu3.calorias);
}else if((menu3.turno).equals("Tarde")){
  turno2.setText("<html>Horario: <p>"+menu3.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu3.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu3.calorias);
}else if((menu3.turno).equals("Noche")){
   turno3.setText("<html>Horario: <p>"+menu3.horario+"<html> <p><p>"+"<html> Platillo:  <p>"+menu3.platillo+"<html> <p><p>"+"<html> Calorías: <p>"+menu3.calorias);
}


//Verificar NO Disponible Turno3
  if((menu3.platillo).equals("No Disponible")&&(menu3.turno).equals("Manana")){
    turno1.setText("NO DISPONIBLE");
  }else if((menu3.platillo).equals("No Disponible")&&(menu3.turno).equals("Tarde")){
    turno2.setText("NO DISPONIBLE");
  }else if((menu3.platillo).equals("No Disponible")&&(menu3.turno).equals("Noche")){
    turno3.setText("NO DISPONIBLE");
  }


    }
   
    public void ColocarBoton(){

  boton.setBounds(850,60,180,50);
   boton.setFont(new Font("Arial",Font.BOLD,20));
   boton.setForeground(Color.white);
   boton.setBackground(Boton);
    panel.add(boton);


    }

    public void CargarDatos(Menu _menu1,Menu _menu2, Menu _menu3){

      //Contador
      int i=0;
    try{
       
        BufferedReader lector=new BufferedReader(new FileReader("DataBasemenu.txt"));
        String linea="";
        while ((linea=lector.readLine())!=null) {
            String [] bloques=linea.split(",");
            if( bloques.length==4){
            if(i==0){
            String turno= bloques[0];
            String platillo=bloques[1];
            String horario= bloques[2];
            double calorias=Double.parseDouble(bloques[3]);
            _menu1.turno=turno;
           _menu1.platillo=platillo;
            _menu1.horario=horario;
           _menu1.calorias=calorias;
           i++;
            }else if(i==1){
                String turno= bloques[0];
            String platillo=bloques[1];
            String horario= bloques[2];
            double calorias=Double.parseDouble(bloques[3]);
            _menu2.turno=turno;
           _menu2.platillo=platillo;
            _menu2.horario=horario;
           _menu2.calorias=calorias; 
             i++; 
        
            }else if(i==2){
            String turno= bloques[0];
            String platillo=bloques[1];
            String horario= bloques[2];
            double calorias=Double.parseDouble(bloques[3]);
            _menu3.turno=turno;
           _menu3.platillo=platillo;
            _menu3.horario=horario;
           _menu3.calorias=calorias;  
                }
           
             }
        
        }
        lector.close();
    }catch(IOException e){
        System.out.println("Error al cargar el archivo. "+e.getMessage());
    }



    }

    

}
