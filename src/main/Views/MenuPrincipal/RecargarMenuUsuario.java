package main.Views.MenuPrincipal;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import main.Controllers.RecargarMenuController;
import main.Models.DatabaseManager;

public class RecargarMenuUsuario extends JFrame {
    // UI Colors
    private static final Color BOTON = new Color(28, 138, 178);
    private static final Color FONDO = new Color(217, 217, 217);
    private static final Color TITULOS = new Color(167, 167, 167);
    private static final Color RECUADRITO = new Color(244, 244, 244);

    // UI Components
    private final JPanel panel = new JPanel();
    private final JLabel entrada = new JLabel("Seleccione su monto", SwingConstants.CENTER);
    private final JButton botonRecargar = new JButton("Recargar");
    private final JLabel recuadroRecarga = new JLabel();
    private final JComboBox<String> listaPrecios = new JComboBox<>(new String[]{"100bs", "200bs", "300bs", "400bs"});
    private final RecargarMenuController controller;

    // Constructor
    public RecargarMenuUsuario() {
        setSize(350, 350);
        setTitle("Recargar saldo");
        setLocationRelativeTo(null);
        setResizable(false);
        this.controller = new RecargarMenuController(this, DatabaseManager.getInstance());
    }

    // Initialize UI components
    public void initComponents(JLabel saldoLabel, int cedula) {
        panel.setLayout(null);
        panel.setBackground(Color.white);
        this.getContentPane().add(panel);

        setupTitle();
        setupPriceOptions();
        setupRecargarButton(saldoLabel, cedula);
        setupRecuadro();
    }

    // Setup title label
    private void setupTitle() {
        entrada.setBounds(55, 15, 220, 70);
        entrada.setOpaque(true);
        entrada.setBackground(FONDO);
        entrada.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(entrada);
    }

    // Setup price selection dropdown
    private void setupPriceOptions() {
        listaPrecios.setBounds(115, 125, 100, 60);
        listaPrecios.setBackground(Color.white);
        listaPrecios.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(listaPrecios);
    }

    // Setup recargar button with action listener
    private void setupRecargarButton(JLabel saldoLabel, int cedula) {
        botonRecargar.setBounds(115, 220, 100, 50);
        botonRecargar.setFont(new Font("Arial", Font.BOLD, 15));
        botonRecargar.setForeground(Color.white);
        botonRecargar.setBackground(BOTON);
        panel.add(botonRecargar);

        botonRecargar.addActionListener(e -> {
            String selectedMonto = (String) listaPrecios.getSelectedItem();
            controller.handleRecargarSaldo(cedula, selectedMonto, saldoLabel);
            dispose();
        });
    }

    // Setup recuadro (bordered panel)
    private void setupRecuadro() {
        recuadroRecarga.setOpaque(true);
        recuadroRecarga.setBounds(35, 100, 260, 200);
        recuadroRecarga.setBackground(RECUADRITO);
        recuadroRecarga.setBorder(BorderFactory.createLineBorder(BOTON, 4));
        panel.add(recuadroRecarga);
    }
}