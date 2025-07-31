package main.Views.MenuUser;

import main.Views.Login.*;
import main.Models.DatabaseManager;
import main.Models.ModeloMenuPrincipal.Menu;
import main.Controllers.MenuUsuarioController;
import main.Controllers.Login.*;
import main.Views.Change.ChangePassView;
import main.Views.Change.ChangeProfileImageView; // Nueva importación

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuPrincipal extends JFrame {

    // Inicialización del panel
    JPanel panel = new JPanel();
    double saldo_auxiliar;
    int cedulaSaldo;
    JLabel profileImageLabel; // JLabel para la imagen de perfil

    // Colores a emplear
    Color Fondo = new Color(217, 217, 217);
    Color titulos = new Color(167, 167, 167);
    Color Boton = new Color(28, 138, 178);
    Color recuadrito = new Color(244, 244, 244);

    // Títulos y componentes
    JLabel titulo1 = new JLabel("", SwingConstants.CENTER);
    JLabel recuadro = new JLabel();
    JLabel saldo = new JLabel("", SwingConstants.CENTER);
    JLabel manana = new JLabel("MAÑANA", SwingConstants.CENTER);
    JLabel tarde = new JLabel("TARDE", SwingConstants.CENTER);
    JLabel turno1 = new JLabel("", SwingConstants.CENTER);
    JLabel turno2 = new JLabel("", SwingConstants.CENTER);
    JLabel recuadro1 = new JLabel();
    JLabel recuadro2 = new JLabel();

    // Inicialización de imagen
    ImageIcon imagen = new ImageIcon("Logo.jpg");
    JLabel icono = new JLabel();

    // Botones
    JButton boton = new JButton("Cerrar Sesión");
    JButton boton2 = new JButton("Recargar saldo");

    // Controladores
    ActionListener botonAction = new ActionListener() {
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

    ActionListener botonRecarga = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            RecargarMenuUsuario recargaMenu = new RecargarMenuUsuario();
            recargaMenu.setVisible(true);
            recargaMenu.initComponents(saldo, cedulaSaldo);
        }
    };

    ActionListener botonPerfil = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            ChangePassView changePasswordView = new ChangePassView(MenuPrincipal.this);
            changePasswordView.showChangePasswordView();
        }
    };

    Menu menuDataSource = new Menu();
    Menu menu1 = new Menu();
    Menu menu2 = new Menu();

    // Constructor de la ventana
    public MenuPrincipal() {
        setSize(1100, 750);
        setTitle("Menú Principal");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        profileImageLabel = new JLabel();
        initProfileImageMenu(); // Inicializar el menú emergente
    }

    private void initProfileImageMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem changePasswordItem = new JMenuItem("Cambiar Contraseña");
        JMenuItem changeImageItem = new JMenuItem("Cambiar Imagen de Perfil");

        changePasswordItem.addActionListener(e -> {
            setVisible(false);
            ChangePassView changePasswordView = new ChangePassView(MenuPrincipal.this);
            changePasswordView.showChangePasswordView();
        });

        changeImageItem.addActionListener(e -> {
            ChangeProfileImageView changeImageView = new ChangeProfileImageView(MenuPrincipal.this, cedulaSaldo);
            changeImageView.setVisible(true);
            changeImageView.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    loadProfileImage(cedulaSaldo); // Recargar la imagen al cerrar
                }
            });
        });

        popupMenu.add(changePasswordItem);
        popupMenu.add(changeImageItem);

        profileImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popupMenu.show(profileImageLabel, e.getX(), e.getY());
                }
            }
        });
    }

    public void IniciarComponentes(String _n, String _p, double _s, MenuUsuarioController _controller, int _c) {
        cedulaSaldo = _c;
        saldo_auxiliar = _s;

        panel.setLayout(null);
        this.getContentPane().add(panel);
        panel.setBackground(Fondo);
        ColocarBoton();
        ColocarTextos(_n, _p, _s);
        loadProfileImage(_c);
    }

    public void ColocarTextos(String __n, String __p, double __s) {
        // Título 1
        titulo1.setText("Bienvenido, " + __n + " " + __p);
        titulo1.setOpaque(true);
        titulo1.setBounds(180, 40, 620, 70);
        titulo1.setForeground(Color.BLACK);
        titulo1.setBackground(titulos);
        titulo1.setFont(new Font("Arial", Font.PLAIN, 35));
        panel.add(titulo1);

        // Saldo
        saldo.setText("Saldo: " + __s);
        saldo.setOpaque(true);
        saldo.setBounds(55, 155, 180, 50);
        saldo.setForeground(Color.BLACK);
        saldo.setBackground(titulos);
        saldo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(saldo);

        // Turno1
        turno1.setBounds(233, 280, 220, 320);
        turno1.setFont(new Font("Arial", Font.PLAIN, 18));
        Border borde4 = BorderFactory.createLineBorder(titulos, 5);
        turno1.setBorder(borde4);
        panel.add(turno1);

        // Turno2
        turno2.setBounds(610, 280, 220, 320);
        turno2.setFont(new Font("Arial", Font.PLAIN, 18));
        Border borde5 = BorderFactory.createLineBorder(titulos, 5);
        turno2.setBorder(borde5);
        panel.add(turno2);

        // Mañana
        manana.setBounds(249, 230, 180, 50);
        manana.setForeground(Boton);
        manana.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(manana);

        // Tarde
        tarde.setBounds(630, 230, 180, 50);
        tarde.setForeground(Boton);
        tarde.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(tarde);

        // Recuadro1
        recuadro1.setOpaque(true);
        recuadro1.setBounds(200, 220, 285, 410);
        recuadro1.setBackground(Color.white);
        Border borde1 = BorderFactory.createLineBorder(Fondo, 14);
        recuadro1.setBorder(borde1);
        panel.add(recuadro1);

        // Recuadro2
        recuadro2.setOpaque(true);
        recuadro2.setBounds(580, 220, 285, 410);
        recuadro2.setBackground(Color.white);
        Border borde2 = BorderFactory.createLineBorder(Fondo, 14);
        recuadro2.setBorder(borde2);
        panel.add(recuadro2);

        // Recuadro
        recuadro.setOpaque(true);
        recuadro.setBounds(35, 140, 1020, 520);
        recuadro.setBackground(recuadrito);
        Border borde = BorderFactory.createLineBorder(Boton, 4);
        recuadro.setBorder(borde);
        panel.add(recuadro);

        // --- Lógica de visualización de menús refactorizada ---

        // 1. Identificar qué menú es de la mañana y cuál de la tarde
        Menu menuManana = null;
        Menu menuTarde = null;

        if (menuDataSource.menu1 != null) {
            if ("Mañana".equalsIgnoreCase(menuDataSource.menu1.turno)) {
                menuManana = menuDataSource.menu1;
            } else if ("Tarde".equalsIgnoreCase(menuDataSource.menu1.turno)) {
                menuTarde = menuDataSource.menu1;
            }
        }

        if (menuDataSource.menu2 != null) {
            if ("Manana".equalsIgnoreCase(menuDataSource.menu2.turno)) {
                menuManana = menuDataSource.menu2;
            } else if ("Tarde".equalsIgnoreCase(menuDataSource.menu2.turno)) {
                menuTarde = menuDataSource.menu2;
            }
        }

        // 2. Poblar las etiquetas con la información correcta y el formato adecuado
        String menuMananaTexto = "NO DISPONIBLE";
        if (menuManana != null && !"No Disponible".equalsIgnoreCase(menuManana.platillo)) {
            menuMananaTexto = String.format(
                "<html><div style='text-align: center; padding: 5px;'>" +
                "<b>Horario:</b><br>%s<br><br>" +
                "<b>Platillo:</b><br>%s<br><br>" +
                "<b>Calorías:</b><br>%.1f<br><br>" +
                "<b>Precio:</b><br>%.2f Bs." +
                "</div></html>",
                menuManana.horario, menuManana.platillo, menuManana.calorias, menuManana.precio);
        // Imagen de perfil
        profileImageLabel.setBounds(37, 30, 100, 100); // Misma posición que el botón "Perfil"
        Border bordeImage = BorderFactory.createLineBorder(Boton, 2);
        profileImageLabel.setBorder(bordeImage);
        profileImageLabel.setOpaque(true);
        profileImageLabel.setBackground(Color.white);
        panel.add(profileImageLabel);

        // Comprobación Turno1
        if ((menu1.turno).equals("Manana")) {
            turno1.setText("<html>Horario: " + menu1.horario + "<br>" + 
                           "Platillo: " + menu1.platillo + "<br>" + 
                           "Calorías: " + menu1.calorias + "</html>");
        } else if ((menu1.turno).equals("Tarde")) {
            turno2.setText("<html>Horario: " + menu1.horario + "<br>" + 
                           "Platillo: " + menu1.platillo + "<br>" + 
                           "Calorías: " + menu1.calorias + "</html>");
        }

        // Verificar NO Disponible Turno1
        if ((menu1.platillo).equals("No Disponible") && (menu1.turno).equals("Manana")) {
            turno1.setText("NO DISPONIBLE");
        } else if ((menu1.platillo).equals("No Disponible") && (menu1.turno).equals("Tarde")) {
            turno2.setText("NO DISPONIBLE");
        }

        // Comprobación Turno2
        if ((menu2.turno).equals("Manana")) {
            turno1.setText("<html>Horario: " + menu2.horario + "<br>" + 
                           "Platillo: " + menu2.platillo + "<br>" + 
                           "Calorías: " + menu2.calorias + "</html>");
        } else if ((menu2.turno).equals("Tarde")) {
            turno2.setText("<html>Horario: " + menu2.horario + "<br>" + 
                           "Platillo: " + menu2.platillo + "<br>" + 
                           "Calorías: " + menu2.calorias + "</html>");
        }
        turno1.setText(menuMananaTexto);

        String menuTardeTexto = "NO DISPONIBLE";
        if (menuTarde != null && !"No Disponible".equalsIgnoreCase(menuTarde.platillo)) {
            menuTardeTexto = String.format(
                "<html><div style='text-align: center; padding: 5px;'>" +
                "<b>Horario:</b><br>%s<br><br>" +
                "<b>Platillo:</b><br>%s<br><br>" +
                "<b>Calorías:</b><br>%.1f<br><br>" +
                "<b>Precio:</b><br>%.2f Bs." +
                "</div></html>",
                menuTarde.horario, menuTarde.platillo, menuTarde.calorias, menuTarde.precio);
        // Verificar NO Disponible Turno2
        if ((menu2.platillo).equals("No Disponible") && (menu2.turno).equals("Manana")) {
            turno1.setText("NO DISPONIBLE");
        } else if ((menu2.platillo).equals("No Disponible") && (menu2.turno).equals("Tarde")) {
            turno2.setText("NO DISPONIBLE");
        }
        turno2.setText(menuTardeTexto);

        boton.addActionListener(botonAction);
        boton2.addActionListener(botonRecarga);
    }

    public void ColocarBoton() {
        boton.setBounds(850, 60, 180, 50);
        boton.setFont(new Font("Arial", Font.BOLD, 20));
        boton.setForeground(Color.white);
        boton.setBackground(Boton);
        panel.add(boton);

        boton2.setBounds(850, 153, 180, 50);
        boton2.setFont(new Font("Arial", Font.BOLD, 20));
        boton2.setForeground(Color.white);
        boton2.setBackground(Boton);
        panel.add(boton2);
    }

    private void loadProfileImage(int cedula) {
        File imageFile = new File("DataBaseImg/" + cedula + ".jpg");
        if (!imageFile.exists()) {
            imageFile = new File("DataBaseImg/" + cedula + ".png");
        }
        if (imageFile.exists()) {
            ImageIcon profileImage = new ImageIcon(imageFile.getPath());
            Image scaledImage = profileImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            profileImageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            profileImageLabel.setText("Sin imagen");
        }
    }
}