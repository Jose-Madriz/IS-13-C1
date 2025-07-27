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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

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


    //XD
  String montoTotal;


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
    JLabel auxiliarSaldo = new JLabel();
    // Colores a emplear
    
   double saldoRecarga;
   int cedulaArchivo;
   
     public RecargarMenuUsuario(){
    setSize(350,350);
    setTitle("Recargar saldo");

    setLocationRelativeTo(null);
    setResizable(false);
  
    }
    public void IniciarComponentes2(JLabel _saldo,double ___s,int _cedula){

          cedulaArchivo = _cedula;
          saldoRecarga = obtenerSaldoActualDesdeJson(_cedula); 
          auxiliarSaldo=_saldo;  
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
       
    }
   
    public JComboBox<String> listaPrecios = new JComboBox<>();

    public void opcionesPrecio(){


        String [] precios = { "100bs" , "200bs","300bs","400bs" };
        listaPrecios=new JComboBox<>(precios);
        listaPrecios.setBounds(115, 125,100,60);
        listaPrecios.setBackground(Color.white);
        listaPrecios.setFont(new Font("Arial",Font.BOLD,15));
        panel2.add(listaPrecios);
        
       
    }

    public void recargarSaldo(){
    boton4.setBounds(115,220,100,50);
    boton4.setFont(new Font("Arial",Font.BOLD,15));
    boton4.setForeground(Color.white);
     boton4.setBackground(Boton);
     panel2.add(boton4);
boton4.addActionListener(saldoVolver);
    }

    public void recuadroSaldo(){
        recuadroRecarga.setOpaque(true);
    recuadroRecarga.setBounds(35,100,260,200);
    recuadroRecarga.setBackground(recuadrito);
  Border borde= BorderFactory.createLineBorder(Boton,4);
     recuadroRecarga.setBorder(borde);
    panel2.add(recuadroRecarga);

    }

public void actualizarSaldoEnJson(int cedula, double montoRecarga) {
    String ruta = "DataBase.json";
    try {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        StringBuilder sb = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            sb.append(linea).append("\n");
        }
        br.close();
        String contenido = sb.toString();

        String patronCedula = "\"cedula\": " + cedula + ",";
        int index = contenido.indexOf(patronCedula);
        while (index != -1) {
            int objStart = contenido.lastIndexOf("{", index);
            int objEnd = contenido.indexOf("}", index);
            if (objStart != -1 && objEnd != -1) {
                String objetoUsuario = contenido.substring(objStart, objEnd + 1);
                if (objetoUsuario.contains(patronCedula)) {
                    int saldoIndex = objetoUsuario.indexOf("\"saldo\":");
                    if (saldoIndex != -1) {
                        int inicioValor = saldoIndex + "\"saldo\":".length();
                        int finValor = objetoUsuario.indexOf(",", inicioValor);
                        if (finValor == -1) {
                            finValor = objetoUsuario.indexOf("}", inicioValor);
                        }

                        String saldoActualStr = objetoUsuario.substring(inicioValor, finValor).trim().replace(",", ".");
                        double saldoActual = Double.parseDouble(saldoActualStr);
                        double nuevoSaldo = saldoActual + montoRecarga;

                       
                        String nuevoSaldoFormateado = String.format(Locale.US, "%.2f", nuevoSaldo);
                        String nuevoObjetoUsuario = objetoUsuario.substring(0, inicioValor) + " " + nuevoSaldoFormateado + objetoUsuario.substring(finValor);

                        contenido = contenido.substring(0, objStart) + nuevoObjetoUsuario + contenido.substring(objEnd + 1);
                        break;
                    }
                }
            }
            index = contenido.indexOf(patronCedula, index + patronCedula.length());
        }

        FileWriter fw = new FileWriter(ruta);
        fw.write(contenido);
        fw.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public double obtenerSaldoActualDesdeJson(int cedula) {
    String ruta = "DataBase.json";
    try {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        StringBuilder contenido = new StringBuilder();
        String linea;
        while ((linea = br.readLine()) != null) {
            contenido.append(linea);
        }
        br.close();

        String patronCedula = "\"cedula\": " + cedula + ",";
        int index = contenido.indexOf(patronCedula);
        if (index != -1) {
            int saldoIndex = contenido.indexOf("\"saldo\":", index);
            if (saldoIndex != -1) {
                int inicioValor = saldoIndex + "\"saldo\":".length();
                int finValor = contenido.indexOf(",", inicioValor);
                if (finValor == -1) {
                    finValor = contenido.indexOf("}", inicioValor);
                }

                String saldoStr = contenido.substring(inicioValor, finValor).trim().replace(",", ".");
                return Double.parseDouble(saldoStr);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0.0;
}
ActionListener saldoVolver = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String montoTotal = (String) listaPrecios.getSelectedItem();
        int monto = 0;
        if(montoTotal.equals("100bs")){
            monto += 100;
        }else if(montoTotal.equals("200bs")){
            monto += 200;
        }else if(montoTotal.equals("300bs")){
            monto += 300;
        }else if(montoTotal.equals("400bs")){
            monto += 400;
        }

        actualizarSaldoEnJson(cedulaArchivo, monto);

        double nuevoSaldo = obtenerSaldoActualDesdeJson(cedulaArchivo);
        auxiliarSaldo.setText("Saldo: "+String.format(Locale.US, "%.2f", nuevoSaldo));

        dispose();
        System.out.println(nuevoSaldo);
    }
};

 
}

