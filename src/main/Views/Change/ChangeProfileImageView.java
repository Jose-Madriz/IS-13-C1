package main.Views.Change;

import main.Models.DatabaseManager;
import main.Views.MenuPrincipal.MenuPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Timer;
import java.util.TimerTask;

public class ChangeProfileImageView extends JFrame {

    private JPanel panel;
    private JTextArea disclaimerArea;
    private JButton selectOriginalImageButton;
    private JButton selectNewImageButton;
    private JButton confirmButton;
    private JLabel statusLabel;
    private JProgressBar progressBar;
    private File newImageFile;
    private File originalImageFile;
    private MenuPrincipal parentFrame;
    private int cedula;
    private boolean scanSuccessful;

    // Colores consistentes con MenuPrincipal
    private final Color Fondo = new Color(217, 217, 217);
    private final Color Boton = new Color(28, 138, 178);
    private final Color titulos = new Color(167, 167, 167);

    public ChangeProfileImageView(MenuPrincipal parentFrame, int cedula) {
        this.parentFrame = parentFrame;
        this.cedula = cedula;
        this.scanSuccessful = false;
        setTitle("Cambiar Imagen de Perfil");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        showInitialDisclaimer();
    }

    private void showInitialDisclaimer() {
        JOptionPane.showMessageDialog(
            this,
            "Vamos a realizar un escaneo facial para verificar su identidad.\n" +
            "Por favor, seleccione la imagen original de su rostro asociada a su cuenta.\n" +
            "Luego podrá elegir una nueva imagen de perfil.",
            "Escaneo Facial",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void initComponents() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Fondo);
        getContentPane().add(panel);

        // Título
        JLabel titleLabel = new JLabel("Cambiar Imagen de Perfil", JLabel.CENTER);
        titleLabel.setBounds(50, 20, 400, 40);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(titulos);
        titleLabel.setOpaque(true);
        panel.add(titleLabel);

        // Disclaimer
        disclaimerArea = new JTextArea(
            "Pasos para cambiar su imagen de perfil:\n" +
            "1. Seleccione su imagen original para el escaneo facial.\n" +
            "2. Espere a que el sistema verifique su identidad.\n" +
            "3. Si el escaneo es exitoso, seleccione una nueva imagen (JPG o PNG).\n" +
            "4. Haga clic en 'Confirmar' para completar el cambio."
        );
        disclaimerArea.setBounds(60, 70, 400, 115);
        disclaimerArea.setFont(new Font("Arial", Font.PLAIN, 14));
        disclaimerArea.setWrapStyleWord(true);
        disclaimerArea.setLineWrap(true);
        disclaimerArea.setEditable(false);
        disclaimerArea.setBackground(Fondo);
        Border border = BorderFactory.createLineBorder(Boton, 2);
        disclaimerArea.setBorder(border);
        panel.add(disclaimerArea);

        // Botón para seleccionar imagen original
        selectOriginalImageButton = new JButton("Escanear Cara");
        selectOriginalImageButton.setBounds(150, 200, 200, 40);
        selectOriginalImageButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectOriginalImageButton.setForeground(Color.WHITE);
        selectOriginalImageButton.setBackground(Boton);
        selectOriginalImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png"));
                int result = fileChooser.showOpenDialog(ChangeProfileImageView.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    originalImageFile = fileChooser.getSelectedFile();
                    statusLabel.setText("Escanear Cara: " + originalImageFile.getName());
                    simulateFacialScan();
                }
            }
        });
        panel.add(selectOriginalImageButton);

        // Barra de progreso para simular el escaneo
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(50, 250, 400, 30);
        progressBar.setStringPainted(true);
        progressBar.setString("Esperando selección...");
        progressBar.setForeground(Boton);
        panel.add(progressBar);

        // Botón para seleccionar nueva imagen (inicialmente deshabilitado)
        selectNewImageButton = new JButton("Nueva Imagen");
        selectNewImageButton.setBounds(50, 290, 200, 40);
        selectNewImageButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectNewImageButton.setForeground(Color.WHITE);
        selectNewImageButton.setBackground(Boton);
        selectNewImageButton.setEnabled(false);
        selectNewImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "png"));
                int result = fileChooser.showOpenDialog(ChangeProfileImageView.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    newImageFile = fileChooser.getSelectedFile();
                    statusLabel.setText("Nueva Imagen: " + newImageFile.getName());
                }
            }
        });
        panel.add(selectNewImageButton);

        // Botón para confirmar (inicialmente deshabilitado)
        confirmButton = new JButton("Confirmar");
        confirmButton.setBounds(260, 290, 200, 40);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBackground(Boton);
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newImageFile == null) {
                    statusLabel.setText("Por favor, seleccione una nueva imagen.");
                    JOptionPane.showMessageDialog(ChangeProfileImageView.this,
                            "Seleccione una nueva imagen antes de confirmar.",
                            "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DatabaseManager dbManager = DatabaseManager.getInstance();
                if (dbManager.updateUserImage(cedula, newImageFile)) {
                    statusLabel.setText("Imagen de perfil actualizada exitosamente.");
                    JOptionPane.showMessageDialog(ChangeProfileImageView.this,
                            "Imagen de perfil actualizada exitosamente.",
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    statusLabel.setText("Error al actualizar la imagen.");
                    JOptionPane.showMessageDialog(ChangeProfileImageView.this,
                            "Error al actualizar la imagen de perfil.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(confirmButton);

        // Etiqueta de estado
        statusLabel = new JLabel("Seleccione la imagen original para el escaneo.", JLabel.CENTER);
        statusLabel.setBounds(50, 340, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setForeground(Color.BLACK);
        panel.add(statusLabel);
    }

    private void simulateFacialScan() {
        progressBar.setString("Escaneando rostro...");
        progressBar.setValue(0);
        selectOriginalImageButton.setEnabled(false);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int progress = 0;

            @Override
            public void run() {
                progress += 10;
                progressBar.setValue(progress);
                if (progress >= 100) {
                    timer.cancel();
                    DatabaseManager dbManager = DatabaseManager.getInstance();
                    if (dbManager.verifyImageHash(cedula, originalImageFile)) {
                        scanSuccessful = true;
                        progressBar.setString("Escaneo exitoso");
                        statusLabel.setText("Escaneo facial exitoso. Seleccione la nueva imagen.");
                        selectNewImageButton.setEnabled(true);
                        confirmButton.setEnabled(true);
                        JOptionPane.showMessageDialog(ChangeProfileImageView.this,
                                "Escaneo facial completado con éxito. Puede seleccionar una nueva imagen.",
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        scanSuccessful = false;
                        progressBar.setString("Escaneo fallido");
                        statusLabel.setText("Error: Imagen no coincide o no está registrada. Intente de nuevo.");
                        JOptionPane.showMessageDialog(ChangeProfileImageView.this,
                                "La imagen original no coincide con el registro o no hay una imagen registrada. Intente de nuevo.",
                                "Error de Verificación", JOptionPane.ERROR_MESSAGE);
                        selectOriginalImageButton.setEnabled(true);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(task, 200, 200);
    }
}