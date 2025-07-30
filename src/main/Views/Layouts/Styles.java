package main.Views.Layouts;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Styles {
    private Map<String, Color> palette;
    private static Font font = new Font( null , 1, 14 );
    private static Font fontTitle = new Font( null , 1, 18 );
    private static Border buttonBorder = BorderFactory.createEmptyBorder( 10, 5, 10, 5 );
    private static Border fieldBorder = BorderFactory.createEmptyBorder( 10, 5, 10, 5 );
    private static Border frameBorder = BorderFactory.createEmptyBorder( 25, 25, 25, 25 );

    private Styles(){
        this.initializeColors();
    }

    public static void stylizeButton( JButton button, int colorIndex, Size sizes ){
        button.setFont( font );
        button.setBorder( buttonBorder );
        button.setSize( sizes.getDimension() );
    }

    public static void stylizeField( JPasswordField textField, int colorIndex, Size sizes ){
        textField.setFont( font );
        textField.setBorder( fieldBorder );
        textField.setSize( sizes.getDimension() );
    }
    public static void stylizeField( JTextField textField, int colorIndex, Size sizes ){
        textField.setFont( font );
        textField.setBorder( fieldBorder );
        textField.setSize( sizes.getDimension() );
    }

    public static void stylizeFrame( JFrame frame, Panel mainPanel, int colorIndex, Size sizes ){
        frame.setFont( font );
        mainPanel.getPanel().setBorder( frameBorder );
        frame.setSize( sizes.getDimension() );
    }
    public static void stylizeAdminFrame( JFrame frame, Panel mainPanel, int colorIndex, Size sizes ){
        frame.setFont( font );
        mainPanel.getPanel().setBorder( frameBorder );
        frame.setSize( sizes.getDimension() );
    }

    public static void stylizeLabel( JLabel label, int colorIndex, Size sizes){
        label.setFont( font );
        label.setSize( sizes.getDimension() );
    }

    public static void stylizeTitle( JLabel label, int colorIndex, Size sizes){
        
    }
    
    private void initializeColors(){
        this.palette = new LinkedHashMap<>();

        // Agregamos los colores usando los códigos hexadecimales como clave
        palette.put("Color1", Color.decode("#772c4dff"));
        palette.put("Color2", Color.decode("#db9c36ff"));
        palette.put("Color3", Color.decode("#fdfdfdff"));
        palette.put("Color4", Color.decode("#1f2224"));
        palette.put("Color5", Color.decode("#2a3b72ff"));
    }
}
